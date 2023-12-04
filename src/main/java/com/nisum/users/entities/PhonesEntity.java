package com.nisum.users.entities;

import javax.persistence.*;

import lombok.Data;

/**
 *
 * @author Gianfranco Sullca
 */
@Entity
@Data
@Table(name = "phones")
public class PhonesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String citycode;

    @Column(nullable = false)
    private String countrycode;

}
