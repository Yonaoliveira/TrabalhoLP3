package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "emprestimo")
public class EmprestimoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    @ManyToOne
    @JoinColumn(name = "aluno_identificador")
    private AlunoModel aluno;
    @ManyToOne
    @JoinColumn(name = "livro_identifcador")
    private LivroModel livro;
    LocalDate data_emprestimo;
    LocalDate devolucao_prevista;
    LocalDate devolucao;

    public LivroModel getLivro() {
        return livro;
    }

    public void setLivro(LivroModel livro) {
        this.livro = livro;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getDevolucao_prevista() {
        return devolucao_prevista;
    }

    public void setDevolucao_prevista(LocalDate devolucao_prevista) {
        this.devolucao_prevista = devolucao_prevista;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(LocalDate devolucao) {
        this.devolucao = devolucao;
    }
}
