package edu.Team2974.IntegratedBot.commands;

import edu.Team2974.IntegratedBot.OI;
import edu.Team2974.IntegratedBot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

    public static OI oi;
    public static CenterConveyor conveyor = new CenterConveyor();
    public static DriveTrain drive = new DriveTrain();
    public static Shooter shot = new Shooter();
    public static Turret turret = new Turret();
    public static TopShooterConveyor feeder = new TopShooterConveyor();
    public static Intake intake = new Intake();

    public static void init() {
        oi = new OI();
        drive.setInvertedSide(false);

        //SmartDashboard.putData(conveyor);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
