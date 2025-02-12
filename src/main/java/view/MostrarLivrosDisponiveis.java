package view;

import model.LivroModel;
import repository.LivroRepository;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MostrarLivrosDisponiveis extends JFrame {
    private JPanel jPanelTabela;
    private JLabel jLabelTitulo;
    private JScrollPane jScrollPaneLivro;
    private JTable tableBuscaLivro;

    public MostrarLivrosDisponiveis() {
        this.setTitle("Sistema - Escola nova CB");
        MostrarLivrosDisponiveis.LivroModeloDeTabela livroModeloDeTabela = new MostrarLivrosDisponiveis.LivroModeloDeTabela();
        tableBuscaLivro.setModel(livroModeloDeTabela);
        tableBuscaLivro.setAutoCreateRowSorter(true);
        this.setContentPane(jPanelTabela);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private static class LivroModeloDeTabela extends AbstractTableModel {
        private LivroRepository livroRepository = new LivroRepository();
        private final String[] COLUMNS = new String[]{"id", "título", "tema", "autor", "ISBN", "data", "quantidadeDisponivel"};
        private List<LivroModel> listaDeLivros = livroRepository.buscarDisponiveis();

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
