package com.santex.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "team")
@Getter
@Setter
public class TeamDto extends GenericObject{
    @Column(unique = true, nullable = false)
    private Long teamId;
    private String name;
    private String tla;
    private String shortName;
    private String areaName;
    private String email;
    @ManyToOne
    @JoinColumn(name = "competitionId", referencedColumnName = "competitionId", nullable = false)
    private CompetitionDto competitionDto;
    @OneToMany
    private Set<PlayerDto> players;
}
