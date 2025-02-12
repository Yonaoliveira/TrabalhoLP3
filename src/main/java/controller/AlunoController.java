package controller;

import model.AlunoModel;
import java.util.List;
import repository.AlunoRepository;
import java.sql.SQLException;

public class AlunoController {
    private AlunoRepository alunoRepository = new AlunoRepository();

    public String salvar (AlunoModel aluno) throws SQLException {
        return alunoRepository.salvar(aluno);
    }

    public List<AlunoModel> buscarTodos() throws SQLException{
        return alunoRepository.buscarTodos();
    }

    public String remover(String identificador) throws SQLException {
        AlunoModel aluno = alunoRepository.buscarPorId(identificador);
        return alunoRepository.remover(aluno);
    }

    public String atualizar(AlunoModel aluno) throws  SQLException{
        return  alunoRepository.atualizar(aluno);
    }
}
