package interfaceGrafica;

import rede.InterfaceNetgamesServer;

public class InterfaceJogador {
	
	protected InterfaceNetgamesServer ngames;
	protected Interface gui;

	public InterfaceJogador() {
		ngames = new InterfaceNetgamesServer();
	}
	
	public InterfaceJogador(Interface interfaceQuadrocromia) {
		super();
		iniciar(interfaceQuadrocromia);
	}
	
	private void iniciar(Interface interfaceQuadrocromia) {
		gui = interfaceQuadrocromia;
		ngames = new InterfaceNetgamesServer();	
		ngames.definirInterfaceJogador(this);
	}

	public void conectar() {
		boolean conectado = ngames.informarConectado();
		if(!conectado) {
			String jogador = gui.obterNomeJogador();
			String servidor = gui.obterEnderecoServidor();
			String notificacao = ngames.conectar(servidor, jogador);
			gui.notificar(notificacao);
		} else {
			gui.notificar("Você já está conectado.");
		}
	}
	
	/* public void receberJogada(Lance lance) {
		tabuleiro.receberJogada(lance);
		gui.exibirEstado();
	} */
	
	public boolean desconectar() {
		boolean conectado = ngames.informarConectado();
		boolean atualizarInterface = true;
		if(conectado) {
			//atualizarInterface = tabuleiro.encerrarPartida(); // encerra a partida via cliente
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
		boolean atualizarInterface = true;
		if(conectado) {
			//atualizarInterface = tabuleiro.encerrarPartida(); // encerra a partida via cliente
			if (atualizarInterface) ngames.encerrarPartida(); // encerra a partida via servidor
			ngames.iniciarPartida();
		} else {
			gui.notificar("Você não está conectado.");
		}
		return atualizarInterface;
	}
	
	/* public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
		gui.exibirEstado();
	} */
	
	public void encerrarPartida() {
		//boolean atualizar = tabuleiro.encerrarPartida();
		gui.notificar("Partida finalizada");
		//if (atualizar) gui.exibirEstado();
	}

	public static InterfaceJogador getInstance(Interface interface1) {
		// TODO Auto-generated method stub
		return null;
	}


}
