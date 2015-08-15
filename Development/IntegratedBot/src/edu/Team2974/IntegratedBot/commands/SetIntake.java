package edu.Team2974.IntegratedBot.commands;

public class SetIntake extends CommandBase {

    private static final int INWARD = 1;
    private static final int STOP = 0;
    private static final int OUTWARD = -1;
    private int setting;

    public SetIntake(int s) {
        requires(intake);
        setting = s;
    }

    protected void initialize() {
    }

    protected void execute() {
        if (setting == INWARD) {
            intake.setIntakeInward();
        } else if (setting == STOP) {
            intake.setIntakeOFF();
        } else if (setting == OUTWARD) {
            intake.setIntakeOutward();
        } else {
            System.out.print("Error in command: SetIntake and/or method: execute");
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
