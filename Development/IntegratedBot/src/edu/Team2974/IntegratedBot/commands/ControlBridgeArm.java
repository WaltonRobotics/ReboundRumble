package edu.Team2974.IntegratedBot.commands;

public class ControlBridgeArm extends CommandBase {

    private static final int UP = 1, DOWN = -1;
    public int direction = 0;

    public ControlBridgeArm(int way) {
        direction = way;
        requires(bridgeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (direction == DOWN) {
            bridgeArm.armDown();
        } else if (direction == UP) {
            bridgeArm.armUp();
        } else {
            bridgeArm.armOFF();
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
