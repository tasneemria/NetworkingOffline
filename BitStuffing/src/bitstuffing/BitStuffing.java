/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitstuffing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author a
 */
public class BitStuffing {

    /**
     * @param args the command line arguments
     */
    
    private void bitSt(String filename) throws IOException
    {
        
        File file = new File(filename);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[(int)length]; 
        InputStream in = new FileInputStream(file);
        
        int count;
        while ((count = in.read(bytes)) > 0) {
         //   out.write(bytes, 0, count);
            byte b1 = (byte)count;
            String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
            System.out.print("The bytes "+ s1);
        }
        
        int i = 0, x, q = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String sy = "01111110", sx;
        StringBuilder sby = new StringBuilder(sy);
        System.out.print("ENTER THE DATA: ");
        sx = br.readLine();
        StringBuilder sbx = new StringBuilder(sx);
        x = sx.length();
        while (i + 5 <= x) {
            String s1 = sx.substring(i, i + 5);
            if (check(s1)) {
                sbx.insert(i + 5, 0);
                i = i + 6;
            } else {
                i++;
            }
        }

        System.out.print("BIT STUFFING: ");
        System.out.println(sbx);
        System.out.print("FINAL OUTPUT: ");
        System.out.println(sby + "  " + sbx + "  " + sby);
        System.out.println(sby.append(sbx.append(sby)));
        
    }
    
    private static boolean check(String s){
      String s1 = "11111";
      if(s.equals(s1))
          return true;
      else return false;      
   }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BitStuffing bitStuffing = new BitStuffing();
        bitStuffing.bitSt("ria.pdf");
    }

}
