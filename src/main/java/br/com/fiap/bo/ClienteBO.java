package br.com.fiap.bo;


import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;

import java.util.ArrayList;

public class ClienteBO {
    private ClienteDAO clienteDAO;

    public ClienteBO() {
        this.clienteDAO = new ClienteDAO(); // Inicializa o DAO
    }

    // Retorna todos os clientes
    public ArrayList<ClienteTO> findAll() {
        return clienteDAO.findAll();
    }

    // Busca um cliente pelo ID
    public ClienteTO findByIdCliente(Long idCliente) {
        return clienteDAO.findByIdCliente(idCliente);
    }

    // Cria um novo cliente
    public ClienteTO save(ClienteTO cliente) {
        validarNome(cliente.getNome());
        validarEmail(cliente.getEmail());
        return clienteDAO.save(cliente);
    }

    // Exclui um cliente pelo ID
    public boolean delete(Long idCliente) {
        return clienteDAO.delete(idCliente);
    }

    // Atualiza um cliente existente
    public ClienteTO update(ClienteTO cliente) {
        validarNome(cliente.getNome());
        validarEmail(cliente.getEmail());
        return clienteDAO.update(cliente);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome inválido! Deve conter pelo menos 3 caracteres.");
        }
    }

    // Validação de e-mail
    private void validarEmail(String email) {
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("E-mail inválido! Verifique o formato.");
        }
    }
}

