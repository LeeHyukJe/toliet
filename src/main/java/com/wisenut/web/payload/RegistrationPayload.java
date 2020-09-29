package com.wisenut.web.payload;

import com.wisenut.domain.application.commands.RegistrationCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegistrationPayload {
    @Size(min =2, max = 50, message = "유저 아이디는 최소 2자 최대 50자 이내여야 합니다.")
    @NotNull
    private String username;

    @Email(message = "이메일 주소는 반드시 유효해야 합니다.")
    @Size(max = 100, message = "이메일 주소는 100자 이상이어서는 안 됩니다.")
    @NotNull
    private String emailAddress;

    @Size(min = 6, max = 30, message = "비밀번호는 최소 6자 최대 30자 이내여야 합니다.")
    @NotNull
    private String password;

    public RegistrationCommand toCommand() {
        return new RegistrationCommand(this.username, this.emailAddress, this.password);
    }
}
