/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Chris
 */

/*
*I didn't make power calculator its own subsystem because I couldn't 
*think of any other part of the robot that would need the calculations
*/
public class AutoShot extends CommandBase {
    
    /*
     * new network table
     * needed information on the current target will be stored in here
     */
    
    public AutoShot()
    {
        /*
        *For now I only want to require the shooter jags, I need to set them with the correct voltage
        *However I'm not requiring the feeder because I want to keep when the ball goes into the wheels
        *Completely under driver control for now, I'll either need to modify this command later or add
        *Another command that has logic that moves the balls into the wheels autonomously
        */
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        try {
            SmartDashboard.putBoolean("Target Found", oi.targetTable.getBoolean("Target Found"));
            if(oi.targetTable.getBoolean("Target Found"))
            {
                /*
                 * gets the base voltage via a helper method but grabs the center y of
                 * the current target as a parameter the reason for casting it into a double here is a matter or personal
                 * preference, I think the helper method is easier to read without having to worry about casting
                 */
                double centerY = (double)oi.targetTable.getInt("Center Y");
                //constant off set
                double baseVoltage = -getVoltage(centerY-16);
                RobotLogger.getInstance().logMessage("CY of target is: "+ centerY);
                RobotLogger.getInstance().logMessage("Base Voltage is: "+ baseVoltage);
                /*
                 * difference is set to the same value that Gil was using to collect the originial data
                 */
                double difference = 2.38;
                double offSet = oi.offSet;
                shot.dummySetShooterWheels(baseVoltage-offSet, baseVoltage-difference-offSet);
            }
        } catch (NetworkTableKeyNotDefined ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
        
        
    }

    protected boolean isFinished() {
        
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    /*
     * This is where the power calculation occurs
     * The equation used is a Quadratic regression relating the base voltage
     * to the center y of the target. The equation was written by Gil and the curve
     * was made from 4 data points the equation will likely need tweaking as conditions change
     * @param the center y of the current target
     * @return the amount of voltage to set the jags to so that the ball will hit the target
     */
    protected double getVoltage(double targetCY)
    {
        double voltage;
//        voltage = .00023658*(targetCY*targetCY) - (.027474*targetCY) +9.5454;
        voltage = .00023658*(targetCY*targetCY) - (.028*targetCY) +9.9;
        SmartDashboard.putDouble("Auto Shooter Voltage", voltage);
        return voltage;
    }
}
