import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {
    // Bird position and physics variables
    private int birdY = 250;             // Initial Y position of the bird
    private int birdVelocity = 0;        // Velocity of the bird
    private int birdAcceleration = 1;    // Acceleration of the bird
    // Pipe variables
    private int pipeX = 600;             // Initial X position of the pipes
    private int pipeY = 0;               // Y position of the upper pipe
    private int pipeGap = 200;           // Gap between upper and lower pipes
    private int pipeWidth = 50;          // Width of the pipes
    private int pipeHeight = 150;        // Height of the pipes
    // Game variables
    private int score = 0;               // Player's score
    private Timer timer;                 // Timer for game updates
    private boolean gameRunning = false; // Flag to indicate if the game is running
    private boolean startScreen = true;  // Flag to indicate if it's the start screen
    
    // Image for the bird
    private Image birdImage;

    /**
     * Constructor for FlappyBirdGame panel.
     * Initializes game settings and loads resources.
     */
    public FlappyBirdGame() {
        try {
            // Load bird image from resources
            birdImage = new ImageIcon(getClass().getResource("/assets/flappybird.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up timer for game updates
        timer = new Timer(20, this);
        timer.start();

        // Set up panel for key events
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(600, 500));
    }

    /**
     * Paints the game components including background, clouds, bird, pipes, and score.
     *
     * @param g The Graphics context to paint on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background
        g.setColor(new Color(44, 174, 229));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw clouds
        g.setColor(Color.WHITE);
        drawCloud(g, 50, 50);
        drawCloud(g, 200, 110);
        drawCloud(g, 350, 400);
        drawCloud(g, 500, 50);
        drawCloud(g, 150, 350);

        // Display start screen message
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

            // Display game over message if game has ended
            if (!gameRunning) {
                g.drawString("Game Over! Press Space to restart", 150, 250);
            }
        }
    }

    /**
     * Helper method to draw a cloud at specified position.
     *
     * @param g The Graphics context to draw on.
     * @param x The X coordinate of the cloud.
     * @param y The Y coordinate of the cloud.
     */
    private void drawCloud(Graphics g, int x, int y) {
        g.fillOval(x, y, 60, 40);
        g.fillOval(x + 20, y - 20, 60, 40);
        g.fillOval(x + 40, y, 60, 40);
        g.fillOval(x + 20, y + 10, 60, 40);
    }

    /**
     * Updates game state each time the timer triggers an action.
     *
     * @param e The ActionEvent triggering the update.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            // Update bird position and velocity
            birdVelocity += birdAcceleration;
            birdY += birdVelocity;

            // Move pipes to the left
            pipeX -= 5;
            
            // Reset pipe position and generate new gap if pipe moves offscreen
            if (pipeX + pipeWidth < 0) {
                pipeX = getWidth();
                pipeHeight = (int) (Math.random() * (getHeight() - pipeGap));
                score++;
            }

            // Check for collisions with pipes or screen boundaries
            if (birdY >= getHeight() || birdY <= 0 ||
                    (birdY + 30 >= pipeY && birdY <= pipeY + pipeHeight && (100 + 30 >= pipeX && 100 <= pipeX + pipeWidth)) ||
                    (birdY <= pipeY + pipeHeight + pipeGap && birdY + 30 >= pipeY + pipeHeight + pipeGap && (100 + 30 >= pipeX && 100 <= pipeX + pipeWidth))) {
                gameRunning = false;
                timer.stop();
            }
        }
        repaint(); // Redraw the panel
    }

    /**
     * Handles key presses to control the game (start, jump, restart).
     *
     * @param e The KeyEvent for the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (startScreen) {
                // Start the game on Space key press from start screen
                startScreen = false;
                gameRunning = true;
            } else if (gameRunning) {
                // Make the bird jump on Space key press during gameplay
                birdVelocity = -15;
            } else if (!gameRunning) {
                // Restart the game on Space key press after game over
                reset();
            }
        }
    }

    /**
     * Resets game variables to start a new game.
     */
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

    // Unused KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
