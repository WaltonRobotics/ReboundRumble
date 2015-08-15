/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2974.StLouisPrep;
import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.io.BufferedWriter;
import java.io.*;
import javax.microedition.io.*;

/**
 *
 * @author team2974
 */
public class DashConnectionTCP {
    private SocketConnection outSocketConnect;
    private SocketConnection inSocketConnect;
    private InputStream myInputStream;
    private OutputStream myOutputStream;
    DataOutputStream dos;
    private DataInputStream dis;
    private static DashConnectionTCP _instance;
    private double[] rectInfo;
    
    public static DashConnectionTCP getInstance()
    {
        if (_instance == null) {
            _instance = new DashConnectionTCP();
        }
        return _instance;
    }
    
    private DashConnectionTCP()
    {
         try {
            //This IP should be the computer's IP, in the from 10.XX.YY.Z
            //XX.YY is your team number (e.g. ours in 2974)
            //Z is whatever your computer is on
            outSocketConnect = (SocketConnection) Connector.open("socket://10.29.74.5:1180");//camera from crio to DS, when camera is connected to port 2 on the crio
            inSocketConnect = (SocketConnection) Connector.open("socket://10.29.74.5:1735");//smart dashboard
            myInputStream = inSocketConnect.openInputStream();
            myOutputStream = outSocketConnect.openOutputStream();
            dos = new DataOutputStream(myOutputStream);
            dis = new DataInputStream(myInputStream);
            rectInfo = new double[4];
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeToDashboard(boolean output)
    {
        try {
            dos.writeBoolean(output);
//            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public int readFromDashboard()
    {
        try {
            return dis.readInt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public void readFromDashboardTarget()
    {
        
        try {
            //System.out.println("Entering read logic");
            if(dis.available()>0)
            { 
                System.out.println("data in pipe");
                
                for(int i =0; i<4; i++)
                {
                    rectInfo[i]= dis.readDouble();
                        
                }
                
                OI.targetInfo[0]= (rectInfo[0]+rectInfo[2])/2;
                OI.targetInfo[1]= (rectInfo[1]+rectInfo[3])/2;
                System.out.println("Center X: "+OI.targetInfo[0]);
                System.out.println("Center y: "+OI.targetInfo[1]);
                    //0== leftx
                    //1== topy
                    //2== rightx
                    //3== boty
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        boolean target = false;
        if(rectInfo[1]<240.0&&rectInfo[1]>0.0)
        {
            target=true;
        }
        else
        {
            target= false;
        }
        
        if(OI.targetFound!=target){
            OI.targetFound=target;
            System.out.println(OI.targetFound);
        }
    }
    
    public void closeConnections()
    {
        try {
            dos.close();
            dis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
