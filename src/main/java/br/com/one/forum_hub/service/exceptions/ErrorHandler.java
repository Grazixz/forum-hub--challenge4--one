package br.com.one.forum_hub.service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity erroEnum(){
        return ResponseEntity.badRequest().body("Não contém este curso no sistema!");
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity erroEnumList(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorDuplicate(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(ExceptionData.class)
    public ResponseEntity errorData(ExceptionData ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex) {
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DateErrors::new).toList());
    }
    private record DateErrors(
            String field,
            String message) {
       public DateErrors(FieldError fieldError){
           this(fieldError.getField(), fieldError.getDefaultMessage());
       }
    }
}
