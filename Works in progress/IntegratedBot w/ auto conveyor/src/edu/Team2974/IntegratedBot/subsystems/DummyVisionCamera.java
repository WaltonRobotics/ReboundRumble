/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.ProtoBot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Chris
 */
public class DummyVisionCamera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private boolean isAimed;

    public DummyVisionCamera() {
        isAimed = false;
    }
    
    public boolean getIsAimed() {
        return isAimed;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
