package com.santex.demo.repository;

import com.santex.demo.dto.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerDto, Long> {

    @Query(value = "SELECT COUNT(*) FROM footballdemo.player\n" +
            "LEFT JOIN footballdemo.team ON team.id = player.team_id\n" +
            "LEFT JOIN footballdemo.competition ON competition.competition_id = team.competition_id\n" +
            "where competition.competition_id = ?1", nativeQuery = true)
    Integer countPlayersByCompetitonId(Long competitionId);
}
