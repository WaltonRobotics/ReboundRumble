/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author team2974
 */
public class FeederAutonomous extends CommandBase
{

    private Watchdog puppy;

    /**
     * normal feeder logic command but will only end when interrupted and no
     * teleop control
     */
    public FeederAutonomous() {
        
        puppy = Watchdog.getInstance();
        requires(feeder);
    }

    protected void initialize() {
        //RobotLogger.getInstance().logMessage("Starting Shooter Feeder Loop");
    }

    protected void execute() {
        //top conveyor code \/ \/ \/;
        puppy.feed();
        //the feeder will turn on if there is a ball in the system but no ball at C
        if (!OI.ballSensors.getC() && OI.ballSensors.getTotalBalls() >= 1) {
            feeder.turnFeederForward();
        } else {
            feeder.turnOffFeeder();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        feeder.turnOffFeeder();
        //RobotLogger.getInstance().logMessage("Finished Shooter Feeder Loop");
    }

    protected void interrupted() {
        end();
    }
}