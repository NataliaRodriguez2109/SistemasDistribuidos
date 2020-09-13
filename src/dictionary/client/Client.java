/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author nata_
 */
public class Client {
     private DatagramSocket socket;
    
    public Client() throws SocketException
    {
        socket = new DatagramSocket();
    }
    
    public void send(String msj) throws UnknownHostException, IOException
    {
        InetAddress host = InetAddress.getByName("127.0.0.1");		
        int puerto = 135;	
        
        byte[] buffer = msj.getBytes();

        DatagramPacket request = 
                new DatagramPacket(buffer, buffer.length, host, puerto);

        socket.send(request);
    }
    
    public String receive() throws IOException
    {
        byte[] buffer = new byte[1000];

        DatagramPacket request = 
                new DatagramPacket(buffer, buffer.length);

        socket.receive(request);
        
        return new String(request.getData()).trim();
    }
}
