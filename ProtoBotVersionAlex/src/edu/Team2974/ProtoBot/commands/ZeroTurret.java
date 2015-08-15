package edu.Team2974.ProtoBot.commands;

public class ZeroTurret extends CommandBase {

    public ZeroTurret() {
        requires(turret);
    }

    protected void initialize() {
    }

    protected void execute() {
        turret.stopRotation();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
