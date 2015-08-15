/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;


/**
 *
 * @author team2974
 */
public class MainConveyorAutonomous extends CommandBase {
    private double updatedSpeed;
    private boolean finished = false;
    private boolean allDone = false;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;
    public Watchdog puppy;

    public MainConveyorAutonomous() {
        puppy = Watchdog.getInstance();
        requires(conveyor);
    }

    protected void initialize() {
    }

    protected void execute() {
        //top conveyor code \/ \/ \/
        puppy.feed();
        while(!isFinished())  
        {
            if (oi.ballSensors.getTotalBalls() == 0) {
                updatedSpeed = CONV_ZERO;
                //System.out.println("place 1");
            } else {
                if (!oi.ballSensors.getC()) {
                    updatedSpeed = CONV_UP;
                    //System.out.println("place 2");
                } else {
                    if ((oi.ballSensors.getTotalBalls() == 2 && !oi.ballSensors.getB()) && oi.ballSensors.getBetweenAAndB()) {
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 3");
                    } else if (oi.ballSensors.getTotalBalls() == 2 && !oi.ballSensors.getB() && !oi.ballSensors.getBetweenAAndB()) {
                        updatedSpeed = CONV_DOWN;
                        //System.out.println("place 4");
                    } else if (oi.ballSensors.getTotalBalls() == 3 && oi.ballSensors.getBetweenAAndB()) { //assuming only 1 is between A and B (I think?)
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 5");
                    } else if (oi.ballSensors.getTotalBalls() == 3 && !oi.ballSensors.getBetweenAAndB() && !oi.ballSensors.getB()) { //don't forget to turn off the intake roller for this situtation
                        updatedSpeed = CONV_DOWN;
                        //System.out.println("place 6");
                    } else {
                        updatedSpeed = CONV_ZERO;
                        //System.out.println("place 7");
                    }
                }
            }
            conveyor.setBigConv(-updatedSpeed);
        }
        allDone = true;
    }

    protected boolean isFinished() {
        return finished;
    }
    
    protected boolean isAllDone()
    {
        return allDone;
    }
    
    protected void end() {
        finished = true;
        while (!isAllDone()) {
            //burn and churn
        }
        conveyor.setBigConv(CONV_ZERO);
        RobotLogger.getInstance().logMessage("Finished Auto Conveyor");
    }

    protected void interrupted() {
        end();
    }
 }
