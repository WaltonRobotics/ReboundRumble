/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.subsystems;

import edu.team2974.StLouisPrep.RobotMap;
import edu.team2974.StLouisPrep.commands.ZeroTurret;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;

/**
 *
 * @author Chris
 */
public class Turret extends Subsystem
{

    AnalogChannel potentiometer;
    Victor turnTable;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    //DummyVisionCamera camera = new DummyVisionCamera();

    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
        //when no other command is running the turret motor is zeroed
        setDefaultCommand(new ZeroTurret());
    }

    public Turret() {
        turnTable = new Victor(RobotMap.turnTable);
        potentiometer = new AnalogChannel(RobotMap.potentiometerChannel);

    }

    public void turn(double speed) {
        turnTable.set(speed);
    }
    
    public double getTurn() {
        return turnTable.get();
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
        return 18 * potentiometer.getVoltage();
    }

    /**
     * assigns the potentiometer voltage to the double potenetiometerVoltage and
     * returns it
     *
     * @return potentiometerVoltage
     */
    public double getPotentiometerVoltage() {
        return potentiometer.getVoltage();
    }


    public void update() {
        SmartDashboard.putDouble("Potentiaometer Voltage",
                potentiometer.getVoltage());
    }
}
