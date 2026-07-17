package br.com.senac.projeto_spring_aula.todolist;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handlevalidation(MethodArgumentNotValidException ex) {

            String mensagem = ex.getBindingResult()
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();

            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", mensagem));
        }
}
