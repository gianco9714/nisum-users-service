package com.nisum.users.entities;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;

/**
 *
 * @author Gianfranco Sullca
 */
@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private ZonedDateTime modifiedAt;

    @Column()
    private ZonedDateTime lastLogin;

    @Column(unique = true, nullable = false)
    private UUID token;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<PhonesEntity> phones;

    @PrePersist
    protected void onCreate() {
        createdAt = ZonedDateTime.now();
        modifiedAt = ZonedDateTime.now();
    }
}
