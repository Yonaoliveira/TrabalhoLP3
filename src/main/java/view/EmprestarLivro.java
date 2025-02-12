package view;

import controller.EmprestimoController;
import model.AlunoModel;
import model.EmprestimoModel;
import controller.LivroController;
import controller.AlunoController;
import model.LivroModel;
import repository.AlunoRepository;
import repository.LivroRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class EmprestarLivro extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JLabel JLabelTitulo;
    private JLabel JLabelAluno;
    private JLabel JLabelLivro;
    private JLabel JLabelDataEmprestimo;
    private JLabel JLabelDataPrevisao;
    private JButton emprestarButton;
    private JList listAlunos;
    private JList listLivros;
    private JTextField textFieldDataEmprestimo;
    private JTextField textFieldDataPrevisao;
    private JScrollPane JScrollPaneAlunos;
    private JScrollPane JScrollPaneLivros;

    public EmprestarLivro() {
        this.setTitle("Sistema- escola nova cb");
        this.setSize(640,480);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultListModel listModelAlunos = new DefaultListModel();
        DefaultListModel listModelLivros = new DefaultListModel();

        LivroController livroController = new LivroController();
        AlunoController alunoController = new AlunoController();
        List<LivroModel> livros;
        List<AlunoModel> alunos;
        try {
            livros = livroController.buscarTodos();
            alunos = alunoController.buscarTodos();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        for (LivroModel livroModel : livros) {
            listModelLivros.addElement(livroModel.getIdentifcador()+" - "+livroModel.getTitulo());
        }

        for (AlunoModel alunoModel : alunos) {
            listModelAlunos.addElement(alunoModel.getIdentificador()+" - "+alunoModel.getNome());
        }

        listAlunos.setModel(listModelAlunos);
        listLivros.setModel(listModelLivros);

        JScrollPaneAlunos.setViewportView(listAlunos);
        JScrollPaneLivros.setViewportView(listLivros);

        this.setVisible(true);

        emprestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                EmprestimoModel emprestimoModel = new EmprestimoModel();
                EmprestimoController emprestimoController = new EmprestimoController();

                String id_aluno = listAlunos.getSelectedValue().toString().split("-")[0];
                AlunoRepository alunoRepository = new AlunoRepository();
                AlunoModel alunoModel = alunoRepository.buscarPorId(id_aluno);

                String id_livro = listLivros.getSelectedValue().toString().split("-")[0];
                LivroRepository livroRepository = new LivroRepository();
                LivroModel livroModel = livroRepository.buscarPorId(id_livro);

                emprestimoModel.setAluno(alunoModel);
                emprestimoModel.setLivro(livroModel);
                String data_emprestimo = textFieldDataEmprestimo.getText();
                String data_previsao = textFieldDataPrevisao.getText();
                emprestimoModel.setData_emprestimo(LocalDate.parse(data_emprestimo, formato));
                emprestimoModel.setDevolucao_prevista(LocalDate.parse(data_previsao, formato));
                livroModel.emprestar();
                try {
                    livroRepository.atualizar(livroModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(null,emprestimoController.salvar(emprestimoModel));
                dispose();
            }
        });
    }
}
