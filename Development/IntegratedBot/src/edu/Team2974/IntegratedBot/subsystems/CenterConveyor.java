package edu.Team2974.IntegratedBot.subsystems;

import edu.Team2974.IntegratedBot.RobotMap;
import edu.Team2974.IntegratedBot.commands.MainConveyor;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterConveyor extends Subsystem {

    private CANJaguar jagBigConveyor;
    public boolean auto=true;


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
        setDefaultCommand(new MainConveyor(0.0,false));
    }
    
    public void setDefault(Command c)
    {
        setDefaultCommand(c);
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
        try {
            //jagConv.setX(0);         
            jagBigConveyor.setX(0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

 
    public void update()
    {
        SmartDashboard.putBoolean("Auto Conveyor On", auto);
    }
   
}
//jagSmallConv.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
//jagSmallConv.enableControl();
//relayIntake = new Relay(RobotMap.relayIntake);
//jagConv = new CANJaguar(RobotMap.jagConv);
//jagConv.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
//jagConv.enableControl();
//private CANJaguar jagConv;
//private boolean newA;
//private boolean newB;
//private boolean newC;
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