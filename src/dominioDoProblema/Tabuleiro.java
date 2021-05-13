package dominioDoProblema;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JOptionPane;

//import dominioDoProblema.Jogador;
//import interfaceGrafica.Interface;
import interfaceGrafica.InterfaceJogador;

public class Tabuleiro implements ActionListener, MouseListener {
	
	protected boolean partidaEmAndamento;
	protected InterfaceJogador atorJogador;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected Peca pecas[];
	protected JButton[][] botoesTabuleiro;
	protected JButton[] botoesInventario1;
	protected JButton[] botoesInventario2;
	protected MouseEvent evento;
	protected Peca pecaSelecionada;
	protected int pecaSelecionadaPos;
	protected String nomeJogador;
	protected Inventario inventarios;
	protected boolean horizontal;
	protected boolean invertida;
	protected int ordem;
	
	public Tabuleiro(JButton[][] botoesTabuleiro, JButton[] botoesInventario1, JButton[] botoesInventario2) {
		super();
		pecas = new Peca[18];
		this.ordem = 2;
		this.botoesTabuleiro = botoesTabuleiro;
		this.botoesInventario1 = botoesInventario1;
		this.botoesInventario2 = botoesInventario2;
		this.inventarios = new Inventario();
		this.horizontal = false;
		this.invertida = false;
		partidaEmAndamento = false;
		pecaSelecionadaPos = -1;
	}
	
	public Tabuleiro() {}	
	
	public boolean tentaColocarPeca(int linha, int coluna, Peca peca, boolean horizontal, boolean invertida, int pecaSelecionadaPos) {
		if (invertida) {
			if (horizontal) {
				coluna -= peca.informarTamanho() - 1;
			}
			else {
				linha += peca.informarTamanho() - 1;
			}
		}
		if (!verificarInsercao(linha, coluna, peca, horizontal)) {
			JOptionPane.showMessageDialog(null, "Posicao invalida");
			return false;
		}

		
		Lance lance = new Lance(linha, coluna, peca.informarTamanho(), peca.informarCor(), horizontal, pecaSelecionadaPos);
		this.efetuaInsercaoPeca(lance);
		atorJogador.enviarJogada(lance);
		
		return true;
	}
	
	public boolean verificarInsercao(int linha, int coluna, Peca peca, boolean horizontal) {
		// testes para insercao na vertical
		if (!peca.informarDisponivel()) return false;
		if (!horizontal) {
			if (((linha - peca.informarTamanho() + 1) >= 0)) {
				for (int i = 0; i < peca.informarTamanho(); i++) {
					if (this.botoesTabuleiro[linha-i][coluna].getBackground() != Color.white) {
						return false;
					}
					if (!verificaAdjacencias(linha - i, coluna, peca.informarCor())) {
						return false;
					}
					
				}				
			}
			else {
				return false;
			}
		}
		// testes para insercao na horizontal
		if (horizontal) {
			if (((coluna + peca.informarTamanho() - 1) <= 5)) {
				for (int i = 0; i < peca.informarTamanho(); i++) {
					if (this.botoesTabuleiro[linha][coluna+i].getBackground() != Color.white) {
						return false;
					}
					if (!verificaAdjacencias(linha, coluna + i, peca.informarCor())) return false;				
				}	
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean verificaAdjacencias(int linha, int coluna, Color cor) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				// Nao entra no if se for a peca atual
				if (!(i == 0 && j == 0)) {
					if ((linha + i) >= 0 && (linha + i) < 6 && (coluna + j) < 6 && (coluna + j) >= 0) {
						if (this.botoesTabuleiro[i+linha][j+coluna].getBackground().equals(cor)) {						
							return false;
						}
					}

				}
			}
		}		
		return true;
	}
	
