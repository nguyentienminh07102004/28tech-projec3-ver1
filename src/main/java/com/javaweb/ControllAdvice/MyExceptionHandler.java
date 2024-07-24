package com.javaweb.ControllAdvice;

import com.javaweb.exception.NotFoundException;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> myNotFoundExceptionHandler(NotFoundException exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(exception.getMessage());
        if(!exception.getDetail().isEmpty()) {
            responseDTO.setData(exception.getDetail());
        }
        return ResponseEntity.status(400).body(responseDTO);
    }
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<?> myNumberFormatExceptionHandler(NumberFormatException exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(exception.getMessage());
        responseDTO.setData("Vui lòng điền đúng định dạng là số !!!");
        return ResponseEntity.status(400).body(responseDTO);
    }
}
