package com.wisenut.domain.model.user

import com.wisenut.domain.common.security.PasswordEncryptor
import org.springframework.stereotype.Component

@Component
class RegistrationManagement (private val repository: UserRepository,
                              private val passwordEncryptor: PasswordEncryptor) {
    @Throws(RegistrationException::class)
    fun register(username: String?, emailAddress: String, password: String?): User {
        var existingUser: User = repository.findByUsername(username)
        if (existingUser != null) {
            throw UsernameExistsException()
        }
        existingUser = repository.findByEmailAddress(emailAddress.toLowerCase())
        if (existingUser != null) {
            throw EmailAddressExistsException()
        }
        val encryptedPassword: String = passwordEncryptor.encrypt(password)
        val newUser: User = User.create(username, emailAddress.toLowerCase(), encryptedPassword)
        repository.save(newUser)
        return newUser
    }
}