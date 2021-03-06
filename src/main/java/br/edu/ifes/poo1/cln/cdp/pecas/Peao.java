package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

/**
 * Representa um peão do jogo de xadrez.
 */
public class Peao extends Peca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo que diz se um determinado peão pode sofrer um en passant ou não
	 */
	private boolean podeEnPassant;

	/**
	 * Instancia um peão.
	 */
	public Peao(TipoCorJogador corJogador) {
		super(TipoPeca.PEAO, corJogador);
		this.podeEnPassant = false;
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		int avanca;
		if (this.getCorJogador() == TipoCorJogador.BRANCO)
			avanca = 1;
		else
			avanca = -1;
		if (super.podeAndar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			// Se o peão quer avançar, não quer se movimentar na coluna
			if (origem.getColuna() - destino.getColuna() == 0) {
				// Se a peça nunca se moveu
				if (this.getJaMoveu() == false) {
					// Se quer andar duas casas para frente
					if (destino.getLinha() - origem.getLinha() == 2 * avanca)
						return true;
				}
				// Se a peça que andar uma casa para frente
				if ((destino.getLinha() - origem.getLinha()) == avanca)
					return true;
			}
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		// Verifica como o peão irá avançar
		int avanca;
		if (this.getCorJogador() == TipoCorJogador.BRANCO)
			avanca = 1;
		else
			avanca = -1;
		if (super.podeAtacar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			// Se quer avançar na coluna 1
			if (this.medeDeslocamentoPeca(origem.getColuna(),
					destino.getColuna()) == 1)
				if (destino.getLinha() - origem.getLinha() == avanca)
					return true;
		return false;
	}

	/**
	 * Método que verifica se o peão andou duas casas para a frente
	 * 
	 * @param origem
	 * @param destino
	 * @param tabuleiro
	 * @return
	 */
	private boolean andouDuasCasas(Posicao origem, Posicao destino) {
		return this.medeDeslocamentoPeca(origem.getLinha(), destino.getLinha()) == 2;
	}

	/**
	 * Método que verifica se o peão está en estado de receber en passant
	 * 
	 * @param jogada
	 */
	public void verificaEnPassant(Jogada jogada) {
		if (andouDuasCasas(jogada.getOrigem(), jogada.getDestino()))
			this.setPodeEnPassant(true);
	}

	public boolean isPodeEnPassant() {
		return podeEnPassant;
	}

	public void setPodeEnPassant(boolean podeEnPassant) {
		this.podeEnPassant = podeEnPassant;
	}

}