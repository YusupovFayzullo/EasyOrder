package uz.tafakkoor.easyorder.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.Document;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.repositories.user.UserRepository;
import uz.tafakkoor.easyorder.utils.BaseUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ImageService {


    private final SessionUser sessionUser;
    @Value("${cloud.aws.bucket.name}")
    private String BUCKET_NAME;

    private final AmazonS3 amazonS3;
    private final AmazonS3Client s3Client;
    private final BaseUtils baseUtils;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;


    public Document saveImageToServer(MultipartFile file) throws IOException {
        String generateUniqueName = baseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename()));
        Document document = Document.builder().originalName(file.getOriginalFilename()).generatedName(generateUniqueName).size(file.getSize()).mimeType(file.getContentType()).extension(StringUtils.getFilenameExtension(file.getOriginalFilename())).build();
        saveFileToAWS(file, generateUniqueName);
        return imageRepository.save(document);
    }


    @Async
    public void saveFileToAWS(MultipartFile file, String generateUniqueName) throws IOException {
        byte[] bytes = file.getBytes();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(bytes.length);
        amazonS3.putObject(BUCKET_NAME, generateUniqueName, new ByteArrayInputStream(bytes), metadata);
    }

    public byte[] getImageFromAWS(String fileName) throws IOException {

        S3Object s3Object = s3Client.getObject(BUCKET_NAME, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        byte[] pngBytes = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return pngBytes;
    }

    public String deleteImageFromAWS(String fileName) {
        userRepository.findById(sessionUser.id()).ifPresent(

                user -> {
                    Document document = imageRepository.findDocument(fileName, user.getId());
                    if (!Objects.requireNonNull(document).isDeleted()) {
                        document.setDeleted(true);
                        imageRepository.save(document);
                        amazonS3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, fileName));
                    }
                });
        return "Deleted :" + fileName;
    }
}
