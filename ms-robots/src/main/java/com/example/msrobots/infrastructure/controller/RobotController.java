package com.example.msrobots.infrastructure.controller;

import com.example.msrobots.application.service.RobotService;
import com.example.msrobots.common.Constants;
import com.example.msrobots.domain.exception.InsufficientNumberOfLinesException;
import com.example.msrobots.domain.exception.OutOfBoundsException;
import com.example.msrobots.domain.model.Room;
import com.example.msrobots.domain.model.Position;
import com.example.msrobots.domain.model.Robot;
import com.example.msrobots.domain.model.Scenario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/robots")
public class RobotController {
    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/lines")
    public List<String> receiveLines(@RequestBody List<String> lines) {

        // Check that we have minimum 3 lines, first for room size, second for the robot start position and third for the movements
        if (lines.size()<3)
            throw new InsufficientNumberOfLinesException("Error! The minimum number of lines is 3");

        return new RobotService().clean(new Scenario(lines));
    }

}