package edu.wpi.first.CleanMecanum.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.CleanMecanum.OI;
import edu.wpi.first.CleanMecanum.subsystems.DriveTrain;
import edu.wpi.first.CleanMecanum.subsystems.ExampleSubsystem;

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
    public static DriveTrain drive;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        drive = new DriveTrain();

        drive.setInvertedSide(false);//boolean is true to invert right, false for left
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drive);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
