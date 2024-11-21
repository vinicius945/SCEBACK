package br.com.fiap.dao;

import br.com.fiap.to.EmpresaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaDAO extends Repository {
    public ArrayList<EmpresaTO> findAll() {
        ArrayList<EmpresaTO> empresas = new ArrayList<>();
        String sql = "select * from empresa order by id_empresa";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EmpresaTO empresa = new EmpresaTO();
                empresa.setIdEmpresa(rs.getLong("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setVantangens(rs.getString("vantagens"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return empresas;
    }

    public EmpresaTO findByIdEmpresa(Long idEmpresa) {
        EmpresaTO empresa = new EmpresaTO();
        String sql = "select * from empresa where id_empresa = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                empresa.setIdEmpresa(rs.getLong("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setVantangens(rs.getString("vantagens"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return empresa;
    }

    public EmpresaTO save(EmpresaTO empresa) {
        String sql = "insert into empresa(id_empresa, nome, cnpj, vantagens) values(?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, empresa.getIdEmpresa());
            ps.setString(2, empresa.getNome());
            ps.setString(3, empresa.getCNPJ());
            ps.setString(4, empresa.getVantangens());

            if (ps.executeUpdate() > 0) {
                return empresa;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }

    public boolean delete(long idEmpresa) {
        String sql = "delete from empresa where id_empresa = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEmpresa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public EmpresaTO update(EmpresaTO empresa) {
        String sql = "update empresa set nome=?, cnpj=?, vantagens=? where id_empresa=?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, empresa.getNome());
            ps.setString(2, empresa.getCNPJ());
            ps.setString(3, empresa.getVantangens());
            ps.setLong(4, empresa.getIdEmpresa());

            if (ps.executeUpdate() > 0) {
                return empresa;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }
}
