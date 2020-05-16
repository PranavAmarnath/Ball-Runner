import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingBoard extends JPanel {

    private JFrame frame;
    private BufferedImage bImage;
    public static Graphics bufferedG;
    public static int w, h;
    public Image bg;
    public static int alpha = 255;

    public DrawingBoard(int w, int h) {
        // SoundPlayer bgSound = new SoundPlayer(R_PATH + "bgMusic.wav");
        // bgSound.playLoop();
        this.w = w;
        this.h = h;
        frame = new JFrame("Ball Runner");
        frame.setBounds(0, 0, w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        bImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        bufferedG = bImage.getGraphics();
        ((Graphics2D) bufferedG).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        bg = new ImageIcon("grass.jpg").getImage();
        // bufferedG.drawImage(bg, 0, 0, null);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bImage, 0, 0, null);
        g.setColor(new Color(0, 0, 0, alpha));
        g.setFont(new Font("times", Font.BOLD, 50));
        g.drawString("Welcome to Ball Runner!", 200, 250);
    }

    public void clear() {
        bufferedG.drawImage(bg, 0, 0, null);
    }

    public JFrame getJFrame() {
        return frame;
    }

    public static Graphics getCanvas() {
        return bufferedG;
    }

}