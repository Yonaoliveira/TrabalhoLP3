package view;

import controller.LivroController;
import model.LivroModel;
import repository.LivroRepository;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BuscarLivro extends JFrame {
    private JPanel jPanelTabela;
    private JLabel jLabelTitulo;
    private JTextField textFieldBusca;
    private JButton buscarButton;
    private JScrollPane jScrollPaneLivro;
    private JTable tableBuscaLivro;
    private JButton removerButton;
    private JButton editarButton;

    public BuscarLivro() {
        this.setTitle("Sistema - Escola nova CB");
        BuscarLivro.LivroModeloDeTabela livroModeloDeTabela = new BuscarLivro.LivroModeloDeTabela();
        tableBuscaLivro.setModel(livroModeloDeTabela);
        tableBuscaLivro.setAutoCreateRowSorter(true);
        this.setContentPane(jPanelTabela);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int linhaSelecionada = tableBuscaLivro.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String identificador = tableBuscaLivro.getValueAt(linhaSelecionada, 0).toString();
                    try{
                        LivroController livroController = new LivroController();
                        JOptionPane.showMessageDialog(null, livroController.remover(identificador));
                        BuscarLivro.LivroModeloDeTabela livroModeloDeTabela = new BuscarLivro.LivroModeloDeTabela();
                        tableBuscaLivro.setModel(livroModeloDeTabela);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione o livro que deseja remover");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaLivro.getSelectedRow();
                if(linhaSelecionada!= -1){
                    String identificador = tableBuscaLivro.getValueAt(linhaSelecionada, 0).toString();
                    LivroRepository livroRepository= new LivroRepository();
                    LivroModel livro= livroRepository.buscarPorId(identificador);
                    new AtualizarLivro(livro);

                }
            }
        });


    }

    private static class LivroModeloDeTabela extends AbstractTableModel {
        private LivroRepository livroRepository = new LivroRepository();
        private final String[] COLUMNS = new String[]{"id", "título", "tema", "autor", "ISBN", "data", "quantidadeDisponivel"};
        private List<LivroModel> listaDeLivros = livroRepository.buscarTodos();

        @Override
        public int getRowCount() {
            return listaDeLivros.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex){
                case 0 -> listaDeLivros.get(rowIndex).getIdentifcador();
                case 1 -> listaDeLivros.get(rowIndex).getTitulo();
                case 3 -> listaDeLivros.get(rowIndex).getTema();
                case 2 -> listaDeLivros.get(rowIndex).getAutor();
                case 4 -> listaDeLivros.get(rowIndex).getISBN();
                case 5 -> listaDeLivros.get(rowIndex).getDataPublicacao();
                case 6 -> listaDeLivros.get(rowIndex).getQuantidadeDisponivel();
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
