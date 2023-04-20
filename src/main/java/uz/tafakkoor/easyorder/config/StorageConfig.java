package uz.tafakkoor.easyorder.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StorageConfig {
    private String accessKey = "AKIAV2VDW3ICSX4DGHSP";
    private String secretKey = "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";
    private String BUCKET_NAME = "easyorder";


    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }

    @Bean
    public S3Object s3Object() {
        S3Object s3Object = new S3Object();
        s3Object.setBucketName(BUCKET_NAME);
        return s3Object;
    }
}
