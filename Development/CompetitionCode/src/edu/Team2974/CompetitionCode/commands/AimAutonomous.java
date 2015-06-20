/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.CompetitionCode.OI;
import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author team2974
 */
public class AimAutonomous extends CommandBase {
    private boolean finished = false;
    private boolean allDone = false;
    public AimAutonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(turret);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       
        while(!isFinished())
        {
            try {
                double direction = OI.shooterRotate.getDouble("direction");
                SmartDashboard.putDouble("AutoAim Power", direction);
                RobotLogger.getInstance().logMessage("AutoAim Power: "+ direction);
                if (OI.shooterRotate.getBoolean("running")) {
                    //we want to inverse somewhere else eventually
                    turret.turn(-direction);
                } else {
                    turret.stopRotation();
                }
            } catch (NetworkTableKeyNotDefined ex) {
                RobotLogger.getInstance().logMessage(ex.getMessage());
            }
        }
        allDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }
    
    protected boolean isAllDone()
    {
        return allDone;
    }

    // Called once after isFinished returns true
    protected void end() {
        finished = true;
        while (!isAllDone()) {
            //burn and churn
        }
        turret.stopRotation();
        RobotLogger.getInstance().logMessage("Aim Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}