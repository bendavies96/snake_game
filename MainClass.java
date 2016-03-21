import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainClass extends Applet implements Runnable, KeyListener {
	
	private Snake snake; //player's snake
	private Block block; // block that snake eats to gain a point and add another segment to its tail
	private boolean alive; //true if snake is alive, false if snake dies
	private int score; //player's score
	private boolean canTurn = true;
	
	@Override
	public void init() {
		System.out.println("Initializing");
		
		setSize(800, 480); //set window dimensions
		setBackground(Color.BLACK); //set window background
		setFocusable(true); //set window to be visible
		addKeyListener(this); //add keyboard listener
		
		Frame frame = (Frame) this.getParent().getParent(); //create frame
		frame.setTitle("Snake"); //set frame name
	}
	
	@Override
	public void start() {
		System.out.println("Starting");
		
		//initialize objects and variables
		snake = new Snake();
		block = new Block();
		alive = true;
		score = 0;
		
		//start game thread
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping");
		super.stop();
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public void run() {
		while (alive) { //while player is alive, update the game and refresh the window every 50 milliseconds
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Graphics g) {
		snake.movement(); //update snake's location (move snake's coordinates)
		if (!canTurn) canTurn = true;
		if (checkCollision()) { //if snake eats the block
			block.newCoord(); //new block created at another random location
			snake.incrSize(); //segment added to snake tail
			score++; //score increased
		}
		else if (tailCollision()) alive = false; //if the snake's head runs into its one tail, it dies
		
		//if snake moves outside the game area, it dies
		if (snake.getX(0) < 0 || snake.getX(0) >= 800 ||
				snake.getY(0) < 0 || snake.getY(0) >= 480)
				alive = false;
		//refresh the game window
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		if (alive) { //if snake is still alive
			//refresh the black background
			g.setColor(Color.black); 
			g.fillRect(0, 0, 800, 480);
			//refresh the green score indicator
			g.setColor(Color.green);
			g.setFont(new Font("default", Font.PLAIN, 16));
			g.drawString(String.valueOf(score), 390, 30);
			//refresh the snake
			g.setColor(Color.white);
			for (int i=0; i < snake.getSize(); i++) {
				g.fillRect(snake.getX(i), snake.getY(i), 10, 10);
			}
			//refresh the block that the snake is meant to eat
			g.setColor(Color.cyan);
			g.fillRect(block.getX(), block.getY(), 10, 10);
		} else { //if the snake dies
			//display "Game Over"
			g.setColor(Color.red);
			g.setFont(new Font("default", Font.BOLD, 60));
			g.drawString("GAME OVER", 200, 200);
			//display the player's final score
			g.setFont(new Font("default", Font.BOLD, 40));
			g.drawString("Score: " + String.valueOf(score), 300, 300);
		}
		
	}
	
	//checks if the snake has collided with the block that the snake can eat
	public boolean checkCollision() {
		if (block.getX() == snake.getX(0) && block.getY() == snake.getY(0)) {
			return true;
		}
		return false;
	}
	
	//checks if the snake's head has collided with its tail
	public boolean tailCollision() {
		for (int i = 1; i < snake.getSize(); i++) {
			if (snake.getX(0) == snake.getX(i) && snake.getY(0) == snake.getY(i)) {
				return true;
			}
		}
		return false;
	}
	
	//changes snake's direction in response to pressing the arrow keys
	@Override
	public void keyPressed(KeyEvent e) {
		if (canTurn) {
			switch (e.getKeyCode()) {
			   case KeyEvent.VK_UP:
				   if (snake.getYSpeed() == 0) {
					   snake.setYSpeed(-10);
					   snake.setXSpeed(0);
					   canTurn = false;
				   }
				   break;
	
			   case KeyEvent.VK_DOWN:
				   if (snake.getYSpeed() == 0) {
					   snake.setYSpeed(10);
					   snake.setXSpeed(0);
				   }
				   break;
	
			   case KeyEvent.VK_LEFT:
				   if (snake.getXSpeed() == 0) {
					   snake.setXSpeed(-10);
					   snake.setYSpeed(0);
				   }
				   break;
	
			   case KeyEvent.VK_RIGHT:
				   if (snake.getXSpeed() == 0) {
					   snake.setXSpeed(10);
					   snake.setYSpeed(0);
				   }
				   break;
			   
			   case KeyEvent.VK_SPACE:
				   snake.incrSize();
				   break;
	
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		 /*switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP:
		   break;

		   case KeyEvent.VK_DOWN:
		   break;

		   case KeyEvent.VK_LEFT:
		   break;

		   case KeyEvent.VK_RIGHT:
		   break;

		   }*/
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		/*switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP:
		   break;

		   case KeyEvent.VK_DOWN:
		   break;

		   case KeyEvent.VK_LEFT:
		   break;

		   case KeyEvent.VK_RIGHT:
		   break;

		   }*/
	}
}
