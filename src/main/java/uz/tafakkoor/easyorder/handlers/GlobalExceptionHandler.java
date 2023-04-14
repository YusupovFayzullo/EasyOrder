package uz.tafakkoor.easyorder.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.tafakkoor.easyorder.dtos.AppErrorDTO;
import uz.tafakkoor.easyorder.exceptions.DuplicatePermissionCodeException;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDTO> handleUnknownExceptions(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                AppErrorDTO.builder()
                        .error_code(500)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(AppErrorDTO.builder()
                        .error_code(404)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }
    @ExceptionHandler(DuplicatePermissionCodeException.class)
    public ResponseEntity<AppErrorDTO> handleDuplicatePermissionCodeException(DuplicatePermissionCodeException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(AppErrorDTO.builder()
                        .error_code(400)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(AppErrorDTO.builder()
                        .error_code(404)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }



}
