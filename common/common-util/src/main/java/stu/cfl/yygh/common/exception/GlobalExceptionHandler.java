package stu.cfl.yygh.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import stu.cfl.yygh.common.result.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // 异常处理，当出翔Exception异常是就会调用此方法
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }


    @ExceptionHandler(YyghException.class)  // 异常处理，当出翔Exception异常是就会调用此方法
    @ResponseBody
    public Result error(YyghException e){
        e.printStackTrace();
        return Result.fail();
    }
}
