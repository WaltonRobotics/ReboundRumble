/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

/**
 *
 * @author team2974
 */
public class AdjustOffSetPower extends CommandBase {
    /**
     * Fields that represent the direction of the change
     */
    public static final int UP =1, DOWN =-1, NULL =0;
    /*
     * the amount the shooter offset will be changed
     */
    private final double CHANGE = .05;
    /**
     * the direction the offset will be changed 
     */
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
            /*
             * if the direction is UP, CHANGE is added to the shooter's offset
             */
            oi.offSet += CHANGE;
        }
        else if(adjustment == DOWN)
        {
            /*
             * if the direction is DOWN, CHANGE is subtracted from the shooter's
             * offset
             */
            oi.offSet -= CHANGE;
        }
        else
        {
            /*
             * if the direction is neither up or down, then the shooter
             * shouldn't be changed.
             */
            oi.offSet = 0;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /*
         * since isFinished() returns true, this command will only be executed
         * once
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
}
