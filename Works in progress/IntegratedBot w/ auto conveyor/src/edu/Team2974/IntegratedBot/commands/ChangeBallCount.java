package edu.Team2974.IntegratedBot.commands;

import edu.Team2974.IntegratedBot.RobotMain;

public class ChangeBallCount extends CommandBase{
private int change;
    public ChangeBallCount(int num){
        change=num;
    }
    
    protected void initialize() {
    }

    protected void execute() {
        RobotMain.up.changeTotalBallCount(change);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
