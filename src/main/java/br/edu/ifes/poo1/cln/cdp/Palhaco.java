package br.edu.ifes.poo1.cln.cdp;

/**
 * Classe para testes. Quando o movimento de cada peça estiver bem definido,
 * esta peça será apagada. O palhaço pode se mover para qualquer lugar do
 * tabuleiro.
 */
// FIXME: Remover a classe palhaço assim que o movimento das outras peças
// estiverem pronto.
public class Palhaco extends Peca {

	public Palhaco(Jogador jogador) {
		super(0, TipoPeca.PEAO, jogador);
	}

	@Override
	public boolean podeAndar(Posicao posicao) {
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao posicao) {
		return true;
	}

}
