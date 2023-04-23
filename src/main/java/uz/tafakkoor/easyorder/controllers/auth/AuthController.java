package uz.tafakkoor.easyorder.controllers.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.dtos.auth.RefreshTokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenResponse;
import uz.tafakkoor.easyorder.dtos.auth.UserCreateDTO;
import uz.tafakkoor.easyorder.services.auth.AuthService;

@RestController
@RequestMapping({"/api/auth"})
public class AuthController {
    private final AuthService authService;

    @PostMapping({"/access/token"})
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(this.authService.generateToken(tokenRequest));
    }

    @PostMapping({"/refresh/token"})
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping({"/register"})
    public ResponseEntity<User> register(@NonNull @ModelAttribute @Valid UserCreateDTO dto) {
        return ResponseEntity.ok(this.authService.create(dto));
    }

    @PostMapping({"/activate"})
    public ResponseEntity<String> activate(@NonNull @RequestParam String code, @NonNull @RequestParam String phoneNumber) {
        return ResponseEntity.ok(this.authService.activate(code, phoneNumber));
    }

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }
}
