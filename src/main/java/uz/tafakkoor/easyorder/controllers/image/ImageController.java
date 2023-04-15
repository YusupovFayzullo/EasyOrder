package uz.tafakkoor.easyorder.controllers.image;


import com.amazonaws.services.s3.model.S3Object;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.services.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ParameterObject
@RequestMapping("/api/v1/images")
@Tag(name = "Image", description = "Image API")
public class ImageController {
    private final ImageService imageService;
    private final S3Object s3Object;

    @PostMapping(value = "/upload/{file}", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file")  MultipartFile file) {
        String fileName = imageService.saveImageToAWS(file);
        return ResponseEntity.ok(fileName);
    }


    @GetMapping(value = "/download/{fileName}", produces = "application/octet-stream")
    public ResponseEntity<MultipartFile> downloadFile(@PathVariable String fileName) throws IOException {
        MultipartFile imageFromAWS = imageService.getImageFromAWS(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(s3Object.getObjectMetadata().getContentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(imageFromAWS);
    }


}
