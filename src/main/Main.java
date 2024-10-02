package main;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // Janela
        JFrame janela = new JFrame("SnakeGame");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);   

        // Adiciona o GamePanel
        GamePanel gp = new GamePanel();
        janela.add(gp);
        janela.pack();

        // Exibe a Janela e inicia o Jogo
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        gp.startGame();
    }
}
