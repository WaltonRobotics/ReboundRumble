package edu.Team2974.conveyor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.Team2974.conveyor.commands.CommandBase;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class RobotMain extends IterativeRobot {

    Command autonomousCommand;
    Watchdog mydogg;

    public RobotMain() {
        mydogg = Watchdog.getInstance();
        mydogg.setExpiration(1);
        
    }

    public void robotInit() {
        CommandBase.init();
        mydogg.feed();
        
    }

    public void autonomousInit() {
Scheduler.getInstance().run();
        autonomousCommand.start();
        mydogg.feed();
    }

    public void autonomousPeriodic() {
        //Scheduler.getInstance().run();
        mydogg.feed();
    }

    public void teleopInit() {
Scheduler.getInstance().run();
        mydogg.feed();
        autonomousCommand.cancel();
        System.out.println("Space 1: teleop init");
    }

    //boolean fakeVariable = false;
    public void teleopPeriodic() {
        mydogg.feed();
        Scheduler.getInstance().run();
    }

    public void teleopContinuous() {
        mydogg.feed();
        Scheduler.getInstance().run();
       // System.out.println("Space 2: teleop continunous");
    }
}
// Scheduler.getInstance().run();
// if (fakeVariable) {
//Get our power calculator
// SetSpeeds speedCommand = new SetSpeeds();
// speedCommand.start();
// }
// This makes sure that the autonomous stops running when
// teleop starts running. If you want the autonomous to 
// continue until interrupted by another command, remove
// this line or comment it out.
// schedule the autonomous command (example)
// instantiate the command used for the autonomous period
        // Initialize all subsystems