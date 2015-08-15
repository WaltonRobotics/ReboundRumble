/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.ProtoBot.subsystems;

import edu.Team2974.ProtoBot.RobotMap;
import edu.Team2974.ProtoBot.commands.ZeroTurret;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Timer;

/**
 *
 * @author Chris
 */
public class Turret extends Subsystem {
    
    Timer clock;
    AnalogChannel potentiometer;
    Victor turnTable;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    DummyVisionCamera camera = new DummyVisionCamera();

    protected void initDefaultCommand() {
         // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
       setDefaultCommand(new ZeroTurret());
    }
    
    public Turret () {
        turnTable = new Victor(RobotMap.turnTable);
        //potentiometer = new AnalogChannel(RobotMap.AnalogChannel);
        clock = new Timer();
        
    }
    
    public void turn(double angle) {
            turnTable.set(angle);
    }

    public void teleopControlledAim(double teleopAim) {
        turnTable.set(teleopAim);
    }

    public void stopRotation() {
        turnTable.set(0);
    }
    
    protected double getGoalAngle() {
        return Double.NaN;
    }
    
    public double calculateAngle() {   
        double degrees = 18 * potentiometer.getVoltage();
        return getGoalAngle() - degrees; //depends on the camera
    }
  
    public double getDegrees() {
        double degrees = 18 * potentiometer.getVoltage();
        return degrees;
    }
    
    public double getPotentiometerVoltage() {
        double potentiometerVoltage = potentiometer.getVoltage();
        return potentiometerVoltage;
    }
}
