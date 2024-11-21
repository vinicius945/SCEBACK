package br.com.fiap.bo;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.to.AvaliacaoTO;

import java.util.ArrayList;

public class AvaliacaoBO {

    private AvaliacaoDAO avaliacaoDAO;

    public AvaliacaoBO() {
        this.avaliacaoDAO = new AvaliacaoDAO(); // Inicializa o DAO
    }

    // Lista Todas as avaliações ;)
    public ArrayList<AvaliacaoTO> findAll() {
        return avaliacaoDAO.findAll();
    }

    // Busca a avaliação pelo ID da mesma
    public AvaliacaoTO findById(Long idAvaliacao) {
        return avaliacaoDAO.findById(idAvaliacao);
    }

    // Criando uma nova avaliação
    public AvaliacaoTO save(AvaliacaoTO avaliacao) {
        validarAvaliacao(avaliacao);

        return avaliacaoDAO.save(avaliacao);
    }

    // Apaga uma avaliação pelo ID
    public boolean delete(Long idAvaliacao) {
        return avaliacaoDAO.delete(idAvaliacao);
    }


    private void validarAvaliacao(AvaliacaoTO avaliacao) {
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
        }
    }

    public AvaliacaoTO update(AvaliacaoTO avaliacao) {
        if (avaliacao.getIdAvaliacao() <= 0) {
            throw new IllegalArgumentException("ID de avaliação inválido: " + avaliacao.getIdAvaliacao());
        }

        return avaliacaoDAO.update(avaliacao);
    }

}
