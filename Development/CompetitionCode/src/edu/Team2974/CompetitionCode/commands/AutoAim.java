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
 * @author Walton Robotics
 */
public class AutoAim extends CommandBase {

    public AutoAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(turret);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        try {
            double direction = OI.shooterRotate.getDouble("direction");
            SmartDashboard.putDouble("AutoAim Power", direction);
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}