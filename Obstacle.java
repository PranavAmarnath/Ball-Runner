import java.awt.*;

public class Obstacle {

	// READ THIS BEFORE CHANGES ARE MADE: THIS IS A STARTER FILE. IT WILL EVENTUALLY BECOME AN ABSTRACT CLASS WITH AN IMAGE.
	
	private int x, y;
	private static int width, height;
	private static int vX;
	//private Color color;
	//private static Image image;

	public static final Color BROWN = new Color(102, 51, 0);
	
	static {
		height = 100;
		width = height/ 2;
		vX = 10;
	}
	
	public Obstacle(int x1, int y1, int w, int h) {
		x = x1;
		y = y1;
	}
	
	public void drawOb(Graphics g) {
		g.setColor(BROWN);
		g.fillRect(x, y, width, height);
		g.setColor(BROWN);
		g.drawRect(x, y, width, height);
	}
}