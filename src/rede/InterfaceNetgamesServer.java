package rede;

import interfaceGrafica.Interface;
import interfaceGrafica.InterfaceJogador;


import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import dominioDoProblema.Inventario;
import dominioDoProblema.Lance;

public class InterfaceNetgamesServer implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected Interface gui;
	protected InterfaceJogador atorJogador;
	protected boolean conectado = false;
	
	public InterfaceNetgamesServer() {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public void definirInterfaceJogador(InterfaceJogador ator) {
		atorJogador = ator;
	}
	
	public String conectar(String servidor, String nome) {
			try {
				proxy.conectar(servidor, nome);
			} catch (JahConectadoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voc� j� est� conectado.";
			} catch (NaoPossivelConectarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "N�o foi possivel se conectar.";
			} catch (ArquivoMultiplayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voc� esqueceu o arquivo de propriedades.";
			}
			this.definirConectado(true);
			return "Conectado com sucesso.";
	}

	public void desconectar() {
			try {
				proxy.desconectar();
			} catch (NaoConectadoException e) {
				// TODO Auto-generated catch block
				gui.notificar("Voc� n�o est� conectado");
				e.printStackTrace();
				
			}
			this.definirConectado(false);
	}

	public void iniciarPartida() {
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gui.notificar("Voc� n�o est� conectado");
		}		
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		int indiceAdversario = 1;
		if (posicao.equals(1)) indiceAdversario = 2;
		String adversario = proxy.obterNomeAdversario(indiceAdversario);
		atorJogador.iniciarNovaPartida(posicao, adversario);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		atorJogador.encerrarPartida();
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receberJogada(Jogada jogada) {
		if (jogada instanceof Lance) {
			atorJogador.receberJogada((Lance) jogada);
		}
		else {
			atorJogador.receberJogada((Inventario) jogada);
		}
		
	}
	
	

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public void enviarJogada(Lance lance) {
		try {
			proxy.enviaJogada(lance);
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarJogada(Inventario inventarios) {
		try {
			proxy.enviaJogada(inventarios);
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean informarConectado() {
		return conectado;
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	
	public void encerrarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
