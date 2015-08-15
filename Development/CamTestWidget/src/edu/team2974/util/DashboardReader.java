/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class DashboardReader {
    //
    private static DashboardReader _instance;
    public static final String READFILE_NAME = "RoboInput.txt";
    private BufferedReader fileReader;
    
    public static DashboardReader getInstance() {
        //ensure reader has been created
        if (_instance == null) {
            _instance = new DashboardReader();
        }
        return _instance;
    }
    
    private DashboardReader() 
    {
        File f = new File(READFILE_NAME);
        if(f.exists())
        {
            try {
                fileReader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DashboardReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
