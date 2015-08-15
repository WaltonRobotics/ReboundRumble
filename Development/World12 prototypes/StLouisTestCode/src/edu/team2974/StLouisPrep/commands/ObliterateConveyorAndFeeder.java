package edu.team2974.StLouisPrep.commands;

public class ObliterateConveyorAndFeeder extends CommandBase {

    public ObliterateConveyorAndFeeder() {
        requires(conveyor);
        requires(feeder);
    }

    protected void initialize() {
    }

    protected void execute() {
        //turns off the conveyors
        conveyor.obliterate();
        feeder.turnOffFeeder();
        
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
//private double updatedSpeed;
//    private double lastSpeed;