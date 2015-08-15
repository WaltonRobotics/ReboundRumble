/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.networktables.NetworkListener;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

/**
 *
 * @author team2974
 * 
 * AimAutonomous was our team's answer to finding and centering on a target during the autonomous period
 * Due to the amount of setbacks in the 2012 season THIS CLASS WAS NEVER 
 * IMPLEMENTED during a competition nor during practice. Therefore, before 
 * using this class, please test extensively.
 * 
 * This should resemble AutoAim, which is the teleop controlled aiming. That one
 * DID work. The main differences you should notice is that AimAutonomous does
 * not stop executing until interrupted and that it listens for changes in order
 * to execute its logic.
 */
public class AimAutonomous extends CommandBase implements NetworkListener
{

    //private RobotLogger logger;
    private final int AIM_THRESHOLD=10;

    public AimAutonomous() {
//        logger = RobotLogger.getInstance();
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(turret);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Auto Aim Loop Started");
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
        turret.stopRotation();
        System.out.println("Auto Aim Loop Ended");
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
            
            boolean targetFound = false;
            /*
             * checks to see whether auto aim should be running from the
             * shooterRotate networktable. If there is an exception, running
             * will default to false.
             */
            try {
                    
                //240 is the default Top value of a target. 
                //thus if it equals something else, the camera has found a target
                if(OI.targetTable.getDouble("Top")!=240)
                {
                    targetFound=true;
                }
                } catch (NetworkTableKeyNotDefined ex) {
                    System.out.println("no value for running in AimAutonomous, defaulting to false");
//                    logger.logException(ex);
                } catch (IllegalArgumentException e) {
                    System.out.println("NULL for running in AimAutonomous, defaulting to false");
//                    logger.logException(e);
                }
           
            /*
             * if running is true, assigns the power (direction) to the 
             * motor running the turret, and puts direction on SD
             */
            if(targetFound)
            {
                System.out.println("target found");
                double leftX;

                leftX = OI.targetTable.getDouble("Left");
                double rightX = OI.targetTable.getDouble("Right");
                double centerX= (leftX+rightX)/2;
                
                //160 is the center of the camera. IF YOU HAVE A DIFFERENT SIZED
                //CAMERA CHANGE THIS
                //320x240
                int differenceCenter = 160 - (int)centerX;
                if(Math.abs(differenceCenter)< AIM_THRESHOLD)
                {
                     /*
                     * ready to shoot, stop rotating
                     */
                    System.out.println("stopping turret");
                    turret.stopRotation();

                } else {
                    /*
                     * turn towards target
                     */
                    double power = rotate(differenceCenter);
                    turret.turn(power);
                    System.out.println("turret power: "+power);
                }
            }
            else
            {
                turret.stopRotation();
                System.out.println("no target found");
            }
        } catch (NetworkTableKeyNotDefined ex) {
            System.out.println("value for running was not a boolean!");
        }
    }

    public void valueConfirmed(String key, Object value) {
    }
    
    /*
     * @param dx is the offset in pixels from the target's center to the center of the camera's view
     * The thresholds for the different powers were found through extensive testing
     * as the robot changes, so too should these values.
     * 
     * Note: if changes are made here update AutoAim logic as well
     */
    private double rotate(int dx) {
        if (dx > 0) {
            if(dx>50)
            {
             return  .35;
            } else if(dx > 35){
                
                return  dx/200;
            }
            else
            {
                return  .175;
            }
        } else if (dx < 0) {
            if (dx < -50) {
                return  -.35;
            } else if(dx < -35){
                
                return  dx/200;
            }
            else
            {
                return  -.175;
            }
        } else {
            return  0;
        }
    }
}