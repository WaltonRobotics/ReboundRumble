package edu.Team2974.ProtoBot.commands;

import edu.Team2974.ProtoBot.OI;
import edu.Team2974.ProtoBot.subsystems.ExampleSubsystem;
import edu.Team2974.ProtoBot.subsystems.Conveyor;
import edu.Team2974.ProtoBot.subsystems.DriveTrain;
import edu.Team2974.ProtoBot.subsystems.Intake;
import edu.Team2974.ProtoBot.subsystems.Shooter;
import edu.Team2974.ProtoBot.subsystems.ShooterConveyor;
import edu.Team2974.ProtoBot.subsystems.Turret;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static Conveyor conveyor = new Conveyor();
    public static DriveTrain drive = new DriveTrain();
    public static Shooter shot = new Shooter();
    public static Turret turret = new Turret();
    public static ShooterConveyor feeder = new ShooterConveyor();
    public static Intake intake = new Intake();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        drive.setInvertedSide(false);

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(exampleSubsystem);
        SmartDashboard.putData(conveyor);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
