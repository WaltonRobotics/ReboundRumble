package edu.wpi.first.wpilibj.ShooterPrototype.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ShooterPrototype.OI;
import edu.wpi.first.wpilibj.ShooterPrototype.subsystems.Shooter;
import edu.wpi.first.wpilibj.ShooterPrototype.subsystems.LazySusan;
import edu.wpi.first.wpilibj.ShooterPrototype.subsystems.PowerCalc;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    
    public static Shooter shot = new Shooter();
    
    public static LazySusan susan = new LazySusan();
    
    public static PowerCalc powerCalc = new PowerCalc(42, 54);
    
    //public static VisionCamera Camera = new Camera();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(shot);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
