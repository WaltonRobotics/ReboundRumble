package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RetrieveUltrasonicData {

    Ultrasonic ultra;
    Ultrasonic.Unit kInches;
    SmartDashboard board;

    public void RetrieveUltrasonicData(Ultrasonic ult, int ping, int echo) {
        ultra = ult;
        ultra = new Ultrasonic(ping, echo, kInches);
    }

    public double getDistanceInInches() {
        return ultra.getRangeInches();
    }
}
