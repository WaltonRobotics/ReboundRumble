package edu.Team2974.conveyor;

import edu.wpi.first.wpilibj.Joystick;
import edu.Team2974.conveyor.commands.MainConveyor;
import edu.Team2974.conveyor.commands.obliterate;
import edu.Team2974.conveyor.commands.Intake;
import edu.Team2974.conveyor.commands.changeBallCount;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    Joystick jStickRight = new Joystick(RobotMap.jStickRight);
    Button rightButton1 = new JoystickButton(jStickRight, 1);
    Button rightButton2 = new JoystickButton(jStickRight, 2);
    Button rightButton3 = new JoystickButton(jStickRight, 3);
    Button rightButton4 = new JoystickButton(jStickRight, 4);
    Button rightButton6 = new JoystickButton(jStickRight, 6);
    Button rightButton7 = new JoystickButton(jStickRight, 7);
    Button rightButton10 = new JoystickButton(jStickRight, 10);
    Button rightButton11 = new JoystickButton(jStickRight, 11);

    public OI() {
        rightButton1.whenPressed(new obliterate());
        rightButton2.whenPressed(new MainConveyor(.75));
        rightButton3.whileHeld(new MainConveyor(-.75));
        rightButton4.whileHeld(new MainConveyor(55.0));
        rightButton4.whenReleased(new obliterate());
        rightButton6.whileHeld(new Intake(true));
        rightButton7.whileHeld(new Intake(false));
        rightButton10.whenPressed(new changeBallCount(-1));
        rightButton11.whenPressed(new changeBallCount(1));

        System.out.println("Space 4: OI constructor");
    }
}
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
//    Joystick jStickLeft = new Joystick(RobotMap.jStickLeft);
//    Button leftButton1 = new JoystickButton(jStickLeft, 1);
//    Button leftButton2 = new JoystickButton(jStickLeft, 2);
//    Button leftButton3 = new JoystickButton(jStickLeft, 3);
//    Button leftButton4 = new JoystickButton(jStickLeft, 4);
//    Button leftButton5 = new JoystickButton(jStickLeft, 5);
