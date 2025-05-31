package com.example.msrobots.application.service;

import com.example.msrobots.domain.model.Room;
import com.example.msrobots.domain.model.Position;
import com.example.msrobots.domain.model.Robot;
import com.example.msrobots.domain.model.Scenario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.example.msrobots.common.TestConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotServiceUseCaseTest {

    @Test
    void checkRobotsFinalPosition() throws IOException {

        // Charge from the json file of the robots we are going to use in the test
        InputStream isDataRobots = getClass().getClassLoader().getResourceAsStream(TestConstants.JSONFILE_ROBOTS);
        ObjectMapper robotMapper = new ObjectMapper();
        ArrayList<Robot> robotsArrayList  = robotMapper.readValue(isDataRobots, new TypeReference<ArrayList<Robot>>() {});

        // Charge from the json file of the room we are going to use in the test
        InputStream isDataRoom = getClass().getClassLoader().getResourceAsStream(TestConstants.JSONFILE_ROOM);
        ObjectMapper roomMapper = new ObjectMapper();
        Room room = roomMapper.readValue(isDataRoom, Room.class);

        // Charge from the json file of the positions expected for the robots after cleaning
        InputStream isDataPositions = getClass().getClassLoader().getResourceAsStream(TestConstants.JSONFILE_POSITIONSEXPECTED);
        ObjectMapper positionsMapper = new ObjectMapper();
        ArrayList<Position> positionsExpectedArrayList  = positionsMapper.readValue(isDataPositions, new TypeReference<ArrayList<Position>>() {});

        // Execution of the use case for cleaning the room
        ArrayList<String> finalPositionsList = new RobotService().clean(new Scenario(robotsArrayList, room));

        // Check out final position for every robot according to expected positions
        for(String robotFinalPosition : finalPositionsList)
            assertEquals(positionsExpectedArrayList.get(finalPositionsList.indexOf(robotFinalPosition)).toString(), robotFinalPosition, "Coordinates should be " + positionsExpectedArrayList.get(finalPositionsList.indexOf(robotFinalPosition)));

    }
}