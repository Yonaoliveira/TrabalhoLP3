package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame{
    private JMenuBar menuBar = new JMenuBar();
    private JPanel panel1 = new JPanel();

    public Principal() {
        criacaoDoMenu();
        this.setTitle(" sistema escola nova- cb");
        this.setContentPane(panel1);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

public void criacaoDoMenu(){
    this.setJMenuBar(menuBar);
    JMenu arquivo = new JMenu("Alunos");
    JMenuItem opcao1 = new JMenuItem("Cadastrar Aluno");
    JMenuItem opcao3 = new JMenuItem("Buscar Aluno");
    arquivo.add(opcao1);
    arquivo.add(opcao3);
    JMenu manterAluno = new JMenu("Livros");
    JMenuItem cadastro = new JMenuItem("Cadastrar Livro");
    JMenuItem buscar = new JMenuItem("Buscar Livro");
    manterAluno.add(cadastro);
    manterAluno.add(buscar);
    JMenu emprestimo = new JMenu("Empréstimos");
    JMenuItem emprestar = new JMenuItem("Realizar empréstimo");
    JMenuItem devolver = new JMenuItem("Registrar devolução");
    JMenuItem disponiveis = new JMenuItem("Livros disponíveis");
    emprestimo.add(emprestar);
    emprestimo.add(devolver);
    emprestimo.add(disponiveis);
    menuBar.add(arquivo);
    menuBar.add(manterAluno);
    menuBar.add(emprestimo);

    opcao1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CadastrarAluno();
        }
    });

    opcao3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new BuscarAluno();
        }
    });

    cadastro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CadastrarLivro();
        }
    });

    buscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new BuscarLivro();
        }
    });

    emprestar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {new EmprestarLivro();}
    });

    disponiveis.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           new MostrarLivrosDisponiveis();
       }
    });

}

    }



