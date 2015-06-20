package edu.team2974.PeachTreeCode.commands;

public class ZeroShooterJaguars extends CommandBase {

    public ZeroShooterJaguars() {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        shot.zeroShooterJaguars();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}