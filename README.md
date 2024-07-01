# FlappyBird-Java

# Overview
The Flappy Bird Game is a Java application that replicates the popular mobile game with smooth animations and responsive user input handling. Developed using Java Swing, this game features intuitive controls, engaging gameplay, and a scoring system to track high scores. The game offers an enjoyable experience with a user-friendly interface and simple mechanics.

# Files
- `FlappyBirdGame.java`: Contains the `FlappyBirdGame` class, which extends `JPanel` and implements `ActionListener` and `KeyListener`. This class handles the game mechanics, rendering, and user input.
- `FlappyBirdMain.java`: Contains the `FlappyBirdMain` class, which serves as the entry point for the application. It creates an instance of the `FlappyBirdGame` class and initializes the game window.

# Usage
To play the Flappy Bird Game, follow these steps:
1. Compile the Java files:
   javac FlappyBirdGame.java FlappyBirdMain.java
   2. Run the compiled Java program:
   java FlappyBirdMain
  
# Class Details

1. # FlappyBirdGame.java
   This class handles the core functionality of the Flappy Bird game.

   - # Attributes
     - `birdY`: The vertical position of the bird.
     - `birdVelocity`: The current velocity of the bird.
     - `birdAcceleration`: The constant acceleration affecting the bird.
     - `pipeX`: The horizontal position of the pipe.
     - `pipeY`: The vertical position of the upper pipe.
     - `pipeGap`: The gap between the upper and lower pipes.
     - `pipeWidth`: The width of the pipes.
     - `pipeHeight`: The height of the upper pipe.
     - `score`: The player's current score.
     - `timer`: A `Timer` object to control the game loop.
     - `gameRunning`: A boolean flag to indicate if the game is running.
     - `startScreen`: A boolean flag to indicate if the game is on the start screen.
     - `birdImage`: An `Image` object to hold the bird's image.

   - # Methods
     - `FlappyBirdGame()`: Constructor that initializes the game, loads the bird image, and starts the timer.
     - `paintComponent(Graphics g)`: Overridden method to render the game components (background, bird, pipes, score).
     - `drawCloud(Graphics g, int x, int y)`: Helper method to draw clouds in the background.
     - `actionPerformed(ActionEvent e)`: Overridden method that updates the game state (bird movement, pipe movement, collision detection) and repaints the components.
     - `keyPressed(KeyEvent e)`: Overridden method to handle key press events (start game, bird flap, restart game).
     - `reset()`: Resets the game state for a new game.
     - `keyTyped(KeyEvent e)`: Overridden method required by `KeyListener`, but not used.
     - `keyReleased(KeyEvent e)`: Overridden method required by `KeyListener`, but not used.

2. # FlappyBirdMain.java
   This class serves as the entry point for the application.

   - # Methods
     - `main(String[] args)`: The main method that creates a `JFrame`, adds an instance of `FlappyBirdGame` to it, and makes the game window visible.
