package uz.tafakkoor.easyorder.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenRequest(@NotBlank String phoneNumber, @NotBlank String password) {
}
