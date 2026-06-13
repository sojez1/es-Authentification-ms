package com.jpstechno.auth_ms.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jpstechno.auth_ms.exceptions.acteurs.ActeurNotFoundException;
import com.jpstechno.auth_ms.exceptions.acteurs.EmailExistedException;

@RestControllerAdvice
public class MesExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> getListeErreurMessage(MethodArgumentNotValidException ex) {

        List<String> listeErreurs = ex.getBindingResult().getFieldErrors().stream()
                .map(eachError -> eachError.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(listeErreurs, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailExistedException.class)
    public ResponseEntity<?> handleEmailExists(EmailExistedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ActeurNotFoundException.class)
    public ResponseEntity<?> acteurNotExist(ActeurNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LienInvalideException.class)
    public ResponseEntity<?> lienNonValide(LienInvalideException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
