package com.wisenut.web.apis.authenticate;

import com.wisenut.utils.JsonUtils;
import com.wisenut.web.results.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        ApiResult failure;

        if(e instanceof BadCredentialsException){
            failure = ApiResult.message("유효하지 않은 사용자 정보입니다.");
        } else if(e instanceof InsufficientAuthenticationException){
            failure = ApiResult.message("유효하지 않은 인증 요청입니다.");
        } else{
            failure = ApiResult.message("인증 실패..");
        }

        JsonUtils.write(httpServletResponse.getWriter(), failure);
    }
}
