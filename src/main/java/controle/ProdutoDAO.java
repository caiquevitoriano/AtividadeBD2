/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import modelo.Produto;
import redis.clients.jedis.Jedis;

/**
 *
 * @author caique
 */
public class ProdutoDAO {
    
    
    
    public boolean salvarProduto(Produto produto) {
        
        try{
            
            Connection con = Conexao.Conectar();
            
            String sql = "INSERT INTO produto(codigo, descricao, preco) VALUES(?, ?, ?)";
            
            PreparedStatement stat = con.prepareStatement(sql);
            
            stat.setInt(1, produto.getCodigo());
            stat.setString(2, produto.getDescricao());
            stat.setFloat(3, produto.getPreco());
            
            stat.executeUpdate();
            stat.close();
            
            return true; 
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public void buscarRedis(int codigo){
        
        String c = String.valueOf(codigo);
        Jedis jedis = new Jedis("localhost", 6379);    
        Gson gson = new Gson();
        ProdutoDAO dao = new ProdutoDAO();
        Boolean aux = jedis.exists(c);   
        
        if( aux != false){        
            
            
            String json = jedis.get(c);
            
            System.out.println(json);   
            
        }else {  
            
            String a = String.valueOf(dao.buscar(codigo));            
            jedis.setex(c, 1800, a);
                      
            
            System.out.println(dao.buscar(codigo));
        }     
        
        jedis.close();
        
        
               
        
    }
    
    public Produto buscar(int codigo){
       
       Produto prod = null;        
        
        try{
            
            Connection con = Conexao.Conectar();
            
            String sql = "SELECT * FROM produto WHERE codigo = ?";
            
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, codigo);
            
            ResultSet rs = stat.executeQuery();  
            
            while (rs.next()){
                        prod = new Produto();
                        
                        prod.setCodigo(rs.getInt("codigo"));
                        prod.setDescricao(rs.getString("descricao"));
                        prod.setPreco(rs.getFloat("preco"));                        
            }    
            
            rs.close();
            stat.close();
            con.close();
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          return prod;
       
            
    }
     
    
}
