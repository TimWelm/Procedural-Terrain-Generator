package main;

public class Line {
	
	// The number of units the left end point is from the left of the World
	private int xDisplace;
	
	// The length of the line
	private int lineLength;
	
	// The y-coordinate of the left end point
	private double y1;
	
	// The y-coordinate of the right end point
	private double y2;
	
	// The slope of the line
	private double slope;
	
	// The vertical displacement of the line
	private double verticalDisp;
	
	// The x-coordinate of the mid point of the line
	private int midPointX;
	
	// The y-coordinate of the mid point of the line
	private double midPointY;

	public Line(int x1, int x2, double y1, double y2) {
		xDisplace = x1;
		lineLength = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.lineLength -= xDisplace;
		slope = (y2 - y1) / (x2 - x1);
		verticalDisp = this.y1 - slope * xDisplace;
		midPointX = (int) ((x2 - x1) / 2);
		midPointY = resolve(midPointX + xDisplace);
	}

	// Reinitializes the variables of the lines
	public void reLine() {
		lineLength = midPointX;
		y2 = midPointY;
		slope = (y2 - y1) / lineLength;
		verticalDisp = y1 - slope * xDisplace;
		midPointX = (int) (lineLength / 2);
		midPointY = resolve(midPointX + xDisplace);
	}

	// Gives the y value for the line at the given x
	public double resolve(int x) {
		return x * slope + verticalDisp;
	}
 
	public int getLength() {
		return lineLength;
	}

	public double getY2() {
		return y2;
	}

	public int getXDisplace() {
		return xDisplace;
	}

	public int getMidPointX() {
		return midPointX;
	}

	public double getMidPointY() {
		return midPointY;
	}

	public void displaceMidPointY(double delta) {
		this.midPointY += delta;
	}

	public String toString() {
		return "y = " + slope + "x + " + verticalDisp;
	}
}