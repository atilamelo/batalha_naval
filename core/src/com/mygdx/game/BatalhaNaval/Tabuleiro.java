package com.mygdx.game.BatalhaNaval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * Representa o tabuleiro do jogo batalha naval.
 */
public class Tabuleiro {
    private int larguraTabuleiro;
    private int alturaTabuleiro;
    Cell[][] tabuleiro;

    /**
     * 
     * Construtor da classe Tabuleiro.
     * 
     * @param larguraTabuleiro a largura do tabuleiro
     * 
     * @param alturaTabuleiro  a altura do tabuleiro
     * 
     * @param navios           a lista de navios que serão posicionados no tabuleiro
     */
    public Tabuleiro(int larguraTabuleiro, int alturaTabuleiro, List<Ship> navios) {
        this.larguraTabuleiro = larguraTabuleiro;
        this.alturaTabuleiro = alturaTabuleiro;
        tabuleiro = new Cell[larguraTabuleiro][alturaTabuleiro];

        // Preenche o tabuleiro com as células
        for (int x = 0; x < larguraTabuleiro; x++) {
            for (int y = 0; y < alturaTabuleiro; y++) {
                tabuleiro[x][y] = new Cell(x, y);
            }
        }

        posicionarNaviosAleatoriamente(navios);
    }

    /**
     * 
     * Retorna a célula na posição (x, y) do tabuleiro.
     * 
     * @param x a coordenada x da célula
     * @param y a coordenada y da célula
     * @return a célula na posição (x, y) do tabuleiro
     */
    public Cell getCell(int x, int y) {
        return tabuleiro[x][y];
    }

    /**
     * 
     * Retorna uma lista com todas as células vizinhas à célula passada como
     * parâmetro.
     * 
     * @param cell a célula cujas vizinhas serão retornadas
     * 
     * @return uma lista com todas as células vizinhas à célula passada como
     *         parâmetro
     */
    public List<Cell> getVizinhos(Cell cell) {
        List<Cell> vizinhas = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();
        int i, j;

        // Percorre todas as células vizinhas a x, y
        for (i = x - 1; i <= x + 1; i++) {
            for (j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < larguraTabuleiro && j >= 0 && j < alturaTabuleiro && (i != x || j != y)) {
                    // A célula (i,j) está dentro do tabuleiro e é diferente da célula em questão
                    // (x, y)
                    vizinhas.add(tabuleiro[i][j]);
                }
            }
        }

        return vizinhas;
    }

    /**
     * 
     * Posiciona os navios aleatoriamente no tabuleiro.
     * 
     * @param navios a lista de navios a serem posicionados
     */
    public void posicionarNaviosAleatoriamente(List<Ship> navios) {
        Random random = new Random();
    
        for (Ship navio : navios) {
            boolean navioPosicionado = false;
    
            while (!navioPosicionado) {
                // Gerar coordenadas aleatórias
                int x = random.nextInt(larguraTabuleiro);
                int y = random.nextInt(alturaTabuleiro);
    
                // Gerar uma orientação aleatória
                boolean orientacaoHorizontal = random.nextBoolean();
    
                // Lista para armazenar as células que o navio irá ocupar
                List<Cell> celulasDoNavio = new ArrayList<>();
    
                // Conjunto para armazenar os vizinhos das células ocupadas pelo navio
                Set<Cell> vizinhos = new HashSet<>();
    
                // Flag para indicar se as células necessárias para posicionar o navio estão disponíveis
                boolean celulasDisponiveis = true;
    
                // Verificar se as células necessárias para posicionar o navio estão disponíveis
                for (int i = 0; i < navio.getSize(); i++) {
                    // Calcular as coordenadas das células ocupadas pelo navio
                    int xNavio = orientacaoHorizontal ? x + i : x;
                    int yNavio = orientacaoHorizontal ? y : y + i;

                    
                    // Verificar se as células estão dentro dos limites do tabuleiro
                    if (xNavio >= larguraTabuleiro || yNavio >= alturaTabuleiro) {
                        celulasDisponiveis = false;
                        break;
                    }

                    // Obter a célula atual
                    Cell cell = tabuleiro[xNavio][yNavio];
    
                    // Verificar se a célula já está ocupada por outro navio
                    if (cell.estaOcupado()) {
                        celulasDisponiveis = false;
                        break;
                    }
    
                    // Adicionar a célula atual à lista de células ocupadas
                    celulasDoNavio.add(cell);
                    vizinhos.addAll(getVizinhos(cell)); 
                }
                
                if(celulasDisponiveis){
                    // Remover as células pertencentes ao navio do conjunto de vizinhos
                    vizinhos.removeAll(celulasDoNavio);
        
                    // Verificar se algum dos vizinhos já está ocupado por outro navio
                    for (Cell vizinho : vizinhos) {
                        if (vizinho.estaOcupado()) {
                            celulasDisponiveis = false;
                            break;
                        }
                    }
                }
    
                // Se durante a execução todos as condições foram atendidas, posicionar o navio
                if (celulasDisponiveis) {
                    for (Cell cell : celulasDoNavio) {
                        cell.setShip(navio);
                        navio.addCell(cell);
                    }
    
                    navioPosicionado = true;
                }
            }
        }
    }
    
    /**
     * 
     * Imprime o tabuleiro no terminal.
     * 
     */
    public void printTabuCells() {
        int cont = 0;
        
        // Imprime a legenda na horizontal
        System.out.print("     ");
        for (char letra = 'A'; letra <= 'J'; letra++) {

            System.out.print(letra + "   ");
        }
        System.out.println();

        // Imprime o tabuleiro
        for (int x = 0; x < alturaTabuleiro; x++) {
            System.out.print(cont + " | "); // Legenda na vertical
            for (int y = 0; y < larguraTabuleiro; y++) {
                System.out.print(tabuleiro[x][y] + " | ");
            }
            System.out.println();
            cont++;
        }

    }

}