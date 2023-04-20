package uz.tafakkoor.easyorder.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.tafakkoor.easyorder.dtos.AppErrorDTO;
import uz.tafakkoor.easyorder.exceptions.DuplicatePermissionCodeException;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }

    @ExceptionHandler(DuplicatePermissionCodeException.class)
    public ResponseEntity<AppErrorDTO> handleDuplicatePermissionCodeException(DuplicatePermissionCodeException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 400));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = "Input is not valid";
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }

//    @ExceptionHandler(UnexpectedTypeException.class)
//    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
//        String errorMessage = "Input is not valid";
//        Map<String, List<String>> errorBody = new HashMap<>();
//        for (FieldError fieldError : e.getFieldErrors()) {
//            String field = fieldError.getField();
//            String message = fieldError.getDefaultMessage();
//            errorBody.compute(field, (s, values) -> {
//                if (!Objects.isNull(values))
//                    values.add(message);
//                else
//                    values = new ArrayList<>(Collections.singleton(message));
//                return values;
//            });
//        }
//        String errorPath = request.getRequestURI();
//        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
//        return ResponseEntity.status(400).body(errorDTO);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDTO> handleUnknownExceptions(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                new AppErrorDTO(
                        request.getRequestURI(),
                        e.getMessage(),
                        null,
                        500
                )
        );
    }

}
