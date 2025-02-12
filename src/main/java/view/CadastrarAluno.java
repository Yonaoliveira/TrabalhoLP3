package view;

import controller.AlunoController;
import model.AlunoModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastrarAluno extends JFrame {

    private JPanel principal;
    private JLabel jLabelTitulo;
    private JLabel jLabelSexo;
    private JLabel jLabelTelefone;
    private JLabel jLabelNome;
    private JButton enviarButton;
    private JPanel panel2;
    private AlunoController alunoController;

    private JTextField textFieldNome;
    private JTextField textFieldIdade;
    private JTextField textFieldTelefone;
    private JTextField textFieldEmail;
    private JLabel jLabelEmail;
    private JLabel jLabelIdentificador;
    private JTextField textFieldIdentificador;
    private JTextField textFieldSexo;

    public CadastrarAluno(){
        this.setTitle("Sistema- escola nova cb");
        this.setContentPane(panel2);
        this.setSize(640,480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoModel aluno = new AlunoModel();
                alunoController = new AlunoController();

                aluno.setNome(textFieldNome.getText());
                aluno.setTelefone(textFieldTelefone.getText());
                aluno.setIdentificador(textFieldIdentificador.getText());
                aluno.setEmail(textFieldEmail.getText());
                aluno.setSexo(textFieldSexo.getText());
                try {
                    JOptionPane.showMessageDialog(null,alunoController.salvar(aluno));
                    dispose();
                } catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

            }
        });
    }


}