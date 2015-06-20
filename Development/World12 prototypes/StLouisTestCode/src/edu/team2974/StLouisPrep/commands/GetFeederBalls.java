package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetFeederBalls extends CommandBase {

    private double updatedSpeed;
    private double lastSpeed;
    private double change;
    private boolean driverControl;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;
    private Watchdog puppy;

    /**
     * 
     * @param diff the direction the feeder will go, either up, down, or off
     * @param driverTakeOver a boolean that controls whether diff determines
     * the feeder's direction. If false, auto code takes over.
     */
    public GetFeederBalls(double diff, boolean driverTakeOver) {
        puppy = Watchdog.getInstance();
        change = diff;
        requires(feeder);
        driverControl = driverTakeOver;
    }

    protected void initialize() {
    }

    protected void execute() {
        //top conveyor code \/ \/ \/
       puppy.feed();
        if (driverControl) {
            if(change == CONV_UP)
            {
                feeder.turnFeederForward();
            }
            else if(change == CONV_DOWN)
            {
                feeder.turnFeederReverse();
            }
            else
            {
                feeder.turnOffFeeder();
            }
        } else {
            //the feeder will turn on if there is a ball in the system but no ball at C
            if (!OI.ballSensors.getC() && OI.ballSensors.getTotalBalls() >= 1) {
                feeder.turnFeederForward();
            } else {
                feeder.turnOffFeeder();
            }
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