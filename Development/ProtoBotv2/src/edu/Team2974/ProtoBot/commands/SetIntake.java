package edu.Team2974.ProtoBot.commands;

public class SetIntake extends CommandBase {
private static final int INWARD = 1;
private static final int STOP = 0;
private static final int OUTWARD = -1;
private int setting;
    public SetIntake(int s) {
        requires(intake);
        setting =s;
    }

    protected void initialize() {
    }

    protected void execute() {
        if(setting == INWARD)
        {
            intake.setIntakeInward();
        }
        if(setting == STOP)
        {
            intake.setIntakeOFF();
        }
        if(setting== OUTWARD)
        {
            intake.setIntakeOutward();
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
