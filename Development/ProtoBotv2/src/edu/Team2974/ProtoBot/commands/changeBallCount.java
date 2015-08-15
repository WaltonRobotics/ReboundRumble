package edu.Team2974.ProtoBot.commands;

public class changeBallCount extends CommandBase {

    public double original = 0;
    public double change = 0;

    public changeBallCount(int moreLess) {
        requires(conveyor);
        change = moreLess;
    }

    protected void initialize() {
    }

    protected void execute() {
        original = conveyor.getTotalBalls();
        conveyor.changeTotalBallCount(change);
    }

    protected boolean isFinished() {
        return (conveyor.getTotalBalls() == (original + change));
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
