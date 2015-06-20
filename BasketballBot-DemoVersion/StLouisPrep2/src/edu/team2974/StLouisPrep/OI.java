package edu.team2974.StLouisPrep;

import edu.team2974.StLouisPrep.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class OI {

    public Joystick leftStick = new Joystick(1);
    public Button leftButton1 = new JoystickButton(leftStick, 1);
    Button leftButton2 = new JoystickButton(leftStick, 2);
    Button leftButton3 = new JoystickButton(leftStick, 3);
    Button leftButton4 = new JoystickButton(leftStick, 4);
    Button leftButton5 = new JoystickButton(leftStick, 5);
    Button leftButton6 = new JoystickButton(leftStick, 6);
    Button leftButton7 = new JoystickButton(leftStick, 7);
    Button leftButton10 = new JoystickButton(leftStick, 10);
    Button leftButton11 = new JoystickButton(leftStick, 11);
    public Joystick rightStick = new Joystick(2);
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
    public Joystick utilityStick = new Joystick(3);
    Button utilButton3 = new JoystickButton(utilityStick, 3);
    Button utilButton4 = new JoystickButton(utilityStick, 4);
    Button utilButton5 = new JoystickButton(utilityStick, 5);
    Button utilButton6 = new JoystickButton(utilityStick, 6);
    Button utilButton7 = new JoystickButton(utilityStick, 7);
    Button utilButton8 = new JoystickButton(utilityStick, 8);
    Button utilButton9 = new JoystickButton(utilityStick, 9);
    DriverStation ds = DriverStation.getInstance();
    public Kinect kin = Kinect.getInstance();
    public KinectStick kR = new KinectStick(2);
    public KinectStick kL = new KinectStick(1);
    static DigitalInput sensA = new DigitalInput(RobotMap.sensA);
    static DigitalInput sensB = new DigitalInput(RobotMap.sensB);
    static DigitalInput sensC = new DigitalInput(RobotMap.sensC);
    public static UpdateSensorValues ballSensors = new UpdateSensorValues();
    public static NetworkTable shooterRotate = NetworkTable.getTable(
            "ShooterRotateCommand");
    public static NetworkTable targetTable = NetworkTable.getTable(
            "Target Table");
    public double offSet = 0.0;
    public static double[] targetInfo = new double[2];
    public static boolean targetFound = false;

    public OI() {
        leftButton1.whileHeld(new GetFeederBalls(1.0, true));
        leftButton4.whileHeld(new TeleopControlledAim(-.5));
        leftButton5.whileHeld(new TeleopControlledAim(.5));

        leftButton2.whileHeld(new ControlBridgeArm(-1));
        leftButton3.whileHeld(new ControlBridgeArm(1));

        rightButton1.whileHeld(new AutoShot());
        rightButton2.whileHeld(new MainConveyor(.75, true));
        rightButton3.whileHeld(new MainConveyor(-.75, true));
        rightButton5.whileHeld(new AutoAim());
        rightButton6.whenPressed(new SetIntake(1));
        rightButton7.whenPressed(new SetIntake(-1));
        rightButton8.whenPressed(new SetIntake(0));

        utilButton3.whenPressed(new AdjustOffSetPower(AdjustOffSetPower.NULL));
        utilButton4.whenPressed(new AdjustOffSetPower(AdjustOffSetPower.DOWN));
        utilButton5.whenPressed(new AdjustOffSetPower(AdjustOffSetPower.UP));
        utilButton6.whenPressed(new ChangeConveyorDefault(false));
        utilButton7.whenPressed(new ChangeConveyorDefault(true));
        utilButton8.whenPressed(new ChangeBallCount(-1));
        utilButton9.whenPressed(new ChangeBallCount(1));

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
        return -topPower; //this needs to be changed back when they wire it correctly
    }

    public double getBotWheels() {
        double botPower = ds.getAnalogIn(2);
        botPower = botPower - 1;//CHANGED FROM *.4-1, it now corresponds more directly to jag inputs- inputs from 0 to 2 are -1 to 1 on motors
        return botPower;
    }

    public double getKinLeftY() {
        return kL.getY();
    }

    public double getKinRightY() {
        return kR.getY();
    }

    public double getKinHip() {
        return (kin.getSkeleton().GetHipCenter().getX() - kin.getSkeleton().GetShoulderCenter().getX()) * 10;
    }
}
