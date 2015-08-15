/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author team2974
 */
public class MainConveyorAutonomous extends CommandBase
{

    private double updatedSpeed;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;
    public Watchdog puppy;

    public MainConveyorAutonomous() {
        puppy = Watchdog.getInstance();
        requires(conveyor);

    }

    protected void initialize() {
        RobotLogger.getInstance().logMessage("Starting Main Conveyor loop");
    }

    protected void execute() {
        //top conveyor code \/ \/ \/
        puppy.feed();
        int ballCount = OI.ballSensors.getTotalBalls();
        if (ballCount == 0) {
//        if (OI.ballSensors.getTotalBalls() == 0) {
            updatedSpeed = CONV_ZERO;
            //System.out.println("place 1");
        } else {
            if (!OI.ballSensors.getC()) {
                updatedSpeed = CONV_UP;
                //System.out.println("place 2");
            } else {
                if ((ballCount == 2 && !OI.ballSensors.getB()) 
                        && OI.ballSensors.getBetweenAAndB()) {
//                if ((OI.ballSensors.getTotalBalls() == 2 && !OI.ballSensors.getB()) && OI.ballSensors.getBetweenAAndB()) {
                    updatedSpeed = CONV_UP;
                    //System.out.println("place 3");
                } else if (ballCount == 2 && !OI.ballSensors.getB() 
                        && !OI.ballSensors.getBetweenAAndB()) {
//                } else if (OI.ballSensors.getTotalBalls() == 2 && !OI.ballSensors.getB() && !OI.ballSensors.getBetweenAAndB()) {
                    updatedSpeed = CONV_DOWN;
                    //System.out.println("place 4");
                } else if (ballCount == 3 && OI.ballSensors.getBetweenAAndB()) {
                    //assuming only 1 is between A and B (I think?)
//                } else if (OI.ballSensors.getTotalBalls() == 3 && OI.ballSensors.getBetweenAAndB()) { //assuming only 1 is between A and B (I think?)
                    updatedSpeed = CONV_UP;
                    //System.out.println("place 5");
                } else if (ballCount == 3 && !OI.ballSensors.getBetweenAAndB() 
                        && !OI.ballSensors.getB()) {
                    //don't forget to turn off the intake roller for this situtation
//                } else if (OI.ballSensors.getTotalBalls() == 3 && !OI.ballSensors.getBetweenAAndB() && !OI.ballSensors.getB()) { //don't forget to turn off the intake roller for this situtation
                    updatedSpeed = CONV_DOWN;
                    //System.out.println("place 6");
                } else {
                    updatedSpeed = CONV_ZERO;
                    //System.out.println("place 7");
                }
            }
        }
        RobotLogger.getInstance().logMessage("Conveyor speed: " + updatedSpeed);
        conveyor.setBigConv(-updatedSpeed);

    }

    protected boolean isFinished() {
        //will continue executing until interrupted
        return false;
    }

    protected void end() {
        conveyor.setBigConv(CONV_ZERO);
        RobotLogger.getInstance().logMessage("Finished Main Conveyor Loop");
    }

    protected void interrupted() {
        end();
    }
}
