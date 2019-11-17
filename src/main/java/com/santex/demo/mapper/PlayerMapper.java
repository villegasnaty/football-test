package com.santex.demo.mapper;

import com.santex.demo.api.model.Player;
import com.santex.demo.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDto playerToPlayerDto(Player player);
}
