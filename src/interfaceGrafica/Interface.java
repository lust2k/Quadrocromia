package interfaceGrafica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dominioDoProblema.Inventario;
import dominioDoProblema.Lance;
import dominioDoProblema.Peca;
import dominioDoProblema.Tabuleiro;

public class Interface extends JFrame {
    private static final long serialVersionUID = 5959874721938974760L;
    protected InterfaceJogador interfaceJogador;
    protected Peca pecaSelecionada;
    protected boolean conectado = false;
    protected boolean jogadoresProntos = false;
    protected String nomeJogador;
    protected String enderecoServidor;
    protected JLabel mensagem;
    protected Tabuleiro tabuleiro_;
    protected JButton[][] botoesTabuleiro;
    protected JButton[] botoesInventario1;
    protected JButton[] botoesInventario2;
    protected JLabel inventarioCima;
    protected JLabel inventarioBaixo;

    public Interface() {
      	super();
        
        // frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // panel com toolbar, nome e inventário do jogador 1 na região norte do frame
        JPanel topo = new JPanel();
        topo.setLayout(new BorderLayout());
        contentPane.add(topo, BorderLayout.NORTH);

        // panel com painelBottom e botões "girar" e "inverter" na região sul do frame
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        contentPane.add(bottom, BorderLayout.SOUTH);

        // toolbar
        JToolBar toolbar = new JToolBar("Menu");
        toolbar.setFloatable(false);
        toolbar.setBackground(Color.WHITE);
        topo.add(toolbar, BorderLayout.NORTH);
        // botões da toolbar
        JButton conectar = new JButton("Conectar");
        conectar.setBackground(Color.WHITE);
        toolbar.add(conectar);
        JButton iniciarPartida = new JButton("Iniciar Partida");
        iniciarPartida.setBackground(Color.WHITE);
        toolbar.add(iniciarPartida);
        JButton desconectar = new JButton("Desconectar");
        desconectar.setBackground(Color.WHITE);
        toolbar.add(desconectar);

        toolbar.addSeparator(new Dimension(0, 0));
        mensagem = new JLabel("");
        toolbar.add(mensagem);

        // painelBottom com nome e inventário do jogador 2
        JPanel painelBottom = new JPanel();
        painelBottom.setLayout(new BorderLayout());
        bottom.add(painelBottom, BorderLayout.NORTH);

        /* caso dê problema nas cores, ative essas linhas
        topo.setBackground(new Color(255, 255, 255, 80));
        painelBottom.setBackground(new Color(255, 255, 255, 80));
        */

        JButton girar = new JButton("Girar peça: Vertical");
        girar.setBackground(Color.WHITE);
        bottom.add(girar, BorderLayout.CENTER);
        JButton inverter = new JButton("Direcao: cima");
        inverter.setBackground(Color.WHITE);
        bottom.add(inverter, BorderLayout.SOUTH);

        Insets buttonMargin = new Insets(1,1,1,1);

        // inventario1
        botoesInventario1 = new JButton[9];
        JPanel inventario1 = new JPanel();
        topo.add(inventario1, BorderLayout.SOUTH);
        inventarioCima = new JLabel(" ");
        topo.add(inventarioCima, BorderLayout.CENTER);
        // inventario2
        botoesInventario2 = new JButton[9];
        JPanel inventario2 = new JPanel();
        painelBottom.add(inventario2, BorderLayout.CENTER);
        inventarioBaixo = new JLabel(" ");
        painelBottom.add(inventarioBaixo, BorderLayout.NORTH);

        // imagem peça vazia
        ImageIcon branco3 = new ImageIcon(getClass().getResource("imagens/branco3.png"));
        botoesTabuleiro = new JButton[6][6];
        tabuleiro_ = new Tabuleiro(botoesTabuleiro, botoesInventario1, botoesInventario2);
        interfaceJogador = new InterfaceJogador(this);
        tabuleiro_.defineAtorJogador(this.interfaceJogador);

        // cria peças e insere no inventario1 e no inventario2
        for (int i = 0; i < botoesInventario1.length; i++) {
        	botoesInventario1[i] = new JButton();
        	botoesInventario1[i].setMargin(buttonMargin);
        	botoesInventario1[i].setBackground(Color.WHITE);
        	botoesInventario1[i].addMouseListener(tabuleiro_);
        	botoesInventario1[i].setIcon(branco3);
        	inventario1.add(botoesInventario1[i]);
        }

        for (int i = 0; i < botoesInventario2.length; i++) {
        	botoesInventario2[i] = new JButton();
        	botoesInventario2[i].setMargin(buttonMargin);
        	botoesInventario2[i].setBackground(Color.WHITE);
        	botoesInventario2[i].addMouseListener(tabuleiro_);
        	botoesInventario2[i].setIcon(branco3);
        	inventario2.add(botoesInventario2[i]);
        }

        // tabuleiro
       
        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(6, 6));
        for (int i = 0; i < 6; i++) {
        	for (int j = 0; j < 6; j++) {
        		botoesTabuleiro[i][j] = new JButton();
        		botoesTabuleiro[i][j].setPreferredSize(new Dimension(64, 64));
        		botoesTabuleiro[i][j].setMargin(buttonMargin);
        		botoesTabuleiro[i][j].setBackground(Color.WHITE);
        		botoesTabuleiro[i][j].addMouseListener(tabuleiro_);
            tabuleiroPanel.add(botoesTabuleiro[i][j]);
        	}
        }

