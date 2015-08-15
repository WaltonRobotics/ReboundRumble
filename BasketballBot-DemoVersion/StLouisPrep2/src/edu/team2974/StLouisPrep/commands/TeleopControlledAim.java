/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.robot.util.RobotLogger;

/**
 *
 * @author Chris
 */
public class TeleopControlledAim extends CommandBase {
    
    private double speed;
    
    /**
     * 
     * @param s the power given to the turret's motor
     */
    public TeleopControlledAim(double s)
    {
        speed =-s;
        requires(turret);
    }

    protected void initialize() {
    }

    protected void execute() {
        RobotLogger.getInstance().logMessage("Turret Speed: "+speed);
        turret.teleopControlledAim(speed);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
