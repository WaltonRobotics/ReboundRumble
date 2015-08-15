/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.ProtoBot.subsystems;

import edu.Team2974.ProtoBot.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Chris
 */
public class ShooterConveyor extends Subsystem{
    private Relay feederConv;
    
    protected void initDefaultCommand() {
    }
    
    public ShooterConveyor()
    {
        feederConv = new Relay(RobotMap.shooterSpikeConveyor);
    }
    
    public void turnOffFeeder()
    {
        feederConv.set(Relay.Value.kOff);
    }
    
    public void turnFeederForward()
    {
        feederConv.set(Relay.Value.kForward);
    }
    
    public void turnFeederReverse() {
       feederConv.set(Relay.Value.kReverse);
    }
    
}
