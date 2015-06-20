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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Chris
 */
public class AutoShotAutonomous extends CommandBase implements NetworkListener
{

    /**
     * finished is what isFinished for this Command returns the reason for this
     * is that the intended use of this Command is to set the speeds of the
     * shooter jags to a power that will get the ball into the target goal,
     * however there may not be a target right away which would result in the
     * jags not getting set, so the finished boolean guarentees that the command
     * will continue executing until a target is found and the jag powers are
     * set or until it has timed out (set in the command group) or is
     * interrupted
     */
    private boolean finished = false;
    /**
     * The speed the shooter will default to if no target is found.
     */
    private double defaultSpeed = -8.4;
    /**
     * The speed added to the bottom wheel to give back spin to the ball
     */
    private double difference = 2.38;
    private RobotLogger logger;

    public AutoShotAutonomous() {
        requires(shot);
        logger = RobotLogger.getInstance();
        
    }

    protected void initialize() {
        logger.logMessage("Beginning AutoShotAutonomous");
    }

    protected void execute() {
        Watchdog.getInstance().feed();
    }

    protected boolean isFinished() {

        return finished;
    }

    protected void end() {
        if (!finished) {
            shot.dummySetShooterWheels(defaultSpeed, defaultSpeed - difference);
            logger.logMessage("Not finished in end, so Wheels Set to Default");
        }
        logger.logMessage("AutoShotAutonomous ending");
    }

    protected void interrupted() {
        end();
    }

    /**
     * This is where the power calculation occurs The equation used is a
     * Quadratic regression relating the base voltage to the center y of the
     * target. The equation was written by Gil and the curve was made from 4
     * data points the equation will likely need tweaking as conditions change
     * @param the center y of the current target 
     * @return the amount of voltage to set the jags to so that the ball will hit the target
     */
    protected double getVoltage(double targetCY) {
        double voltage;
//        voltage = .00023658*(targetCY*targetCY) - (.027474*targetCY) +9.5454;
        voltage = .00023658 * (targetCY * targetCY) - (.028 * targetCY) + 9.1;//9.0
        SmartDashboard.putDouble("Auto Shooter Voltage", voltage);
        return voltage;
    }

    public void valueChanged(String key, Object value) {
        try {
            Boolean bval = (Boolean) value;
            boolean foundIt = bval.booleanValue();
            logger.logMessage("Entering value changed");
            SmartDashboard.putBoolean("Target Found", foundIt);
            if (foundIt) {
                /*
                 * gets the base voltage via a helper method but grabs the
                 * center y of the current target as a parameter the reason for
                 * casting it into a double here is a matter or personal
                 * preference, I think the helper method is easier to read
                 * without having to worry about casting the int
                 */
                double centerY = 0.0;
                try {
                    centerY = (double) OI.targetTable.getInt("Center Y");
                } catch (NetworkTableKeyNotDefined ex) {
                    logger.logMessage("Center Y not in table, defaulting to 0.0");
                    logger.logException(ex);
                } catch (IllegalArgumentException e) {
                    logger.logMessage("Center Y not in table, defaulting to 0.0");
                    logger.logException(e);
                }
                /*
                 * constant off set -16 pixels from center y, the reason being
                 * that the the formula was based off data that had the target
                 * 16pixels lower than it actually was
                 */
                double baseVoltage = -getVoltage(centerY - 16);
                logger.logMessage("CY of target is: " + centerY);
                logger.logMessage("Base Voltage is: " + baseVoltage);
                /*
                 * difference is set to the same value that Gil was using to
                 * collect the originial data
                 */

                double offSet = oi.offSet;
                shot.dummySetShooterWheels(baseVoltage - offSet,
                        baseVoltage - difference - offSet);
                //the wheels have been set so finished is set to true
                finished = true;
            }
        } catch (ClassCastException e) {
            logger.logMessage("Target Found value was not a boolean!");
        }
    }

    public void valueConfirmed(String key, Object value) {
    }
}
