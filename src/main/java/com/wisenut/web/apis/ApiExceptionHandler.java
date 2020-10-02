package com.wisenut.web.apis;

import com.wisenut.web.results.ApiResult;
import com.wisenut.web.results.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ApiResult> handle(RuntimeException exception){
        String errorReferenceCode = UUID.randomUUID().toString();
        log.error("처리되지 않은 오류... [code = " + errorReferenceCode +"]", exception);
        return Result.serverError("죄송합니다. 서버에서 처리되지 못한 에러가 발생하였습니다.", errorReferenceCode);
    }
}
