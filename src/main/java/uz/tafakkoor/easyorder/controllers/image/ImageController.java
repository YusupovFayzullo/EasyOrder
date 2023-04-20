package uz.tafakkoor.easyorder.controllers.image;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.services.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ParameterObject
@RequestMapping("/api/v1/images")
@Tag(name = "Image", description = "Image API")
public class ImageController {
    private final ImageService imageService;
    private final ServletContext servletContext;


    @Operation(summary = "Upload image to server")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = imageService.saveImageToServer(file);
        return ResponseEntity.ok(fileName);
    }

    @Operation(summary = "Upload multiple files to server")
    @PostMapping(value = "/uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("file") List<MultipartFile> file) {
        List<String> filesName = imageService.saveImagesToServer(file);
        return ResponseEntity.ok(filesName);
    }


    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] imageFromAWS = imageService.getImageFromAWS(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(imageFromAWS, headers, HttpStatus.OK);

    }


}