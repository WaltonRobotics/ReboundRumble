package edu.team2974.StLouisPrep.subsystems;

import edu.team2974.StLouisPrep.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.team2974.StLouisPrep.SpeedQuadruple;
import edu.team2974.StLouisPrep.SpeedSet;
import edu.team2974.StLouisPrep.commands.DriveWithJoysticks;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

    CANJaguar frontLeftJag;
    CANJaguar frontRightJag;
    CANJaguar backLeftJag;
    CANJaguar backRightJag;
    static final int TOUGHBOX_CODES_PER_REV = 360;
    public SpeedQuadruple speedsPrevious;
    private double rightInversion = 1;
    private double leftInversion = 1;

    public DriveTrain() {
        try {
//            frontLeftJag = new CANJaguar(RobotMap.frontLeftMotor);
//            frontRightJag = new CANJaguar(RobotMap.frontRightMotor);
//            backLeftJag = new CANJaguar(RobotMap.backLeftMotor);
//            backRightJag = new CANJaguar(RobotMap.backRightMotor);
            frontRightJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            frontLeftJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            backRightJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            backLeftJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            frontRightJag.configEncoderCodesPerRev(TOUGHBOX_CODES_PER_REV);
            frontLeftJag.configEncoderCodesPerRev(TOUGHBOX_CODES_PER_REV);
            backRightJag.configEncoderCodesPerRev(TOUGHBOX_CODES_PER_REV);
            backLeftJag.configEncoderCodesPerRev(TOUGHBOX_CODES_PER_REV);
            frontRightJag.setVoltageRampRate(35);
            frontLeftJag.setVoltageRampRate(35);
            backRightJag.setVoltageRampRate(35);
            backLeftJag.setVoltageRampRate(35);
            speedsPrevious = new SpeedQuadruple(0, 0, 0, 0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void setDefault(Command c) {
        // if no other command is using w
        setDefaultCommand(c);
    }

    //stops all motors
    public void stop() {
        SpeedQuadruple stop = new SpeedQuadruple(0, 0, 0, 0);
        setSpeeds(stop);
    }

    //turns the robot at a given speed- positive is right, negative is left. On a
    //scale from -1 to 1, will interpret all others as full speed.
    public void turn(double speed) {
        SpeedQuadruple turning = new SpeedQuadruple(speed, -speed, speed, -speed);
        turning.reduce();
        setSpeeds(turning);
    }

    //goes forward at a given speed- positive is forward, negative is backward.
    //Should be between -1 and 1, will interpret all others as full speed.
    public void goForward(double speed) {
        SpeedQuadruple forward = new SpeedQuadruple(speed, speed, speed, speed);
        forward.reduce();
        setSpeeds(forward);
    }

    /**
     *
     * @param instructions
     * @return
     */
    public SpeedSet getSpeeds(double[] instructions) {

        //translates array into meaningful movement variables
        double forward = instructions[0];
        double sideways = instructions[1];
        double turn = instructions[2];

        //translates movement variables into motor speeds.
        //motors run same direction to go forward.
        //left motors and right motors run opposite directions to turn.
        //front motors and back motors run opposite directions to go sideways.
        double frontL = forward + turn + sideways;
        double frontR = forward - turn - sideways;
        double backL = forward + turn - sideways;
        double backR = forward - turn + sideways;

        SpeedQuadruple speeds = new SpeedQuadruple(frontR, frontL, backR, backL);
        return speeds;
    }

    /**
     * sets the motor controllers based on a given quadruple and updates
     * speedsPrevious to reflect the change
     *
     * @param speedsNew the speeds that the motors will be set to, must be a
     * quadruple
     */
    public void setSpeeds(SpeedSet spdsNew) {
        if (frontLeftJag == null || frontRightJag == null || backLeftJag == null || backRightJag == null) {
            if (frontLeftJag == null) {
                SmartDashboard.putString("frontLeftJag", "is null");
            }
            if (frontRightJag == null) {
                SmartDashboard.putString("frontRightJag", "is null");
            }
            if (backLeftJag == null) {
                SmartDashboard.putString("backLeftJag", "is null");
            }
            if (backRightJag == null) {
                SmartDashboard.putString("backRightJag", "is null");
            }
            throw new NullPointerException("Null motor provided");
        }

        //casts into a quadruple
        SpeedQuadruple speedsNew = (SpeedQuadruple) spdsNew;

        try {
            frontRightJag.setX(rightInversion * speedsNew.front.right);
            frontLeftJag.setX(leftInversion * speedsNew.front.left);
            backRightJag.setX(rightInversion * speedsNew.back.right);
            backLeftJag.setX(leftInversion * speedsNew.back.left);
        } catch (CANTimeoutException e) {
            e.printStackTrace();
        }
        speedsPrevious = speedsNew;
    }

    //divides the pair by 2 if we are at a demo or overDrive is not engaged
    public SpeedSet handleUnderDrive(SpeedSet speeds, boolean underDrive) {
        if (underDrive) {
            speeds.divideBy(4);
            return speeds;
        } //if overDrive is not engaged, goes at half speed regardless of demoMode
        else {
            return speeds;
        }
    }

    public void zero() {
        SpeedQuadruple zero = new SpeedQuadruple(0, 0, 0, 0);
        setSpeeds(zero);
    }

    public void setInvertedSide(boolean right) {
        if (right) {
            rightInversion = -1;
            leftInversion = 1;
        } else {
            rightInversion = 1;
            leftInversion = -1;
        }
    }

    public void update() {
        try {
            SmartDashboard.putDouble("Front Left Jag", frontLeftJag.getX() * leftInversion);
            SmartDashboard.putDouble("Front Right Jag", frontRightJag.getX() * rightInversion);
            SmartDashboard.putDouble("Back Left Jag", backLeftJag.getX() * leftInversion);
            SmartDashboard.putDouble("Back Right Jag", backRightJag.getX() * rightInversion);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    protected void initDefaultCommand() {
    }
}