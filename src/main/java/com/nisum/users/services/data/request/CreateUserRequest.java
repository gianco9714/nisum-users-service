package com.nisum.users.services.data.request;

import java.util.List;

import lombok.Data;

/**
 *
 * @author Gianfranco Sullca
 */
@Data
public class CreateUserRequest {

    private String name;

    private String email;

    private String password;

    private List<PhoneRequest> phones;

}
