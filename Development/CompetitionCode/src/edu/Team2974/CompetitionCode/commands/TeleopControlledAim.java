/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

/**
 *
 * @author Chris
 */
public class TeleopControlledAim extends CommandBase {
    
    private double speed;
    
    public TeleopControlledAim(double s)
    {
        speed =-s;
        requires(turret);
    }

    protected void initialize() {
    }

    protected void execute() {
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
