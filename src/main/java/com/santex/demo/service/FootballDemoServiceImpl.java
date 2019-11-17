package com.santex.demo.service;

import com.santex.demo.api.FootballDataApi;
import com.santex.demo.api.model.*;
import com.santex.demo.dto.CompetitionDto;
import com.santex.demo.dto.PlayerDto;
import com.santex.demo.dto.TeamDto;
import com.santex.demo.mapper.CompetitionMapper;
import com.santex.demo.mapper.PlayerMapper;
import com.santex.demo.mapper.TeamMapper;
import com.santex.demo.repository.CompetitionRepository;
import com.santex.demo.repository.PlayerRepository;
import com.santex.demo.repository.TeamRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j
public class FootballDemoServiceImpl {

    private FootballDataApi footballDataApi;
    private CompetitionRepository competitionRepository;
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public FootballDemoServiceImpl(FootballDataApi footballDataApi, CompetitionRepository competitionRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.footballDataApi = footballDataApi;
        this.competitionRepository = competitionRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(20);


    public void importCompetition(String leagueCode) throws NotFoundException, ExecutionException, InterruptedException {
        Competitions competitions = footballDataApi.fetchCompetitions();
        Competition competition = matchCompetition(leagueCode, competitions);
        if (competition != null) {
            CompetitionDto competitionDto = CompetitionMapper.INSTANCE.competitionToCompetitionDto(competition);
            Map<Long, TeamDto> teams = searchTeamsByCompetitionId(competitionDto);
            competitionDto.setTeamDtos(new HashSet<TeamDto>(teams.values()));
            competitionRepository.save(competitionDto);
            searchAndImportPlayers(teams);
        } else {
            throw new NotFoundException(String.format("Competition with leagueCode %s not found", leagueCode));
        }
    }

    public int getPlayersByCompetition(Long leagueCode) throws NotFoundException {
        int players = playerRepository.countPlayersByCompetitonId(leagueCode);
        if (players == 0) {
            throw new NotFoundException(String.format("Competition with leagueCode %s not found", leagueCode));
        }
        return players;
    }

    private Competition matchCompetition(String leagueCode, Competitions competitions) {
        for (Competition competition : competitions.getCompetitions()) {
            if (competition.getCode() != null && competition.getCode().equals(leagueCode)) {
                return competition;
            }
        }
        return null;
    }

    private Map<Long, TeamDto> searchTeamsByCompetitionId(CompetitionDto competitionDto) {
        Teams teams = footballDataApi.fetchTeams(competitionDto.getCompetitionId());
        Map<Long, TeamDto> mapTeamDto = new HashMap<Long, TeamDto>();
        for (Team tm : teams.getTeams()) {
            TeamDto teamDto = TeamMapper.INSTANCE.teamToTeamDto(tm);
            teamDto.setCompetitionDto(competitionDto);
            mapTeamDto.put(teamDto.getTeamId(), teamDto);
        }
        return mapTeamDto;
    }

    private void searchAndImportPlayers(Map<Long, TeamDto> mapTeamDto) throws ExecutionException, InterruptedException {

        List<Future<Team>> teamList = new ArrayList<>();
        for (TeamDto tm : mapTeamDto.values()) {
            teamList.add(executorService.submit(fetchTeamInfoTask(tm.getTeamId())));
        }
        for (Future<Team> future : teamList) {
            Team team = future.get();
            for (Player pl : team.getSquad()) {
                PlayerDto playerDto = PlayerMapper.INSTANCE.playerToPlayerDto(pl);
                playerDto.setTeamDto(mapTeamDto.get(team.getId()));
                playerRepository.save(playerDto);
            }
        }
    }

    private Callable<Team> fetchTeamInfoTask(Long id) {
        return new Callable<Team>() {
            @Override
            public Team call() throws Exception {
                return footballDataApi.fetchTeamById(id);
            }
        };
    }
}
