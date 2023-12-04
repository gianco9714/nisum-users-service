package com.nisum.users.services;

import java.util.UUID;

import com.nisum.users.common.exception.EmailAlreadyExistException;
import com.nisum.users.common.exception.InvalidEmailException;
import com.nisum.users.common.exception.InvalidPasswordException;
import com.nisum.users.common.utils.ErrorMessages;
import com.nisum.users.entities.UserEntity;
import com.nisum.users.repositories.UserRepository;
import com.nisum.users.services.data.request.CreateUserRequest;
import com.nisum.users.services.data.response.CreateUserResponse;
import com.nisum.users.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gianfranco Sullca
 */
@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Value("${password.regex}")
    private String passwordPattern;

    @Value("${email.regex}")
    private String emailPattern;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request) {
        // Email validation
        userRepository.findByEmail(request.getEmail()).ifPresent(userEntity -> {
            throw new EmailAlreadyExistException(ErrorMessages.USER_ALREADY_EXIST);
        });

        // Regex email validation
        validateEmail(request.getEmail());

        // Regex password validation
        validatePassword(request.getPassword());

        UserEntity userEntity = userMapper.mapToUserEntity(request);
        userEntity.setToken(UUID.randomUUID());
        userRepository.save(userEntity);

        CreateUserResponse response;
        response = userMapper.mapToUserResponse(userEntity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void validateEmail(String email) {
        if (!email.matches(emailPattern)) {
            throw new InvalidEmailException(ErrorMessages.INVALID_EMAIL_FORMAT);
        }
    }

    private void validatePassword(String password) {
        if (!password.matches(passwordPattern)) {
            throw new InvalidPasswordException(ErrorMessages.INVALID_PASSWORD_FORMAT);
        }
    }

}
