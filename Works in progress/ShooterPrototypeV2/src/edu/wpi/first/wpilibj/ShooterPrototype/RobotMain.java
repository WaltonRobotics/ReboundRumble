/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.ShooterPrototype;

import edu.wpi.first.wpilibj.ShooterPrototype.commands.AimAndFire;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.CommandBase;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.FeedBalls;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.SetShooterWheels;
import edu.wpi.first.wpilibj.ShooterPrototype.subsystems.Shooter;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {

    Command autonomousCommand;
    
    Watchdog mydogg;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public RobotMain(){
        mydogg = Watchdog.getInstance();
        mydogg.setExpiration(1);
        
    }
    
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // Initialize all subsystems
        CommandBase.init(); 
        autonomousCommand = new AimAndFire();
        mydogg.feed();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
        mydogg.feed();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        mydogg.feed();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        
        mydogg.feed();
        autonomousCommand.cancel();      
    }

    //boolean fakeVariable = false;
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       // Scheduler.getInstance().run();
       // if (fakeVariable) {
            //Get our power calculator
           // SetSpeeds speedCommand = new SetSpeeds();
           // speedCommand.start();
       // }
        mydogg.feed();
    }
}
