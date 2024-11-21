package br.com.fiap.dao;

import br.com.fiap.to.AvaliacaoTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AvaliacaoDAO extends Repository{

    // Método para listar todas as avaliações
    public ArrayList<AvaliacaoTO> findAll() {
        ArrayList<AvaliacaoTO> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao ORDER BY id_avaliacao";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AvaliacaoTO avaliacao = new AvaliacaoTO();
                avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao"));
                avaliacao.setTitulo(rs.getString("titulo"));
                avaliacao.setNota(rs.getInt("nota"));
                avaliacao.setComentario(rs.getString("comentario"));
                avaliacao.setIdCliente(rs.getLong("id_cliente"));
                avaliacao.setIdEmpresa(rs.getLong("id_empresa"));
                avaliacoes.add(avaliacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
            e.printStackTrace();  // Melhorando o log de erro
        } finally {
            closeConnection();
        }

        return avaliacoes;
    }

    // Busca avaliação por ID
    public AvaliacaoTO findById(Long idAvaliacao) {
        AvaliacaoTO avaliacao = null;
        String sql = "SELECT * FROM avaliacao WHERE id_avaliacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAvaliacao);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                avaliacao = new AvaliacaoTO(
                        rs.getLong("id_avaliacao"),
                        rs.getString("titulo"),
                        rs.getInt("nota"),
                        rs.getString("comentario"),
                        rs.getLong("id_cliente"),
                        rs.getLong("id_empresa")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
            e.printStackTrace();  // Melhorando o log de erro
        } finally {
            closeConnection();
        }

        return avaliacao;
    }

    public AvaliacaoTO save(AvaliacaoTO avaliacao) {
        String sql = "INSERT INTO avaliacao (id_avaliacao, titulo, nota, comentario, id_cliente, id_empresa) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            // Passa o ID explicitamente
            ps.setLong(1, avaliacao.getIdAvaliacao());
            ps.setString(2, avaliacao.getTitulo());
            ps.setInt(3, avaliacao.getNota());
            ps.setString(4, avaliacao.getComentario());
            ps.setLong(5, avaliacao.getIdCliente());
            ps.setLong(6, avaliacao.getIdEmpresa());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return avaliacao;  // Retorna a avaliação, agora com o ID definido pelo usuário
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar avaliação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;  // Retorna null em caso de erro
    }

    // Método para apagar uma avaliação
    public boolean delete(Long idAvaliacao) {
        String sql = "DELETE FROM avaliacao WHERE id_avaliacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAvaliacao);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir avaliação: " + e.getMessage());
            e.printStackTrace();  // Melhorando o log de erro
        } finally {
            closeConnection();
        }

        return false;
    }

    // Método para atualizar uma avaliação
    public AvaliacaoTO update(AvaliacaoTO avaliacao) {
        String sql = "UPDATE avaliacao SET titulo = ?, nota = ?, comentario = ?, id_cliente = ?, id_empresa = ? "
                + "WHERE id_avaliacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, avaliacao.getTitulo());
            ps.setInt(2, avaliacao.getNota());
            ps.setString(3, avaliacao.getComentario());
            ps.setLong(4, avaliacao.getIdCliente());
            ps.setLong(5, avaliacao.getIdEmpresa());
            ps.setLong(6, avaliacao.getIdAvaliacao());

            System.out.println("Executando UPDATE com SQL: " + sql); // Log para verificar a execução
            int affectedRows = ps.executeUpdate();  // Executa o UPDATE

            if (affectedRows > 0) {
                System.out.println("Avaliação atualizada com sucesso!");
                return avaliacao;  // Retorna a avaliação atualizada
            } else {
                System.out.println("Nenhuma linha foi atualizada. Verifique o ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar avaliação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

}
