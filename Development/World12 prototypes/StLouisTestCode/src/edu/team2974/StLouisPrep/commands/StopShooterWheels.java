package edu.team2974.StLouisPrep.commands;

public class StopShooterWheels extends CommandBase {

    public StopShooterWheels() {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        shot.stopShooterWheels();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}