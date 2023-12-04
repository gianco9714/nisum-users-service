package com.nisum.users.repositories;

import java.util.Optional;
import java.util.UUID;

import com.nisum.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gianfranco Sullca
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
