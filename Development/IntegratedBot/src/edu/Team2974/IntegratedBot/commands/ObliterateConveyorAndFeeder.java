package edu.Team2974.IntegratedBot.commands;

public class ObliterateConveyorAndFeeder extends CommandBase {

    public ObliterateConveyorAndFeeder() {
        requires(conveyor);
        requires(feeder);
    }

    protected void initialize() {
    }

    protected void execute() {
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