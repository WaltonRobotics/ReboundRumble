package edu.team2974.StLouisPrep;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateSensorValues {
//moves all subsystem code of senors to one easily accessible spot

    public int balls;
    public boolean betweenAAndB;
    public boolean oldA;
    public boolean oldB;
    public boolean oldC;

    public UpdateSensorValues() {
    }

    /**
     * Changes the ball count by a specific amount
     *
     * @param change the amount to change the ball count by
     */
    public void changeTotalBallCount(double change) {
        balls += change;
    }

    /**
     * Updates the ball count and whether or not there is a ball between sensors
     *
     * @return the number of balls in the system
     */
    public int getTotalBalls() {
        /*
         * Sensor A is tripped but wasnt before so a new ball is in the system
         * adds a ball and sets b/w a and b to true, since that is where the new
         * ball is in the system
         */
        if (getA() && !oldA) {//a is tripped but wasnt before add ball and one b/w a and b
            addBall();
            betweenAAndB = true;
        } /*
         * Subtracts from the ball count if the last sensor had a ball in the 
         * last cycle and no longer does in this cycle. So if a ball was shot
         * it subtracts from the ball count.
         */ else if (!getC() && oldC) {
            removeBall();
        } /*
         * The ball that entered the system is now at B and not at A
         * so the ball is no longer between sensors A and B
         */ else if (getB() && !oldB && !getA()) {
            betweenAAndB = false;
        } /*
         * If there was a ball at and A and not in this cycle, and there was
         * a ball at B but not in this cycle, then there must be a ball between
         * A and B
         */ else if (!getB() && oldB && (!getA() && oldA)) {//not in b,was before,not in a but was before
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

    /**
     * Adds one to the ball count if the count is not greater than or equal to
     * three
     *
     * @return a boolean that is true when one is added and false if not
     */
    public boolean addBall() {
        if (balls >= 3) {
            return false;
        }
        balls++;
        return true;
    }

    /**
     * Removes 1 from the ball count if the ball count is is not less than or
     * equal to 0
     *
     * @return a boolean that is true when one is subtracted and false if not
     */
    public boolean removeBall() {
        if (balls <= 0) {
            return false;
        }
        balls--;
        return true;
    }

    /**
     * Displays all relevant information on SD
     */
    public void update() {
        //DashConnection.getInstance().writeToDashboard(balls);
    }
}