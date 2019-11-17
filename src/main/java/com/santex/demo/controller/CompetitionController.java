package com.santex.demo.controller;

import com.santex.demo.model.CompetitionResponse;
import com.santex.demo.service.FootballDemoServiceImpl;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class CompetitionController {

    private FootballDemoServiceImpl footballDemoService;

    public CompetitionController(FootballDemoServiceImpl footballDemoService) {
        this.footballDemoService = footballDemoService;
    }

    @RequestMapping(value = "/import-league/{leagueCode}", method = RequestMethod.GET)
    public ResponseEntity<CompetitionResponse> importLeague(@PathVariable String leagueCode) throws NotFoundException, ExecutionException, InterruptedException {
        footballDemoService.importCompetition(leagueCode);
        CompetitionResponse competitionResponse = new CompetitionResponse("Successfully imported");
        return new ResponseEntity<>(competitionResponse, HttpStatus.CREATED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CompetitionResponse> handleConstraintViolationException() {
        CompetitionResponse competitionResponse = new CompetitionResponse("League already imported");
        return new ResponseEntity<>(competitionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CompetitionResponse> handleNotFoundException() {
        CompetitionResponse competitionResponse = new CompetitionResponse("Not found");
        return new ResponseEntity<>(competitionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpClientErrorException.class, ConnectException.class})
    public ResponseEntity<CompetitionResponse> handleException() {
        CompetitionResponse competitionResponse = new CompetitionResponse("Server Error");
        return new ResponseEntity<>(competitionResponse, HttpStatus.GATEWAY_TIMEOUT);
    }
}
