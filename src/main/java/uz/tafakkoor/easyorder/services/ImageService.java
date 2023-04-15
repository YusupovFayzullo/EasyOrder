package uz.tafakkoor.easyorder.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ImageService {


    private String BUCKET_NAME = "easyorder";
    private String SECRET_KEY = "RnvlLSy+lDRyFT9DlFfQuC9d1c5PRulCw9+F7woU";
    private String ACCESS_KEY = "AKIAV2VDW3ICSX4DGHSP";

    private final AmazonS3 amazonS3;
    private final AmazonS3Client s3Client;

    public Collection<String> saveImagesToAWS(Collection<MultipartFile> files) {
        ArrayList<String> imageURLs = new ArrayList<>();
        for (MultipartFile file : files) {
            imageURLs.add(saveImageToAWS(file));
        }
        return imageURLs;
    }

    public String saveImageToAWS(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(bytes.length);
            amazonS3.putObject(BUCKET_NAME, fileName, new ByteArrayInputStream(bytes), metadata);
        } catch (IOException e) {
            throw new RuntimeException("Error while uploading file" + e.getMessage());
        }
        return fileName;
    }

    public MultipartFile getImageFromAWS(String fileName) {
        try {
            S3Object s3Object = s3Client.getObject(BUCKET_NAME, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(inputStream));
            return (MultipartFile) resource;
        } catch (IOException e) {
            throw new RuntimeException("Error while downloading file" + e.getMessage());
        }
    }

/*    public Image generateImage(MultipartFile file) {
        return Image.builder()
                .type(file.getContentType())
                .size(file.getSize())
                .url("google/cloud")
                .generatedName(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
                .originalName(file.getOriginalFilename())
                .build();
    }*/
}
