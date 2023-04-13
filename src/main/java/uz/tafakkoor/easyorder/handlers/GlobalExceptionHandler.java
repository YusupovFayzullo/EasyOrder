package uz.tafakkoor.easyorder.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.tafakkoor.easyorder.dtos.AppErrorDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handler_404(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(AppErrorDTO.builder()
                        .error_code(404)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }
}
