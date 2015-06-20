package edu.Team2974.robot.util;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

//import javax.microedition.io.Connector;
/**
 *
 * @author Doug Neumann
 */
public class RobotLogger
{

    private static RobotLogger _instance;
    private NetworkTable table = NetworkTable.getTable("robotLogger");

    public static RobotLogger getInstance() {
        if (_instance == null) {
            _instance = new RobotLogger();
        }
        return _instance;
    }

    private RobotLogger() {
    }

    public void logMessage(String msg) {
        table.putString("log", msg);
    }
}
