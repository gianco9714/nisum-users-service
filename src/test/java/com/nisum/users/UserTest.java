package com.nisum.users;

import java.lang.reflect.Field;
import java.util.Optional;

import com.nisum.users.common.exception.EmailAlreadyExistException;
import com.nisum.users.common.exception.InvalidEmailException;
import com.nisum.users.common.exception.InvalidPasswordException;
import com.nisum.users.entities.UserEntity;
import com.nisum.users.repositories.UserRepository;
import com.nisum.users.services.UserService;
import com.nisum.users.services.data.request.CreateUserRequest;
import com.nisum.users.services.data.response.CreateUserResponse;
import com.nisum.users.services.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        UserMapper userMapper = new UserMapper();

        Field emailPatternField = ReflectionUtils.findField(UserService.class, "emailPattern");
        ReflectionUtils.makeAccessible(emailPatternField);
        ReflectionUtils.setField(emailPatternField, userService, "^(?!.*(?:\\.-|-\\.))[^@]+@[^\\W_](?:[\\w-]*[^\\W_])?(?:\\.[^\\W_](?:[\\w-]*[^\\W_])?)+$");

        Field passwordPatternField = ReflectionUtils.findField(UserService.class, "passwordPattern");
        ReflectionUtils.makeAccessible(passwordPatternField);
        ReflectionUtils.setField(passwordPatternField, userService, "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");

        Field userMapperField = ReflectionUtils.findField(UserService.class, "userMapper");
        ReflectionUtils.makeAccessible(userMapperField);
        ReflectionUtils.setField(userMapperField, userService, userMapper);
    }

    @Test
    void createUserSuccess() {

        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("TestPass123!");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<CreateUserResponse> response = userService.createUser(request);

        Mockito.verify(userRepository, Mockito.times(1)).save(any(UserEntity.class));

    }

    @Test
    void createUserEmailExist(){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("existing@example.com");
        request.setPassword("TestPass123!");

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail(request.getEmail());

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(EmailAlreadyExistException.class, () -> userService.createUser(request));
    }

    @Test
    void validateEmail_InvalidEmail() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("invalid-email");
        request.setPassword("TestPass123!");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Act y Assert
        assertThrows(InvalidEmailException.class, () -> userService.createUser(request));
    }

    @Test
    void validatePassword_InvalidPassword() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("weakPassword");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Act y Assert
        assertThrows(InvalidPasswordException.class, () -> userService.createUser(request));
    }
}
