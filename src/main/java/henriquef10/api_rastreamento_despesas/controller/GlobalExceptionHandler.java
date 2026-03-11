package henriquef10.api_rastreamento_despesas.controller;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.exception.UnauthenticatedException;
import henriquef10.api_rastreamento_despesas.controller.dto.ExceptionResponse;
import henriquef10.api_rastreamento_despesas.core.exception.category.CategoryNotFoundException;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseAlreadyPaidException;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseNotFoundException;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpensePaymentBeforeCreationException;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserAlreadyExistsException;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthenticatedException(UnauthenticatedException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(ForbiddenException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ExpensePaymentBeforeCreationException.class)
    public ResponseEntity<ExceptionResponse> handleExpensePaymentBeforeCreationException(ExpensePaymentBeforeCreationException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ExpenseAlreadyPaidException.class)
    public ResponseEntity<ExceptionResponse> handleExpenseAlreadyPaidException(ExpenseAlreadyPaidException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Geral

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundEntityException(IllegalStateException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
        ExceptionResponse error = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
