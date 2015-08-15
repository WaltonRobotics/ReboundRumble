package edu.team2974.PeachTreeCode.commands;

import edu.team2974.PeachTreeCode.SpeedQuadruple;
import edu.team2974.PeachTreeCode.SpeedSet;

public class DriveWithJoysticks extends CommandBase {
    
    
    public DriveWithJoysticks() {
        
        requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
        double[] instructions;
        if(oi.rightStick.getRawButton(4))
        {
            instructions = getReducedInstructions(4.0);
        }
        else if(oi.utilityStick.getRawButton(1))
        {
            instructions = getReducedInstructions(3.0);
        }
        else
        {
            instructions = getMovementInstructions();
        }
        //gets raw speeds
        SpeedSet speedsNew = getSpeeds(instructions);

        //reduces speeds to correct range;
        speedsNew.reduce();

        //handles overDrive
        speedsNew.square();

        //speedsNew = handleUnderDrive(speedsNew, underDrive);

        //limits acceleration
        speedsNew = accelerationLimit(speedsNew);

       
        drive.setSpeeds(speedsNew);
        drive.speedsPrevious = (SpeedQuadruple) speedsNew;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public double[] getMovementInstructions() {

        double forward = (oi.getLeftY() + oi.getRightY()) / 2;
        double turn = (oi.getLeftY() - oi.getRightY()) / 2;
        double sideways = -(oi.getLeftX() + oi.getRightX()) / 2;

        double[] instructions = new double[3];

        instructions[0] = forward;
        instructions[1] = sideways;
        instructions[2] = turn;

        return instructions;
    }
    
    public double[] getReducedInstructions(double divisor) {

        double forward = (oi.getLeftY() + oi.getRightY()) / divisor;
        double turn = (oi.getLeftY() - oi.getRightY()) / divisor;
        double sideways = -(oi.getLeftX() + oi.getRightX()) / divisor;

        double[] instructions = new double[3];

        instructions[0] = forward;
        instructions[1] = sideways;
        instructions[2] = turn;

        return instructions;
    }

    //generates an initial SpeedQuadruple based on joystick values
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

    public SpeedSet accelerationLimit(SpeedSet speeds) {
        speeds.limitTo(drive.speedsPrevious);
        return speeds;
    }
}
