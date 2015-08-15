package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.robot.util.RobotLogger;

public class DummySetShooterWheels extends CommandBase {

    public DummySetShooterWheels() {
        requires(shot);
    }

    protected void initialize() {
    }

    protected void execute() {
        double difference = oi.getTopWheels()*12.0;
        double baseSpeed = oi.getBotWheels()*12.0;
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
