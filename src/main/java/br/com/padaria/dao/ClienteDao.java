package br.com.padaria.dao;

import br.com.padaria.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private Conexao conexao = new Conexao();

    public void inserir(Cliente cliente) {
        conexao.conectar();

        String sql = "INSERT INTO clientes "
                + "(sexualidade, nome, sobrenome, cpf, data_nascimento, telefone, "
                + "cep, rua, bairro, cidade, uf, numero, complemento, logradouro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            preencherComando(ps, cliente);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
    }

    
    public Cliente clientePorId(String id) throws SQLException {
        Cliente cliente = new Cliente();
        conexao.conectar();
        
        String sql = "SELECT * FROM clientes where id_cliente = ?";
        
        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
//                cliente.setEmail(rs.getString("email"));
                
            }
        } finally {
            conexao.desconectar();
        }
        
        return cliente;
    }
    
    public void deletar(long id) throws SQLException {
        conexao.conectar();
        
        String sql = "DELETE FROM clientes where id_cliente = ?";
        
        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            conexao.desconectar();
        }
        
    }
    public List<Cliente> listar(String filtro) {
        List<Cliente> clientes = new ArrayList<>();
        conexao.conectar();

        String sql = "SELECT * FROM clientes "
                + "WHERE nome LIKE ? OR sobrenome LIKE ? OR cpf LIKE ? "
                + "ORDER BY nome, sobrenome";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            String busca = "%" + filtro + "%";
            ps.setString(1, busca);
            ps.setString(2, busca);
            ps.setString(3, busca);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes.add(montarCliente(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
        return clientes;
    }

    public void atualizar(Cliente cliente) {
        conexao.conectar();

        String sql = "UPDATE clientes SET sexualidade = ?, nome = ?, sobrenome = ?, "
                + "cpf = ?, data_nascimento = ?, telefone = ?, cep = ?, rua = ?, "
                + "bairro = ?, cidade = ?, uf = ?, numero = ?, complemento = ?, "
                + "logradouro = ? WHERE id_cliente = ?";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            preencherComando(ps, cliente);
            ps.setInt(15, cliente.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
    }

    private Cliente montarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setIdSexualidade(rs.getInt("sexualidade"));
        cliente.setNome(rs.getString("nome"));
        cliente.setSobrenome(rs.getString("sobrenome"));
        cliente.setCpf(rs.getString("cpf"));

        java.sql.Date data = rs.getDate("data_nascimento");
        if (data != null) {
            cliente.setDataNascimento(data.toLocalDate());
        }

        cliente.setTelefone(rs.getString("telefone"));
        cliente.setCep(rs.getString("cep"));
        cliente.setRua(rs.getString("rua"));
        cliente.setBairro(rs.getString("bairro"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setUf(rs.getString("uf"));

        int numero = rs.getInt("numero");
        if (!rs.wasNull()) {
            cliente.setNumero(numero);
        }

        cliente.setComplemento(rs.getString("complemento"));
        cliente.setLogradouro(rs.getString("logradouro"));
        return cliente;
    }

    private void preencherComando(PreparedStatement ps, Cliente cliente) throws SQLException {
        ps.setInt(1, cliente.getIdSexualidade());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getSobrenome());
        ps.setString(4, cliente.getCpf());

        if (cliente.getDataNascimento() != null) {
            ps.setDate(5, java.sql.Date.valueOf(cliente.getDataNascimento()));
        } else {
            ps.setNull(5, Types.DATE);
        }

        ps.setString(6, cliente.getTelefone());
        ps.setString(7, cliente.getCep());
        ps.setString(8, cliente.getRua());
        ps.setString(9, cliente.getBairro());
        ps.setString(10, cliente.getCidade());
        ps.setString(11, cliente.getUf());

        if (cliente.getNumero() != null) {
            ps.setInt(12, cliente.getNumero());
        } else {
            ps.setNull(12, Types.INTEGER);
        }

        ps.setString(13, cliente.getComplemento());
        ps.setString(14, cliente.getLogradouro());
    }
}
