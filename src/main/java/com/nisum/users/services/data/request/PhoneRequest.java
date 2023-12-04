package com.nisum.users.services.data.request;

import lombok.Data;

/**
 *
 * @author Gianfranco Sullca
 */
@Data
public class PhoneRequest {

    private Long id;

    private String number;

    private String citycode;

    private String countrycode;
}
