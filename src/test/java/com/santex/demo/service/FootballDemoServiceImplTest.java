package com.santex.demo.service;

import com.santex.demo.repository.PlayerRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballDemoServiceImplTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private FootballDemoServiceImpl footballDemoService;

    @Test
    void getPlayersByCompetitionShouldReturnIntWhenCountPlayersByCompetitionIdReturnGreaterThan0() throws NotFoundException {
        when(playerRepository.countPlayersByCompetitonId(1L)).thenReturn(300);
        int playersCount = footballDemoService.getPlayersByCompetition(1L);
        assertEquals(300, playersCount);
    }

    @Test
    void getPlayersByCompetitionShouldReturnNotFoundExceptionWhenCountPlayersByCompetitionIdReturn0() {
        when(playerRepository.countPlayersByCompetitonId(1L)).thenReturn(0);
        assertThrows(NotFoundException.class, () -> {
            footballDemoService.getPlayersByCompetition(1L);
        });
    }
}