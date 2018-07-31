/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author caique
 */
public class Conexao {
    
    public static Connection Conectar() throws SQLException{
    
        String url = "jdbc:postgresql://localhost:5432/atividadeBD2";
        String user = "postgres";
        String password = "2718";
        
        Connection con = DriverManager.getConnection(url, user, password);
        
        return con;
    }
}
