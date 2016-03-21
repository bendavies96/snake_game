public class Coord { //an individual segment in the snake
	int x, y; //x and y coordinate
	
	public Coord (int x0, int y0) {
		x = x0;
		y = y0;
	}
	
	//return x coordinate
	public int getcoordX() {
		return x;
	}
	
	//set x coordinate
	public void setcoordX(int x0) {
		x = x0;
	}
	
	//get y coordinate
	public int getcoordY() {
		return y;
	}
	
	//set y coordinate
	public void setcoordY(int y0) {
		y = y0;
	}
}
