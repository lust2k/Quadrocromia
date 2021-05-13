package interfaceGrafica;

import rede.InterfaceNetgamesServer;
import dominioDoProblema.Tabuleiro;

import dominioDoProblema.Inventario;
import dominioDoProblema.Lance;

public class InterfaceJogador {
	
	protected InterfaceNetgamesServer ngames;
	protected Interface gui;
	protected Tabuleiro tabuleiro;

	public InterfaceJogador() {
		ngames = new InterfaceNetgamesServer();
		tabuleiro = new Tabuleiro();
	}
	
	public InterfaceJogador(Interface interfaceQuadrocromia) {
		super();
		iniciar(interfaceQuadrocromia);
	}
	
	private void iniciar(Interface interfaceQuadrocromia) {
		gui = interfaceQuadrocromia;
		ngames = new InterfaceNetgamesServer();
		tabuleiro = gui.tabuleiro_;
		ngames.definirInterfaceJogador(this);
	}

	public void conectar() {
		boolean conectado = ngames.informarConectado();
		if(!conectado) {
			String jogador = gui.obterNomeJogador();
			String servidor = gui.obterEnderecoServidor();
			String notificacao = ngames.conectar(servidor, jogador);
			tabuleiro.registrarJogadorLocal(jogador);
			gui.notificar(notificacao);
		} else {
			gui.notificar("Você já está conectado.");
		}
	}
	
	 public void receberJogada(Lance lance) {
		gui.receberJogada(lance);
		gui.atualizarLabelTurno();
	}
	 
	 public void receberJogada(Inventario inventarios) {
		gui.receberJogada(inventarios);
		gui.atualizarLabelTurno();
		gui.atualizarLabelInventario();
	}
	 
	
	public boolean desconectar() {
		boolean conectado = ngames.informarConectado();
		boolean atualizarInterface = true;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida(); // encerra a partida via cliente
			if (atualizarInterface) ngames.encerrarPartida(); // encerra a partida via servidor
			ngames.desconectar();
			gui.notificar("Desconectado com sucesso.");
		} else {
			gui.notificar("Você não está conectado.");
		}
		return atualizarInterface;
	}
	
	public boolean iniciarPartida() {
		boolean conectado = ngames.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida(); // encerra a partida via cliente
			if (atualizarInterface) ngames.encerrarPartida(); // encerra a partida via servidor
			ngames.iniciarPartida();
		} else {
			gui.notificar("Você não está conectado.");
		}
		return atualizarInterface;
	}
	
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
		gui.atualizarInventario();
	}
	
	public void encerrarPartida() {
		tabuleiro.encerrarPartida();
		gui.notificar("Partida finalizada");
	}
	
	public void enviarJogada(Lance lance) {
		ngames.enviarJogada(lance);
		gui.atualizarLabelTurno();
		
	}
	
	public void enviarJogada(Inventario inventarios) {
		ngames.enviarJogada(inventarios);
		gui.atualizarLabelTurno();
		gui.atualizarLabelInventario();
	}
}