package dominioDoProblema;

import java.awt.Color;
import javax.swing.ImageIcon;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Peca implements Jogada{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1401790979106826646L;
	protected boolean disponivel;
	protected int tamanho;
	protected ImageIcon icone;
	protected Color cor;
	
	public Peca(Color cor, int tamanho) {
		this.disponivel = true;
		this.cor = cor;
		this.tamanho = tamanho;

		setIcon();
	}

	
    // imagens das peças
    ImageIcon branco = new ImageIcon(getClass().getResource("imagens/branco.png")); // número de peças:
    ImageIcon verde2 = new ImageIcon(getClass().getResource("imagens/verde2.png")); // 2
    ImageIcon verde3 = new ImageIcon(getClass().getResource("imagens/verde3.png")); // 2
    ImageIcon vermelho1 = new ImageIcon(getClass().getResource("imagens/vermelho1.png")); // 2
    ImageIcon vermelho2 = new ImageIcon(getClass().getResource("imagens/vermelho2.png")); // 2
    ImageIcon amarelo1 = new ImageIcon(getClass().getResource("imagens/amarelo1.png")); // 1
    ImageIcon amarelo3 = new ImageIcon(getClass().getResource("imagens/amarelo3.png")); // 2
    ImageIcon azul1 = new ImageIcon(getClass().getResource("imagens/azul1.png")); // 3
    ImageIcon azul2 = new ImageIcon(getClass().getResource("imagens/azul2.png")); // 2
    ImageIcon azul3 = new ImageIcon(getClass().getResource("imagens/azul3.png")); // 2
    ImageIcon branco3 = new ImageIcon(getClass().getResource("imagens/branco3.png"));
	
	public void setIcon() {
		if (this.tamanho == 2 && this.cor == Color.green) {
			this.icone = verde2;
		}
		if (this.tamanho == 3 && this.cor == Color.green) {
			this.icone = verde3;
		}
		if (this.tamanho == 1 && this.cor == Color.red) {
			this.icone = vermelho1;
		}
		if (this.tamanho == 2 && this.cor == Color.red) {
			this.icone = vermelho2;
		}		
		if (this.tamanho == 1 && this.cor == Color.yellow) {
			this.icone = amarelo1;
		}
		if (this.tamanho == 3 && this.cor == Color.yellow) {
			this.icone = amarelo3;
		}
		if (this.tamanho == 1 && this.cor == Color.blue) {
			this.icone = azul1;
		}
		if (this.tamanho == 2 && this.cor == Color.blue) {
			this.icone = azul2;
		}
		if (this.tamanho == 3 && this.cor == Color.blue) {
			this.icone = azul3;
		}
	}
	
	public Color informarCor() {
		return cor;
	}
	
	public boolean informarDisponivel() {
		return disponivel;
	}
	

	public void zerarPeca() {
		this.disponivel = false;
		this.icone = branco3;
		this.cor = Color.white;
		this.tamanho = 0;
	}
	
	public ImageIcon informarIcone() {
		return icone;
	}
	
	public int informarTamanho() {
		return tamanho;
	}

}