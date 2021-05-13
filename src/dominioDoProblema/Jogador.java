package dominioDoProblema;

import javax.swing.ImageIcon;

public class Jogador {

	protected String nome;
	protected boolean turnoAtivo;
	protected boolean vencedor;
	protected Inventario inventario;
	
	public void iniciar () {
		inventario = new Inventario();
		turnoAtivo = false;
		vencedor = false;
	}
	
	public void definirNome(String jogador) {
		nome = jogador;
	}
	
	public void definirComoPrimeiro() {
		turnoAtivo = true;
	}
	
	public void definirVencedor(boolean valor) {
		vencedor = valor;
	}
	
	public String informarNome() {
		return nome;
	}
	
	public boolean informarTurno() {
		return turnoAtivo;
	}
	
	public boolean informarVencedor() {
		return vencedor;
	}
	
	public void inverterTurno() {
		turnoAtivo = !turnoAtivo;
	}
	
	public Inventario informarInventario() {
		return inventario;
	}
	
	public void definirInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
	public void recebePeca(Peca p, int posicao) {
		inventario.recebePeca(p, posicao);
	}
	
	public void zerarPeca(int i) {
		inventario.informarPeca(i).zerarPeca();
	}
	
	public ImageIcon informarIcone(int i) {
		return inventario.informarPeca(i).informarIcone();
	}
	
	
	public boolean maoVazia() {
		for (int i = 0; i < 9; i++) {
			if (inventario.informarPeca(i).informarDisponivel() == true) return false;
		}
		return true;
	}
}