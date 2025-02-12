package repository;

import model.AlunoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    private static AlunoRepository instance;
    protected EntityManager entityManager;

    public AlunoRepository() {
        entityManager = getEntityManager();

    }
  private EntityManager getEntityManager() {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
      if (entityManager == null) {
          entityManager = factory.createEntityManager();
      }
        return entityManager;

  }
public static AlunoRepository getInstance() {
        if (instance == null) {
            instance = new AlunoRepository();
        }
        return instance;
}
public String salvar (AlunoModel aluno) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(aluno);
            entityManager.getTransaction().commit();
            return "Salvo com sucesso!";
        } catch (Exception e) {
            return "Erro ao Salvar: " + e.getMessage();
        }
}

public List<AlunoModel> buscarTodos(){
        try{
            List<AlunoModel> alunos = entityManager.createQuery("from AlunoModel").getResultList();
            return alunos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
}

public String remover(AlunoModel aluno){
    try{
        entityManager.getTransaction().begin();
        entityManager.remove(aluno);
        entityManager.getTransaction().commit();
        return "Removido com sucesso!";
    }catch (Exception e) {
        return "Erro ao remover: " + e.getMessage();
    }
}

public AlunoModel buscarPorId(String id){
        AlunoModel aluno = new AlunoModel();
        try{
            aluno = entityManager.find(AlunoModel.class, id);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;

}

public String atualizar(AlunoModel aluno)throws SQLException{
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(aluno);
            entityManager.getTransaction().commit();
            return "Atualizado com sucesso!";


        } catch (Exception e){
            return "Erro ao atualizar: " + e.getMessage();
        }




}

}