	public void efetuaInsercaoPeca(Lance lance) {
		if (jogadorLocal.informarTurno()) {
			jogadorLocal.zerarPeca(lance.informarPecaPos());
			botoesInventario2[lance.informarPecaPos()].setIcon(jogadorLocal.informarIcone(lance.informarPecaPos()));
		}
		else {
			jogadorRemoto.zerarPeca(lance.informarPecaPos());
			botoesInventario1[lance.informarPecaPos()].setIcon(jogadorRemoto.informarIcone(lance.informarPecaPos()));
		}
		// atualiza posicoes do tabuleiro
		if (!lance.informaHorizontal()) {
			for (int i = 0; i < lance.informarTamanho(); i++) {
				botoesTabuleiro[lance.informarLinha()-i][lance.informarColuna()].setBackground(lance.informarCor());
				
			}
		}
		// insere peca horizontal
		if (lance.informaHorizontal()) {
			for (int i = 0; i < lance.informarTamanho(); i++) {
				botoesTabuleiro[lance.informarLinha()][lance.informarColuna()+i].setBackground(lance.informarCor());
			}		
		}
		avaliaTerminoPartida();
	}
	
	
	public void girarPeca() {
		this.horizontal = !horizontal;
	}
	
	public void inverterPeca() {
		this.invertida = !invertida;
	}
	
	public boolean informarTurno() {
		if (jogadorLocal.informarTurno()) return true;
		return false;
	}
	

	public boolean encerrarPartida() {	
		if (partidaEmAndamento) {
			this.encerrarPartidaLocalmente();
			return true;
		} else return false;
	}
	
	public void encerrarPartidaLocalmente() {	
		this.esvaziarTabuleiro();
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador();
		jogadorRemoto.iniciar();
	}
	
	public void embaralharPecas() {
		pecas[0] = new Peca(Color.green, 2);
		pecas[1] = new Peca(Color.green, 2);
		pecas[2] = new Peca(Color.green, 3);
		pecas[3] = new Peca(Color.green, 3);
		pecas[4] = new Peca(Color.red, 1);
		pecas[5] = new Peca(Color.red, 1);
		pecas[6] = new Peca(Color.red, 2);
		pecas[7] = new Peca(Color.red, 2);
		pecas[8] = new Peca(Color.blue, 1);
		pecas[9] = new Peca(Color.blue, 1);
		pecas[10] = new Peca(Color.blue, 1);
		pecas[11] = new Peca(Color.blue, 2);
		pecas[12] = new Peca(Color.blue, 2);
		pecas[13] = new Peca(Color.blue, 3);
		pecas[14] = new Peca(Color.blue, 3);
		pecas[15] = new Peca(Color.yellow, 1);
		pecas[16] = new Peca(Color.yellow, 3);
		pecas[17] = new Peca(Color.yellow, 3);
		Collections.shuffle(Arrays.asList(pecas));
			
	}
	
	public void distribuirPecas() {
		embaralharPecas();
		for (int i = 0; i < 9; i++) {
			jogadorLocal.recebePeca(pecas[i], i);
			inventarios.recebePeca(pecas[i], i);
		}
		for (int i = 0; i < 9; i++) {
			jogadorRemoto.recebePeca(pecas[i+9], i);
			inventarios.recebePeca(pecas[i+9], i+9);
		}
	}
		
