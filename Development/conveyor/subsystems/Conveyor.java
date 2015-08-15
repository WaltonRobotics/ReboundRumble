/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.conveyor.subsystems;
import edu.Team2974.conveyor.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 * @author Chris
 */
public class Conveyor extends Subsystem {
    private CANJaguar jagConv;
    
    public Conveyor()
    {
        try {
            jagConv = new CANJaguar(RobotMap.jagConv);
            jagConv.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagConv.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void initDefaultCommand() {
    }
    
    public void destroyBigConveyor() {
        try {
            jagConv.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
     public void updateBigConveyor(double update) {
        try {
            //SmartDashboard.putString(DASHBOARD_STS_MSG_KEY, "IN updateBigConveyor");
            System.out.println("Space 10: shooter update entering");
            double old = jagConv.getX();
            jagConv.setX(old + update);
            System.out.println("Space 11: shooter update exiting");
            //SmartDashboard.putString(DASHBOARD_STS_MSG_KEY, "EXITING updateBigConveyor");
        } catch (CANTimeoutException ex) {
            //SmartDashboard.putString(DASHBOARD_STS_MSG_KEY, ex.toString());
            System.out.println("Space 12: shooter update catching exception");
            ex.printStackTrace();
        }
    }
    
    public double getBigConveyorSpeed() {
        try {
            return jagConv.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    
}