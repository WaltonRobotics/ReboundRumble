package edu.team2974.StLouisPrep.commands;

import edu.team2974.StLouisPrep.SpeedQuadruple;
import edu.team2974.StLouisPrep.SpeedSet;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveWithKinect extends CommandBase {

    private Skeleton skel;
    private Skeleton.Joint head;
    private Skeleton.Joint midChest;
    private Skeleton.Joint leftAnkle;
    private Skeleton.Joint rightAnkle;
    double yL = 0, yR = 0, headX = 0, ankleDist = 0;
    private CommandGroup kinAuto;

    public DriveWithKinect() {
        requires(drive);
        requires(bridgeArm);
        //System.out.println("DWK construct");
    }

    protected void initialize() {
        //System.out.println("DWK init");
        kinAuto = new KinectAutoGroup();
    }

    protected void execute() {

        //System.out.println("DWK Exexcute");

        skel = oi.kin.getSkeleton();
        head = skel.GetHead();
        midChest = skel.GetShoulderCenter();
        headX = head.getX() - midChest.getX();

        leftAnkle = skel.GetAnkleLeft();
        rightAnkle = skel.GetAnkleRight();
        ankleDist = Math.abs(leftAnkle.getX() - rightAnkle.getX());

        if (ankleDist < .5) {//if the controler wants to drive
//            if (headX > .045) {
//                bridgeArm.armUp();
//            } else if (headX < -.045) {
//                bridgeArm.armDown();
//            } else {
//                bridgeArm.armOFF();
//            }
            if (headX < -.04) {
                bridgeArm.armDown();
            } else if (headX > .02 && bridgeArm.getPosition() < 3.0) {
                bridgeArm.armUp();
            } else {
                bridgeArm.armOFF();
            }

            double[] instructions;//instructions from the driver
            instructions = getMovementInstructions();
            SpeedSet speedsNew = getSpeeds(instructions);//gets raw speeds
            speedsNew.reduce();//reduces speeds to correct range;
            speedsNew.square();//handles overDrive
            speedsNew = accelerationLimit(speedsNew);//limits acceleration
            drive.setSpeeds(speedsNew);
            drive.speedsPrevious = (SpeedQuadruple) speedsNew;
        } else {//if controller wants to shoot
            //System.out.println("Kienct auto shooting");
            kinAuto.start();

            double[] zero = {0, 0, 0};
            SpeedSet speedsNew = getSpeeds(zero);
            drive.setSpeeds(speedsNew);
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public double[] getMovementInstructions() {

        double forward = (oi.getKinLeftY() + oi.getKinRightY()) / 2;
        double turn = (oi.getKinLeftY() - oi.getKinRightY()) / 2;
        //System.out.println("gMI: DWK: kinLeft: " + oi.getKinLeftY() + " kinRight: " + oi.getKinRightY());
        double sideways = (oi.getKinHip() * -1);
        if (sideways <= .0 && sideways >= -.01) {
            sideways = 0;
        }

//        if (turn <= .1) {
//            turn /= 2;
//        }
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
//    public double[] getReducedInstructions(double divisor) {
//
//        double forward = (oi.getLeftY() + oi.getRightY()) / divisor;
//        double turn = (oi.getLeftY() - oi.getRightY()) / divisor;
//        double sideways = -(oi.getLeftX() + oi.getRightX()) / divisor;
//        //double sideways = 0;
//        double[] instructions = new double[3];
//
//        instructions[0] = forward;
//        instructions[1] = sideways;
//        instructions[2] = turn;
//
//        return instructions;
//    }
//        if (ankleDist >= .5) {
//            shootFirst = true;
//        } else {
//            shootFirst = false;
//        }     
//if (oi.kin.getNumberOfPlayers() > 0) {
        //}