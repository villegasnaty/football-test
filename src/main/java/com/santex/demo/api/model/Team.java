package com.santex.demo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Team {
    private Long id;
    private String name;
    private String tla;
    private String shortName;
    private Area area;
    private String email;
    private List<Player> squad;
}
