package view;

import controller.LivroController;
import model.LivroModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastrarLivro extends JFrame {
    private JPanel panel1;
    private JLabel jLabelTitulo;
    private JLabel jLabelTitle;
    private JLabel jLabelTema;
    private JLabel jLabelAutor;
    private JLabel jLabelIsbn;
    private JLabel jLablDataPub;
    private JLabel jLabelQuantidade;
    private JLabel jLabelIdentificador;
    private JButton cadastrarButton;
    private JTextField textFieldTitulo;
    private JTextField textFieldTema;
    private JTextField textFieldAutor;
    private JTextField textFieldIsbn;
    private JTextField textFieldDataPub;
    private JTextField textFieldQuantidade;
    private JTextField textFieldIdentifcador;

    public CadastrarLivro() {
        this.setTitle("Sistema- escola nova cb");
        this.setSize(640,480);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivroModel livro = new LivroModel();
                LivroController livroController = new LivroController();
                livro.setTitulo(textFieldTitulo.getText());
                livro.setAutor(textFieldAutor.getText());
                livro.setTema(textFieldTema.getText());
                livro.setISBN(textFieldIsbn.getText());
                livro.setDataPublicacao(textFieldDataPub.getText());
                livro.setQuantidadeDisponivel(Integer.parseInt(textFieldQuantidade.getText()));
                livro.setIdentifcador(textFieldIdentifcador.getText());

                try{
                    JOptionPane.showMessageDialog(null,livroController.salvar(livro));
                    dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }
}
