package com.santex.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "player")
@Getter
@Setter
public class PlayerDto extends GenericObject {
    private String name;
    private String position;
    private String dateOfBirth;
    private String countryOfBirth;
    private String nationality;
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private TeamDto teamDto;
}
