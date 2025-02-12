package view;

import controller.AlunoController;
import model.AlunoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class AtualizarAluno extends JFrame {
    private JPanel panel2;
    private JTextField textFieldNome;
    private JLabel jLabelNome;
    private JLabel jLabelSexo;
    private JLabel jLabelTelefone;
    private JTextField textFieldTelefone;
    private JButton atualizarButton;
    private JLabel jLabelEmail;
    private JTextField textFieldEmail;
    private JTextField textFieldIdentificador;
    private JLabel jLabelIdentificador;
    private JTextField textFieldSexo;
    private JLabel jLabelAtualizarAluno;

    public AtualizarAluno(AlunoModel aluno){
        this.setTitle("Sistema- escola nova cb");
        this.setContentPane(panel2);
        this.setSize(640,480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textFieldNome.setText(aluno.getNome());
        textFieldEmail.setText(aluno.getEmail());
        textFieldIdentificador.setText(aluno.getIdentificador());
        textFieldTelefone.setText(aluno.getTelefone());
        textFieldSexo.setText(aluno.getSexo());

        this.setVisible(true);

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoModel aluno = new AlunoModel();
                AlunoController alunoController = new AlunoController();

                aluno.setNome(textFieldNome.getText());
                aluno.setTelefone(textFieldTelefone.getText());
                aluno.setIdentificador(textFieldIdentificador.getText());
                aluno.setEmail(textFieldEmail.getText());
                aluno.setSexo(textFieldSexo.getText());
                try {
                    JOptionPane.showMessageDialog(null,alunoController.atualizar(aluno));
                    dispose();
                } catch (SQLException ex){
                    throw new RuntimeException(ex);
                }


            }
        });
    }

}
