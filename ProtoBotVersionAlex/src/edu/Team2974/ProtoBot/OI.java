package edu.Team2974.ProtoBot;

import edu.Team2974.ProtoBot.commands.DummySetShooterWheels;
import edu.Team2974.ProtoBot.commands.ShootOutBalls;
import edu.Team2974.ProtoBot.commands.SetIntake;
import edu.Team2974.ProtoBot.commands.MainConveyor;
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
    Button rightButton8 = new JoystickButton(rightStick, 8);
    Button rightButton10 = new JoystickButton(rightStick, 10);
    Button rightButton11 = new JoystickButton(rightStick, 11);
    DriverStation ds = DriverStation.getInstance();

    public OI() {
        leftButton1.whileHeld(new ShootOutBalls(true));
        leftButton1.whenReleased(new ShootOutBalls(false));
        leftButton4.whileHeld(new TeleopControledAim(.5));
        leftButton5.whileHeld(new TeleopControledAim(-.5));
//        rightButton5.whileHeld(new DummySetShooterWheels());
//        rightButton5.whenReleased(new StopShooterWheels());

        rightButton1.whenPressed(new obliterate());
        rightButton2.whenPressed(new MainConveyor(.75));
        
        rightButton3.whileHeld(new MainConveyor(-.75));
        rightButton4.whileHeld(new MainConveyor(55.0));
        rightButton6.whenPressed(new SetIntake(1));
        rightButton7.whenPressed(new SetIntake(-1));
        rightButton8.whenPressed(new SetIntake(0));
        rightButton10.whenPressed(new changeBallCount(-1));
        rightButton11.whenPressed(new changeBallCount(1));
    }

    public double getLeftY() {
        return leftStick.getY();
    }

    public double getRightY() {
        return rightStick.getY();
    }

    public double getLeftX() {
        return leftStick.getX();
    }

    public double getRightX() {
        return rightStick.getX();
    }

    public double getTopWheels() {
        double topPower = ds.getAnalogIn(1);
        topPower = topPower - 1;//CHANGED FROM *.4-1, it now corresponds more directly to jag inputs- inputs from 0 to 2 are -1 to 1 on motors
        return topPower;
    }

    public double getBotWheels() {
        double botPower = ds.getAnalogIn(2);
        botPower = botPower - 1;//CHANGED FROM *.4-1, it now corresponds more directly to jag inputs- inputs from 0 to 2 are -1 to 1 on motors
        return botPower;
    }
}
