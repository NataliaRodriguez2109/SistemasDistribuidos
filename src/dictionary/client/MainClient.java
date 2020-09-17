/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.client;

import dictionary.elements.Paquete;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author nata_
 */
public class MainClient {

    public static void main(String[] args) {
        Client client = null;

        try {
            client = new Client();
        } catch (SocketException e) {
            System.out.println("No fue posible crear al cliente: " + e.getMessage());
        }

        Scanner teclado = new Scanner(System.in);

        while (true) {
            try {
                String palabras[] = new String[4];
                System.out.println("Ingrese su orden: ");
                Scanner scanA = new Scanner(System.in);
                String linea = (scanA.nextLine());
                
                if (linea.trim().equals("listar")) {
                    palabras[0] = "listar";
                    palabras[1] = "";
                    palabras[2] = "";

                } else if (linea.equals("crear")) {
                    palabras[0] = "crear";

                    System.out.println("Por favor introduzca una palabra: ");

                    Scanner scanW = new Scanner(System.in);
                    palabras[1] = (scanW.nextLine()); //Invocamos un método sobre un objeto Scanner

                    System.out.println("Por favor introduzca la definición: ");

                    Scanner scanD = new Scanner(System.in);
                    palabras[2] = (scanD.nextLine());

                } else if (linea.equals("buscar")) {
                    palabras[0] = "buscar";
                    System.out.println("Cual es la palabra a buscar? ");
                    Scanner scanW = new Scanner(System.in);
                    palabras[1] = (scanW.nextLine()); //Invocamos un método sobre un objeto Scanner
                    palabras[2] = "";

                } else if (linea.equals("eliminar")) {
                    palabras[0] = "eliminar";
                    System.out.println("Cual es la palabra a eliminar ");
                    Scanner scanW = new Scanner(System.in);
                    palabras[1] = (scanW.nextLine()); //Invocamos un método sobre un objeto Scanner
                    palabras[2] = "";

                } else if (linea.equals("editar")) {
                    palabras[0] = "editar";
                    System.out.println("Cual es la palabra a modificar? ");
                    Scanner scanW = new Scanner(System.in);
                    palabras[1] = (scanW.nextLine()); //Invocamos un método sobre un objeto Scanner
                    
                    System.out.println("Cual es la nueva palabra? ");
                    scanW = new Scanner(System.in);
                    palabras[3] = (scanW.nextLine()); 
                    
                    System.out.println("Cual es la nueva definición? ");
                    scanW = new Scanner(System.in);
                    palabras[2] = (scanW.nextLine()); 
                   
                }

                Paquete p = new Paquete(palabras);
                client.send(p);

                String respuesta = client.receive();
                System.out.println("client << (" + respuesta + ")");

                if (respuesta.equals("chao")) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("No fue posible enviar el mensaje: " + e.toString());
            }
        }

        System.out.println("Fin del cliente");
    }

}
