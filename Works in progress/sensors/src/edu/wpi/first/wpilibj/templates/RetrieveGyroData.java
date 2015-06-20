package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RetrieveGyroData {

    SendableGyro gyroUsed;

    public void RetrieveGyroData(SendableGyro gy, int gyroChan) { //, int period
        gyroUsed = gy;
        gyroUsed = new SendableGyro(gyroChan);
        gyroUsed.setUpdatePeriod(.2);
        gyroUsed.reset();
    }

    public NetworkTable getTable() {
        return gyroUsed.getTable();
    }
}
//    public double getGyroAngle() {
//        return gyroUsed.getAngle();
//    }