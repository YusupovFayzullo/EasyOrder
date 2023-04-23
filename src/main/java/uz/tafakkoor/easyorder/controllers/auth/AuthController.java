package uz.tafakkoor.easyorder.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.dtos.AppErrorDTO;
import uz.tafakkoor.easyorder.dtos.auth.RefreshTokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenResponse;
import uz.tafakkoor.easyorder.dtos.auth.UserCreateDTO;
import uz.tafakkoor.easyorder.services.auth.AuthService;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
@Tag(name = "Authorization", description = "Authorization API")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "This API is used for access token generation", responses = {
            @ApiResponse(responseCode = "200", description = "Access token generated", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = AppErrorDTO.class)))
    })
    @PostMapping({"/access/token"})
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(this.authService.generateToken(tokenRequest));
    }

    @Operation(summary = "This API is used for generating a new access token using the refresh token",
    responses = {
            @ApiResponse(responseCode = "200", description = "Access token generated", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = AppErrorDTO.class)))
    }
    )
    @PostMapping({"/refresh/token"})
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshTokenRequest));
    }

    @Operation(summary = "This API is used for user registration", responses = {
            @ApiResponse(responseCode = "200", description = "User registered", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = AppErrorDTO.class)))
    })
    @PostMapping({"/register"})
    public ResponseEntity<User> register(@NonNull @ModelAttribute @Valid UserCreateDTO dto) {
        return ResponseEntity.ok(this.authService.create(dto));
    }

    @Operation(summary = "This API is used for user activating users through the activation code that was sent via SMS", responses = {
            @ApiResponse(responseCode = "200", description = "User activated", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = AppErrorDTO.class)))
    })
    @PostMapping({"/activate"})
    public ResponseEntity<String> activate(@NonNull @RequestParam String code, @NonNull @RequestParam String phoneNumber) {
        return ResponseEntity.ok(this.authService.activate(code, phoneNumber));
    }
}
