package com.POS_System.POS_Application.advisor;

import com.POS_System.POS_Application.exception.BadRequestException;
import com.POS_System.POS_Application.exception.NotFoundException;
import com.POS_System.POS_Application.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error Coming",e.getMessage()+ "!!!!!!!!!!!!"),
                HttpStatus.NOT_FOUND
        );
    }
//    @ExceptionHandler(NotNullException.class)
//    public ResponseEntity<StandardResponse> handleNotFoundException(NotNullException e){
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(404,"Error Coming",e.getMessage()),
//                HttpStatus.NOT_FOUND
//        );
//    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardResponse> handleBadRequestException(BadRequestException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(405,"Error Coming",e.getMessage()+ "!!!!!!!!!!!!"),
                HttpStatus.BAD_REQUEST
        );
    }
}
