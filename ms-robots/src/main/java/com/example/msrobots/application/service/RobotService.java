package com.example.msrobots.application.service;

import com.example.msrobots.domain.exception.OutOfBoundsException;
import com.example.msrobots.domain.model.Robot;
import com.example.msrobots.domain.model.Scenario;
import com.example.msrobots.domain.port.in.RobotServiceUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RobotService implements RobotServiceUseCase {
    public RobotService(){}

    @Override
    public ArrayList<String> clean(Scenario scenario) {
        ArrayList<String> finalRobotPositionsList = new ArrayList<>();

        for (Robot robot : scenario.getRobotsArrayList()){

            robot.setRoom(scenario.getRoom());

            // Preconditions
            // If the robot position is not inside the room he will not clean, an error will be launched
            if(!robot.isInsideRoom())
                throw new OutOfBoundsException("Error! The robot with position " + robot.toString() + " is out of limits of the room with dimensions " + scenario.getRoom().toString());

            if (robot.isDebugMode()) {
                robot.drawPosition();
                System.out.println(robot.getInstructions());
            }

            // We process every movement
            for(char c : robot.getInstructions().toCharArray())
                robot.processMovement(c);

            finalRobotPositionsList.add(robot.toString());

        }

        return finalRobotPositionsList;
    }



}