package edu.team2974.StLouisPrep.commands;

import edu.team2974.robot.util.RobotLogger;

public class DummySetShooterWheels extends CommandBase {

    public DummySetShooterWheels() {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        
        //multiplying the methods by 12, turns the -1 to 1 scale to the voltage scale
        double difference = oi.getTopWheels()*12.0;
        double baseSpeed = oi.getBotWheels()*12.0;
//        RobotLogger.getInstance().logMessage("Dummy shooter");
//        RobotLogger.getInstance().logMessage("Base Wheel Speed: "+baseSpeed);
//        RobotLogger.getInstance().logMessage("Top difference: "+difference);
        
        shot.dummySetShooterWheels(baseSpeed-difference, baseSpeed);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
