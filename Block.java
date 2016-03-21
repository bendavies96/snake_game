import java.util.Random;

public class Block { //the block that the snake must eat to increase its score by 1 and add a segment to its tail
	int x, y; //x and y coordinate
	Random random = new Random(); //random number generator
	
	//initializes the block at a random coordinate
	public Block () {
		x = random.nextInt(80) * 10;
		y = random.nextInt(48) * 10;
	}
	
	//changes the block to a new random coordinate
	public void newCoord () {
		x = random.nextInt(80) * 10;
		y = random.nextInt(48) * 10;
	}
	
	//return the x coordinate
	public int getX() {
		return x;
	}
	
	//return the y coordinate
	public int getY () {
		return y;
	}
}
