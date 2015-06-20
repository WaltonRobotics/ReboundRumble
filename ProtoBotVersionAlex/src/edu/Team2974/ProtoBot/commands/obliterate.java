package edu.Team2974.ProtoBot.commands;

public class obliterate extends CommandBase {

    public obliterate() {
        requires(conveyor);
    }

    protected void initialize() {
    }

    protected void execute() {
//        System.out.println("SensorA: " + conveyor.getA());
//        System.out.println("SensorB: " + conveyor.getB());
//        System.out.println("SensorC: " + conveyor.getC());
          System.out.println("Ball count: " + conveyor.getTotalBalls());
        System.out.println("\n");
        conveyor.obliterate();
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