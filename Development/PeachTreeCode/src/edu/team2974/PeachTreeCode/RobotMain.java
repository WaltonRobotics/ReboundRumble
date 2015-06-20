package edu.team2974.PeachTreeCode;

import edu.team2974.PeachTreeCode.commands.AutonomousTest;
import edu.team2974.PeachTreeCode.commands.ChangeConveyorDefault;
import edu.team2974.PeachTreeCode.commands.CommandBase;
import edu.team2974.PeachTreeCode.commands.ZeroShooterJaguars;
import edu.team2974.robot.util.FileReader;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMain extends IterativeRobot
{

    FileReader reader;
    CommandGroup autonomous;
    private final double SCHEDULERDELAY = 1.0;
    // Command autonomousCommand
    Watchdog puppy;
    //RobotLogger log = RobotLogger.getInstance();
    Scheduler currentScheduler;

    public void robotInit() {
        //autonomousCommand = new ExampleCommand();
        RobotLogger.getInstance().logMessage("ROBOInit");
        puppy = Watchdog.getInstance();
        puppy.setExpiration(3.0);

//        SmartDashboard.putData(Scheduler.getInstance());
        currentScheduler = Scheduler.getInstance();
        SmartDashboard.putData(currentScheduler);

        CommandBase.init();
//        puppy = Watchdog.getInstance();
//        puppy.setExpiration(3.0);
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

    public void disabledInit() {
//        Scheduler.getInstance().add(new ChangeConveyorDefault(true));
//        Scheduler.getInstance().run();
        if (autonomous != null) {
            RobotLogger.getInstance().logMessage("Canceling AutonomousTest");
            autonomous.cancel();
        }
    }

    public void disabledContinuous() {
        puppy.feed();
    }

    public void disabledPeriodic() {
    }

    public void autonomousInit() {
        RobotLogger.getInstance().logMessage("Starting AutonomousTest INIT");

        OI.ballSensors.balls = 2;
        autonomous = new AutonomousTest();
        RobotLogger.getInstance().logMessage("Back from AutonomousTest INIT");
        RobotLogger.getInstance().logMessage("AutonomousTest start");
        autonomous.start();
        puppy.feed();
    }

    public void autonomousContinuous() {
//        puppy.feed();
//        currentScheduler.run();
//        Scheduler.getInstance().run();
//        puppy.feed();
    }

    public void autonomousPeriodic() {
        updateDash();
        puppy.feed();
        currentScheduler.run();
        puppy.feed();
    }

    public void teleopInit() {
        RobotLogger.getInstance().logMessage("Canceling AutonomousTest");
        if (autonomous != null) {
            autonomous.cancel();
        }
        currentScheduler.add(new ChangeConveyorDefault(false));
        currentScheduler.add(new ZeroShooterJaguars());
//        Scheduler.getInstance().add(new ChangeConveyorDefault(false));
//        Scheduler.getInstance().add(new ZeroShooterJaguars());
        puppy.feed();
    }

    public void teleopPeriodic() {
        updateDash();
        puppy.feed();
        currentScheduler.run();
        puppy.feed();
    }

    public void teleopContinuous() {
//        puppy.feed();
//        currentScheduler.run();
//        Scheduler.getInstance().run();
//        puppy.feed();
//        updateDash();
//        puppy.feed();
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