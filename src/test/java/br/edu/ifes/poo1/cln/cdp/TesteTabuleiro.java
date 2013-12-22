package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteTabuleiro {

	Tabuleiro tabuleiro;

	@Before
	public void before() {
		tabuleiro = new Tabuleiro();
	}

	@Test
	public void espiarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = new Peao(null);

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser lida.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));

		// E ela deve permanecer no tabuleiro.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));
	}

	@Test
	public void retirarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = new Peao(null);

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser retirada.
		Assert.assertEquals(peca, tabuleiro.retirarPeca(new Posicao(1, 1)));

		// E ela não deve mais estar no tabuleiro.
		Assert.assertEquals(null, tabuleiro.retirarPeca(new Posicao(1, 1)));
	}

	@Test
	public void colocarPeca_emCasaVazia() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Peao(null));
	}

	@Test(expected = CasaOcupadaException.class)
	public void colocarPeca_emCasaOcupada() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Peao(null));

		// Coloca uma peça em uma casa OCUPADA.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Peao(null));
	}

	@Test
	public void estaVazio() throws CasaOcupadaException {
		// Adiciona uma peça ao tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Peao(null));

		// Testa a casa ocupada.
		Assert.assertFalse(tabuleiro.estaVazio(new Posicao(1, 1)));

		// Testa uma casa vazia.
		Assert.assertTrue(tabuleiro.estaVazio(new Posicao(2, 2)));
	}

	@Test
	public void atravessouTabuleiro() {
		// Testa os limites do tabuleiro
		Assert.assertFalse(Tabuleiro.estaForaDoTabuleiro(new Posicao(1, 1)));
		Assert.assertFalse(Tabuleiro.estaForaDoTabuleiro(new Posicao(8, 8)));

		// Testa quando atravessa o tabuleiro
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(4, 9)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(4, 0)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(9, 5)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(0, 5)));
	}

	@Test
	public void podeRealizarMovimentacao() throws CasaOcupadaException {

		// Adiciona uma peça ao tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 3), new Peao(null));

		// Verifica se o caminho até a casa desejada é possível ou se irá se
		// encontrar algum obstáculo
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(1, 1),
				new Posicao(8, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(4, 1),
				new Posicao(4, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(8, 1),
				new Posicao(1, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(1, 4),
				new Posicao(8, 4)));
		Assert.assertFalse(tabuleiro.podeRealizarMovimentacao(
				new Posicao(2, 1), new Posicao(2, 8)));
		Assert.assertFalse(tabuleiro.podeRealizarMovimentacao(
				new Posicao(1, 3), new Posicao(4, 3)));
	}
	
	@Test
	public void roqueMenor() {
		
	}

	@Test
	public void roqueMaior() {
	
	}
}
