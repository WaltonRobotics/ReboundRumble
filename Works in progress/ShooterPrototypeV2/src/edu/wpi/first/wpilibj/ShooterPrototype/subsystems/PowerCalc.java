/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ShooterPrototype.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import  edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.ShooterPrototype.RobotMap;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Walton Robotics
 */
public class PowerCalc extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    CANJaguar jagTop;
    CANJaguar jagBot;
    Encoder topCoder;
    Encoder botCoder;
    Timer tickTockClock;
    
      /**
     * used in comparisons to select the low goal for calculations
     */   
    public static final int LOW_GOAL = 1;    
    
    /**
     * used in comparisons to select the mid goals for calculations
     */    
    public static final int  MID_GOAL = 2;
    
    /**
     * used in comparisons to select the highest goal for calculations
     */    
    public static final int HIGH_GOAL = 3;
   
    /**
     * force of gravity in ft/s
     */    
    private static final double GRAVITY = 9.81;
   
    /**
     * angle of the shooter in degrees
     */  
    private double angle = 42;
   
    /**
     * height of the shooter in inches
     */   
    private double shooterHeight = 54;
   
    /**
     * lowest basket height in inches
     */   
    private static final double LOW_HEIGHT = 28;
   
    /**
     * middle basket height in inches
     */
    private static final double MID_HEIGHT = 61;
    
    /**
     * highest basket height in inches
     */
    private static final double HIGH_HEIGHT = 98;
    
    /**
     * height of the basket that the robot is shooting at in inches
     * default is highest goal height
     */
    private double goalHeight = HIGH_HEIGHT;
    
    /**
     * Mass of the ball in grams, standard is 11
     */    
    private static final double ballMass = 11;
    
    private static final double WheelCircumference = 25.132;
    
    /**
     * Constructor for the power calculator
     * @param angle the angle of the shooter is fixed so it won't change during a match but may change after one
     * @param shooterHeight the height of the shooter is fixed and won't change during the match but may change after one
     */
 public PowerCalc(double angle, double shooterHeight)
    {
        this.angle = angle;
        this.shooterHeight = shooterHeight;
        try {
            jagTop = new CANJaguar(RobotMap.jagTop);
            jagBot = new CANJaguar(RobotMap.jagBot);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        topCoder = new Encoder(300,300);
        topCoder.setDistancePerPulse(WheelCircumference);
        botCoder = new Encoder(300,300);
        botCoder.setDistancePerPulse(WheelCircumference);
        tickTockClock = new Timer();
    }
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * calculates speed of both sets of wheels on the shooter
     * @param wheelSpeed
     * @return returns the avg. speed of both the wheel sets
     */
    public double getWheelSpeed() {
        double wheelSpeed;
        tickTockClock.start();
        topCoder.reset();
        botCoder.reset();
        wheelSpeed = ((topCoder.getDistance() / tickTockClock.get()) + (botCoder.getDistance() / tickTockClock.get())) / 2;
        return wheelSpeed;
    }
    
    /**
     * This method provides the power needed to send the ball into the specidfied goal and will set the goal to be used in future calculations
     * @param r distance between the shooter and the basket
     * @param goalChoice chooses which goal the shot is being taken at. The value needs to be LOW_GOAL, MID_GOAL, or HIGH_GOAL
     * @return returns a number between -1 and 1 that can be fed directly into the jags
     */
    public double getPower(double r, int goalChoice)
    {
        setGoal(goalChoice);
        double feetPerSecond = getInitialVelocityNeeded(r);
        double power = convertToPower(feetPerSecond);
        return power;
    }
    
    /**
     * This method provides the power needed to send the ball into the currently selected goal
     * @param r distance between the shooter and the basket
     * @return returns a number between -1 and 1 that can be fed directly into the jags
     */
    public double getPower(double r)
    {
        double feetPerSecond = getInitialVelocityNeeded(r);
        double power = convertToPower(feetPerSecond);
        return power;
    }
    
    /**
     * This method gives the initial velocity needed to send the ball into the goal
     * @param r distance between the shooter and the basket
     * @return the initial velocity in ft/s
     */
    public double getInitialVelocityNeeded(double range)
    {
        double feetPerSecond;
        feetPerSecond = range*Math.sqrt(GRAVITY/((range*Math.tan(angle))-goalHeight+shooterHeight));
        feetPerSecond/=(Math.sqrt(2.0)*Math.cos(angle));
        return feetPerSecond;
    }
    
    /**
     * internal helper method that converts the ft/s calc to the power needed
     * @param feetPerSecond the ft/s of the inital velocity the ball needs 
     * 2.09439ft = circumference
     * @return 
     */
    public double convertToPower(double feetPerSecond)
    {
        double circ = 8 * Math.PI;
        double power = (feetPerSecond * 60) / circ;
        return power;
    }
    
    
    
    /**
     * Allows other parts of the program to change the target goal without grabbing the power
     * @param goalChoice needs to be LOW_GOAL, MID_GOAL, or HIGH_GOAL
     */
   public void setGoal(int goalChoice) {
    if(goalChoice == LOW_GOAL)
        {
            goalHeight = LOW_HEIGHT;
        }
        else if(goalChoice == MID_GOAL)
        {
            goalHeight = MID_HEIGHT;
        }
        else if(goalChoice==HIGH_GOAL)
        {
            goalHeight = HIGH_HEIGHT;
        }
   }
}