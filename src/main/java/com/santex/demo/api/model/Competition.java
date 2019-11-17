package com.santex.demo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Competition {
    private Long id;
    private String code;
    private String name;
    private Area area;
}
