/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlineclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author a
 */
public class OfflineClient {
 
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String clientInput;
        String filename;
        String serverOutput, catchId;
        int clID;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 1234);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        
        while (true) {
            System.out.println("Id of 1st and 2nd client : \n");
            clientInput = inFromUser.readLine();
            outToServer.writeBytes(clientInput + "\n");
            clientInput = inFromUser.readLine();
            outToServer.writeBytes(clientInput + "\n");
            
            System.out.println("give the filename to transfer : \n");
            Scanner sc = new Scanner(System.in);
            filename = sc.nextLine();
            
            File file = new File(filename);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[(int)length]; 
        InputStream in = new FileInputStream(file);
        OutputStream out = clientSocket.getOutputStream();
        System.out.println("length of the file "+length);
        
        
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            
        }

        //out.close();
        //in.close();
        
//        InputStream in2 = clientSocket.getInputStream();
//                        OutputStream out2 = new FileOutputStream("client.pdf");
//                        byte[] bytes2 = new byte[16*1024];
//
//                        int count2;
//                        while ((count2 = in2.read(bytes2)) > 0) {
//                            out2.write(bytes2, 0, count2);
//                        }

                    //out2.close();
                    //in2.close();
        
        //clientSocket.close();
           
        }
    }
    
}
