package com.example.msrobots.domain.port.in;

import com.example.msrobots.domain.model.Scenario;

import java.util.List;

public interface RobotServiceUseCase {

    // Use case for cleaning a room with one or severals robots
    List<String> clean(Scenario scenario);

}
