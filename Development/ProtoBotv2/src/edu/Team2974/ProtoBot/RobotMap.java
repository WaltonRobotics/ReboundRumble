package edu.Team2974.ProtoBot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    public static final int frontLeftMotor = 11;
    public static final int frontRightMotor = 12;
    public static final int backRightMotor = 13;
    public static final int backLeftMotor = 14;
    public static final int jagTop = 22;
    public static final int jagBot = 21;
    public static final int aChannel = 7;
    public static final int bChannel = 8;
    public static final int aChannel2 = 9;
    public static final int bChannel2 = 10;
    public static final int turnTable = 5;
    public static final int relayIntake = 2;
    public static final int shooterSpikeConveyor = 3;
    public static final int jagBigConveyor = 23;
    public static final int sensA = 4;
    public static final int sensB = 3;
    public static final int sensC = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
