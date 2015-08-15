/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.PeachTreeCode.commands;

import edu.team2974.PeachTreeCode.OI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkListener;

/**
 *
 *
 * @author team2974
 */
public class AutonomousTest extends CommandGroup
{

    NetworkListener autonomousAim;
    NetworkListener autonomousAutoShot;

    public AutonomousTest() {
        setInterruptible(true);
        addParallel(new MainConveyorAutonomous(), 14.9);
        addParallel(new FeederAutonomous(), 14.9);

        autonomousAim = new AimAutonomous();
        addParallel((Command) autonomousAim, 14.9);
//        addParallel(new AimAutonomous(),14.9);
        autonomousAutoShot = new AutoShotAutonomous();
        addSequential((Command) autonomousAutoShot, 4.0);
//        addSequential(new AutoShotAutonomous(), 4.0);
//        addSequential(new DelayMaker(1));
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));
        addSequential(new DelayMaker(0));
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_ZERO, true));
        addSequential(new DelayMaker(0));
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));

        OI.shooterRotate.addListener("running", autonomousAim);
        OI.targetTable.addListener("Target Found", autonomousAutoShot);





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
        OI.shooterRotate.removeListener("running", autonomousAim);
        OI.targetTable.removeListener("Target Found", autonomousAutoShot);
    }
}
