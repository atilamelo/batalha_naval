package com.mygdx.game.BatalhaNaval;

import java.util.List;

import com.mygdx.game.Dificuldade;

/**
 * Classe responsável por gerenciar a lógica do jogo Batalha Naval.
 */
public class LogicGame {
    private Tabuleiro tabuleiro; // Tabuleiro do jogo
    private List<Ship> navios; // Lista de navios no jogo
    private Player jogador; // Jogador

    /**
     * Construtor da classe LogicGame.
     * 
     * @param alturaTabuleiro  a altura do tabuleiro do jogo
     * @param larguraTabuleiro a largura do tabuleiro do jogo
     * @param navios           a lista de navios no jogo
     * @param dificuldade      a dificuldade do jogo
     */
    public LogicGame(int alturaTabuleiro, int larguraTabuleiro, List<Ship> navios, Dificuldade dificuldade) {
        int qntdBombas = 0;

        // Inicializa as variáveis do jogo
        this.tabuleiro = new Tabuleiro(larguraTabuleiro, alturaTabuleiro, navios);
        this.navios = navios;

        // Configura a quantidade de bombas disponíveis do jogador de acordo com a
        // dificuldade escolhida
        switch (dificuldade) {
            case FACIL:
                qntdBombas = Integer.MAX_VALUE;
                break;
            case MEDIO:
                qntdBombas = 80;
                break;
            case DIFICIL:
                qntdBombas = 40;
                break;
        }

        this.jogador = new Player(qntdBombas);

    }
    
    /**
     * Retorna o tabuleiro do jogo.
     * 
     * @return o tabuleiro do jogo
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Retorna a lista de navios do jogo
     * 
     * @return Lista de navios
     */
    public List<Ship> getNavios() {
        return navios;
    }

    /**
     * Retorna a quantidade de bombas que o jogador ainda pode usar
     * 
     * @return Quantidade de bombas restantes
     */
    public int getQntdBombas() {
        return jogador.getQntdBombas();
    }

    /**
     * Verifica se o jogo foi terminado.
     * 
     * @return true se o jogo terminou, false caso contrário
     */
    public boolean isJogoTerminado() {
        // Verifica se o jogador não tem mais bombas
        if (jogador.getQntdBombas() == 0) {
            return true;
        }

        // Verifica se todos os navios foram afundados
        for (Ship ship : navios) {
            if (!ship.isAfundado()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se o jogador venceu o jogo.
     * 
     * @return true se o jogador venceu o jogo, false caso contrário
     */
    public boolean jogadorVenceu() {
        // Verifica se todos os navios foram afundados
        for (Ship ship : navios) {
            if (!ship.isAfundado()) {
                return false;
            }
        }

        return true;
    }


    /**
     * Diminui em 1 a quantidade de bombas do jogador, caso ele ainda tenha bombas
     * disponíveis
     */
    public void atacar() {
        if (jogador.getQntdBombas() != Integer.MAX_VALUE) {
            jogador.atacar();
        }
    }

}
