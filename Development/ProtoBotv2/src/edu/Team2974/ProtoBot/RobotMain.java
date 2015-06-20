/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.Team2974.ProtoBot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.Team2974.ProtoBot.commands.CommandBase;
import edu.Team2974.ProtoBot.commands.ExampleCommand;
import edu.Team2974.ProtoBot.commands.driveWithJoysticks;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
//    FileReader reader;
   // Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
        SmartDashboard.putData(Scheduler.getInstance());

        // Initialize all subsystems
        CommandBase.init();
//        try {
//            reader = new FileReader("file:///Txt//FileTest.txt");
//            reader.readAndPrint();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
     //   autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
     //   autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    public void dashboardPotentiometer() {
        double potentiometerVoltage = CommandBase.turret.getPotentiometerVoltage();
        String potentiometerVolts = "potentiometerVolts";
        SmartDashboard.putDouble(potentiometerVolts, potentiometerVoltage);
        try {
            SmartDashboard.getDouble(potentiometerVolts);
        } catch (NetworkTableKeyNotDefined ex) {
            ex.printStackTrace();
        }
    }
}