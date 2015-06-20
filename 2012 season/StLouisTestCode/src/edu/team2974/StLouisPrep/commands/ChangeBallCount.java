package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;

/**
 *
 * @author Chris
 */
public class ChangeBallCount extends CommandBase {

    private int change;


    /**
     * Limits the possible ball count to be between 0 and 3
     * @param c the amount to change the ball count
     */
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
