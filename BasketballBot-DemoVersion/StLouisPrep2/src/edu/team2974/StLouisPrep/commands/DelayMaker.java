/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Timer;



/**
 *
 * @author Chris
 */
public class DelayMaker extends CommandBase{
    private double delaySeconds;
    /**
     * 
     * @param secs the amount of time in seconds to delay 
     */
    public DelayMaker(double secs)
    {
        delaySeconds = secs;
    }
    protected void initialize() {
    }

    protected void execute() {
        RobotLogger.getInstance().logMessage("Delaying for "+delaySeconds+" seconds");
        Timer.delay(delaySeconds);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
