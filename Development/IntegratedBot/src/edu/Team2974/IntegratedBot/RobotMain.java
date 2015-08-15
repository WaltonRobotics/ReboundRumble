package edu.Team2974.IntegratedBot;

import edu.Team2974.IntegratedBot.commands.ChangeConveyorDefault;
import edu.Team2974.IntegratedBot.commands.CommandBase;
import edu.Team2974.IntegratedBot.commands.MainConveyor;
import edu.Team2974.IntegratedBot.commands.GetFeederBalls;
import edu.Team2974.robot.util.FileReader;
import edu.Team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkListener;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;

public class RobotMain extends IterativeRobot {


    FileReader reader;
    // Command autonomousCommand;

    Watchdog puppy;
    NetworkTable inputTable = NetworkTable.getTable("robotFileInput");
    TestListener testListener;
    //RobotLogger log = RobotLogger.getInstance();

    public void robotInit() {
        //autonomousCommand = new ExampleCommand();
        SmartDashboard.putData(Scheduler.getInstance());
        CommandBase.init();
        puppy = Watchdog.getInstance();
        puppy.setExpiration(3.0);
        OI.ballSensors.balls = 0;
        puppy.feed();
        testListener = new TestListener();
        inputTable.addListenerToAll(testListener);
       // log.logMessage("Logger works!");
//        try {
//            reader = new FileReader("file:///RobotLogger.txt");
//            reader.readAndPrint();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }
    
    public void disabledInit()
    {
        Scheduler.getInstance().add(new ChangeConveyorDefault(true));
        Scheduler.getInstance().run();
    }

    public void disabledContinuous() {
        puppy.feed();
    }

    public void autonomousInit() {
        puppy.feed();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        puppy.feed();
    }

    public void teleopInit() {
        //   autonomousCommand.cancel();
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
//            ex.printStackTrace();
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
        CommandBase.oi.update();
    }
    
    public class TestListener implements NetworkListener{
    
        public TestListener()
        {
            
        }

        public void valueChanged(String key, Object value) {
            RobotLogger.getInstance().logMessage("Value: "+key+" is "+value);
        }

        public void valueConfirmed(String key, Object value) {
        }
                
    }

}
