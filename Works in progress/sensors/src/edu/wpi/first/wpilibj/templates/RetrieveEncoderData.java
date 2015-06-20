package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboardData;

public class RetrieveEncoderData implements SmartDashboardData {

    CANJaguar jagUsed;
    SmartDashboard board;
    NetworkTable SpeedTable;

    public void RetrievEncoderData(CANJaguar jag, int jagNum) throws CANTimeoutException {
        jagUsed = jag;
        jagUsed = new CANJaguar(jagNum);
        jagUsed.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
        board = new SmartDashboard();
        SpeedTable = new NetworkTable();
        SpeedTable.putDouble("Encoder Speed", jagUsed.getSpeed());
        SmartDashboard.putDouble("Encoder Speed", jagUsed.getSpeed());
    }

    public String getType() {
        return "Encoder Speed";
    }

    public NetworkTable getTable() {
        return SpeedTable;
    }

}
//    public double getEncoderSpeed() throws CANTimeoutException {
//        return jagUsed.getSpeed();
//    }
//
//    public double getEncoderPosition() throws CANTimeoutException {
//        return jagUsed.getPosition();
//    }