package edu.team2974.StLouisPrep;

public class RobotMap {

    /*
     * Robot map is where we define all the channels and inputs for the 
     * different hardware componenets of the robot. This keeps all that 
     * information so its easy to find.
     */
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
    public boolean stateA;
    public boolean stateB;
    public boolean stateC;
    public int totalBalls;
    public static final int potentiometerChannel = 2;
    
    public static final int bridgeArmSpike=4;
    public static final int bridgeArmPotent=3;
    
   
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
