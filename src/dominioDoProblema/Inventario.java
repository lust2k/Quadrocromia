package dominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Inventario implements Jogada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1433703142313024878L;
	protected Peca[] arrayPecas;
	
	public Inventario() {
		arrayPecas = new Peca[18];
	}
	
	public void recebePeca(Peca peca, int posicao) {
		arrayPecas[posicao] = peca;
	}
	
	public Peca informarPeca(int posicao) {
		return arrayPecas[posicao];
	}
}