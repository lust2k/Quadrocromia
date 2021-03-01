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
        ImageIcon branco = new ImageIcon(getClass().getResource("branco.png"));
        ImageIcon verde1 = new ImageIcon(getClass().getResource("verde1.png"));
        ImageIcon verde2 = new ImageIcon(getClass().getResource("verde2.png"));
        ImageIcon verde3 = new ImageIcon(getClass().getResource("verde3.png"));
        ImageIcon vermelho1 = new ImageIcon(getClass().getResource("vermelho1.png"));
        ImageIcon vermelho2 = new ImageIcon(getClass().getResource("vermelho2.png"));
        ImageIcon vermelho3 = new ImageIcon(getClass().getResource("vermelho3.png"));
        ImageIcon amarelo1 = new ImageIcon(getClass().getResource("amarelo1.png"));
        ImageIcon amarelo2 = new ImageIcon(getClass().getResource("amarelo2.png"));
        ImageIcon amarelo3 = new ImageIcon(getClass().getResource("amarelo3.png"));
        
        pecas1[0].setIcon(verde2);
        pecas1[1].setIcon(vermelho1);
        pecas1[2].setIcon(vermelho3);
        pecas1[3].setIcon(amarelo1);
        pecas1[4].setIcon(verde3);
        pecas1[5].setIcon(verde1);
        pecas1[6].setIcon(vermelho2);
        pecas1[7].setIcon(amarelo2);
        pecas1[8].setIcon(amarelo1);
        pecas2[0].setIcon(amarelo3);
        pecas2[1].setIcon(verde2);
        pecas2[2].setIcon(vermelho3);
        pecas2[3].setIcon(amarelo2);
        pecas2[4].setIcon(vermelho2);
        pecas2[5].setIcon(vermelho1);
        pecas2[6].setIcon(verde3);
        pecas2[7].setIcon(amarelo3);
        pecas2[8].setIcon(verde1);

        // tabuleiro
        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(6, 6));
        JButton[] posicoes = new JButton[36];
        for (int i = 0; i < posicoes.length; i++) {
        	posicoes[i] = new JButton();
        	posicoes[i].setIcon(branco);
        	posicoes[i].setMargin(buttonMargin);
        	posicoes[i].setBackground(Color.WHITE);
        	tabuleiro.add(posicoes[i]);
        }
        contentPane.add(tabuleiro, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Interface frame = new Interface();
        frame.setTitle("Quadrocromia");
        frame.pack();
        frame.setMaximumSize(frame.getSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
