package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Food {
    
    GamePanel gp;
    int x, y;
    BufferedImage image;
    Random random = new Random();

    public Food(GamePanel gp) {

        this.gp = gp;
        loadImage();
        randomPos();
    }

    public void loadImage() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/entity/food/food.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Coloca a comida em algum lugar aleatório do mapa.
     */
    public void randomPos() {

        do {
            x = random.nextInt(gp.SCREEN_COLS) * gp.TILE_SIZE;
            y = random.nextInt(gp.SCREEN_ROWS) * gp.TILE_SIZE;
        } while (verificarPosBody(x, y, gp.snake));
    }
    
    /*
     * Verifica se a posição a ser colocada a comida não está ocupada pela cobra.
     */
    private boolean verificarPosBody(int x, int y, Snake snake) {

        for (int i = 0; i < snake.snakeX.size(); i++) {
            if (snake.snakeX.get(i) == x && snake.snakeY.get(i) == y) {
                return true;
            }
        
        }

        return false;
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

}
