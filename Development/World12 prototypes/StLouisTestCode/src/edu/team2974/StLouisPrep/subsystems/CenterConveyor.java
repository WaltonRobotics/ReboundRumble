package edu.team2974.StLouisPrep.subsystems;

import edu.team2974.StLouisPrep.RobotMap;
import edu.team2974.StLouisPrep.commands.MainConveyor;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterConveyor extends Subsystem {

    private CANJaguar jagBigConveyor;
    public boolean auto = true;

    public CenterConveyor() {
        try {
            jagBigConveyor = new CANJaguar(RobotMap.jagBigConveyor);
            jagBigConveyor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagBigConveyor.enableControl();

        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    protected void initDefaultCommand() {
        System.out.println("conveyer initDefaultCommand");
        /*
         * when the conveyor is not being used in any other commands
         * then the conveyor is zeroed out
         */
        setDefaultCommand(new MainConveyor(0.0, true));
    }

    public void setDefault(Command c) {
       //allows the default command to be set outside of the subsystem
        setDefaultCommand(c);
    }

    public double getBigConv() {
        try {
            return jagBigConveyor.getX();
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
        return 0.0;
    }

    public void setBigConv(double update) {
        try {
            jagBigConveyor.setX(update);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    /**
     * Sets the conveyor's motor to zero
     */
    public void obliterate() {
        try {
            //jagConv.setX(0);         
            jagBigConveyor.setX(0);
        } catch (CANTimeoutException ex) {
            RobotLogger.getInstance().logMessage(ex.getMessage());
        }
    }

    public void update() {
        SmartDashboard.putBoolean("Auto Conveyor On", auto);
    }
}