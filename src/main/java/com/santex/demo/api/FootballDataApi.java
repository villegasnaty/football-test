package com.santex.demo.api;

import com.santex.demo.api.model.Competitions;
import com.santex.demo.api.model.Team;
import com.santex.demo.api.model.Teams;
import com.santex.demo.config.YAMLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FootballDataApi {

    public static final String X_AUTH_TOKEN = "X-Auth-Token";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private YAMLConfig config;

    /**
     * Retrieve competitions fetched from football-data api
     *
     * @return CompetitionsDTO
     */
    public Competitions fetchCompetitions() {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<Competitions> response = restTemplate.exchange(config.getCompetitionsurl(), HttpMethod.GET, entity, Competitions.class);
        return response.getBody();
    }

    /**
     * Retrieve teams fetched from football-data api by competitionId
     *
     * @return TeamsDTO
     */
    public Teams fetchTeams(Long competitionId) {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<Teams> response = restTemplate.exchange(config.getCompetitionsurl() + competitionId + "/teams", HttpMethod.GET, entity, Teams.class);
        return response.getBody();
    }

    /**
     * Retrieve a team fetched from football-data api by teamId
     *
     * @return TeamDTO
     */
    public Team fetchTeamById(Long teamId) {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<Team> response = restTemplate.exchange(config.getTeamsurl() + teamId, HttpMethod.GET, entity, Team.class);
        return response.getBody();
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_AUTH_TOKEN, config.getFootballapikey());
        return new HttpEntity(headers);
    }
}
