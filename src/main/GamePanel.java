package main;

import javax.swing.JPanel;
import javax.swing.Timer;

import entity.Food;
import entity.Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel implements ActionListener{

    // Configurações
    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 2;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public final int SCREEN_COLS = 15;
    public final int SCREEN_ROWS = 15;
    public final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLS;
    public final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;

    final int FPS = 1000 / 10;

    // Game State
    final int PLAY_STATE = 0;
    final int PAUSE_STATE = 1;
    final int GAME_OVER_STATE = 2;
    int gameState;

    // Sistema
    public Timer timer;
    public Snake snake = new Snake(this);
    public Food powerUp = new Food(this);
    Random random = new Random();
    public TecladoAdapter tecladoAdapter = new TecladoAdapter(this);
    Background background = new Background(this);
    UI ui = new UI(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(null);
        this.addKeyListener(tecladoAdapter);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
    }

    /*
     * Inicia o Jogo.
     */
    public void startGame() {

        timer = new Timer(FPS, this);
        timer.start();
    }

    /*
     * Coloca o Estado do Jogo como GAME OVER.
     */
    public void gameOver() {

        gameState = GAME_OVER_STATE;
    }

    /*
     * Reinicia, após a cobra morrer.
     */
    public void reiniciar() {

        background = new Background(this);
        snake = new Snake(this);
        tecladoAdapter.adapterDir = snake.dir;
        powerUp = new Food(this);
        ui = new UI(this);
        gameState = PLAY_STATE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState == PLAY_STATE) {

            snake.update();
            ui.points++;
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Background
        background.draw(g);

        // Snake
        snake.draw(g);

        // Power Up
        powerUp.draw(g);

        // UI
        ui.draw(g);
        
        g.dispose();
    }

}
