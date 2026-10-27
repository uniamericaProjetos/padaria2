package br.com.padaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author igoor
 */
//classe que abre e fecha conexao com o banco MySQL
public class Conexao {

    //dados para montar a URL de conexao
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
     
    
    private boolean ambienteRemoto = true;

    //objeto que representa a conexao aberta com o banco
    private Connection conexao;

    //construtor: define os dados de acesso ao banco
    public Conexao(){
        
        if (ambienteRemoto) {
              this.servidor = "";
        this.banco = "";   //preencher com o nome do banco
        this.usuario = ""; //preencher com o usuario do MySQL
        this.senha = "";   //preencher com a senha do MySQL
        }
        else {
              this.servidor = "localhost";
        this.banco = "grupo04_Mensal";   //preencher com o nome do banco
        this.usuario = "root"; //preencher com o usuario do MySQL
        this.senha = "123456";   //preencher com a senha do MySQL
        }
      
    }
    


    //abre a conexao com o banco
    public boolean conectar(){
        try {
            //URL no formato necessario com as informacoes
            String url = "jdbc:mysql://" + this.servidor + ":3306/" + this.banco
                    + "?useSSL=false&allowPublicKeyRetrieval=true";

            //pede pro driver abrir conexao
            this.conexao = DriverManager.getConnection(url, this.usuario, this.senha);
            return true;
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    //encerra a conexao, caso ela exista e esteja aberta
    public void desconectar(){
        try {
            //verifica se nao e nula e se nao esta fechada
            if (conexao != null && !conexao.isClosed()){
                conexao.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //devolve a conexao aberta, para o DAO criar os PreparedStatement
    public Connection getConnection(){
        return conexao;
    }
    
    
    public static void main (String[] args) {
        Conexao c  = new Conexao();
        c.conectar();
        
        String sql = "Select id_genero, descricao, sigla, status from genero";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
            ps = c.getConnection().prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()) {
                int id = rs.getInt("id_genero");
                String descricao = rs.getString("descricao");
                String sigla = rs.getString("sigla");
                String status = rs.getString("status");

                System.out.println("ID: " + id + " | Descrição: " + descricao + 
                                   " | Sigla: " + sigla + " | Status: " + status);
           }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        finally {
      
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            c.desconectar();
        }
        
        
         
    }
}
