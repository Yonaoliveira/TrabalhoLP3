package view;

import controller.AlunoController;
import controller.LivroController;
import model.AlunoModel;
import model.LivroModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AtualizarLivro extends JFrame {
    private JLabel jLabelTitle;
    private JLabel jLabelTema;
    private JLabel jLabelAutor;
    private JLabel jLabelIsbn;
    private JLabel jLablDataPub;
    private JLabel jLabelQuantidade;
    private JLabel jLabelIdentificador;
    private JTextField textFieldTitulo;
    private JTextField textFieldTema;
    private JTextField textFieldAutor;
    private JTextField textFieldIsbn;
    private JTextField textFieldDataPub;
    private JTextField textFieldquantidadeDisponivel;
    private JTextField textFieldIdentifcador;
    private JLabel jLabelTitulo;
    private JButton atualizarButton;
    private JPanel panel2;


    public AtualizarLivro(LivroModel livro){
      this.setTitle("Sistema- escola nova cb");
      this.setContentPane(panel2);
      this.setSize(640,480);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      textFieldTema.setText(livro.getTema());
      textFieldAutor.setText(livro.getAutor());
      textFieldIsbn.setText(livro.getISBN());
      textFieldquantidadeDisponivel.setText(livro.getQuantidadeDisponivel()+"");
      textFieldDataPub.setText(livro.getDataPublicacao());
      textFieldIdentifcador.setText(livro.getIdentifcador());
      textFieldTitulo.setText(livro.getTitulo());

      this.setVisible(true);

   atualizarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LivroModel livro = new LivroModel();
            LivroController LivroController = new LivroController();

            livro.setTitulo(textFieldTitulo.getText());
            livro.setTema(textFieldTema.getText());
            livro.setAutor(textFieldAutor.getText());
            livro.setISBN(textFieldIsbn.getText());
            livro.setIdentifcador(textFieldIdentifcador.getText());
            livro.setQuantidadeDisponivel(Integer.parseInt( textFieldquantidadeDisponivel.getText()));
            livro.setDataPublicacao(textFieldDataPub.getText());
            try {
                JOptionPane.showMessageDialog(null,LivroController.atualizar(livro));
                dispose();
            } catch (SQLException ex){
                throw new RuntimeException(ex);
            }


        }
    });
}

}
