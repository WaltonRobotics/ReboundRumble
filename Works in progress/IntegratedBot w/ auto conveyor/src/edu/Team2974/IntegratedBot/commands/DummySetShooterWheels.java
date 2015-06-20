package edu.Team2974.IntegratedBot.commands;

public class DummySetShooterWheels extends CommandBase {

    public DummySetShooterWheels() {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        shot.dummySetShooterWheels(oi.getTopWheels(), oi.getBotWheels());
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
