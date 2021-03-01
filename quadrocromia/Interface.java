package quadrocromia;

import java.awt.*;
import javax.swing.*;

public class Interface extends JFrame {
    private static final long serialVersionUID = 5959874721938974760L;

    public Interface() {
        super();
        // frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        // panel com toolbar e inventario1 na região norte do frame
        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.PAGE_AXIS));
        contentPane.add(topo, BorderLayout.NORTH);
        
        // toolbar
        JToolBar toolbar = new JToolBar("Menu");
        toolbar.setFloatable(false);
        toolbar.setBackground(Color.WHITE);
        topo.add(toolbar);
        // botões da toolbar
        JButton conectar = new JButton("Conectar");
        conectar.setBackground(Color.WHITE);
        toolbar.add(conectar);
        JButton desconectar = new JButton("Desconectar");
        desconectar.setBackground(Color.WHITE);
        toolbar.add(desconectar);

        Insets buttonMargin = new Insets(0,0,0,0);
        
        // inventario1
        JPanel inventario1 = new JPanel();
        topo.add(inventario1, BorderLayout.NORTH);
        JButton[] pecas1 = new JButton[9];
        // inventario2
        JPanel inventario2 = new JPanel();
        contentPane.add(inventario2, BorderLayout.SOUTH);
        JButton[] pecas2 = new JButton[9];
        // cria peças e insere no inventario1 e no inventario2
        for (int i = 0; i < pecas1.length; i++) {
        	pecas1[i] = new JButton();
        	pecas1[i].setMargin(buttonMargin);
        	pecas1[i].setBackground(Color.WHITE);
        	inventario1.add(pecas1[i]);
        	pecas2[i] = new JButton();
        	pecas2[i].setMargin(buttonMargin);
        	pecas2[i].setBackground(Color.WHITE);
        	inventario2.add(pecas2[i]);
        }
        // imagens das peças
        ImageIcon verde1 = new ImageIcon(getClass().getResource("verde1.png"));
        ImageIcon verde2 = new ImageIcon(getClass().getResource("verde2.png"));
        ImageIcon verde3 = new ImageIcon(getClass().getResource("verde3.png"));
        
        pecas1[0].setIcon(verde2);
        pecas2[0].setIcon(verde3);

        //ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(6, 6));
        JButton[] posicoes = new JButton[36];
        for (int i = 0; i < posicoes.length; i++) {
        	posicoes[i] = new JButton();
        	posicoes[i].setMargin(buttonMargin);
        	posicoes[i].setBackground(Color.WHITE);
        	
        	tabuleiro.add(posicoes[i]);
        }
        contentPane.add(tabuleiro, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Interface frame = new Interface();
        frame.setTitle("Quadrocromia");
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
