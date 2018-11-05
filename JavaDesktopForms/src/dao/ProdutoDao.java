/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import model.Produto;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Desenvolvedor
 */
public class ProdutoDao {
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Produto> lista = new ArrayList<Produto>();
    
    public ProdutoDao(){   //CONSTRUTOR
        conn = new ConnectionFactory().getConexao();  //charmar a conexao
    }
    
    
    public void inserir(Produto produto){
        String sql = "INSERT INT produto (descricao_produto, preco_produto) VALUES(?,?)";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,produto.getDescricao_produto());
            stmt.setDouble(2, produto.getPreco_produto());
            stmt.execute();
            stmt.close();
            
        }catch(Exception erro){
            throw new RuntimeException("Erro2:" + erro);
        }
        
    }  
    
        public void alterar(Produto produto){
        String sql = "UPDATE produto SET descricao_produto = ? , preco_produto =  ? WHERE codigo_produto=?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,produto.getDescricao_produto());
            stmt.setDouble(2, produto.getPreco_produto());
            stmt.setInt(3 , produto.getCodigo_produto());
            stmt.execute();
            stmt.close();
           
        }catch(Exception erro){
            throw new RuntimeException("Erro3:" + erro);
        }
     }

        
        
        
        
        public void excluir(int valor){
        String sql = "DELETE FROM produto WHERE codigo_produto= " + valor;
        try{
            st = conn.createStatement();
            st.execute(sql);
            st.close();
          }catch(Exception erro){
            throw new RuntimeException("Erro 4:" + erro);
        }
        
       }    
        
        public ArrayList<Produto> listarTodos(){
            String sql= "SELECT * FROM produto";
            try{
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setCodigo_produto(rs.getInt("codigo_produto"));
                    produto.setDescricao_produto(rs.getString("descricao_produto"));
                    produto.setPreco_produto(rs.getDouble("preco_produto"));
                    lista.add(produto);
                }
            }catch(Exception erro){
                 throw new RuntimeException("Erro 5:" + erro);
            }
            return lista;
        }
        
        
         public ArrayList<Produto> listarTodosDescricao(String valor){
            String sql= "SELECT * FROM produto WHERE descricao_produto LIKE '%"+valor+" %";
            try{
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setCodigo_produto(rs.getInt("codigo_produto"));
                    produto.setDescricao_produto(rs.getString("descricao_produto"));
                    produto.setPreco_produto(rs.getDouble("preco_produto"));
                    lista.add(produto);
                }
            }catch(Exception erro){
                 throw new RuntimeException("Erro 7:" + erro);
            }
            return lista;
        }
        
  }


