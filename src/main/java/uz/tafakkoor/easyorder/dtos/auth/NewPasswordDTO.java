package uz.tafakkoor.easyorder.dtos.auth;

public record NewPasswordDTO(
        String code,
        String password,
        String confirmPassword) {
}
