/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.IntegratedBot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Chris
 */
public class ChangeConveyorDefault extends CommandBase {

    private boolean driverControl;
    public ChangeConveyorDefault(boolean dc)
    {
        requires(conveyor);
        requires(feeder);
        driverControl = dc;
        conveyor.auto = !dc;
    }
    protected void initialize() {
    }

    protected void execute() {
         if(driverControl)
        {
            conveyor.setDefault(new MainConveyor(0.0,true));
            feeder.setDefault(new GetFeederBalls(0.0,true));
        }
        else
        {
            conveyor.setDefault(new MainConveyor(0.0,false));
            feeder.setDefault(new GetFeederBalls(0.0,false));
        }

    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
