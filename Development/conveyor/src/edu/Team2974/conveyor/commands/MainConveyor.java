package edu.Team2974.conveyor.commands;

public class MainConveyor extends CommandBase {

    private double updatedSpeed;
    private double lastSpeed;
    private double change;
    public static double CONV_UP = 1.0, CONV_DOWN = -1.0, CONV_ZERO = 0.0;

    public MainConveyor(double diff) {
        change = diff;
        requires(conveyor);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (change == 55.0) {
            if (conveyor.getTotalBalls() == 0) {
                updatedSpeed = CONV_ZERO;
                System.out.println("place 1");
            } else {
                if (!conveyor.getC()) {
                    updatedSpeed = CONV_UP;
                    System.out.println("place 2");
                } else {
                    if ((conveyor.getTotalBalls() == 2 && !conveyor.getB()) && conveyor.getBetweenAAndB()) {
                        updatedSpeed = CONV_UP;
                        System.out.println("place 3");
                    } else if (conveyor.getTotalBalls() == 2 && !conveyor.getB() && !conveyor.getBetweenAAndB()) {
                        updatedSpeed = CONV_DOWN;
                        System.out.println("place 4");
                    } else if (conveyor.getTotalBalls() == 3 && conveyor.getBetweenAAndB()) { //assuming only 1 is between A and B (I think?)
                        updatedSpeed = CONV_UP;
                        System.out.println("place 5");
                    } else if (conveyor.getTotalBalls() == 3 && !conveyor.getBetweenAAndB() && !conveyor.getB()) { //don't forget to turn off the intake roller for this situtation
                        updatedSpeed = CONV_DOWN;
                        System.out.println("place 6");
                    } else {
                        updatedSpeed = CONV_ZERO;
                        System.out.println("place 7");
                    }
                }
            }
            conveyor.setBigConv(-updatedSpeed);
        } else {
            conveyor.setBigConv(change);
        }
    }

    protected boolean isFinished() {
        return (conveyor.getBigConv() == (lastSpeed + updatedSpeed));
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
