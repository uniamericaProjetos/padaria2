package br.com.padaria.dao;

import br.com.padaria.model.Sexualidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SexualidadeDao {

    private Conexao conexao = new Conexao();

    public void inserir(Sexualidade s) {
        conexao.conectar();
        String sql = "INSERT INTO genero (descricao, sigla, status) VALUES (?, ?, 'ativo')";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getDescricao());
            ps.setString(2, s.getSigla());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
    }

    public void atualizar(Sexualidade s) {
        conexao.conectar();
        String sql = "UPDATE genero SET descricao = ?, sigla = ?, status = ? WHERE id_genero = ?";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getDescricao());
            ps.setString(2, s.getSigla());
            ps.setString(3, s.getStatus());
            ps.setInt(4, s.getIdGenero());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
    }

    public void inativar(int idGenero) {
        conexao.conectar();
        String sql = "UPDATE genero SET status = 'inativo' WHERE id_genero = ?";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idGenero);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
    }

    public List<Sexualidade> listarAtivas() {
        List<Sexualidade> lista = new ArrayList<>();
        conexao.conectar();

        String sql = "SELECT * FROM genero "
                + "WHERE status = 'ativo' AND descricao <> 'Outros' "
                + "ORDER BY descricao";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
        return lista;
    }

    public List<Sexualidade> listarTodas() {
        List<Sexualidade> lista = new ArrayList<>();
        conexao.conectar();
        String sql = "SELECT * FROM genero ORDER BY descricao";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
        return lista;
    }

    public Sexualidade buscarPorId(int idGenero) {
        Sexualidade sexualidade = null;
        conexao.conectar();
        String sql = "SELECT * FROM genero WHERE id_genero = ?";

        try (PreparedStatement ps = conexao.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idGenero);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sexualidade = montar(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            conexao.desconectar();
        }
        return sexualidade;
    }

    private Sexualidade montar(ResultSet rs) throws SQLException {
        Sexualidade s = new Sexualidade();
        s.setIdGenero(rs.getInt("id_genero"));
        s.setDescricao(rs.getString("descricao"));
        s.setSigla(rs.getString("sigla"));
        s.setStatus(rs.getString("status"));
        return s;
    }
}
