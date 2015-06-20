/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ShooterPrototype.commands;

/**
 *
 * @author Walton Robotics
 */
public class SetShooterWheels extends CommandBase
{
    //double topSpeed;
    //double bottomSpeed;
    
    public SetShooterWheels() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shot);
        requires(powerCalc);
//        this.topSpeed = topSpeed;
//        this.bottomSpeed = bottomSpeed;
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
        shot.setShooterWheels(powerCalc.getPower(r),
                powerCalc.getPower(r));
    }

    /**
     * Make this return true when this Command no longer needs to run execute().
     * When the timeout value is reached, we exit.
     */
    protected boolean isFinished() {
        return false;
            
        
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
