package com.santex.demo;

import com.santex.demo.controller.PlayerController;
import com.santex.demo.repository.PlayerRepository;
import com.santex.demo.service.FootballDemoServiceImpl;
import javassist.NotFoundException;
import javassist.compiler.NoFieldException;
import org.hamcrest.core.Is;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.net.ConnectException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class FootballDemoApplicationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private FootballDemoServiceImpl footballDemoService;


    @Test
    void whenGetTotalPlayersThenReturnCorrectResponse() throws Exception {
        when(footballDemoService.getPlayersByCompetition(1L)).thenReturn(300);
        mvc.perform(get("/total-players/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("total", Is.is(300)));
    }

    @Test
    void whenGetTotalPlayersThenReturnCorrect404Response() throws Exception {
        when(footballDemoService.getPlayersByCompetition(1L)).thenThrow(new NotFoundException("not found"));
        mvc.perform(get("/total-players/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
