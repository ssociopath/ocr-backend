package org.whutosa.modulecommon.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.whutosa.modulecommon.exception.ApplicationException;
import org.whutosa.modulecommon.response.ApplicationResponse;

import javax.servlet.http.HttpServletRequest;

import static org.whutosa.modulecommon.response.SystemCodeEnum.*;

/**
 * @author bobo
 * @date 2021/4/3
 */

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ApplicationResponse<String> defaultErrorHandler(HttpServletRequest request, Exception exception){
        if(exception instanceof ApplicationException){
            ApplicationException applicationException = (ApplicationException) exception;
            log.info("捕获应用错误", exception);
            return ApplicationResponse.fail(applicationException.getSystemCode(), applicationException.getResponseMessage());
        }else if (exception instanceof IllegalArgumentException
                || exception instanceof HttpMessageNotReadableException) {
            log.info("捕获参数错误", exception);
            return ApplicationResponse.fail(ARGUMENT_WRONG);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            log.debug("捕获调用方法错误", exception);
            return ApplicationResponse.fail(REQUEST_METHOD_NOT_SUPPPORTED);
        }
        log.error("捕获意外异常", exception);
        return ApplicationResponse.fail(UNKNOWN_ERROR);
    }
}