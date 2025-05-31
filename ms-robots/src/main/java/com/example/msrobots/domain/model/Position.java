package com.example.msrobots.domain.model;

import com.example.msrobots.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private int x, y;
    private String orientation;

    public Position() {}

    @Override
    public String toString() {
        return x + Constants.ESPACE + y + Constants.ESPACE + orientation;
    }
}
