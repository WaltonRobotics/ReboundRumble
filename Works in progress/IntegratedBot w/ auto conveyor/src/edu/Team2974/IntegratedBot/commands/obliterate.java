package edu.Team2974.IntegratedBot.commands;

import edu.Team2974.IntegratedBot.RobotMain;

public class obliterate extends CommandBase {

    public obliterate() {
        requires(conveyor);
        requires(feeder);
    }

    protected void initialize() {
    }

    protected void execute() {
        System.out.println("SensorA: " + RobotMain.up.getA());
        System.out.println("SensorB: " + RobotMain.up.getB());
        System.out.println("SensorC: " + RobotMain.up.getC());
          System.out.println("Ball count: " + RobotMain.up.getTotalBalls());
        System.out.println("\n");
        conveyor.obliterate();
        feeder.turnOffFeeder();
        
    }

    protected boolean isFinished() {
        return (conveyor.getBigConv() == 0);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
//private double updatedSpeed;
//    private double lastSpeed;