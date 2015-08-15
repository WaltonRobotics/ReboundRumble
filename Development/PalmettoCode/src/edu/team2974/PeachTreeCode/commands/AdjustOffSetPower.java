/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.PeachTreeCode.commands;

/**
 *
 * @author team2974
 */
public class AdjustOffSetPower extends CommandBase {
    public static final int UP =1, DOWN =-1, NULL =0;
    private final double CHANGE = .05;
    private int adjustment;
    public AdjustOffSetPower(int a) {
        adjustment = a;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(adjustment == UP)
        {
            oi.offSet += CHANGE;
        }
        else if(adjustment == DOWN)
        {
            oi.offSet -= CHANGE;
        }
        else
        {
            oi.offSet = 0;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
