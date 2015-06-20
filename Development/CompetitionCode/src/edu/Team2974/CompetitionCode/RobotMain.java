package edu.Team2974.CompetitionCode;

import edu.Team2974.CompetitionCode.commands.ChangeConveyorDefault;
import edu.Team2974.CompetitionCode.commands.CommandBase;
import edu.Team2974.CompetitionCode.commands.AutonomousTest;
import edu.Team2974.robot.util.FileReader;
import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;

public class RobotMain extends IterativeRobot {
    FileReader reader;
    CommandGroup autonomous;
    // Command autonomousCommand

    Watchdog puppy;
    //RobotLogger log = RobotLogger.getInstance();

    public void robotInit() {
        //autonomousCommand = new ExampleCommand();
        RobotLogger.getInstance().logMessage("ROBOInit");
        SmartDashboard.putData(Scheduler.getInstance());
        
        
        CommandBase.init();
        puppy = Watchdog.getInstance();
        puppy.setExpiration(3.0);
        OI.ballSensors.balls = 0;
        puppy.feed();
       // log.logMessage("Logger works!");
//        try {
//            reader = new FileReader("file:///RobotLogger.txt");
//            reader.readAndPrint();
//        } catch (IOException ex) {
//            RobotLogger.getInstance().logMessage(ex.getMessage());
//        }
    }
    
    public void disabledInit()
    {
//        Scheduler.getInstance().add(new ChangeConveyorDefault(true));
//        Scheduler.getInstance().run();
        if(autonomous!=null)
        {
            RobotLogger.getInstance().logMessage("Canceling Autonomous");
            autonomous.cancel();
        }
    }

    public void disabledContinuous() {
        puppy.feed();
    }

    public void autonomousInit() {
        RobotLogger.getInstance().logMessage("Autonomous start");
        autonomous = new AutonomousTest();
        autonomous.start();
        puppy.feed();
        
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopInit() {
        RobotLogger.getInstance().logMessage("Canceling Autonomous");
        autonomous.cancel();
        Scheduler.getInstance().add(new ChangeConveyorDefault(false));
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopContinuous() {
        Scheduler.getInstance().run();
        updateDash();
        
        puppy.feed();
    }

//    public void dashboardPotentiometer() {
//        double potentiometerVoltage = CommandBase.turret.getPotentiometerVoltage();
//        String potentiometerVolts = "potentiometerVolts";
//        SmartDashboard.putDouble(potentiometerVolts, potentiometerVoltage);
//        try {
//            SmartDashboard.getDouble(potentiometerVolts);
//        } catch (NetworkTableKeyNotDefined ex) {
//            RobotLogger.getInstance().logMessage(ex.getMessage());
//        }
//    }
    public void updateDash() {
        puppy.feed();
        OI.ballSensors.update();
        CommandBase.turret.update();
        CommandBase.drive.update();
        CommandBase.shot.update();
        CommandBase.bridgeArm.update();
        CommandBase.conveyor.update();
    }
}