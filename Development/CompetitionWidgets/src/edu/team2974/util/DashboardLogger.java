/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doug Neumann
 */
public class DashboardLogger {  //create and name logger

    private static DashboardLogger _instance;
    public static final String LOGFILE_NAME = "DashboardLogger.txt";
    private BufferedWriter fileWriter;

    public static DashboardLogger getInstance() {
        //ensure logger has been created
        if (_instance == null) {
            _instance = new DashboardLogger();
        }
        return _instance;
    }

    private DashboardLogger() {
        try {
            //creating file for fileWriter to write in
            File f = new File(LOGFILE_NAME);
            if (!f.exists()) {
                f.createNewFile();
            }
            fileWriter = new BufferedWriter(new FileWriter(f));
            //fileWriter new BufferedWriter(f);
        } catch (IOException e) {
            //logging error message
            Logger.getLogger(DashboardLogger.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    public void logMessage(String msg) {
        try {
            //prints date and time before message
            Date now = GregorianCalendar.getInstance().getTime();
            fileWriter.write(
                    DateFormat.getDateTimeInstance().format(now) + ": " + msg);
            fileWriter.newLine();
        } catch (IOException ex) {
            //error message logging
            Logger.getLogger(DashboardLogger.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}

