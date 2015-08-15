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
 */
public class AutoAim extends CommandBase {

    private final int AIM_THRESHOLD = 5;
    
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
            if (OI.targetFound) {
                /*
                 * if running is true, assigns the power (direction) to the 
                 * motor running the turret, and puts direction on SD
                 */
                //320x240
                System.out.println("target found");
                int differenceCenter = 160 - (int)OI.targetInfo[0];
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
    }
    
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