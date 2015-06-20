package edu.team2974.StLouisPrep.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class KinectAutoGroup extends CommandGroup {

    public KinectAutoGroup() {
        setInterruptible(true);
//        addParallel(new MainConveyorAutonomous(), startDelay);
//        addParallel(new FeederAutonomous(), startDelay);
//        //addParallel(new AimAutonomous(), startDelay);
//        addSequential(new AutoShotAutonomous(), 4.0);
//        addSequential(new DelayMaker(2.0));
//        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));
//        addSequential(new DelayMaker(2.0));
//        addSequential(new GetFeederBalls(GetFeederBalls.CONV_ZERO, true));
//        addSequential(new DelayMaker(1.0));
//        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP, true));
        addParallel(new AutoShot(), 0);
        //addSequential(new DelayMaker(.5));
        addParallel(new GetFeederBalls(1.0, true), 0);
        addParallel(new MainConveyorAutonomous(), 0);
    }
}
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