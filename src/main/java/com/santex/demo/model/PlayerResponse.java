package com.santex.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponse {
    private int total;

    public PlayerResponse(int total) {
        this.total = total;
    }
}
