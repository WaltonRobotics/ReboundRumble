/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.conveyor.commands;

/**
 *
 * @author Walton Robotics
 */
public class StartBigConveyer extends CommandBase {

    private double update;
    private double previousSpeed;

    public StartBigConveyer(double up) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
        update = up;
        System.out.println("Space 5: StartBigConveyer("+update+") constructor");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("StartBigConveyor("+update+") initialize(d)!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        previousSpeed = conveyor.getBigConveyorSpeed();
        conveyor.updateBigConveyor(update);
        System.out.print("Executing class: startBigConveyer("+update+")");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean ended;
        ended= (conveyor.getBigConveyorSpeed() == (previousSpeed + update));
        if (!ended) {
            System.out.println("conveyer("+update+") finished: "+ended);
        }
        return ended;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("conveyer("+update+") endING");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("conveyer("+update+") was interrupted");
    }
}