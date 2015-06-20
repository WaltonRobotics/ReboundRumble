package edu.Team2974.CompetitionCode.commands;

import edu.Team2974.CompetitionCode.subsystems.Turret;
import edu.Team2974.CompetitionCode.subsystems.Intake;
import edu.Team2974.CompetitionCode.subsystems.TopShooterConveyor;
import edu.Team2974.CompetitionCode.subsystems.BridgeArm;
import edu.Team2974.CompetitionCode.subsystems.CenterConveyor;
import edu.Team2974.CompetitionCode.subsystems.DriveTrain;
import edu.Team2974.CompetitionCode.subsystems.Shooter;
import edu.Team2974.CompetitionCode.OI;
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
