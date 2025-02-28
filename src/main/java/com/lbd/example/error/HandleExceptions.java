package com.lbd.example.error;


import com.lbd.example.domain.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandleExceptions {

    /**
     *
     * @param exception
     * @return  ErrorDTO error description
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorDTO handleRepositoryException(
            final MethodArgumentNotValidException exception) {

        return ErrorDTO.builder()
                .code(ErrorCode.VALIDATION_ERROR)
                .description(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    /**
     *
     * @param UserException
     * @return create a new error response with details of exception
     */
    @ExceptionHandler(UserException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorDTO handleRepositoryException(
            final UserException exception) {

        return ErrorDTO.builder()
                .code(ErrorCode.USER_FOUND)
                .description(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     *
     * @param CryptoException
     * @return create a new error response with details of exception
     */
    @ExceptionHandler(CryptoException.class)
    @ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY)
    public @ResponseBody ErrorDTO handleCryptoException(
            final UserException exception) {

        return ErrorDTO.builder()
                .code(ErrorCode.CRYPTO_ERROR)
                .description(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
