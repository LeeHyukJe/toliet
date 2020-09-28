package com.wisenut.domain.common.security;

public interface PasswordEncryptor {

    String encrypt(String rawPassword);
}