        contentPane.add(tabuleiroPanel, BorderLayout.CENTER);

        // ações dos botões
        ActionListener eventosBotoes = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (e.getSource() == conectar) {
        			nomeJogador = JOptionPane.showInputDialog("Insira seu nome.");
        			enderecoServidor = JOptionPane.showInputDialog("Insira o endereço do servidor.");
        			interfaceJogador.conectar();
        		} else if (e.getSource() == iniciarPartida) {
        			interfaceJogador.iniciarPartida();
        		} else if (e.getSource() == desconectar) {
        			interfaceJogador.desconectar();
        		} else if (e.getSource() == girar) {
        			this.girarPeca();
        		} else if (e.getSource() == inverter) {
        			this.inverterPeca();
        		}
        	}

        	public void girarPeca() {
    			tabuleiro_.girarPeca();
    			if (tabuleiro_.informarHorizontal()) girar.setText("Girar peça: Horizontal");
    			else girar.setText("Girar peca: Vertical");
    			atualizarBotaoInverter();
        	}

        	public void inverterPeca() {
    			tabuleiro_.inverterPeca();
    			atualizarBotaoInverter();
        	}

          public void atualizarBotaoInverter() {
        		if ((tabuleiro_.informarHorizontal()) && (tabuleiro_.informarInvertida())) inverter.setText("Direcao: Esquerda");
        		else if (!(tabuleiro_.informarHorizontal()) && (tabuleiro_.informarInvertida())) inverter.setText("Direcao: Baixo");
        		else if ((tabuleiro_.informarHorizontal()) && !(tabuleiro_.informarInvertida())) inverter.setText("Direcao: Direita");
        		else inverter.setText("Direcao: cima");
          }
        };
        conectar.addActionListener(eventosBotoes);
        iniciarPartida.addActionListener(eventosBotoes);
        desconectar.addActionListener(eventosBotoes);
        girar.addActionListener(eventosBotoes);
        inverter.addActionListener(eventosBotoes);
    }

    public void atualizarLabelTurno() {
    	if (tabuleiro_.informarJogadorLocal().informarTurno()) mensagem.setText("Seu turno");
    	else mensagem.setText("Aguarde...");
    }

    public void atualizarLabelInventario() {
    	if (tabuleiro_.informarOrdem() == 1) {
    		inventarioCima.setText(tabuleiro_.informarJogadorRemoto().informarNome());
    		inventarioBaixo.setText(tabuleiro_.informarJogadorLocal().informarNome());
    	}
    	else {
    		inventarioCima.setText(tabuleiro_.informarJogadorRemoto().informarNome());
    		inventarioBaixo.setText(tabuleiro_.informarJogadorLocal().informarNome());
    	}
    }

    public void receberJogada(Inventario inventarios) {
  		for (int i = 0; i < 9; i++) {
  			tabuleiro_.informarJogadorRemoto().recebePeca(inventarios.informarPeca(i), i);
  			tabuleiro_.informarJogadorLocal().recebePeca(inventarios.informarPeca(i+9), i);
  		}
  		atualizarInventario();
    }

    public void atualizarInventario() {
    	for (int i = 0; i < 9; i++) {
    		botoesInventario2[i].setIcon(tabuleiro_.informarJogadorLocal().informarInventario().informarPeca(i).informarIcone());
    		botoesInventario1[i].setIcon(tabuleiro_.informarJogadorRemoto().informarInventario().informarPeca(i).informarIcone());
    	}
    }

    public String obterNomeJogador() {
    	return nomeJogador;
    }

    public String obterEnderecoServidor() {
    	return enderecoServidor;
    }


  	public void receberJogada(Lance lance) {
  		tabuleiro_.definirPartidaEmAndamento(true);
  		tabuleiro_.efetuaInsercaoPeca(lance);
  	}

    public void notificar(String notificacao) {
    	JOptionPane.showMessageDialog(null, notificacao);
    }

    public void definirPecaSelecionada(Peca p) {
    	pecaSelecionada = p;
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