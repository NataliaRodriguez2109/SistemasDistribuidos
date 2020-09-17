/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.elements;

import dictionary.databases.Connection1;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author nata_
 */
public class Term {

    String word;
    String definition;
    String id = null;
    Connection con;

    Connection1 conection = new Connection1();
    PreparedStatement pst;

    Statement st;
    ResultSet rs;

    public String[] enlistarAmigos(String ip) throws SQLException {
        Connection con = null;
        ArrayList<String> address = new ArrayList();
        try {
            con = conection.getConection(ip);
            st = con.createStatement();
            rs = st.executeQuery("SELECT direccion FROM AMIGOS");

            while (rs.next()) {

                String direccion = rs.getString("direccion");
                address.add(direccion);
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        String amigos[] = new String[address.size()];
        for (int i = 0; i < address.size(); i++) {
            amigos[i] = address.get(i);
        }
        return amigos;
    }

    public Term() {

    }

    // es necesario
    public String create(String ip, String palabra, String def) {
        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("INSERT INTO TERMINOs (palabra, definicion) VALUES (?, ?) ");
//            

            //rs = con.g
            pst.setString(1, palabra);
            pst.setString(2, def);

            int res = pst.executeUpdate();

            if (res > 0) {
                con.close();
                return "Término Agregado";
            } else {
                con.close();
                return "Error al agregar Término";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String update(String ip, Paquete p) {
        Connection con = null;

        try {

            con = conection.getConection(ip);

            pst = con.prepareStatement("SELECT * FROM TERMINOS WHERE palabra = ?");
            //Invocamos un método sobre un objeto Scanner

            pst.setString(1, p.arreglo[1]);

            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getString("id");
                pst = con.prepareStatement("UPDATE terminos SET palabra=?, definicion=? WHERE id=" + id);

                pst.setString(1, p.arreglo[3]);

                pst.setString(2, p.arreglo[2]);

                int res = pst.executeUpdate();
                con.close();
                if (res > 0) {
                    return "Término Editado";
                } else {
                    return "Error al editar Término";
                }
            } else {
                con.close();
                return "No se encotró la palabra en el diccionario";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String list(String ip) {
        Connection con = null;
        try {
            con = conection.getConection(ip);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM TERMINOS");
            String li = "";
            while (rs.next()) {
                li = li + rs.getInt("id") + " " + rs.getString("palabra") + " " + rs.getString("definicion");
            }
            con.close();
            if (li != "") {
                return li;
            } else {
                return "Esta base de datos está vacía.";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    public String delete(String ip, Paquete p) {
        Connection con = null;

        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("SELECT * FROM TERMINOS WHERE palabra = ?");

            pst.setString(1, p.arreglo[1]);

            rs = pst.executeQuery();

            if (rs.next()) {
                pst = con.prepareStatement("DELETE FROM TERMINOS WHERE palabra=?");
                pst.setString(1, p.arreglo[1]);

                int res = pst.executeUpdate();
                con.close();
                if (res > 0) {
                    return "Término Eliminado";
                } else {
                    return "Error al eliminar Término";
                }
            } else {
                con.close();
                return "No se encontró el término";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String find(String ip, Paquete p, String origen) {
        Connection con = null;

        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("SELECT * FROM TERMINOS WHERE palabra = ?");
            System.out.println(rs);
            pst.setString(1, p.arreglo[1]);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("id") + "-" + rs.getString("palabra") + ": " + rs.getString("definicion");
            } else {
                String[] pala = enlistarAmigos(ip);
                for (int i = 0; i < pala.length; i++) {
                    if (!pala[i].equals(origen)) {
                        System.out.println("buscando en: " + pala[i] + "");
                        String cadena = find(pala[i], p, ip);
                        return cadena;
                    }
                }
            }

        } catch (Exception e) {
            return "Error7: " + e;
        }
        return "Error: palabra no encontrada";

    }

    public void crearSenuelo() {

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}
