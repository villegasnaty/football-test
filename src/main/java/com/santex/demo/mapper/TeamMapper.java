package com.santex.demo.mapper;

import com.santex.demo.api.model.Competition;
import com.santex.demo.api.model.Team;
import com.santex.demo.dto.CompetitionDto;
import com.santex.demo.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    @Mappings({
            @Mapping(target="teamId", source="id"),
            @Mapping(target="areaName", source="area.name"),
            @Mapping(target="id", ignore=true),
            @Mapping(target="players", source="squad")
    })
    TeamDto teamToTeamDto(Team team);
}
