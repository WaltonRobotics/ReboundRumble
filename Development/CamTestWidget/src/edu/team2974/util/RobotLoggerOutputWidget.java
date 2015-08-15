package edu.team2974.util;

import edu.wpi.first.smartdashboard.gui.DashboardFrame;
import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.wpilibj.networking.NetworkListener;
import edu.wpi.first.wpilibj.networking.NetworkTable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doug Neumann
 */
public class RobotLoggerOutputWidget extends StaticWidget
{

    public static final String NAME = "RobotLoggerOutputWidget";

    public class GCThread extends Thread
    {

        boolean destroyed = false;

        @Override
        public void run() {
            while (!destroyed) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                }
                System.gc();
            }
        }

        public void destroy() {
            destroyed = true;
            interrupt();
        }
    }

    public class BGThread extends Thread
    {

        boolean destroyed = false;
        Runnable draw = new Runnable()
        {

            public void run() {
                DashboardFrame.getInstance().getPanel().repaint(getBounds());
            }
        };

        public BGThread() {
            super("Robot Logger Output");
        }

        @Override
        public void run() {
            while (!destroyed) {
                //
            }
            try {
                bw.close();
            } catch (IOException ex) {
                DashboardLogger.getInstance().logMessage(ex.getMessage());
            }
        }

        @Override
        public void destroy() {
            destroyed = true;
        }
    }

    public class MyListener implements NetworkListener
    {

        @Override
        public void valueChanged(String string, Object o) {
            try {
                String nowString = DateFormat.getDateTimeInstance().format(
                        new Date());
                bw.write(nowString);
                bw.write(": ");
                bw.write(string);
                bw.write(" :: ");
                bw.write(o.toString());
                bw.newLine();
                bw.flush();
            } catch (IOException ex) {
                DashboardLogger.getInstance().logMessage(ex.getMessage());
            }
           //}
        }

        @Override
        public void valueConfirmed(String string, Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    //
    private String lastMessage = "NO MESSAGE FROM ROBOT";
    private BGThread bgThread = new BGThread();
    private GCThread gcThread = new GCThread();
    private static final String LOGFILE_NAME = "RobotLogger.txt";
    private BufferedWriter bw;
    private NetworkTable table = NetworkTable.getTable("robotLogger");

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(lastMessage, 0, 0);
//        g.drawRect(10, 25, 10, 25);
//g.fillRect(10, 25, 10, 25);
    }

    @Override
    public void init() {
        try {
            File f = new File(LOGFILE_NAME);
            if (!f.exists()) {
                f.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(f));
            bw.write("Logfile initialized.");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.addListener("log", new MyListener());

        setPreferredSize(new Dimension(75, 25));

        bgThread.start();
        gcThread.start();
        revalidate();
        DashboardFrame.getInstance().getPanel().repaint(getBounds());
    }

    @Override
    public void propertyChanged(Property prprt) {
        //
    }
}
