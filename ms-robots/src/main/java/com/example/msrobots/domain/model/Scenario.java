package com.example.msrobots.domain.model;

import com.example.msrobots.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Scenario {

    ArrayList<Robot> robotsArrayList;
    Room room;

    @Value("${debug.mode}")
    boolean debugMode;

    public Scenario(){}

    public Scenario(ArrayList<Robot> robotsArrayList, Room room){
        this.robotsArrayList= robotsArrayList;
        this.room= room;
    }

    public Scenario(List<String> lines){
        robotsArrayList= getRobotsFromLines(lines);
        room= getRoomFromLines(lines);
    }

    /**
     * Method to obtain the robots presents in lines
     * @param lines lines received via API
     * @return
     */
    private ArrayList<Robot> getRobotsFromLines(List<String> lines){
        ArrayList<Robot> robotsArrayList = new ArrayList<>();
        Robot r= new Robot();
        int i = 0;
        for (String line : lines){
            // Jump first line because line of the room dimensions
            if (i>0){
                // Odd lines represent initial position for a robot
                if (isOddLine(i)){
                    r = new Robot();
                    r.setDebugMode(debugMode);
                    r.setPosition(new Position(Integer.parseInt(line.split(Constants.ESPACE)[0]), Integer.parseInt(line.split(Constants.ESPACE)[1]), line.split(Constants.ESPACE)[2]));
                    // Not odd lines represent movements for a robot
                } else {
                    r.setInstructions(line);
                    robotsArrayList.add(r);
                }
            }
            i++;
        }
        return robotsArrayList;
    }

    /**
     * Function to return if the number of a line is odd or not
     * @param i
     * @return
     */
    private boolean isOddLine(int i){
        return (i%2!=0);
    }

    /**
     * Function to get the room present in lines
     * @param lines lines received via API
     * @return
     */
    private Room getRoomFromLines(List<String> lines) {
        return new Room(getXCoordFromLine(lines.get(0)), getYCoordFromLine(lines.get(0)));
    }

    /**
     * Function to get the coordenate X from a line
     * @param line
     * @return
     */
    private int getXCoordFromLine(String line){
        return Integer.parseInt(getCoordFromLine(line, Constants.X));
    }

    /**
     * Function to get the coordenate Y from a line
     * @param line
     * @return
     */
    private int getYCoordFromLine(String line){
        return Integer.parseInt(getCoordFromLine(line, Constants.Y));
    }

    /**
     * Function to get the coordinate from a line
     * @param line
     * @param coord X or Y
     * @return
     */
    private String getCoordFromLine(String line, char coord){
        return line.split(Constants.ESPACE)[coord==Constants.X?0:1];
    }


}
