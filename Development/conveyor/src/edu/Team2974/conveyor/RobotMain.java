package edu.Team2974.conveyor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.Team2974.conveyor.commands.CommandBase;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Scheduler;

public class RobotMain extends IterativeRobot {

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
        mydogg.feed();
    }

    public void autonomousPeriodic() {
        //Scheduler.getInstance().run();
        mydogg.feed();
    }

    public void teleopInit() {
        Scheduler.getInstance().run();
        mydogg.feed();
        System.out.println("Space 1: teleop init");
    }

    public void teleopPeriodic() {
        mydogg.feed();
        Scheduler.getInstance().run();
    }

    public void teleopContinuous() {
        mydogg.feed();
        Scheduler.getInstance().run();
    }
}
// Scheduler.getInstance().run();