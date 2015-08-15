package edu.team2974.util;

import edu.wpi.first.smartdashboard.gui.DashboardFrame;
import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.gui.elements.Button;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.wpilibj.networking.NetworkTable;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class InitFileReaderButtonWidget  extends StaticWidget {
    
   public static final String NAME = "FileReaderButtonWidget";
   
   


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
                fileReader.close();
            } catch (IOException ex) {
                DashboardLogger.getInstance().logMessage(ex.getMessage());
            }
        }

        @Override
        public void destroy() {
            destroyed = true;
        }
    }
  
    private BGThread bgThread = new BGThread();
    private GCThread gcThread = new GCThread();
    public static final String READFILE_NAME = "RoboInput.txt";
    private BufferedReader fileReader;
    private NetworkTable table = NetworkTable.getTable("robotFileInput");
    private static final String SEPARATOR = ",";
    private StringTokenizer tokenizer;
    
     @Override
    public void init() {
        ButtonPanel bp = new ButtonPanel();
        bp.getButton().setText("READ INPUT FILE");
        bp.addMouseListener(new MouseListener()
            {

                @Override
                public void mouseClicked(MouseEvent me) {
                    DashboardLogger.getInstance().logMessage("Reader Button Clicked, Attempting to read from file");
                   doStuff();
                }

                @Override
                public void mousePressed(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseReleased(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseEntered(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseExited(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        this.add(bp);
        
        try {
            File f = new File(READFILE_NAME);
            if (!f.exists()) {
                f.createNewFile();
            }
            fileReader = new BufferedReader(new FileReader(f));
            
            
            
        }
         catch (IOException e) {
            e.printStackTrace();
        }

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
    
    public void doStuff()
    {
        try {
            String line = fileReader.readLine();
            while(line != null)
            {
                this.tokenizer = new StringTokenizer(line, SEPARATOR);
                String type = tokenizer.nextToken();
                String name = tokenizer.nextToken();
                String stringValue = tokenizer.nextToken();
                
                if(type.equals("double"))
                {
                    table.putDouble(name, Double.valueOf(stringValue));
                }
                else if(type.equals("int"))
                {
                    table.putInt(name, Integer.valueOf(stringValue));
                }
                else if(type.equals("boolean"))
                {
                    table.putBoolean(name, Boolean.getBoolean("stringValue"));
                }
//                while (tokenizer.hasMoreTokens()) {
//                    System.out.println(tokenizer.nextToken());
//                }
                line = fileReader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(InitFileReaderButtonWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}