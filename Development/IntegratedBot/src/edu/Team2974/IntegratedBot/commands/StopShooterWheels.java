package edu.Team2974.IntegratedBot.commands;

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