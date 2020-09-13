/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.client;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author nata_
 */
public class MainClient {
    public static void main(String[] args){
        Client client = null;
        
        try {
            client = new Client();
        } 
        catch (SocketException e) {
            System.out.println("No fue posible crear al cliente: " + e.getMessage());
        }
        
        Scanner teclado = new Scanner(System.in);
           
        while(true)
        {
            try {
                System.out.println("Ingrese su orden: ");
                
                String linea = teclado.nextLine();
                
                client.send(linea.trim());
                
                String respuesta = client.receive();
                
                System.out.println("client << (" + respuesta + ")");
                
                if(respuesta.equals("chao"))
                    break;               
            } 
            catch (IOException e) {
                System.out.println("No fue posible enviar el mensaje: " + e.getMessage());
            }
        }
        
        System.out.println("Fin del cliente");
    }        
    
}
