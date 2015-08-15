
package edu.Team2974.ProtoBot;

import edu.Team2974.ProtoBot.commands.DummySetShooterWheels;
import edu.Team2974.ProtoBot.commands.FeedBalls;
import edu.Team2974.ProtoBot.commands.SetIntake;
import edu.Team2974.ProtoBot.commands.MainConveyor;
import edu.Team2974.ProtoBot.commands.StopFeeder;
import edu.Team2974.ProtoBot.commands.StopShooterWheels;
import edu.Team2974.ProtoBot.commands.TeleopControledAim;
import edu.Team2974.ProtoBot.commands.changeBallCount;
import edu.Team2974.ProtoBot.commands.obliterate;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick leftStick = new Joystick(1);
    public Button leftButton1 = new JoystickButton(leftStick, 1);
    Button leftButton2 = new JoystickButton(leftStick, 2);
    Button leftButton3 = new JoystickButton(leftStick, 3);
    Button leftButton4 = new JoystickButton(leftStick, 4);
    Button leftButton5 = new JoystickButton(leftStick, 5);
    
    Joystick rightStick = new Joystick(2);
    Button rightButton1 = new JoystickButton(rightStick, 1);
    Button rightButton2 = new JoystickButton(rightStick, 2);
    Button rightButton3 = new JoystickButton(rightStick, 3);
    Button rightButton4 = new JoystickButton(rightStick, 4);
    Button rightButton5 = new JoystickButton(rightStick, 5);
    Button rightButton6 = new JoystickButton(rightStick, 6);
    Button rightButton7 = new JoystickButton(rightStick, 7);
    Button rightButton10 = new JoystickButton(rightStick, 10);
    Button rightButton11= new JoystickButton(rightStick, 11);
    DriverStation ds = DriverStation.getInstance();
    
    public OI()
    {
        leftButton1.whenPressed(new FeedBalls());
        leftButton4.whileHeld(new TeleopControledAim(.5));
        leftButton5.whileHeld(new TeleopControledAim(-.5));
//        rightButton5.whileHeld(new DummySetShooterWheels());
//        rightButton5.whenReleased(new StopShooterWheels());
        
        rightButton1.whenPressed(new obliterate());
        rightButton2.whenPressed(new MainConveyor(.75));
        rightButton3.whileHeld(new MainConveyor(-.75));
        //rightButton4.whileHeld(new MainConveyor(55.0));
        rightButton4.whenReleased(new MainConveyor(55.0));
        rightButton6.whileHeld(new SetIntake(1));
        rightButton7.whileHeld(new SetIntake(-1));
        rightButton10.whenPressed(new changeBallCount(-1));
        rightButton11.whenPressed(new changeBallCount(1));
    }
    
    public double getLeftY()
    {
        return leftStick.getY();
    }
    
    public double getRightY()
    {
        return rightStick.getY();
    }
    
    public double getLeftX()
    {
        return leftStick.getX();
    }
    
    public double getRightX()
    {
        return rightStick.getX();
    }
    
    public double getTopWheels() { 
        double topPower = ds.getAnalogIn(1);
        topPower= topPower-1;//CHANGED FROM *.4-1, it now corresponds more directly to jag inputs- inputs from 0 to 2 are -1 to 1 on motors
        return topPower;
    }
    
    public double getBotWheels() {
        double botPower = ds.getAnalogIn(2);
        botPower= botPower-1;//CHANGED FROM *.4-1, it now corresponds more directly to jag inputs- inputs from 0 to 2 are -1 to 1 on motors
        return botPower;
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
