package br.com.picpay.desafio.picpay.infrastructure.exception;

import br.com.picpay.desafio.picpay.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionResponseDTO> noBalanceOrAuthorization(IllegalStateException e){
        ExceptionResponseDTO message = new ExceptionResponseDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(message);
    }
}
