package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TerrainConstructer {

	// The dimensions/variables of the World
	final static int X = 4000;
	final static int Y = 50;
	
	// The number of distortions is 2 to the power of this number minus 1
	final static int NUM_DISTORTIONS = 9;
	
	// The variable responsible for how mountainous the terrain is
	static double random = 18;
	
	public static void main(String[] args) throws IOException {
		PrintWriter writer = new PrintWriter("World.txt");

		char[][] grid = new char[X][Y];
		ArrayList<Line> lines = new ArrayList<>();

		// Adds one straight line at the center of the y
		lines.add(new Line(0, X, Y / 2, Y / 2));
		
		for (int i = 0; i < NUM_DISTORTIONS; i++) {	
			int length = lines.size();
			for (int j = 0; j < length; j++) {
				
				// Randomly displaces the midpoint of the selected line
				lines.get(j * 2).displaceMidPointY(
						Math.random() * random - random / 2);
				
				// Adds a new line at the first lines midpoint
				lines.add(
						j * 2 + 1,
						new Line(lines.get(j * 2).getMidPointX()
								+ lines.get(j * 2).getXDisplace(), lines.get(
								j * 2).getLength()
								+ lines.get(j * 2).getXDisplace(), lines.get(
								j * 2).getMidPointY(), lines.get(j * 2).getY2()));
				
				// Changes the first line to adapt to the new line
				lines.get(j * 2).reLine();
			}
			
			// Randomly decrements the size of the vertical distortion
			random -= (int) (Math.random() * 5);
		}

		// Initializes the World to be spaces
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = ' ';
			}
		}

		// Sets the filled land to be stars instead of spaces
		int index = 0;
		for (int i = 0; i < grid.length; i++) {
			grid[i][(int) lines.get(index).resolve(i)] = '*';
			for (int j = (int) (lines.get(index).resolve(i)); j < 50; j++) {
				grid[i][j] = '*';
			}
			if (i == lines.get(index).getLength() + lines.get(index).getXDisplace()) {
				index++;
			}
		}

		// Writes the array to the file
		for (int i = 0; i < grid[0].length; i++) {
			String fill = "";
			for (int j = 0; j < grid.length; j++) {
				fill += grid[j][i];
			}
			writer.println(fill);
		}
		writer.close();
	}
}