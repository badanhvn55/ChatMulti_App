/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmenuswing;

import java.io.*;
import java.net.*;

/**
 *
 * @author ueda
 */
public class serverChatform {

    public static void main(String[] args) {

        try {
            Socket s = new Socket("192.168.8.102", 1234);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String msgin="",msgout="";
            while(!msgin.equals("end")){
                msgout=br.readLine();
                dos.writeUTF(msgout);
                msgin=dis.readUTF();
                System.out.println(msgin);
                
                
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
