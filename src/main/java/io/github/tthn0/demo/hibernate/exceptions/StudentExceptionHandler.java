package io.github.tthn0.demo.hibernate.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class StudentExceptionHandler {
        @ExceptionHandler(RecordNotFoundException.class)
        public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(
                        RecordNotFoundException e) {
                return new StudentErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                e.getMessage())
                                .toResponseEntity();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<StudentErrorResponse> handleValidationException(
                        MethodArgumentNotValidException e) {
                String details = e.getBindingResult().getFieldErrors().stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.joining("; "));

                return new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), details)
                                .toResponseEntity();
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<StudentErrorResponse> handleValidationException(
                        MethodArgumentTypeMismatchException e) {
                return new StudentErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                "ID must be an integer.")
                                .toResponseEntity();
        }

        // @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        // public ResponseEntity<StudentErrorResponse> handleValidationException(
        // MethodArgumentNotValidException e) {

        // }

        // @ExceptionHandler(Exception.class)
        // public ResponseEntity<StudentErrorResponse> handleGenericException(
        // Exception e) {
        // return new StudentErrorResponse(
        // HttpStatus.INTERNAL_SERVER_ERROR.value(),
        // "An internal server error occurred.")
        // .toResponseEntity();
        // }
}
