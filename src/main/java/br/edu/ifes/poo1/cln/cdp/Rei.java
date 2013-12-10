package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		super(1, jogador); // REI NÃO TEM VALOR!.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Casa casaAtual, Casa casaDesejada) {
			if (Math.abs(casaAtual.getPosicao().getLinha()
				- casaDesejada.getPosicao().getLinha()) <= 1
				& Math.abs(casaAtual.getPosicao().getColuna()
						- casaDesejada.getPosicao().getColuna()) <= 1)
			return true;
		else
			return false;
			// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

}