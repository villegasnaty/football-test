package com.santex.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "competition")
@Getter
@Setter
public class CompetitionDto extends GenericObject{

    @Column(unique = true, nullable = false)
    private Long competitionId;
    @Column(unique = true, nullable = false)
    private String code;
    private String name;
    private String areaName;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TeamDto> teamDtos;
}
