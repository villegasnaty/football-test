package com.santex.demo.controller;

import com.santex.demo.model.PlayerResponse;
import com.santex.demo.service.FootballDemoServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class PlayerControllerTest {

    @MockBean
    private FootballDemoServiceImpl footballDemoService;

    @Autowired
    private PlayerController playerController;

    @Test
    void getTotalPlayersShouldReturnHttpStatus200AndTotalInBodyWhenGetPlayersByCompetitionGreaterThan0() throws NotFoundException {
        when(footballDemoService.getPlayersByCompetition(1L)).thenReturn(300);
        ResponseEntity<PlayerResponse> response = playerController.getTotalPlayers(1L);
        assertEquals(200, response.getStatusCodeValue(), "Http Status not ok");
        assertEquals(300, response.getBody().getTotal(), "incorrect total value in response");
    }

    @Test
    void handleNotFoundExceptionShouldReturnHttpStatus404() throws NotFoundException {
        ResponseEntity<PlayerResponse> response = playerController.handleNotFoundException();
        assertEquals(404, response.getStatusCodeValue(), "Http Status NOT_FOUND");
    }

}