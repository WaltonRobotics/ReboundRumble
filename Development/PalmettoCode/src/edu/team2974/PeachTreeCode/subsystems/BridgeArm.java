package edu.team2974.PeachTreeCode.subsystems;

import edu.team2974.PeachTreeCode.RobotMap;
import edu.team2974.PeachTreeCode.commands.ControlBridgeArm;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BridgeArm extends Subsystem{
    
        private Relay bArm;
        private AnalogChannel potentiometer;
    public BridgeArm() {
        bArm = new Relay(RobotMap.bridgeArmSpike);
        potentiometer = new AnalogChannel(RobotMap.bridgeArmPotent);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ControlBridgeArm(0));
    }

    public double getPosition(){
        
        return potentiometer.getAverageVoltage();
    }
    
    public void failSafe(double LowerMax, double UpperMax) {
        if (getPosition() <= LowerMax || getPosition() >= UpperMax) {
            bArm.set(Relay.Value.kOff);
        }
    }
    
    public void armUp() {
        bArm.set(Relay.Value.kForward);
    }

    public void armDown() {
       bArm.set(Relay.Value.kReverse);
    }

    public void armOFF() {
        bArm.set(Relay.Value.kOff);
    }
    
    public void update()
    {
        SmartDashboard.putDouble("Potentiometer BA",getPosition());
    }
}

