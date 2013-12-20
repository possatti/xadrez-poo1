package br.edu.ifes.poo1.cln.cdp;

public class Rainha extends Peca {

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(Pessoa jogador) {
		super(9, TipoPeca.RAINHA, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAndar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) > 0)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0))
				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAtacar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) > 0)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0))
				return true;
		return false;
	}
}