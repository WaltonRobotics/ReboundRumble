package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.SpeedQuadruple;
import edu.team2974.StLouisPrep.SpeedSet;

public class DriveWithJoysticks extends CommandBase {

    /**
     * This is where driving for the shooterBot drivetrain is handled
     * Though it has the beginnings of structure X it currently can only translate
     * instructions into a SpeedQuadruple. See logo bot code for full structure X
     */
    public DriveWithJoysticks() {

        requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
        //instructions from the driver
        double[] instructions;
        
        /*
         * The following further reduces the speed of the robot by dividing
         * the instructions from the joysticks if the driver is holding down
         * a specific button. 
         */
        if (oi.rightStick.getRawButton(4)) {
            instructions = getReducedInstructions(4.0);
        } else if (oi.utilityStick.getRawButton(1)) {
            instructions = getReducedInstructions(3.0);
        } else {
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
        //speedsNew = accelerationLimit(speedsNew);


        drive.setSpeeds(speedsNew);
        drive.speedsPrevious = (SpeedQuadruple) speedsNew;
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    /**
     * 
     * @return translates current joystick position into doubles in an array representing forward, sideways, and turn motion.
     */
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
