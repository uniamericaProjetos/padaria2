
package br.com.padaria.dao;

import br.com.padaria.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDao {

    public void salvarProduto(Produto produto) {

        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        String sql = "INSERT INTO produto_pronto "
                + "(nome, descricao, quantidade, preco, data_fabricacao, data_validade, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco());
            ps.setDate(5, java.sql.Date.valueOf(produto.getDataFabricacao()));
            ps.setDate(6, java.sql.Date.valueOf(produto.getDataValidade()));
            ps.setString(7, produto.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBanco.desconectar();
        }
    }
    
    public List<Produto> listar(String busca) {
        List<Produto> lista = new ArrayList<>();
        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        
        String sql = "SELECT * FROM produto_pronto WHERE nome LIKE ?";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + busca + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id")); // Certifique-se de que o nome da coluna de ID no banco é "id"
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    p.setPreco(rs.getDouble("preco"));
                    
                    if (rs.getDate("data_fabricacao") != null) {
                        p.setDataFabricacao(rs.getDate("data_fabricacao").toLocalDate());
                    }
                    if (rs.getDate("data_validade") != null) {
                        p.setDataValidade(rs.getDate("data_validade").toLocalDate());
                    }
                    
                    p.setStatus(rs.getString("status"));
                    
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos: " + e.getMessage(), e);
        } finally {
            conexaoBanco.desconectar();
        }
        return lista;
    }
    
    public void atualizarProduto(Produto produto) {
        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        String sql = "UPDATE produto_pronto SET nome=?, descricao=?, quantidade=?, preco=?, data_fabricacao=?, data_validade=?, status=? WHERE id=?";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco());
            ps.setDate(5, java.sql.Date.valueOf(produto.getDataFabricacao()));
            ps.setDate(6, java.sql.Date.valueOf(produto.getDataValidade()));
            ps.setString(7, produto.getStatus());
            ps.setInt(8, produto.getId()); // O ID diz qual produto vamos atualizar
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar: " + e.getMessage(), e);
        } finally {
            conexaoBanco.desconectar();
        }
    }

    public void atualizar(Produto produto) {
        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar(); // abre a conexão

        String sql = "UPDATE produto_pronto SET nome = ?, descricao = ?, quantidade = ?, "
                + "preco = ?, data_fabricacao = ?, data_validade = ?, status = ? "
                + "WHERE id = ?";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco());
            ps.setDate(5, java.sql.Date.valueOf(produto.getDataFabricacao()));
            ps.setDate(6, java.sql.Date.valueOf(produto.getDataValidade()));
            ps.setString(7, produto.getStatus());
            ps.setInt(8, produto.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexaoBanco.desconectar();
        }
    }

    public void excluir(long id) {
        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        String sql = "DELETE FROM produto_pronto WHERE id = ?";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexaoBanco.desconectar();
        }
    }

    // Converte a linha atual do ResultSet em um objeto Produto.
    // O texto entre aspas é o nome da coluna no banco.
    private Produto montar(ResultSet rs) throws SQLException {
        Produto p = new Produto(); // objeto vazio, será preenchido abaixo

        p.setId(rs.getInt("id"));               // coluna int
        p.setNome(rs.getString("nome"));        // coluna varchar
        p.setDescricao(rs.getString("descricao")); // coluna varchar
        p.setQuantidade(rs.getInt("quantidade"));   // coluna int
        p.setPreco(rs.getDouble("preco"));          // coluna decimal

        // Converte a data SQL para LocalDate.
        java.sql.Date fabricacao = rs.getDate("data_fabricacao");
        if (fabricacao != null) {
            p.setDataFabricacao(fabricacao.toLocalDate());
        }

        java.sql.Date validade = rs.getDate("data_validade");
        if (validade != null) {
            p.setDataValidade(validade.toLocalDate());
        }

        p.setStatus(rs.getString("status")); // coluna varchar

        return p; // devolve o objeto preenchido para quem chamou
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();

        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        String sql = "SELECT * FROM produto_pronto ORDER BY nome";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); // guarda as linhas devolvidas pelo banco

            while (rs.next()) {
                Produto p = montar(rs);
                lista.add(p);
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBanco.desconectar();
        }

        return lista; // devolve a lista para quem chamou
    }

    public Produto buscarPorId(int idProduto) {
        Produto p = null; // começa null, só muda se o banco encontrar algo

        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        String sql = "SELECT * FROM produto_pronto WHERE id = ?";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idProduto); // coloca o ID no lugar do "?"

            ResultSet rs = ps.executeQuery();

            // if, pois o ID é único, então só retorna um registro
            if (rs.next()) {
                p = montar(rs);
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBanco.desconectar();
        }

        return p; // devolve o objeto para quem chamou
    }

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> lista = new ArrayList<>();

        Conexao conexaoBanco = new Conexao();
        conexaoBanco.conectar();

        // LIKE com % procura o texto em qualquer parte do nome.
        String sql = "SELECT * FROM produto_pronto WHERE nome LIKE ? ORDER BY nome";

        try (PreparedStatement ps = conexaoBanco.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = montar(rs);
                lista.add(p);
            }

            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexaoBanco.desconectar();
        }

        return lista;
    }
}
