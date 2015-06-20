package edu.Team2974.ProtoBot.commands;

public class ShootOutBalls extends CommandBase {

    boolean feeding;
    int speed;

    public ShootOutBalls(boolean feeds) {
        requires(feeder);
        feeding = feeds;
    }

    protected void initialize() {
    }

    protected void execute() {
        if (feeding) {
            feeder.turnFeederForward();
            speed = 1;
        } else {
            feeder.turnOffFeeder();
            speed = 0;
        }
    }

    protected boolean isFinished() {
        if (oi.leftButton1.get() && speed == 1) {
            return true;
        } else if (!oi.leftButton1.get() && speed == 0) {
            return true;
        } else {
            return false;
        }
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}