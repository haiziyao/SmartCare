package com.hzy.exception;

import com.hzy.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(value = MyException.class)
    public Result exceptionHandler(MyException e){
        e.printStackTrace();
        return Result.fail(e.getCode(),e.getMessage());
    }
}
