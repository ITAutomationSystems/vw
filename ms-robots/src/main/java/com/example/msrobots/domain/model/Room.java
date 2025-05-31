package com.example.msrobots.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private int dimX, dimY;
    public Room(){}
    @Override
    public String toString() {
        return dimX + "," + dimY;
    }

}
