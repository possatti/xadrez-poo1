package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;

public class GLaDOS extends Maquina {

	// Informa com quantas camadas estamos lidando no problema (5 é o oficial,
	// mas valor pode ser adequado na interface conforme dificuldade escolhida
	// pelo jogador)
	private final int ALCANCEMAQUINA = 1;

	RealizaBusca busca = new RealizaBusca();

	public GLaDOS(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Método que gera, dado um nó, os filhos desse nó
	 * 
	 * @param listaNos
	 * @param novaListaNos
	 * @return
	 * @throws CasaOcupadaException
	 */
	public ArrayList<NoArvore> geraFilhos(NoArvore noPai,
			ArrayList<NoArvore> novaListaNos) throws CasaOcupadaException {
		// Cria uma lista de tabuleiros que contém todos os estados possíveis a
		// serem gerados dado o nó pai
		ArrayList<Tabuleiro> listaTabuleiros = noPai.getTabuleiroNo()
				.proximosEstadosPossiveis(this);
		// Para cada tabuleiro da lista de tabuleiros
		for (Tabuleiro tabuleiroEstado : listaTabuleiros) {
			// Crie um nó que reconheça seu pai
			NoArvore no = new NoArvore(noPai, tabuleiroEstado);
			// Faça esse nó ser adicionado a nova lista de nós
			novaListaNos.add(no);
		}
		return novaListaNos;
	}

	/**
	 * Método que gera todos os nós de uma camada
	 * 
	 * @param listaNos
	 * @return
	 * @throws CasaOcupadaException
	 */
	public ArrayList<NoArvore> criaCamada(ArrayList<NoArvore> listaNos)
			throws CasaOcupadaException {
		ArrayList<NoArvore> novaListaNos = new ArrayList<NoArvore>();
		// Para cada nó da atual lista de nós
		for (NoArvore noPai : listaNos)
			// Faça a nova lista de nós receber os filhos
			novaListaNos = geraFilhos(noPai, novaListaNos);
		return novaListaNos;
	}

	/**
	 * Método que insere nos nós folhas os seus respectivos valores
	 * 
	 * @param listaNos
	 */
	public void inserirValorFolhas(ArrayList<NoArvore> listaNos) {
		for (NoArvore no : listaNos) {
			no.setValor(no.getTabuleiroNo().valorTabuleiro());
			no.setTemValor();
		}
	}

	/**
	 * Cria a lista de jogadas que podem ser realizadas pelo nó raiz
	 * 
	 * @param noRaiz
	 * @return
	 * @throws CasaOcupadaException
	 */
	public ArrayList<Jogada> criaJogadas(NoArvore noRaiz) {
		// Cria uma lista de jogadas que podem ser feitas pelo tabuleiro do nó
		// inicial
		ArrayList<Jogada> listaJogadas = noRaiz.getTabuleiroNo()
				.geraJogadasPossiveis(this);
		return listaJogadas;
	}

	/**
	 * Método que escolhe a jogada da máquina
	 * 
	 * @throws CasaOcupadaException
	 */
	public Jogada escolherJogada() throws CasaOcupadaException {

		// Crio nó raiz e informo a ele o tabuleiro atual
		NoArvore raiz = new NoArvore(tabuleiro);

		// Crio uma lista que irá conter todos os nós
		ArrayList<NoArvore> listaNos = new ArrayList<NoArvore>();

		// Inserimos, inicialmente apenas o nó raiz
		listaNos.add(raiz);

		// Crio a árvore de possibilidades
		// TODO deve alternar entre jogadas da máquina e jogadas da pessoa!!
		for (int camada = 1; camada <= ALCANCEMAQUINA; camada++)
			listaNos = criaCamada(listaNos);

		// Insiro os valores nos nós folhas
		inserirValorFolhas(listaNos);

		// Realizo a busca em profundidade (aplicando minimax e poda alfa beta)
		busca.buscaEmProfundidade(raiz);

		// Crio a lista de jogadas
		ArrayList<Jogada> listaJogadas = criaJogadas(raiz);

		// Para cada nó na lista de adjacência do pai
		for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
			// Se o nó possuir o mesmo valor do pai, então o tabuleiro escolhido
			// foi o desse nó
			if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
					.getValor())
				return listaJogadas.get(indice);
		return null;
	}
}
