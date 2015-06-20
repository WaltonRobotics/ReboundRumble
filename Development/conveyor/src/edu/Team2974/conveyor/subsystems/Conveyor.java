package edu.Team2974.conveyor.subsystems;

import edu.Team2974.conveyor.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

public class Conveyor extends Subsystem {

    private CANJaguar jagBigConveyor;
    private Relay smallConv;
    private boolean oldA;
    private boolean oldB;
    private boolean oldC;
    private int balls;
    private boolean betweenAAndB;
    private DigitalInput sensA;
    private DigitalInput sensB;
    private DigitalInput sensC;

    public Conveyor() {
        try {
            jagBigConveyor = new CANJaguar(RobotMap.jagBigConveyor);
            jagBigConveyor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagBigConveyor.enableControl();

            smallConv = new Relay(RobotMap.smallConveyor);

            sensA = new DigitalInput(RobotMap.sensA);
            sensB = new DigitalInput(RobotMap.sensB);
            sensC = new DigitalInput(RobotMap.sensC);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void init() {
        balls = 0;
    }

    protected void initDefaultCommand() {
        System.out.println("conveyer initDefaultCommand");
    }

    public double getTotalBalls() {
        if (getA() && !oldA) {
            balls++;
            betweenAAndB = true;
            System.out.println("one ball added: " + balls);
        } else if (!getC() && oldC) {
            balls--;
            System.out.println("one ball fired: " + balls);
        } else if (getB() && !oldB && !getA()) {
            betweenAAndB = false;
        } else if (!getB() && oldB && (!getA()&& oldA)) {
            betweenAAndB = true;
        } else {
            //System.out.println("No change in totalBalls");
        }
        oldA = !sensA.get();
        oldB = !sensB.get();
        oldC = !sensC.get();
        return balls;
    }

    public void changeTotalBallCount(double change) {
        balls += change;
    }

    public boolean getBetweenAAndB() {
        return betweenAAndB;
    }

    public boolean addBall() {
        if (balls >= 3) {
            return false;
        }
        balls++;
        return true;
    }

    public boolean removeBall() {
        if (balls <= 0) {
            return false;
        }
        balls--;
        return true;
    }

    public void setSmallConv(double update) {
        if (update == 0) {
            smallConv.set(Relay.Value.kOff);
        } else if (update > 0) {
            smallConv.set(Relay.Value.kForward);
        } else if (update < 0) {
            smallConv.set(Relay.Value.kReverse);
        } else {
            System.out.println("You Failure");
        }
    }

    public double getBigConv() {
        try {
            return jagBigConveyor.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }

    public void setBigConv(double update) {
        try {
            jagBigConveyor.setX(update);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void obliterate() {
        //relayIntake.set(kOff);
        try {
            //jagConv.setX(0);
            jagBigConveyor.setX(0);
            smallConv.set(Relay.Value.kOff);
            System.out.println("Total Balls" + getTotalBalls());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public boolean getA() {
        //System.out.println("sensor A: " + !sensA.get());
        return !sensA.get();
    }

    public boolean getB() {
        //System.out.println("sensor B: "+!sensB.get());
        return !sensB.get();
    }

    public boolean getC() {
        // System.out.println("sensor C: "+!sensC.get());
        return !sensC.get();
    }
}
//    public void setIntake(int move) {//0=neutral, 1=forward, -1=reverse
//        //jagIntake.setX();
//        if (move == 0) {
//            relayIntake.set(kOff);
//        } else if (move > 0) {
//            relayIntake.set(kForward);
//        } else if (move < 0) {
//            relayIntake.set(kReverse);
//        } else {
//            System.out.println("You Failure");
//        }
//    }
//jagSmallConv.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
//jagSmallConv.enableControl();
//relayIntake = new Relay(RobotMap.relayIntake);
//jagConv = new CANJaguar(RobotMap.jagConv);
//jagConv.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
//jagConv.enableControl();
//    private Relay relayIntake;
//    private Relay.Value kOff;
//    private Relay.Value kForward;
//    private Relay.Value kReverse;
//private CANJaguar jagConv;
//private boolean newA;
//private boolean newB;
    //private boolean newC;