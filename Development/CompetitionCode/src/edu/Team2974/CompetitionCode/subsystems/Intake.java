package edu.Team2974.CompetitionCode.subsystems;

import edu.Team2974.CompetitionCode.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    private Relay relayIntake;

    public Intake() {
        relayIntake = new Relay(RobotMap.relayIntake);
    }

    protected void initDefaultCommand() {
    }

    public void setIntakeInward() {
        relayIntake.set(Relay.Value.kForward);
    }

    public void setIntakeOutward() {
        relayIntake.set(Relay.Value.kReverse);
    }

    public void setIntakeOFF() {
        relayIntake.set(Relay.Value.kOff);
    }
}
