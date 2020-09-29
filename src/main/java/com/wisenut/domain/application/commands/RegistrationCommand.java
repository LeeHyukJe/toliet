package com.wisenut.domain.application.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationCommand {

    private String username;
    private String emailAddress;
    private String password;

    public RegistrationCommand(String username, String emailAddress, String password){
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }


}
