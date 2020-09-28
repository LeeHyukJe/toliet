package com.wisenut.web.apis;

import com.wisenut.domain.application.UserService;
import com.wisenut.domain.model.user.EmailAddressExistsException;
import com.wisenut.domain.model.user.RegistrationException;
import com.wisenut.domain.model.user.UsernameExistsException;
import com.wisenut.web.payload.RegistrationPayload;
import com.wisenut.web.results.ApiResult;
import com.wisenut.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {
    private UserService service;

    public RegistrationApiController(UserService service){
        this.service = service;
    }

    @RequestMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload){
        try{
            service.register(payload.toCommand());
            return Result.created();
        }catch (RegistrationException e){
            String errorMessage = "회원가입 실패";
            if(e instanceof UsernameExistsException){
                errorMessage = "유저아이디가 이미 존재합니다.";
            }else if(e instanceof EmailAddressExistsException){
                errorMessage = "Email이 이미 존재합니다.";
            }
            return Result.failure(errorMessage);
        }
    }
}
