package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first
public class RobotTemplate extends IterativeRobot {

    Gyro gy;
    Timer time;

    public void robotInit() {
        gy = new Gyro(1);
        time = new Timer();
    }

    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
        gy.free();
        gy.reset();
        // gy.setSensitivity(7);
        double ang = 0;
        time.start();
        while (time.get() <= 5000) {
            ang = gy.getAngle();
            System.out.println("Angle: " + ang);
        }
    }
}
