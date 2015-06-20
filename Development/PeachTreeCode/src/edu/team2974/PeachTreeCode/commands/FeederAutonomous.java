/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.PeachTreeCode.commands;

import edu.team2974.PeachTreeCode.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author team2974
 */
public class FeederAutonomous extends CommandBase
{

    private Watchdog puppy;

    public FeederAutonomous() {
        RobotLogger.getInstance().logMessage("Starting Shooter Feeder Loop");
        puppy = Watchdog.getInstance();
        requires(feeder);
    }

    protected void initialize() {
    }

    protected void execute() {
        //top conveyor code \/ \/ \/;
        puppy.feed();
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
        RobotLogger.getInstance().logMessage("Finished Shooter Feeder Loop");
    }

    protected void interrupted() {
        end();
    }
}