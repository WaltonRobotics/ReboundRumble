package edu.team2974.StLouisPrep.subsystems;

import edu.team2974.StLouisPrep.RobotMap;
import edu.team2974.StLouisPrep.commands.GetFeederBalls;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TopShooterConveyor extends Subsystem {

    private Relay feederConv;


    protected void initDefaultCommand() {
        setDefaultCommand(new GetFeederBalls(0.0,true));

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
    
    public void setDefault(Command c)
    {
        setDefaultCommand(c);
    }
}
