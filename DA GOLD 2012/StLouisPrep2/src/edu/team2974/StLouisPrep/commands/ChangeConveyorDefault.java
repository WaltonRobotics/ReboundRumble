/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Chris
 */
public class ChangeConveyorDefault extends CommandBase {

    private boolean driverControl;

    /**
     * 
     * @param dc boolean that if true, makes the conveyor defaults zeroing the
     * motors. If dc is false, makes the conveyor auto logic run.
     */
    public ChangeConveyorDefault(boolean dc) {
        requires(conveyor);
        requires(feeder);
        driverControl = dc;
        conveyor.auto = !dc;
    }

    protected void initialize() {
       if (driverControl) {
            conveyor.setDefault(new MainConveyor(0.0, true));
            feeder.setDefault(new GetFeederBalls(0.0, true));
        } else {
            conveyor.setDefault(new MainConveyor(0.0, false));
            feeder.setDefault(new GetFeederBalls(0.0, false));
        }

    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
