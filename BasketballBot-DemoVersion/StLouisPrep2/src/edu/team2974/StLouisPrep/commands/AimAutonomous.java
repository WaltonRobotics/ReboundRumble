/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

/**
 *
 * @author team2974
 */
public class AimAutonomous extends CommandBase
{

    private RobotLogger logger;

    public AimAutonomous() {
        logger = RobotLogger.getInstance();
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(turret);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        logger.logMessage("Auto Aim Loop Started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Watchdog.getInstance().feed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /*
         * since isFinished always returns false, this command will not end 
         * until it is interrupted
         */
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
        /*
         * Calling end in interrupted() will make sure the end() logic 
         * will always execute.
         */
        end();
    }

    /*
     * this method will only be called if the power (direction) the turret
     * should be running has changed
     */
    public void valueChanged(String key, Object value) {
        try {
            
            boolean running = false;
            
            /*
             * checks to see whether auto aim should be running from the
             * shooterRotate networktable. If there is an exception, running
             * will default to false.
             */
            try {
                    running = OI.shooterRotate.getBoolean("running");
                } catch (NetworkTableKeyNotDefined ex) {
                    logger.logMessage("no value for running in AimAutonomous, defaulting to false");
                    logger.logException(ex);
                } catch (IllegalArgumentException e) {
                    logger.logMessage("NULL for running in AimAutonomous, defaulting to false");
                    logger.logException(e);
                }
           
            /*
             * It will change the power the turret is rotating at if running is 
             * true.
             */
            if (running) {
                Double dval = (Double)value;
                double direction = dval.doubleValue();
                
                turret.turn(-direction);
            } 
            /*
             * stops the turret if running is false.
             */
            else {
                turret.stopRotation();
            }
        } catch (ClassCastException e) {
            logger.logMessage("value for running was not a boolean!");
        }
    }

    public void valueConfirmed(String key, Object value) {
    }
}