/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.server;

import dictionary.elements.DictionaryController;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author nata_
 */
public class Server {
    public static final int PUERTO = 135;
    
    private DictionaryController controller;
    private DatagramSocket socket = null;		
    
    public Server() throws SocketException
    {
        socket = new DatagramSocket(PUERTO);
    }
    
    public void receive() throws IOException
    {
        while(true)
        {
            byte[] buffer = new byte[1000];
            
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);	
            
            socket.receive(request);
            
            String order = new String(request.getData()).trim();
            
            System.out.println("server << ("+order+")");
            
            if(order.equals("chao"))
            {
                enviar("chao", request.getAddress(), request.getPort());
                break;
            }
            else
                if(order.startsWith("d "))
                {
                    String resultado = 
                            controller.action(order.substring(2));

                    enviar(resultado,request.getAddress(), request.getPort());
                }
                else
                    enviar("Desconocido: "+order, request.getAddress(), request.getPort());
        }
        
        System.out.println("Fin del servidor");
    }
    
    private void enviar(String msj, InetAddress host, int puerto) throws IOException
    {
        byte[] buffer = msj.getBytes();

        DatagramPacket request = 
                new DatagramPacket(buffer, buffer.length, host, puerto);

        socket.send(request);
    }

    public void setController(DictionaryController controller) {
        this.controller = controller;
    }
}
