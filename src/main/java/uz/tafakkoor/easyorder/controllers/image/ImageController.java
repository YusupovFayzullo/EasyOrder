package uz.tafakkoor.easyorder.controllers.image;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.Document;
import uz.tafakkoor.easyorder.services.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ParameterObject
@RequestMapping("/api/v1/images")
@Tag(name = "Image", description = "Image API")
public class ImageController {
    private final ImageService imageService;


    @Operation(summary = "Upload image to server")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(201).body(imageService.saveImageToServer(file));
    }


    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] imageFromAWS = imageService.getImageFromAWS(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(imageFromAWS, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        String deleted = imageService.deleteImageFromAWS(fileName);
        return ResponseEntity.status(200).body(deleted);
    }


}
