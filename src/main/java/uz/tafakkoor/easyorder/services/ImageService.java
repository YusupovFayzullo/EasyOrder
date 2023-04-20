package uz.tafakkoor.easyorder.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.utils.BaseUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class ImageService {


    private String BUCKET_NAME = "easyorder";

    private final AmazonS3 amazonS3;
    private final AmazonS3Client s3Client;
    private final BaseUtils baseUtils;


    public List<String> saveImagesToServer(Collection<MultipartFile> files) {

        ArrayList<String> generateFileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            generateFileNames.add(baseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())));
        }
        CompletableFuture.runAsync(() -> {
            try {
                saveFilesToAWS(files, generateFileNames);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return generateFileNames;
    }

    protected void saveFilesToAWS(Collection<MultipartFile> files, List<String> generateNames) throws IOException {
        int i = 0;
        for (MultipartFile file : files) {
            saveFileToAWS(file, generateNames.get(i));
            i++;
        }
    }

    public String saveImageToServer(MultipartFile file) {
        String generateUniqueName = baseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename()));
        CompletableFuture.runAsync(() -> {
            try {
                saveFileToAWS(file, generateUniqueName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return generateUniqueName;
    }


    protected void saveFileToAWS(MultipartFile file, String generateUniqueName) throws IOException {
        byte[] bytes = file.getBytes();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(bytes.length);
        amazonS3.putObject(BUCKET_NAME, generateUniqueName, new ByteArrayInputStream(bytes), metadata);
    }

    public byte[] getImageFromAWS(String fileName) {
        try {
            S3Object s3Object = s3Client.getObject(BUCKET_NAME, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            byte[] pngBytes = IOUtils.toByteArray(inputStream);
            inputStream.close();
            return pngBytes;
        } catch (Exception e) {
            throw new RuntimeException("Error while downloading file " + e.getMessage());
        }
    }
}
