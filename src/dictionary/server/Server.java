/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.server;

import dictionary.elements.DictionaryController;
import dictionary.elements.Paquete;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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

    public Server() throws SocketException {
        socket = new DatagramSocket(PUERTO);
    }

    public void receive(String ip) throws IOException, ClassNotFoundException {
        while (true) {
            byte[] buffer = new byte[1000];

            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            socket.receive(request);

            
            ByteArrayInputStream bis = new ByteArrayInputStream(request.getData());
            ObjectInput in = null;
            Paquete o ;
            try {
                in = new ObjectInputStream(bis);
                o = (Paquete)in.readObject();                 
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
            String order = o.getArreglo()[0];
            System.out.println("server << (" + order + ")");
            if (order.equals("chao")) {
                enviar("chao", request.getAddress(), request.getPort());
                break;
            } else if (order.equals("crear") | order.equals("editar")
                    | order.equals("eliminar") | order.equals("listar") | order.equals("buscar")) {
                String resultado
                        = controller.action(o, ip);
                enviar(resultado, request.getAddress(), request.getPort());
            } else {
                enviar("Desconocido: " + order, request.getAddress(), request.getPort());
            }
            
        }

        System.out.println("Fin del servidor");
    }

    private void enviar(String msj, InetAddress host, int puerto) throws IOException {
        byte[] buffer = msj.getBytes();

        DatagramPacket request
                = new DatagramPacket(buffer, buffer.length, host, puerto);

        socket.send(request);
    }

    public void setController(DictionaryController controller) {
        this.controller = controller;
    }
}
