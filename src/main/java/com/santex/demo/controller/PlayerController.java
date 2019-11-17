package com.santex.demo.controller;

import com.santex.demo.model.PlayerResponse;
import com.santex.demo.service.FootballDemoServiceImpl;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PlayerController {

    private FootballDemoServiceImpl footballDemoService;

    public PlayerController(FootballDemoServiceImpl footballDemoService) {
        this.footballDemoService = footballDemoService;
    }

    @RequestMapping(value = "/total-players/{leagueCode}", method = RequestMethod.GET)
    public ResponseEntity<PlayerResponse> getTotalPlayers(@PathVariable Long leagueCode) throws NotFoundException {
        PlayerResponse playerResponse = new PlayerResponse(footballDemoService.getPlayersByCompetition(leagueCode));
        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<PlayerResponse> handleNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
