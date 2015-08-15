package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MainConveyor extends CommandBase {

    private double updatedSpeed;
    private double change;
    private boolean driverControl;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;
    public Watchdog puppy;

     /**
     * 
     * @param diff the direction the conveyor will go, either up, down, or off
     * @param driverTakeOver a boolean that controls whether diff determines
     * the conveyor's direction. If false, auto code takes over.
     */
    public MainConveyor(double diff, boolean driverTakeOver) {
        puppy = Watchdog.getInstance();
        change = diff;
        requires(conveyor);
        driverControl = driverTakeOver;
    }

    protected void initialize() {
    }

    protected void execute() {
        //top conveyor code \/ \/ \/
        puppy.feed();
        if (driverControl) {
            conveyor.setBigConv(change);
           
        } else {
            if (OI.ballSensors.getTotalBalls() == 0) {
                updatedSpeed = CONV_ZERO;
                //System.out.println("place 1");
            } else {
                if (!OI.ballSensors.getC()) {
                    updatedSpeed = CONV_UP;
                    //System.out.println("place 2");
                } else {
                    if ((OI.ballSensors.getTotalBalls() == 2 && !OI.ballSensors.getB()) && OI.ballSensors.getBetweenAAndB()) {
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 3");
                    } else if (OI.ballSensors.getTotalBalls() == 2 && !OI.ballSensors.getB() && !OI.ballSensors.getBetweenAAndB()) {
                        updatedSpeed = CONV_DOWN;
                        //System.out.println("place 4");
                    } else if (OI.ballSensors.getTotalBalls() == 3 && OI.ballSensors.getBetweenAAndB()) { //assuming only 1 is between A and B (I think?)
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 5");
                    } else if (OI.ballSensors.getTotalBalls() == 3 && !OI.ballSensors.getBetweenAAndB() && !OI.ballSensors.getB()) { //don't forget to turn off the intake roller for this situtation
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
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}