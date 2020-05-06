import java.awt.*;

public class Obstacle {

	// READ THIS BEFORE CHANGES ARE MADE: THIS IS A STARTER FILE. IT WILL EVENTUALLY BECOME AN ABSTRACT CLASS WITH AN IMAGE.
	
	private int x, y;
	private static int width, height;
	private Color color;
	private static int vX;
	//private static Image image;
	
	static {
		width = 100;
		height = 100;
		vX = 10;
	}
	
	public Obstacle(int x1, int y1, int w, int h, Color c) {
		x = x1;
		y = y1;
		color = c;
	}
	
	public void drawOb(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
}