	public void esvaziarTabuleiro() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				botoesTabuleiro[i][j].setBackground(Color.white);				
			}
		}
	}

	public void iniciarNovaPartida(int ordem, String adversario) {
		this.esvaziarTabuleiro();
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador();
		jogadorRemoto.iniciar();
		jogadorRemoto.definirNome(adversario);
		if (ordem == 1) {
			this.ordem = 1;		
			jogadorLocal.definirComoPrimeiro();
			this.distribuirPecas();
			atorJogador.enviarJogada(inventarios);	
						
		}
		else jogadorRemoto.definirComoPrimeiro();
		definirPartidaEmAndamento(true);
	}
	
	public int informarOrdem() {
		return ordem;
	}
	
	public void definirPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}
	
	public void avaliaTerminoPartida() {
        Peca peca;
        // verifica se algum jogador está sem peças
        if (jogadorLocal.maoVazia() || jogadorRemoto.maoVazia()) {
        	avaliaVencedor();
        	return;
        }
        if (jogadorLocal.informarTurno()) {
        	boolean localValida = false;
        	boolean remotoValida = false;
            for (int p = 0; p < 9; p++) {
                // verifica se jogadorLocal pode fazer alguma inserção
                peca = jogadorLocal.informarInventario().informarPeca(p);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (verificarInsercao(i, j, peca, this.horizontal) || (verificarInsercao(i, j, peca, !this.horizontal))) localValida = true;
                    }
                }
            }
           for (int p = 0; p < 9; p++) {
                // verifica se jogadorRemoto pode fazer alguma inserção
                peca = jogadorRemoto.informarInventario().informarPeca(p);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if ((verificarInsercao(i, j, peca, this.horizontal)) || (verificarInsercao(i, j, peca, !this.horizontal))) remotoValida = true;
                    }
                }
            }
           if (localValida == true && remotoValida == false) {
        	   JOptionPane.showMessageDialog(null, "Sua vez denovo, jogador adversario nao possui jogada valida");
        	   return;
           }
           if (localValida || remotoValida) {
        	   jogadorLocal.inverterTurno();
        	   jogadorRemoto.inverterTurno();
        	   return;
           }
        }
        if (!jogadorLocal.informarTurno()) {
        	boolean localValida = false;
        	boolean remotoValida = false;
            for (int p = 0; p < 9; p++) {
                // verifica se jogadorRemoto pode fazer alguma inserção
                peca = jogadorRemoto.informarInventario().informarPeca(p);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (verificarInsercao(i, j, peca, this.horizontal) || (verificarInsercao(i, j, peca, !this.horizontal))) remotoValida = true;
                    }
                }
            }
           for (int p = 0; p < 9; p++) {
                // verifica se jogadorLocal pode fazer alguma inserção
                peca = jogadorLocal.informarInventario().informarPeca(p);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if ((verificarInsercao(i, j, peca, this.horizontal)) || (verificarInsercao(i, j, peca, !this.horizontal))) localValida = true;
                    }
                }
            }
           if (localValida == false && remotoValida == true) {
        	   JOptionPane.showMessageDialog(null, "Voce passou a vez por nao ter jogada valida");
        	   return;
           }   
           if (remotoValida || localValida) {
        	   jogadorLocal.inverterTurno();
        	   jogadorRemoto.inverterTurno();
        	   return;
           }
        }
        avaliaVencedor(); 
    }
	
	public void avaliaVencedor() {
        int pontosLocal = 0;
        int pontosRemoto = 0;
        for (int i = 0; i < 9; i++) {
            pontosLocal += jogadorLocal.informarInventario().informarPeca(i).informarTamanho();
            pontosRemoto += jogadorRemoto.informarInventario().informarPeca(i).informarTamanho();
        }
        String vencedor = "";
        if (pontosLocal < pontosRemoto) vencedor = jogadorLocal.informarNome();
        else vencedor = jogadorRemoto.informarNome();
        JOptionPane.showMessageDialog(null, "O vencedor é: " + vencedor); 
        definirPartidaEmAndamento(false);
    }
	

	@Override
	public void mouseClicked(MouseEvent e) {
		evento = e;
		// clique no inventário: seleciona peça
		if (e.getSource() != botoesTabuleiro) {
			for (int i = 0; i < 9; i++) {
				botoesInventario2[i].setBackground(Color.white);
				if (e.getSource() == botoesInventario2[i]) {
					pecaSelecionada = jogadorLocal.inventario.informarPeca(i);
					this.pecaSelecionadaPos = i;
					botoesInventario2[i].setBackground(Color.blue);
				}
			}
		}
		// clique no tabuleiro: insere peça
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
            	if (e.getSource() == botoesTabuleiro[i][j] && jogadorLocal.informarTurno() && pecaSelecionadaPos != -1) {
            		tentaColocarPeca(i,j, pecaSelecionada, this.horizontal, this.invertida, this.pecaSelecionadaPos);
            	}
            }
		}
	}
	
	public void registrarJogadorLocal(String nome) {
		jogadorLocal = new Jogador();
		jogadorLocal.definirNome(nome);
		jogadorLocal.iniciar();
	}
	
	public Peca obterPecaSelecionada() {
		return pecaSelecionada;
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Jogador informarJogadorLocal() {
		return jogadorLocal;
	}
	
	public boolean informarInvertida() {
		return invertida;
	}
	
	public boolean informarHorizontal() {
		return horizontal;
	}
	
	public Jogador informarJogadorRemoto() {
		return jogadorRemoto;
	}
	
	public void defineAtorJogador(InterfaceJogador atorJogador) {
		this.atorJogador = atorJogador;
	}
	
}