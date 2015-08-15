package edu.team2974.StLouisPrep.commands;

public class ControlBridgeArm extends CommandBase {

    private static final int UP = 1, DOWN = -1;
    
    public int direction = 0;

    /**
     * 
     * @param way an int that determines if the bridge arm is told to go up
     * down, or stop.
     */
    public ControlBridgeArm(int way) {
        direction = way;
        requires(bridgeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        
//        if (direction == DOWN && bridgeArm.getPosition() > .98) {
//            bridgeArm.armDown();
//        } else if (direction == UP && bridgeArm.getPosition() < 3.0) {
//            bridgeArm.armUp();
//        } else {
//            bridgeArm.armOFF();
//        }
        if (direction == DOWN ) {
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
