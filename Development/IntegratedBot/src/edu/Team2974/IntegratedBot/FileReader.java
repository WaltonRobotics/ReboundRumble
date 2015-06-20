/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Team2974.ProtoBot;
import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import com.sun.squawk.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;

/**
 *
 * @author Chris
 */
public class FileReader {
    private FileConnection connection;
    private BufferedReader file;
    private boolean error;
    private static final String SEPARATOR=";";
    private StringTokenizer tokenizer;
    
    public FileReader(String path) throws IOException
    {
        try{
            this.connection = (FileConnection) Connector.open(path,Connector.READ);
            if(!this.connection.exists()){
                this.connection.create();
            }
            this.file=new BufferedReader(
                    new InputStreamReader(
                    this.connection.openInputStream()));
        }catch (IOException ioe){
            throw ioe;
        }
    }
    
    public void readAndPrint()
    {
        try {
            String line =this.file.readLine();
            while(line!=null)
            {
                
                this.tokenizer = new StringTokenizer(line, SEPARATOR);
                while(tokenizer.hasMoreTokens())
                {
                    System.out.println(tokenizer.nextToken());
                }
                line =this.file.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close() throws IOException{
        if(this.file != null){
            this.file.close();
        }
    }
}
   
