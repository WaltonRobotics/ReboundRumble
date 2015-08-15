package edu.team2974.PeachTreeCode.commands;

import edu.team2974.PeachTreeCode.subsystems.Turret;
import edu.team2974.PeachTreeCode.subsystems.Intake;
import edu.team2974.PeachTreeCode.subsystems.TopShooterConveyor;
import edu.team2974.PeachTreeCode.subsystems.BridgeArm;
import edu.team2974.PeachTreeCode.subsystems.CenterConveyor;
import edu.team2974.PeachTreeCode.subsystems.DriveTrain;
import edu.team2974.PeachTreeCode.subsystems.Shooter;
import edu.team2974.PeachTreeCode.OI;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

    public static OI oi;
    public static CenterConveyor conveyor = new CenterConveyor();
    public static DriveTrain drive = new DriveTrain();
    public static Shooter shot = new Shooter();
    public static Turret turret = new Turret();
    public static TopShooterConveyor feeder = new TopShooterConveyor();
    public static Intake intake = new Intake();
    public static BridgeArm bridgeArm = new BridgeArm();

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
