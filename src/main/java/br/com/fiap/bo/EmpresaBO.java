package br.com.fiap.bo;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.to.EmpresaTO;

import java.util.ArrayList;

public class EmpresaBO {

    private EmpresaDAO empresaDAO;

    public EmpresaBO() {
        empresaDAO = new EmpresaDAO();  // Instanciando o DAO
    }


    public ArrayList<EmpresaTO> listarEmpresas() {
        return empresaDAO.findAll();  //Lista todas as empresas
    }


    public EmpresaTO buscarPorId(long idEmpresa) {
        return empresaDAO.findByIdEmpresa(idEmpresa);
    }


    public EmpresaTO salvarEmpresa(EmpresaTO empresa) {
        validarNomeEmpresa(empresa.getNome());
        validarCNPJ(empresa.getCNPJ());
        return empresaDAO.save(empresa);
    }


    public EmpresaTO atualizarEmpresa(EmpresaTO empresa) {
        validarNomeEmpresa(empresa.getNome());
        validarCNPJ(empresa.getCNPJ());
        return empresaDAO.update(empresa);
    }


    public boolean excluirEmpresa(long idEmpresa) {

        return empresaDAO.delete(idEmpresa);
    }

    private void validarNomeEmpresa(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome da empresa inválido! Deve conter pelo menos 3 caracteres.");
        }
    }


    private void validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", ""); // Remove caracteres não numéricos

        // Verifica se tem 14 dígitos no CNPJ do usuário
        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido! Deve conter 14 dígitos.");
        }

    }
}