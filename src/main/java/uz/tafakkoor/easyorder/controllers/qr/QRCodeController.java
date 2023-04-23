package uz.tafakkoor.easyorder.controllers.qr;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.dtos.AppErrorDTO;
import uz.tafakkoor.easyorder.services.qr.QRCodeService;

@RestController
@RequestMapping("/api/v1/qr")
@RequiredArgsConstructor
@Tag(name = "QR Code", description = "QR Code API")
public class QRCodeController {
    private final QRCodeService qrCodeService;
    @Operation(summary = "This API is used for QR code generation",
            responses = {
                    @ApiResponse(responseCode = "200", description = "QR code generated", content = @Content(schema = @Schema(implementation = MultipartFile.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = AppErrorDTO.class)))
            })
    @PostMapping("/generate")
    public ResponseEntity<Resource> generateQRCode(@RequestParam String text) {
        byte[] bytes = qrCodeService.generateQRCode(text);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat(text.concat(".png")));
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .body(resource);
    }
}
