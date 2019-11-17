package com.santex.demo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Player {
    private String name;
    private String position;
    private String dateOfBirth;
    private String countryOfBirth;
    private String nationality;
}
