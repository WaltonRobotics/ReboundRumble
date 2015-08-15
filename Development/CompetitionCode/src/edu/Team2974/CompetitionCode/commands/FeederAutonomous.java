/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author team2974
 */
public class FeederAutonomous extends CommandBase {
    private Watchdog puppy;
    private boolean finished = false;

    public FeederAutonomous() {
        puppy = Watchdog.getInstance();
        requires(feeder);
    }

    protected void initialize() {
    }
    
    boolean alldone = false;

    protected void execute() {
        //top conveyor code \/ \/ \/
       alldone=false;
       puppy.feed();
       while(!isFinished())
       {
            if (!oi.ballSensors.getC() && oi.ballSensors.getTotalBalls() >= 1) {
                feeder.turnFeederForward();
            } else {
                feeder.turnOffFeeder();
            }
       }
       alldone = true;
    }

    protected boolean isFinished() {
        return finished;
    }
    
    boolean isAllDone() {
        return alldone;
    }

    protected void end() {
        finished = true;
        while (!isAllDone()) {
            //burn and churn
        }
        feeder.turnOffFeeder();
        RobotLogger.getInstance().logMessage("Finishing Auto Feeder");
    }

    protected void interrupted() {
        end();
    }
}