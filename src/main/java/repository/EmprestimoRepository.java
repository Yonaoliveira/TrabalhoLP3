package repository;

import model.EmprestimoModel;
import model.LivroModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmprestimoRepository {
    private static EmprestimoRepository instance;
    protected EntityManager entityManager;

    public EmprestimoRepository() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;

    }

    public static EmprestimoRepository getInstance() {
        if (instance == null) {
            instance = new EmprestimoRepository();
        }
        return instance;
    }

    public String salvar(EmprestimoModel emprestimo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(emprestimo);
            entityManager.getTransaction().commit();
            return "Empréstimo realizado com sucesso!";

        } catch (Exception e) {
            return "Erro ao realizar o empréstimos " + e.getMessage();
        }
    }
}
