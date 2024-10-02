package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TecladoAdapter extends KeyAdapter {

    GamePanel gp;
    public int adapterDir;

    public TecladoAdapter(GamePanel gp) {
        
        this.gp = gp;
        adapterDir = gp.snake.dir;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int tecla = e.getKeyCode();

        // Caso o Player deseje reiniciar o Jogo *apertando BARRA DE ESPAÇO*
        if (tecla == KeyEvent.VK_SPACE && gp.gameState == gp.GAME_OVER_STATE) {

            gp.reiniciar();
        }
        // Caso o Player deseje pausar o Jogo *apertando P*
        else if (tecla == KeyEvent.VK_P) {

            if (gp.gameState == gp.PLAY_STATE) {
                gp.gameState = gp.PAUSE_STATE;
            } else if (gp.gameState == gp.PAUSE_STATE) {
                gp.gameState = gp.PLAY_STATE;
            }
        } 
        // Cima
        else if ((tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) && gp.snake.dir != 1) {
            adapterDir = 0;
        }
        // Baixo
        else if ((tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) && gp.snake.dir != 0) {
            adapterDir = 1;
        }
        // Esquerda
        else if ((tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) && gp.snake.dir != 3) {
            adapterDir = 2;
        }
        // Direita
        else if ((tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) && gp.snake.dir != 2) {
            adapterDir = 3;
        }
    }
}
