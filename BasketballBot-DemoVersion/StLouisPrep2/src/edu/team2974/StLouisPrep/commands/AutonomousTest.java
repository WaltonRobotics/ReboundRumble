/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.OI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *
 * @author team2974
 */
public class AutonomousTest extends CommandGroup {

    //NetworkListener autonomousAim;
    //NetworkListener autonomousAutoShot;
    public AutonomousTest() {
        //makes it so that the group can be interrupted
        setInterruptible(true);
        /*
         //makes auto conveyor run with a 14.9 second timeout
         addParallel(new MainConveyorAutonomous(), 14.9);
         addParallel(new FeederAutonomous(), 14.9);
         autonomousAim = new AimAutonomous();
         //casts as a command since autonomousAim is defined as a NetworkListener
         addParallel((Command) autonomousAim, 14.9);
         autonomousAutoShot = new AutoShotAutonomous();
         //casts as a command since autonomousAutoShot is defined as a NetworkListener
         /*
         * has a time out of 4 seconds, can be made lower. The timeout is so
         * autonomousAutoShot has time to find a target 
         */
        /*
         addSequential((Command) autonomousAutoShot, .1);
         //starts firing the balls, this will interrupt FeederAutonomous
         addSequential(new DelayMaker(2));
         addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));
        
         //        addSequential(new GetFeederBalls(GetFeederBalls.CONV_ZERO, true));
         //        addSequential(new DelayMaker(0));
         //        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));

         //autonomousAim's logic will execute every time direction changes
         OI.shooterRotate.addListener("direction", autonomousAim);
         //autonomousAutoShot's logic will execute every time Target Found changes
         OI.targetTable.addListener("Target Found", autonomousAutoShot);

         */

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }

    public void interrupted() {
        end();
    }

    public void end() {
        // OI.shooterRotate.removeListener("running", autonomousAim);
        // OI.targetTable.removeListener("Target Found", autonomousAutoShot);
    }
}
