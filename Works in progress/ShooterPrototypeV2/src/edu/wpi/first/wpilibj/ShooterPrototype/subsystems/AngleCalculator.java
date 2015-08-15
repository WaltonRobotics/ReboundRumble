/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ShooterPrototype.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Walton Robotics
 */
public class AngleCalculator extends Subsystem
{
    double rotateValue = 1;
   
    // Put methods for controlling this subsystem
    // here. Call these from Commands. 
    
    public AngleCalculator(){
        
    }

    public double getRotation(){
        return rotateValue;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}
