/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.databases;
import dictionary.elements.Term;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nata_
 */
public class Connection1 {

    private Connection conn = null;

    public Connection getConection(String ip) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://"+ip+":1527/diccionario");
            System.out.println("ON");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;
    }

}
