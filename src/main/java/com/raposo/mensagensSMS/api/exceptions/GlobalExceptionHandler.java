package com.raposo.mensagensSMS.api.exceptions;

import com.raposo.mensagensSMS.infrastructure.exceptions.ResourceNotFoundException;
import com.raposo.mensagensSMS.infrastructure.record.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // ID não existe -> 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }
    // Falta de parametro -> 400
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handlerMissingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest request){
        return build(HttpStatus.BAD_REQUEST,
                "Parâmetro obrigatório ausente: " + exception.getParameterName(), request);
    }
    // Valor com tipo errado -> 400
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request){
        return build(HttpStatus.BAD_REQUEST,
                "Valor inválido para o parâmetro '" + exception.getName() + "': " + exception.getValue(), request);
    }

    // Falha nas anotações do DTO (@NotBlank, @Pattern) -> 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidation(MethodArgumentNotValidException exception,
                                                           HttpServletRequest request) {
        // Junta a mensagem de cada campo inválido em um texto só
        String mensagem = exception.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return build(HttpStatus.BAD_REQUEST, mensagem, request);
    }

    // JSON quebrado ou ilegível -> 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlerUnreadable(HttpMessageNotReadableException exception,
                                                           HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido ou mal formado", request);
    }

    //Qualquer erro inesperado -> 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception exception, HttpServletRequest request) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", request);
    }



    // Metodo auxiliar: evita repetir a montagem da resposta do Record
    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message,
                                                HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(body);
    }

}
