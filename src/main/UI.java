package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
    
    GamePanel gp;
    Graphics g;
    int points;
    BufferedImage imageGameOver, imagePause;
    Font maruMonica;

    public UI(GamePanel gp) {
        
        this.gp = gp;
        load();
    }

    public void load() {

        try {
            
            // Imagens
            imageGameOver = ImageIO.read(getClass().getResourceAsStream("/res/ui/game_over.png"));
            imagePause = ImageIO.read(getClass().getResourceAsStream("/res/ui/pause.png"));

            // Fonte
            InputStream is = getClass().getResourceAsStream("/res/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {

        this.g = g;
        this.g.setFont(maruMonica);

        if (gp.gameState == gp.PLAY_STATE) {

            drawPlayState();
        } else if (gp.gameState == gp.PAUSE_STATE) {

            drawPauseState();
        } else if (gp.gameState == gp.GAME_OVER_STATE) {
            
            drawGameOverState();
        }
    }

    /*
     * Desenha a Tela Normal de Jogo.
     */
    public void drawPlayState() {

        // Pontos
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        String texto = "SCORE: " + points;

        int x = 15;
        int y = 25;

        g.setColor(Color.WHITE);
        g.drawString(texto, x, y);
    }

    /*
     * Desenha a Tela de PAUSE.
     */
    public void drawPauseState() {

        // Cabeça Cobra
        int width = gp.TILE_SIZE * 4;
        int height = gp.TILE_SIZE * 4;

        int x = (gp.SCREEN_WIDTH - width) / 2;
        int y = 50;

        g.drawImage(imagePause, x, y, width, height, null);

        // Escrita Pause
        g.setFont(g.getFont().deriveFont(Font.BOLD, 70F));
        String texto = "PAUSED";

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        width = metrics.stringWidth(texto);
        height = metrics.getHeight();

        x = (gp.SCREEN_WIDTH - width) / 2 - 3;
        y = (gp.SCREEN_HEIGHT - height) / 2 - 3 + 80;

        g.setColor(Color.BLACK);
        g.drawString(texto, x, y);

        metrics = g.getFontMetrics(g.getFont());
        width = metrics.stringWidth(texto);
        height = metrics.getHeight();

        x = (gp.SCREEN_WIDTH - width) / 2;
        y = (gp.SCREEN_HEIGHT - height) / 2 + 80;

        g.setColor(Color.WHITE);
        g.drawString(texto, x, y);
    }

    /*
     * Desenha a tela de GAME OVER.
     */
    public void drawGameOverState() {

        // Cabeça Cobra Morta
        int width = gp.TILE_SIZE * 4;
        int height = gp.TILE_SIZE * 4;

        int x = (gp.SCREEN_WIDTH - width) / 2;
        int y = 50;

        g.drawImage(imageGameOver, x, y, width, height, null);

        // Escrita Game Over
        g.setFont(g.getFont().deriveFont(Font.BOLD, 70F));
        String texto = "GAME OVER!";

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        width = metrics.stringWidth(texto);
        height = metrics.getHeight();

        x = (gp.SCREEN_WIDTH - width) / 2 - 3;
        y = (gp.SCREEN_HEIGHT - height) / 2 - 3 + 80;

        g.setColor(Color.BLACK);
        g.drawString(texto, x, y);

        metrics = g.getFontMetrics(g.getFont());
        width = metrics.stringWidth(texto);
        height = metrics.getHeight();

        x = (gp.SCREEN_WIDTH - width) / 2;
        y = (gp.SCREEN_HEIGHT - height) / 2 + 80;

        g.setColor(new Color(236, 39, 63));
        g.drawString(texto, x, y);

        // Escrita para reiniciar o Jogo
        g.setFont(g.getFont().deriveFont(Font.BOLD, 25F));
        texto = "Pressione ESPAÇO para tentar novamente!";

        metrics = g.getFontMetrics(g.getFont());
        width = metrics.stringWidth(texto);
        height = metrics.getHeight();

        x = (gp.SCREEN_WIDTH - width) / 2 - 2;
        y = (gp.SCREEN_HEIGHT - height) / 2 + 10 - 2 + 80;

        g.setColor(Color.BLACK);
        g.drawString(texto, x, y);

        x = (gp.SCREEN_WIDTH - width) / 2;
        y = (gp.SCREEN_HEIGHT - height) / 2 + 10 + 80;

        g.setColor(Color.WHITE);
        g.drawString(texto, x, y);
    }
}
