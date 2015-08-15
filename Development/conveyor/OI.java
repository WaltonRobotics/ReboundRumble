package edu.Team2974.conveyor;

import edu.wpi.first.wpilibj.Joystick;
import edu.Team2974.conveyor.commands.EndBigConveyer;
import edu.Team2974.conveyor.commands.StartBigConveyer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    Joystick jStickLeft = new Joystick(RobotMap.jStickLeft);
    Button leftButton1 = new JoystickButton(jStickLeft, 1);
    Button leftButton2 = new JoystickButton(jStickLeft, 2);
    Button leftButton3 = new JoystickButton(jStickLeft, 3);
    Button leftButton4 = new JoystickButton(jStickLeft, 4);
    Button leftButton5 = new JoystickButton(jStickLeft, 5);
    
    Joystick jStickRight = new Joystick(RobotMap.jStickRight);
    Button rightButton1 = new JoystickButton(jStickRight, 1);
    Button rightButton2 = new JoystickButton(jStickRight, 2);
    Button rightButton3 = new JoystickButton(jStickRight, 3);
    Button rightButton4 = new JoystickButton(jStickRight, 4);
    Button rightButton5 = new JoystickButton(jStickRight, 5);

    public OI() {
        rightButton1.whileHeld(new EndBigConveyer());
        rightButton2.whenPressed(new StartBigConveyer(-.1));
        rightButton2.whenReleased(new EndBigConveyer());
        rightButton3.whileHeld(new StartBigConveyer(.1));
        
        System.out.println("Space 4: OI constructor");
    }
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
//    LeftButton1.whenPressed(new Aim());
//    LeftButton1.whenReleased(new StopRotation());
//    LeftButton2.whenPressed(new SetShooterWheels());
//    LeftButton2.whenReleased(new StopShooterWheels());
//    LeftButton3.whenPressed(new FeedBalls());
//    LeftButton3.whenReleased(new StopConveyor());
//    LeftButton4.whileHeld(new CompleteKillSwitch());
//    RightButton1.whenPressed(new FeedBalls());
//    RightButton1.whenReleased(new StopConveyor());
//    RightButton2.whenPressed(new Aim());
//    RightButton2.whenReleased(new StopRotation());
//    RightButton3.whenPressed(new SetShooterWheels());
//    RightButton3.whenReleased(new StopShooterWheels());