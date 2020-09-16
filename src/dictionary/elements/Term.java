/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.elements;

import dictionary.databases.Connection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public Term() {
        
    }
    // es necesario
    public void create(String ip){        
        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("INSERT INTO TERMINOs (id, palabra, definicion) VALUES (?, ?, ?) ");
            System.out.println("Por favor introduzca una palabra: ");

            Scanner scanW = new Scanner(System.in);
            word = (scanW.nextLine()); //Invocamos un método sobre un objeto Scanner

            System.out.println("Por favor introduzca la definición: ");

            Scanner scanD = new Scanner(System.in);
            definition = (scanD.nextLine());
            pst.setString(1,"0");
            pst.setString(2, word);
            pst.setString(3, definition);

            int res = pst.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Término Agregado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar Término");
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void update(String ip){
        Connection con = null;

        try {

            con = conection.getConection(ip);

            pst = con.prepareStatement("SELECT * FROM terminos WHERE palabra = ?");
            System.out.println("Ingrese la palabra a editar: ");
            Scanner scanWU = new Scanner(System.in);
            String wordU = (scanWU.nextLine()); //Invocamos un método sobre un objeto Scanner

            System.out.println("Por favor introduzca la palabra con la edición: ");

            pst.setString(1, wordU);

            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(null, "No se encotró la palabra en el diccionario");
            }

            pst = con.prepareStatement("UPDATE terminos SET palabra=?, definicion=? WHERE id=" + id);
            Scanner scanW3 = new Scanner(System.in);
            word = (scanW3.nextLine()); //Invocamos un método sobre un objeto Scanner

            System.out.println("Por favor introduzca la definición: ");

            Scanner scanD2 = new Scanner(System.in);
            definition = (scanD2.nextLine()); //Invocamos un método sobre un objeto Scanner
            pst.setString(1, word);
            pst.setString(2, definition);

            int res = pst.executeUpdate();
            
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Término Editado");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error al editar Término");
                }
            
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void list(String ip) {
        Connection con = null;
        try {
            con = conection.getConection(ip);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM terminos");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("palabra") + " " + rs.getString("definicion"));
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void delete(String ip){
        Connection con = null;

        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("DELETE FROM terminos WHERE palabra=?");
            System.out.println("Ingrese la palabra a eliminar: ");
            Scanner scanW2 = new Scanner(System.in);
            word = (scanW2.nextLine());  //Invocamos un método sobre un objeto Scanner

            pst.setString(1, word);

            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Término Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Término");
            }
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void find(String ip){
        Connection con = null;

        try {
            con = conection.getConection(ip);
            pst = con.prepareStatement("SELECT * FROM terminos WHERE palabra = ?");
            System.out.println("Ingrese la palabra a buscar: ");
            Scanner scanW4 = new Scanner(System.in);
            word = (scanW4.nextLine());  //Invocamos un método sobre un objeto Scanner
            pst.setString(1, word);

            rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("palabra") + " " + rs.getString("definicion"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encotró la palabra en el diccionario");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
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
