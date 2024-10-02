package entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Snake {
    
    GamePanel gp;

    // Movimentação
    ArrayList<Integer> snakeX = new ArrayList<>();
    ArrayList<Integer> snakeY = new ArrayList<>();
    int snakeBody;
    int x, y;
    int speed;
    public int dir;
    int incrementX, incrementY;

    // Sprites
    BufferedImage bodyImage;
    BufferedImage headImage[];
    BufferedImage linguaImage[][];
    int spriteNum;
    int spriteCounter;
    

    public Snake(GamePanel gp) {

        this.gp = gp;

        // Sets Iniciais da Cobra
        x = 0;
        y = 0;
        speed = gp.TILE_SIZE;
        dir = 3;
        snakeBody = 3;

        // Ocupa os espaços dos arrays com as cordenadas da cobra
        for (int b = 0; b < snakeBody; b++) {
            snakeX.add(x);
            snakeY.add(y);
        }

        loadImage();
    }

    public void loadImage() {

        headImage = new BufferedImage[4];
        linguaImage = new BufferedImage[4][3];

        try {

            // Corpo
            bodyImage = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_body.png"));

            // Cabeça
            for (int n = 0; n < headImage.length; n++) {
                headImage[n] = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_head-" + (n + 1) + ".png"));
            }

            // Lingua
            for (int n = 0; n < linguaImage[0].length; n++) {
                linguaImage[0][n] = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_lingua_up-" + (n + 1) + ".png"));
                linguaImage[1][n] = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_lingua_down-" + (n + 1) + ".png"));
                linguaImage[2][n] = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_lingua_left-" + (n + 1) + ".png"));
                linguaImage[3][n] = ImageIO.read(getClass().getResourceAsStream("/res/entity/snake/snake_lingua_right-" + (n + 1) + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Aumenta o tamanho da Cobra após ingerir a Comida.
     */
    public void crescer() {

        snakeBody++;
        snakeX.add(x);
        snakeY.add(y);
    }

    public void update() {

        dir = gp.tecladoAdapter.adapterDir;

        switch (dir) {
        case 0:
            y -= speed;
            incrementX = 0;
            incrementY = -gp.TILE_SIZE;
            break;
        case 1:
            incrementX = 0;
            incrementY = gp.TILE_SIZE;
            y += speed;
            break;
        case 2:
            incrementX = -gp.TILE_SIZE;
            incrementY = 0;
            x -= speed;
            break;
        case 3:
            incrementX = gp.TILE_SIZE;
            incrementY = 0;
            x += speed;
            break;
        }

        // Remove as cordenadas que a cobra não está mais utilizando
        snakeX.remove(0); 
        snakeY.remove(0);
        snakeX.add(x);
        snakeY.add(y);

        // Verificações
        verificarCorpo();
        verificarMapa();
        verificarComida();

        // Animação da lingua
        spriteCounter++;
        if (spriteCounter >= 3) {

            spriteCounter = 0;
            spriteNum++;

            if (spriteNum >= linguaImage[0].length) {
                spriteNum = 0;
            }
        }
    }

    /*
     * Verifica se a Cobra não bateu no próprio corpo.
     */
    public void verificarCorpo() {

        for (int body = 0; body < snakeBody-2; body++) {
            if (snakeX.get(body).equals(snakeX.get(snakeBody-1)) && snakeY.get(body).equals(snakeY.get(snakeBody-1))) {
                gp.gameOver();
            }
        }
    }

    /*
     * Verifica se a cobra não bateu nas bordas do mapa.
     */
    public void verificarMapa() {
        
        if (x < 0 || y < 0 || x >= gp.SCREEN_WIDTH || y >= gp.SCREEN_HEIGHT) {
            gp.gameOver();
        }
    }

    /*
     * Verifica se a Cobra ingeriu alguma comida.
     */
    public void verificarComida() {

        if (x == gp.powerUp.x && y == gp.powerUp.y) {
            crescer();
            gp.powerUp.randomPos();
        }
    }

    public void draw(Graphics g) {

        // Cabeça
        g.drawImage(headImage[dir], x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        
        // Lingua
        g.drawImage(linguaImage[dir][spriteNum], x + incrementX, y + incrementY, gp.TILE_SIZE, gp.TILE_SIZE, null);

        // Corpo
        for (int body = snakeBody-2; body >= 0; body--) {
            g.drawImage(bodyImage, snakeX.get(body), snakeY.get(body), gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }

}
