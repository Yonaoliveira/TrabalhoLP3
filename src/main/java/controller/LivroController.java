package controller;

import model.AlunoModel;
import model.LivroModel;
import repository.LivroRepository;
import java.sql.SQLException;
import java.util.List;

public class LivroController {
    private LivroRepository livroRepository = new LivroRepository();
    public String salvar (LivroModel livro) throws SQLException {
        return livroRepository.salvar(livro);
    }

    public List<LivroModel> buscarTodos() throws SQLException{
        return livroRepository.buscarTodos();
    }

    public String remover(String identificador) throws SQLException {
        LivroModel livro = livroRepository.buscarPorId(identificador);
        return livroRepository.remover(livro);
    }


    public String atualizar(LivroModel livro) throws  SQLException{
        return  livroRepository.atualizar(livro);
    }
}

