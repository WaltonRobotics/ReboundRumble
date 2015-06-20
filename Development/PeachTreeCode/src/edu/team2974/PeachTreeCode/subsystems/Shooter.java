package edu.team2974.PeachTreeCode.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.ShooterPrototype.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.ShooterPrototype.commands.CompleteKillSwitch;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.team2974.PeachTreeCode.RobotMap;
import edu.team2974.PeachTreeCode.commands.DummySetShooterWheels;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

    CANJaguar jagTop;
    CANJaguar jagBot;
    //LazySusan susan;
    Encoder topCoder;
    Encoder botCoder;
    Timer tickTock;
    public static final double WheelCircumference = 25.132;
    public static final int CodeBoxRevolutions = 360;

    public Shooter() {
        try {
            jagTop = new CANJaguar(RobotMap.jagTop);
            jagBot = new CANJaguar(RobotMap.jagBot);
            jagTop.changeControlMode(CANJaguar.ControlMode.kVoltage);
            jagBot.changeControlMode(CANJaguar.ControlMode.kVoltage);
            jagBot.enableControl();
            jagTop.enableControl();
            jagTop.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            jagBot.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            jagTop.configEncoderCodesPerRev(CodeBoxRevolutions);
            jagBot.configEncoderCodesPerRev(CodeBoxRevolutions);

        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        //susan = new LazySusan();
        botCoder = new Encoder(RobotMap.aChannel2, RobotMap.bChannel2);
        botCoder.setDistancePerPulse(WheelCircumference);
        tickTock = new Timer();

    }

    public void initDefaultCommand() {
        setDefaultCommand(new DummySetShooterWheels());
    }

    public void zeroShooterJaguars() {
        try {
            jagTop.setX(0);
            jagBot.setX(0);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    /**
     * Sets the speeds (x-values) of the top and bottom Jaguars.
     * @param topSpeed the speed of the top Jaguar. Set both to 0 to stop.
     * @param bottomSpeed the speed of the bottom Jaguar. Set both to 0 to stop.
     */
    public void setShooterWheels(double topSpeed, double bottomSpeed) throws CANTimeoutException {
        try {
            jagTop.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagBot.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagTop.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            jagBot.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            jagTop.configEncoderCodesPerRev(CodeBoxRevolutions);
            jagBot.configEncoderCodesPerRev(CodeBoxRevolutions);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        while (jagTop.getSpeed() < topSpeed && jagBot.getSpeed() < bottomSpeed) {
            try {
                jagTop.setX(topSpeed);
                jagBot.setX(bottomSpeed);
            } catch (CANTimeoutException ex) {
                RobotLogger.getInstance().logMessage(ex.getMessage());
            }
        }
    }

    public void setShooterWheelsInstantly(double topSpeed, double bottomspeed) {
        try {
            jagTop.changeControlMode(CANJaguar.ControlMode.kSpeed);
            jagBot.changeControlMode(CANJaguar.ControlMode.kSpeed);
            jagTop.setX(topSpeed);
            jagBot.setX(bottomspeed);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    public void stopShooterWheels() {
        try {
            jagTop.setX(0);
            jagBot.setX(0);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    public void dummySetShooterWheels(double topWheel, double botWheel) {
        try {
            //succesion) for some reason!?
            jagTop.setX(topWheel);
            jagBot.setX(botWheel);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void dummySetTopWheels(double topWheel) {
        try {
            jagTop.setX(topWheel);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    public void dummySetBottomWheels(double botWheel) {
        try {
            jagBot.setX(botWheel);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }
    
    public void update()
    {
        try {
            SmartDashboard.putDouble("Bot Shooter Jag", jagBot.getX());
            SmartDashboard.putDouble("Top Shooter Jag", jagTop.getX());
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }
}