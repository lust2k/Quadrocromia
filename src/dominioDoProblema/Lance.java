package dominioDoProblema;

import java.awt.Color;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {
	
	private static final long serialVersionUID = -7925382400008937099L;
	protected int linha;
	protected int coluna;
	protected int tamanhoPeca;
	protected Color corPeca;
	protected int pecaSelecionadaPos;
	protected boolean horizontal;
	
	public Lance(int linha, int coluna, int tamanho, Color cor, boolean horizontal, int pecaSelecionadaPos) {
		this.linha = linha;
		this.coluna = coluna;
		this.tamanhoPeca = tamanho;
		this.corPeca = cor;
		this.horizontal = horizontal;
		this.pecaSelecionadaPos = pecaSelecionadaPos;
	}
	
	public int informarLinha() {
		return linha;
	}
	
	public int informarColuna() {
		return coluna;
	}
	
	public int informarPecaPos() {
		return pecaSelecionadaPos;
	}

	public int informarTamanho() {
		return tamanhoPeca;
	}
	
	public Color informarCor() {
		return corPeca;
	}
	
	public boolean informaHorizontal() {
		return horizontal;
	}


}