package edu.Team2974.IntegratedBot;

import edu.Team2974.IntegratedBot.commands.CommandBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMain extends IterativeRobot {

    Watchdog puppy;
    public static UpdateSensorValues up;

    public void robotInit() {
        //autonomousCommand = new ExampleCommand();
        SmartDashboard.putData(Scheduler.getInstance());
        CommandBase.init();
        puppy = Watchdog.getInstance();
        up = new UpdateSensorValues();
//        try {
//            reader = new FileReader("file:///Txt//FileTest.txt");
//            reader.readAndPrint();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopInit() {
        //   autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopContinuous() {
        Scheduler.getInstance().run();
        puppy.feed();
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