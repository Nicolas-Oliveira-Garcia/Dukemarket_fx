/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nicolas.dukemarket.javafx.DAO;

import br.com.nicolas.dukemarket.javafx.model.Cliente;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qualifica
 */
   
public class ClienteDAO {
    private static final String SQL_INSERT = "INSERT INTO cliente(nome, endereco, cidade, uf, cep," 
                 + "telefone ,celular, email)"
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM cliente";
    private static final String SQL_SELECT_ID = "SELECT * FROM cliente"
                 + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE cliente set nome = ?,"
                 + "endereco = ?, cidade = ?, uf = ?, cep = ?,"
                 + "telefone = ?, celular = ?, email = ?"
                 + "WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id = ?";

    public void create(Cliente c) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getEndereco());
            stmt.setString(3,c.getCidade());
            stmt.setString(4,c.getUf());
            stmt.setString(5,c.getCep());
            stmt.setString(6,c.getTelefone());
            stmt.setString(7,c.getCelular());
            stmt.setString(8,c.getEmail());
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Inclusao: " + auxRetorno);
            
        } catch (SQLException sQLException){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    public List<Cliente> getResults(){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c  = null;
        List<Cliente> listaCliente = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            listaCliente = new ArrayList<>();
            
            while(rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                c.setCep(rs.getString("cep"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setEmail(rs.getString("email"));
                listaCliente.add(c);
            }
        } catch (SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            return listaCliente;
        }
        
    }
    
    public Cliente getResultsById(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                c.setCep(rs.getString("cep"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setEmail(rs.getString("email"));
            }
        } catch (SQLException sQLException){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        
        return c;
    }
    
    public void update(Cliente c){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getEndereco());
            stmt.setString(3,c.getCidade());
            stmt.setString(4,c.getUf());
            stmt.setString(5,c.getCep());
            stmt.setString(6,c.getTelefone());
            stmt.setString(7,c.getCelular());
            stmt.setString(8,c.getEmail());
            stmt.setInt(9,c.getId());
            
        int auxRetorno = stmt.executeUpdate();
            
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno);
            
        } catch (SQLException sQLException){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    public void delete(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
        int auxRetorno = stmt.executeUpdate();
            
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Delete: " + auxRetorno);
            
        } catch (SQLException sQLException){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }  
    }
}
