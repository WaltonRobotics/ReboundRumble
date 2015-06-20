/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Watchdog;
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
    
    /**
     * The speed the shooter will default to if no target is found.
     */
    private double defaultSpeed = -8.8;
    /**
     * The speed added to the bottom wheel to give back spin to the ball
     */
    private double difference = 2.38;
    private boolean targetFound = false;
    
    public AutoShot()
    {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        Watchdog.getInstance().feed();

            try {
                /*
                 * gets the base voltage via a helper method but grabs the center y of
                 * the current target as a parameter the reason for casting it into a double here is a matter or personal
                 * preference, I think the helper method is easier to read without having to worry about casting
                 */
                double topY = OI.targetTable.getDouble("Top");
                if(topY!=240)
                {
                    targetFound = true;
                }
                
                if(targetFound)
                {
                    double botY = OI.targetTable.getDouble("Bottom");
                    double centerY = (botY+topY)/2;
                    //constant off set
                    double baseVoltage = -getVoltage((2*centerY)-16);
                    System.out.println("Target Found, setting shooter speed using equation");
                    /*
                     * difference is set to the same value that Gil was using to 
                     * collect the originial data offset begins at 0 every reboot,
                     * but is incremented or decremented by driver control
                     */
                    double offSet = oi.offSet;
                    shot.dummySetShooterWheels(baseVoltage-offSet, baseVoltage-difference-offSet);
                }
            } catch (NetworkTableKeyNotDefined ex) {

                ex.printStackTrace();
            }
        
    }

    protected boolean isFinished() {
        
        return true;
    }

    protected void end() {
        //reverts to default speed if a traget was not found
        if(!targetFound)
        {
            System.out.println("Reverting to Default Speed in Auto Shot");
            shot.dummySetShooterWheels(defaultSpeed-oi.offSet, defaultSpeed-difference-oi.offSet);
        }
    }

    protected void interrupted() {
        end();
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
        voltage = .00023658*(targetCY*targetCY) - (.020*targetCY) +9.0;//9.4
//        SmartDashboard.putDouble("Auto Shooter Voltage", voltage);
        return voltage;
    }
}
