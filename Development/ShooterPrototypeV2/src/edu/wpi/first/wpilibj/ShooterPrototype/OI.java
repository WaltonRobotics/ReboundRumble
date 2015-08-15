
package edu.wpi.first.wpilibj.ShooterPrototype;

import edu.wpi.first.wpilibj.ShooterPrototype.commands.Aim;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.CompleteKillSwitch;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.DelayedShot;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.SetShooterWheels;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.StopConveyor;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.StopRotation;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.StopShooterWheels;
import edu.wpi.first.wpilibj.ShooterPrototype.commands.FeedBalls;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick jStickRight = new Joystick(RobotMap.jStickRight);
    Joystick jStickLeft = new Joystick(RobotMap.jStickLeft);
    Button LeftButton1 = new JoystickButton(jStickLeft, 1),
            LeftButton2 = new JoystickButton(jStickLeft, 2),
            LeftButton3 = new JoystickButton(jStickLeft, 3),
            LeftButton4 = new JoystickButton(jStickLeft, 4),
            LeftButton5 = new JoystickButton(jStickLeft, 5);                    
    Button RightButton1 = new JoystickButton(jStickRight, 1), 
            RightButton2 = new JoystickButton(jStickRight, 2),
            RightButton3 = new JoystickButton(jStickRight, 3),
            RightButton4 = new JoystickButton(jStickRight, 4),
            RightButton5 = new JoystickButton(jStickRight, 5);
    
    public OI() {
    LeftButton1.whenPressed(new Aim());
    LeftButton1.whenReleased(new StopRotation());
    LeftButton2.whenPressed(new SetShooterWheels());
    LeftButton2.whenReleased(new StopShooterWheels());
    LeftButton3.whenPressed(new FeedBalls());
    LeftButton3.whenReleased(new StopConveyor());
    LeftButton4.whileHeld(new CompleteKillSwitch());
    RightButton1.whenPressed(new FeedBalls());
    RightButton1.whenReleased(new StopConveyor());
    RightButton2.whenPressed(new Aim());
    RightButton2.whenReleased(new StopRotation());
    RightButton3.whenPressed(new SetShooterWheels());
    RightButton3.whenReleased(new StopShooterWheels());
    RightButton4.whileHeld(new CompleteKillSwitch());
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

