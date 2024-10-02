package main;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {

    GamePanel gp;
    BufferedImage image;

    public Background(GamePanel gp) {

        this.gp = gp;
        loadImage();
    }
    
    public void loadImage() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/tile/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {

        // Desenha por toda a tela o Tile Específico
        for (int y = 0; y < gp.SCREEN_HEIGHT; y += gp.TILE_SIZE) {
            for (int x = 0; x < gp.SCREEN_WIDTH; x += gp.TILE_SIZE) {
                g.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
        }
    }

}