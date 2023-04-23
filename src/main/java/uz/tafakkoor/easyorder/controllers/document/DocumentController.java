package uz.tafakkoor.easyorder.controllers.document;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import uz.tafakkoor.easyorder.services.DocumentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ParameterObject
@RequestMapping("/api/v1/documents")
@Tag(name = "Document", description = "Document API")
public class DocumentController {
    private final DocumentService documentService;

    @Operation(summary = "Upload document to server", responses = {
            @ApiResponse(responseCode = "201", description = "Document uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Document not uploaded")})
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(201).body(documentService.saveDocumentToServer(file));
    }

    @Operation(summary = "Download document from server", responses = {
            @ApiResponse(responseCode = "200", description = "Document downloaded successfully"),
            @ApiResponse(responseCode = "404", description = "Document not found")})
    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] documentFromAWS = documentService.getDocumentFromAWS(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(documentFromAWS, headers, HttpStatus.OK);
    }

    @Operation(summary = "Delete document from server", responses = {
            @ApiResponse(responseCode = "204", description = "Document deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Document not found")})
    @DeleteMapping(value = "/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        String deleted = documentService.deleteDocumentFromAWS(fileName);
        return ResponseEntity.status(204).body(deleted);
    }

}
