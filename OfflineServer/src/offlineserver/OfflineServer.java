/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlineserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static offlineserver.OfflineServer.clientArr;
import static offlineserver.OfflineServer.loginClient;

/**
 *
 * @author a
 */
public class OfflineServer {

    public static ArrayList<String> loginClient = new ArrayList<String>();
    public static Socket clientArr[] = new Socket[100];
    public static int clientNum = 0;
    
    public static void main(String[] args) throws IOException {
        int workerThreadCount = 0;
        int id = 1;
        ServerSocket welcomeSocket = new ServerSocket(1234);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            //clientArr[id] = connectionSocket;
            WorkerThread wt = new WorkerThread(connectionSocket, id);
            Thread t = new Thread(wt);
            t.start();
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }
    }
    
}


class WorkerThread implements Runnable {

    private int clientID = 0;
    //private String[] loginClient = new String[100];
    private String clientSentence;
    private String writeFromServer1;
    private String writeFromServer2;
    private String[] users;

    private Socket connectionSocket;
    private int id;

    public WorkerThread(Socket ConnectionSocket, int id) {
        this.connectionSocket = ConnectionSocket;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                clientSentence = inFromServer.readLine();
                writeFromServer1 = clientSentence;

                clientSentence = inFromServer.readLine();
                writeFromServer2 = clientSentence;
                int check = 0, clientFound, clientFoundSec;
                for (String s : loginClient) {
                    if (s.equals(writeFromServer1)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {

                    loginClient.add(writeFromServer1);
                    clientFound = Integer.parseInt(writeFromServer1);
                    clientArr[clientFound] = connectionSocket;

                } else if (check == 1) {
                    System.out.println("Previously connected. Cannot connect to server\n");
                }
                int flag = 0;
                clientFound = Integer.parseInt(writeFromServer2);

                for (String s : loginClient) {
                    
                    if (s.equals(writeFromServer2)) {
                        flag = 1;
                       // File f = saveFile(connectionSocket,"newfile.txt");
                        //sendFile(clientArr[clientFound],"another.txt");
                        InputStream in = connectionSocket.getInputStream();
                        //OutputStream out = connectionSocket.getOutputStream();
                        OutputStream out = new FileOutputStream("new.pdf");
                        byte[] bytes = new byte[16*1024];

                        int count;
                        while ((count = in.read(bytes)) > 0) {
                            out.write(bytes, 0, count);
                        }

                    //out.close();
                    //in.close();
                    File file = new File("new.pdf");
                    clientFoundSec = Integer.parseInt(writeFromServer2);
                    //long length = file.length();
                    byte[] bytes2 = new byte[16 * 1024];
                        InputStream in2 = new FileInputStream(file);
                       OutputStream out2 = clientArr[clientFoundSec].getOutputStream();
                        int count2;
                        while ((count2 = in2.read(bytes2)) > 0) {
                            out2.write(bytes2, 0, count2);
                        }
                        
                        //out2.close();
                        //in2.close();
                        
                        break;
                    }
                }
                if (flag == 0) {
                    System.out.println("Not present in the list\n");
                }
            } catch (Exception e) {

            }
        }
    }
}
