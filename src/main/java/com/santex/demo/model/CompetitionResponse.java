package com.santex.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionResponse {
    public CompetitionResponse(String message) {
        this.message = message;
    }
    private String message;
}
