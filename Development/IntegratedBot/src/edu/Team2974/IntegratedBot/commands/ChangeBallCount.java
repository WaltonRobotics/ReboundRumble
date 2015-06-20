package edu.Team2974.IntegratedBot.commands;

import edu.Team2974.IntegratedBot.OI;

/**
 *
 * @author Chris
 */
public class ChangeBallCount extends CommandBase {

    private int change;

    public ChangeBallCount(int c) {
        change = c;
    }

    protected void initialize() {
    }

    protected void execute() {
        OI.ballSensors.balls += change;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    protected boolean isFinished() {
        return true;
    }
}
