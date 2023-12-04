package com.nisum.users.services.data.response;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

/**
 *
 * @author Gianfranco Sullca
 */
@Data
public class CreateUserResponse {

    private UUID id;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private ZonedDateTime lastLogin;

    private UUID token;

    private boolean isActive;

}
