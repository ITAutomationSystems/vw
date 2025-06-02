package com.example.msrobots.domain.model;

import com.example.msrobots.common.Constants;
import com.example.msrobots.domain.exception.BadMovement;
import com.example.msrobots.domain.exception.OutOfBoundsException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
public class Robot {

    private Position position;
    private String instructions;
    private Room room;

    @Value("${debug.mode}")
    private boolean debugMode;

    public Robot() {
    }

    public void drawPosition(){
        System.out.println(position.getX()+Constants.ESPACE+position.getY()+Constants.ESPACE+position.getOrientation());
    }

    /**
     *
     * @param movement
     */
    public void processMovement(char movement){
        switch (movement){
            case Constants.MOVEMENT_L:
                turnLeft();
                break;
            case Constants.MOVEMENT_R:
                turnRight();
                break;
            case Constants.MOVEMENT_M:
                advance();
                break;
            default:
                throw new BadMovement("Error! Movement not recognised!");
        }
    }

    /**
     *
     */
    private void turnLeft(){
        switch(position.getOrientation()){
            case Constants.ORIENTATION_WEST:
                position.setOrientation(Constants.ORIENTATION_SOUTH);
                break;
            case Constants.ORIENTATION_EAST:
                position.setOrientation(Constants.ORIENTATION_NORTH);
                break;
            case Constants.ORIENTATION_NORTH:
                position.setOrientation(Constants.ORIENTATION_WEST);;
                break;
            case Constants.ORIENTATION_SOUTH:
                position.setOrientation(Constants.ORIENTATION_EAST);
                break;
        }
        if (debugMode)
            drawPosition();
    }

    /**
     *
     */
    private void turnRight(){
        switch(position.getOrientation()){
            case Constants.ORIENTATION_WEST:
                position.setOrientation(Constants.ORIENTATION_NORTH);
                break;
            case Constants.ORIENTATION_EAST:
                position.setOrientation(Constants.ORIENTATION_SOUTH);
                break;
            case Constants.ORIENTATION_NORTH:
                position.setOrientation(Constants.ORIENTATION_EAST);
                break;
            case Constants.ORIENTATION_SOUTH:
                position.setOrientation(Constants.ORIENTATION_WEST);
                break;
        }
        if (debugMode)
            drawPosition();
    }

    /**
     * Method to move forward the robot
     */
    private void advance(){
        // Acoording to it orientation..
        switch(position.getOrientation()){
            case Constants.ORIENTATION_WEST:
                // It will advance to the left only if it is inside the room limits
                if (canAdvanceLeft()){
                    position.setX(position.getX()-1);
                    if (debugMode)
                        drawPosition();
                }
                break;
            case Constants.ORIENTATION_EAST:
                // It will advance to the right only if it is inside the room limits
                if(canAvanceRight()){
                    position.setX(position.getX()+1);
                    if (debugMode)
                        drawPosition();
                }
                break;
            case Constants.ORIENTATION_NORTH:
                // It will advance to up only if it is inside the room limits
                if(canAdvanceUp()){
                    position.setY(position.getY()+1);
                    if (debugMode)
                        drawPosition();
                }
                break;
            case Constants.ORIENTATION_SOUTH:
                // It will advance to down only if it is inside the room limits
                if(canAdvanceDown()){
                    position.setY(position.getY()-1);
                    if (debugMode)
                        drawPosition();
                }
                break;
        }
    }

    /**
     * Function that returns if the robot cand advance to the LEFT regarding to it position
     * @return
     */
    private boolean canAdvanceLeft(){
        return position.getX()>0;
    }

    /**
     * Function that returns if the robot cand advance to the RIGHT regarding to it position
     * @return
     */
    private boolean canAvanceRight(){
        return position.getX()< room.getDimX();
    }

    /**
     * Function that returns if the robot cand advance UP regarding to it position
     * @return
     */
    private boolean canAdvanceUp(){
        return position.getY()< room.getDimY();
    }

    /**
     * Function that returns if the robot cand advance DOWN regarding to it position
     * @return
     */
    private boolean canAdvanceDown(){
        return position.getY()>0;
    }

    /**
     * Function to know if the robot is inside the dimensions of the room
     * @return
     */
    public boolean isInsideRoom(){
        return (position.getX()>=0 && position.getX()<= room.getDimX() && position.getY()>=0 && position.getY()<= room.getDimY());
    }

    @Override
    public String toString() {
        return position.getX() + Constants.ESPACE + position.getY() + Constants.ESPACE + position.getOrientation();
    }
}