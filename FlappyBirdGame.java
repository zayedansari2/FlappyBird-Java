import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {
    private int birdY = 250;
    private int birdVelocity = 0;
    private int birdAcceleration = 1;
    private int pipeX = 600;
    private int pipeY = 0;
    private int pipeGap = 200;
    private int pipeWidth = 50;
    private int pipeHeight = 150;
    private int score = 0;
    private Timer timer;
    private boolean gameRunning = false;
    private boolean startScreen = true;

    private Image birdImage;

    public FlappyBirdGame() {
        try {
            birdImage = new ImageIcon(getClass().getResource("/assets/flappybird.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(600, 500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(44, 174, 229));
        g.fillRect(0, 0, getWidth(), getHeight()); // Background

        // Draw clouds
        g.setColor(Color.WHITE);
        drawCloud(g, 50, 50);
        drawCloud(g, 200, 110);
        drawCloud(g, 350, 400);
        drawCloud(g, 500, 50);
        drawCloud(g, 150, 350);

        if (startScreen) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Press Space to Play", 150, 250);
        } else {
            // Draw pipes as rectangles
            g.setColor(Color.GREEN);
            g.fillRect(pipeX, pipeY, pipeWidth, pipeHeight); // Upper pipe
            g.fillRect(pipeX, pipeY + pipeHeight + pipeGap, pipeWidth, getHeight() - pipeY - pipeHeight - pipeGap); // Lower pipe

            // Draw bird
            g.drawImage(birdImage, 100, birdY, 30, 30, this);

            // Draw score
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 30);

            if (!gameRunning) {
                g.drawString("Game Over! Press Space to restart", 150, 250);
            }
        }
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.fillOval(x, y, 60, 40);
        g.fillOval(x + 20, y - 20, 60, 40);
        g.fillOval(x + 40, y, 60, 40);
        g.fillOval(x + 20, y + 10, 60, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            birdVelocity += birdAcceleration;
            birdY += birdVelocity;

            pipeX -= 5;
            if (pipeX + pipeWidth < 0) {
                pipeX = getWidth();
                pipeHeight = (int) (Math.random() * (getHeight() - pipeGap));
                score++;
            }

            if (birdY >= getHeight() || birdY <= 0 ||
                    (birdY + 30 >= pipeY && birdY <= pipeY + pipeHeight && (100 + 30 >= pipeX && 100 <= pipeX + pipeWidth)) ||
                    (birdY + 30 >= pipeY + pipeHeight + pipeGap && birdY <= pipeY + getHeight() - pipeY - pipeHeight - pipeGap && (100 + 30 >= pipeX && 100 <= pipeX + pipeWidth))) {
                gameRunning = false;
                timer.stop();
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (startScreen) {
                startScreen = false;
                gameRunning = true;
            } else if (gameRunning) {
                birdVelocity = -15;
            } else if (!gameRunning) {
                reset();
            }
        }
    }

    private void reset() {
        birdY = 250;
        birdVelocity = 0;
        pipeX = 600;
        pipeY = 0;
        pipeGap = 200;
        pipeWidth = 50;
        pipeHeight = 150;
        score = 0;
        gameRunning = true;
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}