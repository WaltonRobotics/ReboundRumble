/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ShooterPrototype.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.ShooterPrototype.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.CompleteKillSwitch;

/**
 *
 * @author Walton Robotics
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    CANJaguar jagTop;
    CANJaguar jagBot;
    CANJaguar jagUp;
    LazySusan susan;
    Encoder topCoder;
    Encoder botCoder;
    Timer tickTock;
    public static final double WheelCircumference = 25.132;

    public Shooter() {
        try {
            jagTop = new CANJaguar(RobotMap.jagTop);
            jagBot = new CANJaguar(RobotMap.jagBot);
            jagUp = new CANJaguar(RobotMap.jagUp);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        susan = new LazySusan();
        topCoder = new Encoder(300,300);
        topCoder.setDistancePerPulse(WheelCircumference);
        botCoder = new Encoder(300,300);
        topCoder.setDistancePerPulse(WheelCircumference);
        tickTock = new Timer();
        
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new CompleteKillSwitch());
    }

    public void zeroShooterJaguars() {
        try {
            jagTop.setX(0);
            jagBot.setX(0);
            jagUp.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets the speeds (x-values) of the top and bottom Jaguars.
     * @param topSpeed the speed of the top Jaguar. Set both to 0 to stop.
     * @param bottomSpeed the speed of the bottom Jaguar. Set both to 0 to stop.
     */
    public void setShooterWheels(double topSpeed, double bottomSpeed) {
        topCoder.reset();
        botCoder.reset();
        tickTock.start();
       // long currentTime = System.currentTimeMillis();
       //while (System.currentTimeMillis() <= currentTime + 7000) {
         while (topCoder.getRate() < topCoder.getDistance() / tickTock.get() &&
         botCoder.getRate() < botCoder.getDistance() / tickTock.get()) {
             tickTock.reset();
             topCoder.reset();
             botCoder.reset();
             tickTock.delay(.1);
            try {
                jagTop.setX(topSpeed);
                jagBot.setX(bottomSpeed);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void feedBalls() {
        long currentTime = System.currentTimeMillis();
        while (System.currentTimeMillis() <= currentTime + 12000) {
            try {
                jagUp.setX(.7);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delayedShot(double topSpeed, double botSpeed) {
        setShooterWheels(topSpeed, botSpeed);
        feedBalls();
        zeroShooterJaguars();
    }

    public void aimAndFire(double topSpeed, double botSpeed) {
        susan.aim();
        susan.stopRotation();
        setShooterWheels(topSpeed, botSpeed);
        feedBalls();
        zeroShooterJaguars();
    }
    
    public void stopConveyor() {
        try {
            jagUp.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stopShooterWheels() {
        try {
            jagTop.setX(0);
            jagBot.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void completeKillSwitch() {
        try {
            jagTop.setX(0);
            jagBot.setX(0);
            jagUp.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        susan.stopRotation();
    }
}