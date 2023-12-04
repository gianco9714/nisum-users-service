package com.nisum.users.services;

import com.nisum.users.services.data.request.CreateUserRequest;
import com.nisum.users.services.data.response.CreateUserResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Gianfranco Sullca
 */
public interface IUserService {

    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request);

}
