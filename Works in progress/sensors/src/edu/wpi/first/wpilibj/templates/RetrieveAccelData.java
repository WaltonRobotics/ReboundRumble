package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboardData;

public class RetrieveAccelData implements SmartDashboardData {

    Accelerometer accel;
    SmartDashboard board;
    NetworkTable AccelSpeedTable;

    public void RetrieveAccelData(Accelerometer Acceler, int port) {
        accel = Acceler;
        accel = new Accelerometer(port);
        board = new SmartDashboard();
        AccelSpeedTable = new NetworkTable();
        AccelSpeedTable.putDouble("Accel Speed", accel.getAcceleration());
        SmartDashboard.putDouble("Accel Speed", accel.getAcceleration());
    }

    public String getType() {
        return "Accel Speed";
    }

    public NetworkTable getTable() {
        return AccelSpeedTable;
    }
}
//    public double getAccel() {
//        return accel.getAcceleration();
//    }
