package edu.Team2974.IntegratedBot.commands;

import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetFeederBalls extends CommandBase {

    private double updatedSpeed;
    private double lastSpeed;
    private double change;
    private boolean driverControl;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;
    private Watchdog puppy;

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
            if (!oi.ballSensors.getC() && oi.ballSensors.getTotalBalls() >= 1) {
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