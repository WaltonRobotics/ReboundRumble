package edu.Team2974.IntegratedBot;

public class UpdateSensorValues {
//moves all subsystem code of senors to one easily accessible spot

    public int balls;
    public boolean betweenAAndB;
    public boolean oldA;
    public boolean oldB;
    public boolean oldC;

    public UpdateSensorValues() {
    }

    public void changeTotalBallCount(double change) {
        balls += change;
    }

    public double getTotalBalls() {
        if (getA() && !oldA) {//a is tripped but wasnt before add ball and one b/w a and b
            balls++;
            betweenAAndB = true;
            //System.out.println("one ball added: " + balls);
        } else if (!getC() && oldC) {//c not tripped but was before,minus one ball
            balls--;
            //System.out.println("one ball fired: " + balls);
        } else if (getB() && !oldB && !getA()) {//is b, wasnt before, not in a
            betweenAAndB = false;
        } else if (!getB() && oldB && (!getA() && oldA)) {//not in b,was before,not in a but was before
            betweenAAndB = true;
        } else {
            //System.out.println("No change in totalBalls");
        }
        oldA = !OI.sensA.get();
        oldB = !OI.sensB.get();
        oldC = !OI.sensC.get();
        return balls;
    }

    public boolean getBetweenAAndB() {
        return betweenAAndB;
    }

    public boolean getA() {
        return !OI.sensA.get();
    }

    public boolean getB() {
        return !OI.sensB.get();
    }

    public boolean getC() {
        return !OI.sensC.get();
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
}
//System.out.println("sensor A: " + !sensA.get());
//System.out.println("sensor B: " + !sensB.get());
//System.out.println("sensor C: "+!sensC.get());