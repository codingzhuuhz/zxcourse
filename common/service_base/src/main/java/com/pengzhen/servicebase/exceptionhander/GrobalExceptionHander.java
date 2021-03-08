package com.pengzhen.servicebase.exceptionhander;

import com.pengzhen.commonutils.R;
import com.pengzhen.servicebase.exception.zxException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
//@Slf4j
public class GrobalExceptionHander {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("全局异常处理去...") ;
    }
    @ExceptionHandler(zxException.class)
    @ResponseBody
    //自定义异常处理器
    public R error(zxException e){
//        log.error(e.getMsg());
        return R.error().code(e.getCode()).message(e.getMsg()) ;
    }
}
