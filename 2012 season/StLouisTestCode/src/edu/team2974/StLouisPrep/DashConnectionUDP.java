package edu.team2974.StLouisPrep;
import java.io.*;
import javax.microedition.io.*;



/**
 *
 * @author team2974
 * 
 * This class was a prototype built during the palmetto regional 2012
 * and was never verified to work. There is no real reason to use this class due to other
 * communication options being more effective. 
 * 
 * This class has the same setup as the TCP DashConnection but designed to use
 * udp protocol. 
 */
public class DashConnectionUDP {
    private Datagram outPacket;
    private Datagram inPacket;
    private DatagramConnection outDC;
    private DatagramConnection inDC;
    private static DashConnectionUDP _instance;
    
    public static DashConnectionUDP getInstance()
    {
        if (_instance == null) {
            _instance = new DashConnectionUDP();
        }
        return _instance;
    }
    
    private DashConnectionUDP()
    {
         try {
            //This IP should be the computer's IP, in the from 10.XX.YY.Z
            //XX.YY is your team number (e.g. ours in 2974)
            //Z is whatever your computer is on
            outDC = (DatagramConnection)Connector.open("datagram://10.29.74.5:"+1337);
            inDC = (DatagramConnection) Connector.open("datagram://:"+7331);
            inPacket = inDC.newDatagram(8);
            outPacket = outDC.newDatagram(4);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeToDashboard(int output)
    {
        try {
            outPacket.reset();
            outPacket.setLength(4);
            outPacket.writeInt(output);
            outDC.send(outPacket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    public void readFromDashboardTarget()
    {
        try {
            double[] rectInfo = new double[4];
            inPacket.reset();
            inPacket.setLength(32);
            inDC.receive(inPacket);
            for(int i =0; i<4; i++)
            {
                rectInfo[i]= inPacket.readDouble();
                //0== leftx
                //1== topy
                //2== rightx
                //3== boty
            }
            OI.targetInfo[0]= (rectInfo[0]+rectInfo[2])/2;
            OI.targetInfo[1]= (rectInfo[1]+rectInfo[3])/2;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //if rectInfo has information other than the default values passed
        //it is a legitimate target
        if(OI.targetInfo[1]>0.0)
        {
            OI.targetFound = true;
        }
        else
        {
            OI.targetFound = false;
        }
        System.out.println("Center X: "+OI.targetInfo[0]);
        System.out.println("Center y: "+OI.targetInfo[1]);
        System.out.println(OI.targetFound);
    }
}