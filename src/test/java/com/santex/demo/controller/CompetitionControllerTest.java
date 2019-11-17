package com.santex.demo.controller;

import com.santex.demo.service.FootballDemoServiceImpl;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompetitionController.class)
public class CompetitionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FootballDemoServiceImpl footballDemoService;

    @Test
    void whenGetImportLeagueAndLeagueImportedThenReturnHttp200() throws Exception {
        mvc.perform(get("/import-league/PL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void whenGetImportLeagueAndLeagueNotFoundThenReturnHttp404() throws Exception {
        doThrow(new NotFoundException("not found")).when(footballDemoService).importCompetition(isA(String.class));
        mvc.perform(get("/import-league/PL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetImportLeagueAndConnectionErrorThenReturnHttp504() throws Exception {
        doThrow(new HttpClientErrorException(HttpStatus.GATEWAY_TIMEOUT)).when(footballDemoService).importCompetition(isA(String.class));
        mvc.perform(get("/import-league/PL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout());
    }

    @Test
    void whenGetImportLeagueAndLeagueAlreadyImportedThenReturnHttp409() throws Exception {
        doThrow(new ConstraintViolationException("", new SQLException(), "")).when(footballDemoService).importCompetition(isA(String.class));
        mvc.perform(get("/import-league/PL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}