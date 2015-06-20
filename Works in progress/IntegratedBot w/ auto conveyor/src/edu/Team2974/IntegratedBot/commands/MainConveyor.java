package edu.Team2974.IntegratedBot.commands;

import edu.Team2974.IntegratedBot.RobotMain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MainConveyor extends CommandBase {

    private double updatedSpeed;
    private double lastSpeed;
    private double change;
    private boolean driverControl;
    // private boolean on;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;

    public MainConveyor(double diff, boolean driverTakeOver) {
        change = diff;
        requires(conveyor);
        requires(feeder);
        driverControl = driverTakeOver;
    }

    protected void initialize() {
    }

    protected void execute() {
        //top conveyor code \/ \/ \/
        //SmartDashboard.putDouble("Balls in Conveyor", RobotMain.up.getTotalBalls());
        if (driverControl) {
            feeder.turnFeederForward();
        } else {
            if (!RobotMain.up.getC() && RobotMain.up.getTotalBalls() >= 1) {
                feeder.turnFeederForward();
            } else {
                feeder.turnOffFeeder();
            }
        }
        //top conveyor code ^^^^^

        if (change == 55.0) {
            if (RobotMain.up.getTotalBalls() == 0) {
                updatedSpeed = CONV_ZERO;
                //System.out.println("place 1");
            } else {
                if (!RobotMain.up.getC()) {
                    updatedSpeed = CONV_UP;
                    //System.out.println("place 2");
                } else {
                    if ((RobotMain.up.getTotalBalls() == 2 && !RobotMain.up.getB()) && RobotMain.up.getBetweenAAndB()) {
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 3");
                    } else if (RobotMain.up.getTotalBalls() == 2 && !RobotMain.up.getB() && !RobotMain.up.getBetweenAAndB()) {
                        updatedSpeed = CONV_DOWN;
                        //System.out.println("place 4");
                    } else if (RobotMain.up.getTotalBalls() == 3 && RobotMain.up.getBetweenAAndB()) { //assuming only 1 is between A and B (I think?)
                        updatedSpeed = CONV_UP;
                        //System.out.println("place 5");
                    } else if (RobotMain.up.getTotalBalls() == 3 && !RobotMain.up.getBetweenAAndB() && !RobotMain.up.getB()) { //don't forget to turn off the intake roller for this situtation
                        updatedSpeed = CONV_DOWN;
                        //System.out.println("place 6");
                    } else {
                        updatedSpeed = CONV_ZERO;
                        //System.out.println("place 7");
                    }
                }
            }
            conveyor.setBigConv(-updatedSpeed);
        } else {
            conveyor.setBigConv(change);
        }
    }

    protected boolean isFinished() {
        return (conveyor.getBigConv() == (lastSpeed + updatedSpeed));
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}