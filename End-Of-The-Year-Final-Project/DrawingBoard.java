import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

public class DrawingBoard extends JPanel {

    private BufferedImage bImage;
    private Graphics bufferedG;
    private JFrame frame;
    private BufferedImage bg;

    public DrawingBoard(int w, int h) {
        frame = new JFrame("Goat Runner");
        frame.setBounds(100, 0, 0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(w, h));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        bImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        bufferedG = bImage.getGraphics();
        ((Graphics2D) bufferedG).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        bufferedG.setColor(Color.WHITE);
        bufferedG.fillRect(0, 0, w, h);

        try {
            bg = ImageIO.read(new File("grass.jpg"));
        } catch (IOException e) { }
        bufferedG.drawImage(bg, 0, -1, null);
	}

    public JFrame getJFrame() {
        return frame;
    }

    public Graphics getCanvas() {
        return bufferedG;
    }

    public void clear() {
        bufferedG.drawImage(bg, 0, -1, null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(bImage, 0, 0, null);
    }

}