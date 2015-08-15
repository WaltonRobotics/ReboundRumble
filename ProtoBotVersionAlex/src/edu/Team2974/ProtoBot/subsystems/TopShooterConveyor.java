package edu.Team2974.ProtoBot.subsystems;

import edu.Team2974.ProtoBot.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TopShooterConveyor extends Subsystem {

    private Relay feederConv;

    protected void initDefaultCommand() {
    }

    public TopShooterConveyor() {
        feederConv = new Relay(RobotMap.shooterSpikeConveyor);
    }

    public void turnOffFeeder() {
        feederConv.set(Relay.Value.kOff);
    }

    public void turnFeederForward() {
        feederConv.set(Relay.Value.kForward);
    }

    public void turnFeederReverse() {
        feederConv.set(Relay.Value.kReverse);
    }
}
