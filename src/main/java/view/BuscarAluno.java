package view;

import controller.AlunoController;
import repository.AlunoRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import model.AlunoModel;
import javax.swing.table.AbstractTableModel;

public class BuscarAluno extends JFrame {
    private JLabel jLabelTitulo;
    private JTextField textFieldBusca;
    private JButton buscarButton;
    private JScrollPane jScrollPaneAluno;
    private JTable tableBuscaAluno;
    private JPanel jPanelTabela;
    private JButton removerButton;
    private JButton editarButton;

    public BuscarAluno() {
        this.setTitle("Sistema - Escola nova CB");
        AlunoModeloDeTabela alunoModeloDeTabela = new AlunoModeloDeTabela();
        tableBuscaAluno.setModel(alunoModeloDeTabela);
        tableBuscaAluno.setAutoCreateRowSorter(true);
        this.setContentPane(jPanelTabela);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int linhaSelecionada = tableBuscaAluno.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String identificador = tableBuscaAluno.getValueAt(linhaSelecionada, 0).toString();
                    try{
                        AlunoController alunoController = new AlunoController();
                        JOptionPane.showMessageDialog(null, alunoController.remover(identificador));
                        AlunoModeloDeTabela alunoModeloDeTabela = new AlunoModeloDeTabela();
                        tableBuscaAluno.setModel(alunoModeloDeTabela);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione o aluno que deseja remover");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaAluno.getSelectedRow();
                if(linhaSelecionada!= -1){
                    String identificador = tableBuscaAluno.getValueAt(linhaSelecionada, 0).toString();
                    AlunoRepository alunoRepository= new AlunoRepository();
                    AlunoModel aluno= alunoRepository.buscarPorId(identificador);
                    new AtualizarAluno(aluno);
                }
            }
        });


    }

    private static class AlunoModeloDeTabela extends AbstractTableModel{
        private AlunoRepository alunoRepository = new AlunoRepository();
        private final String[] COLUMNS = new String[]{"id", "nome", "sexo", "email", "telefone"};
        private List<AlunoModel> listaDeAlunos = alunoRepository.buscarTodos();

        @Override
        public int getRowCount() {
            return listaDeAlunos.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex){
                case 0 -> listaDeAlunos.get(rowIndex).getIdentificador();
                case 1 -> listaDeAlunos.get(rowIndex).getNome();
                case 2 -> listaDeAlunos.get(rowIndex).getSexo();
                case 3 -> listaDeAlunos.get(rowIndex).getEmail();
                case 4 -> listaDeAlunos.get(rowIndex).getTelefone();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int columnIndex) {
            return COLUMNS[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }
}