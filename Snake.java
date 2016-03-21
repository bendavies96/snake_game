import java.util.ArrayList;
import java.util.List;

public class Snake { //the snake that the player controls
	
	List<Coord> segment = new ArrayList<>(); //list of the snake's segments
	int xSpeed, ySpeed; //speeds in the x or y movement
	
	public Snake () {
		//starts with the snake's head near top left corner of window, moving right
		segment.add(new Coord(10, 20));
		xSpeed = 10;
		ySpeed = 0;
	}
	
	public void movement() {
		//shift the snake's tail forward
		for (int i=segment.size()-1; i > 0 ; i--) {
			segment.get(i).setcoordX(segment.get(i-1).getcoordX());
			segment.get(i).setcoordY(segment.get(i-1).getcoordY());
		}
		
		//move the snake's head to a new location
		segment.get(0).setcoordX(segment.get(0).getcoordX() + xSpeed);
		segment.get(0).setcoordY(segment.get(0).getcoordY() + ySpeed);
		
		
	}
	
	//get the number of segments
	public int getSize() {
		return segment.size();
	}
	
	//add another segment to the snake's tail
	public void incrSize() {
		int size = segment.size();
		segment.add(new Coord(segment.get(size-1).getcoordX(), segment.get(size-1).getcoordY()));
		size++;
	}
	
	//gets the corresponding segment x coordinate
	public int getX(int index) {
		return segment.get(index).getcoordX();
	}
	
	//gets the corresponding segment y coordinate
	public int getY(int index) {
		return segment.get(index).getcoordY();
	}
	
	//get the head's speed in the x direction
	public int getXSpeed() {
		return xSpeed;
	}
	
	//sets the head's speed in the x direction
	public void setXSpeed(int xSpeed0) {
		xSpeed = xSpeed0;
	}
	
	//get the head's speed in the y direction
	public int getYSpeed() {
		return ySpeed;
	}
	
	//sets the head's speed in the y direction
	public void setYSpeed(int ySpeed0) {
		ySpeed = ySpeed0;
	}
	
}
