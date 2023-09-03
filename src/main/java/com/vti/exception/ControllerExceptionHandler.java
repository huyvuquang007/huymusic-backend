package com.vti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // try catch ở các tầng controller trong project
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class) // catch 1 class đc khai báo bên trong hàm (tức đang catch customException
    public ResponseEntity<CustomException> catchExceptionCustom(CustomException exception, HttpServletRequest request){
        exception.setPath(request.getRequestURI()); // lấy ra path có thể bị lỗi
        return ResponseEntity.status(exception.getStatus()) // lấy đc status ở bên CustomException
                .body(exception); // cuối cùng là trả về cả đối tượng CustomException
    }


    // Method bắt lỗi validate , sẽ trả về hết các lỗi bằng cách cộng chuỗi nếu đầu vào ko hợp lệ
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomException> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = "";
        if (e.getBindingResult().hasErrors()){
            for(int i=0;i< e.getBindingResult().getAllErrors().size();i++){
                errorMessage += e.getBindingResult().getAllErrors().get(i).getDefaultMessage();
                errorMessage += (i==e.getBindingResult().getAllErrors().size()-1) ? "." : ", ";
            }
        }
        CustomException appException= new CustomException(errorMessage,400, request.getRequestURI());
        return new ResponseEntity<>(appException, HttpStatus.valueOf(appException.getStatus()));
    }


    @ExceptionHandler(Exception.class)// bắt các lỗi còn lại (toàn cục) vd lỗi 500 như lỗi backend
    public ResponseEntity<CustomException> catchExceptionGlobal(Exception exception, HttpServletRequest request){
        CustomException appException = new CustomException(exception.getMessage(),500,request.getRequestURI());
        appException.setPath(request.getRequestURI());
        return ResponseEntity.status(appException.getStatus())
                .body(appException);
    }




}
