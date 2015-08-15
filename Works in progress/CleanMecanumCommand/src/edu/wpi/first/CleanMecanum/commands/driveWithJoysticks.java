package edu.wpi.first.CleanMecanum.commands;

import edu.wpi.first.CleanMecanum.OI;
import edu.wpi.first.CleanMecanum.SpeedPair;
import edu.wpi.first.CleanMecanum.SpeedQuadruple;
import edu.wpi.first.CleanMecanum.SpeedSet;

/**
 *
 * @author Chris
 */
public class driveWithJoysticks extends CommandBase {

    public driveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
            double[] instructions = getMovementInstructions();
            //gets raw speeds
            SpeedSet speedsNew = getSpeeds(instructions);

            //reduces speeds to correct range;
            speedsNew.reduce();

            //handles overDrive
            speedsNew.square();

            //speedsNew = handleUnderDrive(speedsNew, underDrive);

            //limits acceleration
            speedsNew = accelerationLimit(speedsNew);

            //sets the processed motor speeds, casted to be a pair
            drive.setSpeeds(speedsNew);
            drive.speedsPrevious = (SpeedQuadruple)speedsNew;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public double[] getMovementInstructions(){
  
        double forward = (oi.getLeftY() + oi.getRightY()) / 2;
        double turn = (oi.getLeftY() - oi.getRightY()) / 2;
        double sideways = -(oi.getLeftX() + oi.getRightX()) / 2;

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
    
    public SpeedSet accelerationLimit(SpeedSet speeds){
             speeds.limitTo(drive.speedsPrevious);
             return speeds;
     }
 }

