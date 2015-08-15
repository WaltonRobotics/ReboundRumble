package edu.Team2974.conveyor.commands;

public class Intake extends CommandBase {
private boolean forward;
    public Intake(boolean forward1) {
        requires(conveyor);
        forward=forward1;
    }

    protected void initialize() {
    }

    protected void execute() {
//        if (conveyor.getTotalBalls() == 3) {
//            if (conveyor.getA()) {
//                conveyor.setIntake(0);
//            } else {
//                conveyor.setIntake(-1);
//            }
//        } else {
//            conveyor.setIntake(1);
//        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
