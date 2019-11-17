package com.santex.demo.mapper;

import com.santex.demo.api.model.Competition;
import com.santex.demo.dto.CompetitionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface CompetitionMapper {

    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);
    @Mappings({
            @Mapping(target="competitionId", source="id"),
            @Mapping(target="areaName", source="area.name"),
            @Mapping(target="id", ignore=true)
    })
    CompetitionDto competitionToCompetitionDto(Competition competition);
}
