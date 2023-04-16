package uz.tafakkoor.easyorder.controllers.image;


import com.amazonaws.services.s3.model.S3Object;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.services.ImageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ParameterObject
@RequestMapping("/api/v1/images")
@Tag(name = "Image", description = "Image API")
public class ImageController {
    private final ImageService imageService;
    private final S3Object s3Object;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = String.valueOf(imageService.saveImageToAWS(file));
        return ResponseEntity.ok(fileName);
    }


    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] imageFromAWS = imageService.getImageFromAWS(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(imageFromAWS, headers, HttpStatus.OK);

    }


}
