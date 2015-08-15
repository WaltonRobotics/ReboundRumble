package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.CompetitionCode.OI;

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
        if ((OI.ballSensors.balls) > 3) {
            OI.ballSensors.balls=3;
        }
        if ((OI.ballSensors.balls) < 0) {
            OI.ballSensors.balls=0;
        }
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    protected boolean isFinished() {
        return true;
    }
}
