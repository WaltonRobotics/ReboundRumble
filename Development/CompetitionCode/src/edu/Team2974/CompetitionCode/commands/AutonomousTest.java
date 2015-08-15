/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 * @author team2974
 */
public class AutonomousTest extends CommandGroup {
    
    public AutonomousTest() {
        setInterruptible(true);
        Timer timer = new Timer();        
        addParallel(new MainConveyorAutonomous());
        RobotLogger.getInstance().logMessage("Main Conveyor Loop Started");
        addParallel(new FeederAutonomous());
        RobotLogger.getInstance().logMessage("Feeder Loop Started");
        addParallel(new AimAutonomous());
        RobotLogger.getInstance().logMessage("Aiming Started");
        addSequential(new AutoShot());
        RobotLogger.getInstance().logMessage("Wheels Set");
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP,true));
        RobotLogger.getInstance().logMessage("Feeder set forward");
        timer.delay(2.0);
        RobotLogger.getInstance().logMessage("Back from 1st 2 sec delay");
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_ZERO,false));
        RobotLogger.getInstance().logMessage("Feeder zeroed");
        timer.delay(2.0);
        RobotLogger.getInstance().logMessage("Back from 2nd 2 sec delay");
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_UP,true));
        RobotLogger.getInstance().logMessage("Feeder set forward");
        timer.delay(2.0);
        RobotLogger.getInstance().logMessage("Back from 3rd 2 sec delay");
        addSequential(new GetFeederBalls(GetFeederBalls.CONV_ZERO,true));
        RobotLogger.getInstance().logMessage("Feeder zeroed");
        
       
        
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
}
