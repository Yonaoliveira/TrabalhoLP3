package model;

import javax.persistence.*;


/*@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="pessoa")*/
public abstract class PessoaModel{
     /*@Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private long idPessoa;
    private String  nome;
    private long idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdade() {
        return idade;
    }

    public void setIdade(long idade) {
        this.idade = idade;
    }

}
