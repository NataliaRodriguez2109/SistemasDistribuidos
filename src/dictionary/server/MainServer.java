/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.server;

import dictionary.elements.DictionaryController;
import java.io.IOException;

/**
 *
 * @author nata_
 */
public class MainServer {
    public static void main(String[] args){        
        Server server;
        DictionaryController controller = new DictionaryController();
        
        try {
            server = new Server();
            server.setController(controller);
            
            server.receive();
        } 
        catch (IOException ex) {
            System.out.println("No se pudo iniciar el servidor: " + ex.getMessage());
        }
    }
}
