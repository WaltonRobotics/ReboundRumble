package edu.Team2974.conveyor.commands;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.Team2974.conveyor.OI;
import edu.Team2974.conveyor.subsystems.Conveyor;

public abstract class CommandBase extends Command {

    public static OI oi;
    public static Conveyor conveyor = new Conveyor();

    public static void init() {
        oi = new OI();

        //SmartDashboard.putData(conveyor);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
