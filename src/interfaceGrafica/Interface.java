package interfaceGrafica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interface extends JFrame {
    private static final long serialVersionUID = 5959874721938974760L;
    protected InterfaceJogador atorJogador;
    protected boolean pecaSelecionada = false;
    protected boolean conectado = false;
    protected boolean jogadoresProntos = false;
    protected String nomeJogador;
    protected String enderecoServidor;

    public Interface() {
    	super();
    	atorJogador = new InterfaceJogador();
        // frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        // panel com toolbar e inventario1 na regi�o norte do frame
        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.PAGE_AXIS));
        contentPane.add(topo, BorderLayout.NORTH);
        
        // toolbar
        JToolBar toolbar = new JToolBar("Menu");
        toolbar.setFloatable(false);
        toolbar.setBackground(Color.WHITE);
        topo.add(toolbar);
        // bot�es da toolbar
        JButton conectar = new JButton("Conectar");
        conectar.setBackground(Color.WHITE);
        toolbar.add(conectar);
        JButton iniciarPartida = new JButton("Iniciar Partida");
        iniciarPartida.setBackground(Color.WHITE);
        toolbar.add(iniciarPartida);
        JButton desconectar = new JButton("Desconectar");
        desconectar.setBackground(Color.WHITE);
        toolbar.add(desconectar);
        
        // a��es dos bot�es da toolbar
        ActionListener eventosToolbar = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (e.getSource() == conectar) {
        			nomeJogador = JOptionPane.showInputDialog("Insira seu nome.");
        			enderecoServidor = JOptionPane.showInputDialog("Insira o endere�o do servidor.");
        			atorJogador.conectar();
        		} else if (e.getSource() == iniciarPartida) {
        			atorJogador.iniciarPartida();
        		} else if (e.getSource() == desconectar) {
        			atorJogador.desconectar();
        		}
        	}
        };
        
        conectar.addActionListener(eventosToolbar);
        iniciarPartida.addActionListener(eventosToolbar);
        desconectar.addActionListener(eventosToolbar);
        
        Insets buttonMargin = new Insets(1,1,1,1);
        
        // inventario1
        JPanel inventario1 = new JPanel();
        topo.add(inventario1, BorderLayout.NORTH);
        JButton[] pecas1 = new JButton[9];
        // inventario2
        JPanel inventario2 = new JPanel();
        contentPane.add(inventario2, BorderLayout.SOUTH);
        JButton[] pecas2 = new JButton[9];
        
        // a��es dos bot�es dos invent�rios
        ActionListener eventosInventario = new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < pecas1.length; i++) {
        			pecas1[i].setBackground(Color.WHITE);
        			pecas2[i].setBackground(Color.WHITE);
        		}
        		((JButton) e.getSource()).setBackground(Color.BLACK);
        		pecaSelecionada = true;
        	}
        };
        
        // cria pe�as e insere no inventario1 e no inventario2
        for (int i = 0; i < pecas1.length; i++) {
        	pecas1[i] = new JButton();
        	pecas1[i].setMargin(buttonMargin);
        	pecas1[i].setBackground(Color.WHITE);
        	pecas1[i].addActionListener(eventosInventario);
        	inventario1.add(pecas1[i]);
        	pecas2[i] = new JButton();
        	pecas2[i].setMargin(buttonMargin);
        	pecas2[i].setBackground(Color.WHITE);
        	pecas2[i].addActionListener(eventosInventario);
        	inventario2.add(pecas2[i]);
        }
        
        // imagens das pe�as
        ImageIcon branco = new ImageIcon(getClass().getResource("imagens/branco.png")); // n�mero de pe�as:
        ImageIcon verde2 = new ImageIcon(getClass().getResource("imagens/verde2.png")); // 2
        ImageIcon verde3 = new ImageIcon(getClass().getResource("imagens/verde3.png")); // 2
        ImageIcon vermelho1 = new ImageIcon(getClass().getResource("imagens/vermelho1.png")); // 2
        ImageIcon vermelho2 = new ImageIcon(getClass().getResource("imagens/vermelho2.png")); // 2
        ImageIcon amarelo1 = new ImageIcon(getClass().getResource("imagens/amarelo1.png")); // 1
        ImageIcon amarelo3 = new ImageIcon(getClass().getResource("imagens/amarelo3.png")); // 2
        ImageIcon azul1 = new ImageIcon(getClass().getResource("imagens/azul1.png")); // 3
        ImageIcon azul2 = new ImageIcon(getClass().getResource("imagens/azul2.png")); // 2
        ImageIcon azul3 = new ImageIcon(getClass().getResource("imagens/azul3.png")); // 2
        
        pecas1[0].setIcon(verde2);
        pecas1[1].setIcon(vermelho1);
        pecas1[2].setIcon(azul2);
        pecas1[3].setIcon(amarelo1);
        pecas1[4].setIcon(verde3);
        pecas1[5].setIcon(azul1);
        pecas1[6].setIcon(vermelho2);
        pecas1[7].setIcon(amarelo3);
        pecas1[8].setIcon(azul3);
        pecas2[0].setIcon(amarelo3);
        pecas2[1].setIcon(verde2);
        pecas2[2].setIcon(vermelho1);
        pecas2[3].setIcon(azul1);
        pecas2[4].setIcon(azul3);
        pecas2[5].setIcon(vermelho2);
        pecas2[6].setIcon(verde3);
        pecas2[7].setIcon(azul2);
        pecas2[8].setIcon(azul1);

        // a��es dos bot�es do tabuleiro
        ActionListener eventosTabuleiro = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (pecaSelecionada == false) {
        			notificar("Selecione uma pe�a antes de jogar.");
        		};
        	};
        };
        
        // tabuleiro
        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridLayout(6, 6));
        JButton[] posicoes = new JButton[36];
        for (int i = 0; i < posicoes.length; i++) {
        	posicoes[i] = new JButton();
        	posicoes[i].setIcon(branco);
        	posicoes[i].setMargin(buttonMargin);
        	posicoes[i].setBackground(Color.WHITE);
        	posicoes[i].addActionListener(eventosTabuleiro);
        	tabuleiro.add(posicoes[i]);
        }
        contentPane.add(tabuleiro, BorderLayout.CENTER);
    }
    
    public String obterNomeJogador() {
    	return nomeJogador;
    }
    
    public String obterEnderecoServidor() {
    	return enderecoServidor;
    }
    
    public void notificar(String notificacao) {
    	JOptionPane.showMessageDialog(null, notificacao);
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