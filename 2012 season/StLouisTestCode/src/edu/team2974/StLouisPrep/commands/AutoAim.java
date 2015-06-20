/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Walton Robotics
 * This class was designed so that given a target, a driver can hold down a 
 * button and the turret would center on the target freeing the driver
 * from having to line up shots.
 * 
 * This class has been proven to work, however it does rely on network tables
 * shared between the robot and the laptop. If this communication does not work
 * neither will AutoAim
 */
public class AutoAim extends CommandBase {

    private final int AIM_THRESHOLD = 10;
    
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
            boolean targetFound = false;
            
            //240 is the default Top value of a target. 
            //thus if it equals something else, the camera has found a target
            if(OI.targetTable.getDouble("Top")!=240)
            {
                targetFound=true;
            }
            
            if (targetFound) {
                /*
                 * if running is true, assigns the power (direction) to the 
                 * motor running the turret, and puts direction on SD
                 */
                
                System.out.println("target found");
                double leftX = OI.targetTable.getDouble("Left");
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

                    /*
                     * do other things TODO: shoot here
                     */
                } else {
                    /*
                     * turn towards target
                     */
                    double power = rotate(differenceCenter);
                    turret.turn(power);
                    System.out.println("turret power: "+power);
                }
            } else {
                turret.stopRotation();
            }
        } catch (NetworkTableKeyNotDefined ex) {
            ex.printStackTrace();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /*
         * since auto aim is tied to a whileheld button, the command should only
         * execute once.
         */
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //end logic will always be called
        end();
    }
    
    /*
     * @param dx is the offset in pixels from the target's center to the center of the camera's view
     * The thresholds for the different powers were found through extensive testing
     * as the robot changes, so too should these values.
     * 
     * Note: if changes are made in this logic, update autonomous logic as well
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