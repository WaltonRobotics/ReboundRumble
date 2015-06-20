/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.PeachTreeCode.commands;

import edu.team2974.PeachTreeCode.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.networktables.NetworkListener;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

/**
 *
 * @author team2974
 */
public class AimAutonomous extends CommandBase implements NetworkListener
{

    private RobotLogger logger;

    public AimAutonomous() {
        logger = RobotLogger.getInstance();
        logger.logMessage("Auto Aim Loop Started");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(turret);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Watchdog.getInstance().feed();
//        try {
//            //SmartDashboard.putDouble("AutoAim Power", direction);
//            //RobotLogger.getInstance().logMessage("AutoAim Power: "+ direction);
//            if (OI.shooterRotate.getBoolean("running")) {
//                //we want to inverse somewhere else eventually
//                double direction = OI.shooterRotate.getDouble("direction");
//                turret.turn(-direction);
//            } else {
//                turret.stopRotation();
//            }
//        } catch (NetworkTableKeyNotDefined ex) {
//        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        //turret.stopRotation();
        logger.logMessage("Auto Aim Loop Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    public void valueChanged(String key, Object value) {
        try {
            Boolean bval = (Boolean) value;
            //if (OI.shooterRotate.getBoolean("running")) {
            if (bval.booleanValue()) {
                //we want to inverse somewhere else eventually
                double direction = 0.0;
                try {
                    direction = OI.shooterRotate.getDouble("direction");
                } catch (NetworkTableKeyNotDefined ex) {
                    logger.logMessage("no value for direction in AimAutonomous, defaulting to 0.0");
                    logger.logException(ex);
                } catch (IllegalArgumentException e) {
                    logger.logMessage("NULL for direction in AimAutonomous, defaulting to 0.0");
                    logger.logException(e);
                }
                turret.turn(-direction);
            } else {
                turret.stopRotation();
            }
        } catch (ClassCastException e) {
            logger.logMessage("value for running was not a boolean!");
        }
    }

    public void valueConfirmed(String key, Object value) {
    }
}