package edu.team2974.StLouisPrep;

import edu.team2974.StLouisPrep.commands.AutonomousTest;
import edu.team2974.StLouisPrep.commands.ChangeConveyorDefault;
import edu.team2974.StLouisPrep.commands.CommandBase;
import edu.team2974.StLouisPrep.commands.DriveWithJoysticks;
import edu.team2974.StLouisPrep.commands.DriveWithKinect;
import edu.team2974.StLouisPrep.commands.ZeroShooterJaguars;
import edu.team2974.robot.util.FileReader;
import edu.team2974.robot.util.RobotLogger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMain extends IterativeRobot {

    FileReader reader;
    CommandGroup autonomous;
    Watchdog puppy;
    Scheduler currentScheduler;
    int oldInput;

    public void robotInit() {

        RobotLogger.getInstance().logMessage("ROBOInit");
        puppy = Watchdog.getInstance();
        puppy.setExpiration(3.0);

        currentScheduler = Scheduler.getInstance();
        //SmartDashboard.putData(currentScheduler);

        CommandBase.init();
        OI.ballSensors.balls = 0;
        puppy.feed();
        oldInput = -1;
    }

    public void disabledInit() {
        /*
         * Shuts down autonomous when the robot is disabled if autonomous
         * has been initialized
         */
        if (autonomous != null) {
            RobotLogger.getInstance().logMessage("Canceling AutonomousTest");
            autonomous.cancel();
        }
    }

    public void disabledContinuous() {
        //makes sure the watchdog is still being fed during disabled
        puppy.feed();
    }

    public void disabledPeriodic() {
    }

    public void autonomousInit() {
        RobotLogger.getInstance().logMessage("Starting AutonomousTest INIT");

        OI.ballSensors.balls = 2;
        autonomous = new AutonomousTest();
        RobotLogger.getInstance().logMessage("Back from AutonomousTest INIT");
        RobotLogger.getInstance().logMessage("AutonomousTest start");
        if (CommandBase.oi.kin.getNumberOfPlayers() == 0) {
            autonomous.start();
        } else {
            CommandBase.drive.setDefault(new DriveWithKinect());
        }
        puppy.feed();

    }

    public void autonomousContinuous() {
        /*
         * We don't have our logic in autonomous continuous because continuous is called to
         * fast. The scheduler will start to bog down crio side processing
         * because there will be too many commands firing off.
         */
    }

    public void autonomousPeriodic() {
        //we want the SD updated every cycle, so updateDash() is called here.
        //updateDash();
        puppy.feed();
        /*
         * The scheduler does the following when run() is called:
         * Poll the Buttons
         * Execute/Remove the Commands
         * Send values to SmartDashboard
         * Add Commands
         * Add Defaults
         */
        currentScheduler.run();
        puppy.feed();
    }

    public void teleopInit() {
        //Cancels autonomous if it was initiated. 
        if (autonomous != null) {
            RobotLogger.getInstance().logMessage("Canceling AutonomousTest");
            autonomous.cancel();
        }
        /*
         * Adding the change conveyor default command to the scheduler 
         * will turn the the auto conveyor logic as the default if the parameter
         * is false and will zero the motors as default if true. 
         * 
         * Adding the ZeroShooterJaguars command will zero out the shooter next
         * time the scheduler runs.
         */
        currentScheduler.add(new ChangeConveyorDefault(true));
        currentScheduler.add(new ZeroShooterJaguars());
        puppy.feed();
        CommandBase.drive.setDefault(new DriveWithJoysticks());
    }

    public void teleopPeriodic() {
        //we want the SD updated every cycle, so updateDash() is called here.
        //updateDash();
        puppy.feed();
        /*
         * The scheduler does the following when run() is called:
         * Poll the Buttons
         * Execute/Remove the Commands
         * Send values to SmartDashboard
         * Add Commands
         * Add Defaults
         */
        currentScheduler.run();
        puppy.feed();
    }

    public void teleopContinuous() {
        /*
         * We don't have our logic in continuous because continuous is called to
         * fast. The scheduler will start to bog down crio side processing
         * because there will be too many commands firing off.
         */
    }

    /**
     * This method is used to send updates of the robot's state to the
     * SmartDashboard. This is done by calling the update methods of the
     * different subsystems which in turn put relevant data into the
     * SmartDashboard network table.
     */
    public void updateDash() {
        puppy.feed();
        //DashConnection.getInstance().writeToDashboard(OI.ballSensors.balls);
//        int input = dashConnection.readFromDashboard();
//        
//        if(input!=oldInput)
//        {
//            System.out.println(input);
//            oldInput = input;
//        }
        //DashConnection.getInstance().readFromDashboardTarget();
        /*
         * Currently the other update methods are commented out because the 
         * information floods the dashboard and is noncritical to the drivers.
         * For a testing setup it would be wise to uncomment the subsystem
         * you are trying to debug.
         */
        //CommandBase.turret.update();
        //CommandBase.drive.update();
        //CommandBase.shot.update();
        //CommandBase.bridgeArm.update();
        //CommandBase.conveyor.update();
    }
}