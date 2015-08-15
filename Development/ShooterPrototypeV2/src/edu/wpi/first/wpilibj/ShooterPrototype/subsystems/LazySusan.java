/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ShooterPrototype.subsystems;

import edu.wpi.first.wpilibj.ShooterPrototype.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.ShooterPrototype.subsystems.DummyVisionCamera;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.StopRotation;

/**
 *
 * @author Walton Robotics
 */
public class LazySusan extends Subsystem
{
    Victor turnTable;
    AngleCalculator angle;
    double rotateValue = angle.getRotation();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    DummyVisionCamera camera = new DummyVisionCamera();
    
    public LazySusan(){
        turnTable = new Victor(RobotMap.turnTable);
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new StopRotation());
    }
    
     public void aim(){       
        //long currentTime = System.currentTimeMillis();
        //while(System.currentTimeMillis() <= currentTime + 10000)
         while(camera.isAimed = false)
            turnTable.set(rotateValue);
         turnTable.set(0);
    }
     
     public void stopRotation() {
         turnTable.set(0);
     }
}
