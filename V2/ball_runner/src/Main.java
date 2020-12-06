package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.net.URL;

class Main extends JPanel {

    JFrame frame;
    int width = 1024;
    int height = 576;
    JMenuBar menuBar;
    JMenu fileMenu, preferencesMenu;
    JMenuItem restartItem, colorBallItem, colorObstacleItem;
    JColorChooser ballChooser, obstacleChooser;
    Color ballColor = Color.BLACK;
    Color obstacleColor = Color.BLACK;
    JPanel mainPanel, textPanel, gamePanel;
    JLabel level;
    int currentLevel = 1;
    Timer timerUp, timerDown;
    Action spacePressedUp;
    Ball ball;
    Obstacle obstacle = new Obstacle();
    Timer obstacleMove;
    boolean isCollided = false;
    int obstacleWidth = obstacle.width;
    static URL bg = Main.class.getResource("img/grass.jpg");
	static ImageIcon icon = new ImageIcon(bg);
    static URL exp = Main.class.getResource("img/explosion_nontransparent.png");
	static ImageIcon explosionIcon = new ImageIcon(exp);
    static URL gm = Main.class.getResource("img/gameOver2.png");
	static ImageIcon gmIcon = new ImageIcon(gm);

    public Main() {
        createAndShowGUI();

        ball = new Ball();

        obstacleMove = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obstacle.move();
                //repaint(obstacle.oX, obstacle.y, obstacle.width, obstacle.height);
                repaint();
                int center_x = ball.x + ball.radius;
                int center_y = ball.y + ball.radius;
                if(center_x >= obstacle.x-ball.radius
                    && center_y >= obstacle.y-ball.radius
                    && center_x <= obstacle.x+obstacle.width+ball.radius) {
                        isCollided = true;
                }
                if(isCollided) {
                    ((Timer)e.getSource()).stop();
                    obstacle.width = obstacleWidth;
                    getActionMap().remove("pressed");
                    timerUp.stop();
                    timerDown.stop();
                }
            }
        });
        obstacleMove.start();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Ball Runner Copyright \u00a9 2020 by Three Goats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);

        mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel);

        textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(150, 150, 150, 150));
        mainPanel.add("welcome", textPanel);
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "welcome");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        restartItem = new JMenuItem("Restart (Level 1)");
        restartItem.setEnabled(false);
        fileMenu.add(restartItem);
        restartItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerUp.stop();
                timerDown.stop();
                isCollided = false;
                ball.y = ball.oY;
                currentLevel = 1;
                level.setText("Level: " + currentLevel);
                getActionMap().put("pressed", spacePressedUp);
                obstacle.x = obstacle.originalX;
                obstacle.speed = obstacle.originalSpeed;
                ball.dy = ball.originaldy;
                repaint();
                obstacleMove.start();
            }
        });
        frame.setJMenuBar(menuBar);

        preferencesMenu = new JMenu("Preferences");
        colorBallItem = new JMenuItem("Ball Color");
        colorBallItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ballChooser = new JColorChooser();
                ballChooser.setColor(Color.BLACK);
                ballChooser.setBorder(BorderFactory.createTitledBorder("Choose Ball Color"));
                ballChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent arg0) {
                        Color color = ballChooser.getColor();
                        ballColor = color;
                        repaint();
                    }
                });
                JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, ballChooser, null, null);
                dialog.setVisible(true);
            }
        });
        preferencesMenu.add(colorBallItem);
        menuBar.add(preferencesMenu);

        gamePanel = new JPanel(new BorderLayout());
        mainPanel.add("game", gamePanel);
        JButton button = new JButton("Continue");
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "game");
                // Restart logic
                restartItem.setEnabled(true);
                timerUp.stop();
                timerDown.stop();
                isCollided = false;
                ball.y = ball.oY;
                currentLevel = 1;
                level.setText("Level: " + currentLevel);
                getActionMap().put("pressed", spacePressedUp);
                obstacle.x = obstacle.originalX;
                obstacle.speed = obstacle.originalSpeed;
                repaint();
                obstacleMove.start();
            }
        });
        JPanel smallPanel = new JPanel();
        textPanel.add(smallPanel, BorderLayout.SOUTH);
        smallPanel.add(button);

        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(new Color(121, 201, 250));
        gamePanel.add(levelPanel, BorderLayout.NORTH);
        level = new JLabel("Level: " + currentLevel);
        level.setFont(new Font("Segoe UI", Font.BOLD, 12));
        levelPanel.add(level);

        JLabel welcomeLabel = new JLabel("Welcome to Ball Runner (V2)!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        textPanel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel continueLabel = new JLabel("<html>Press the button below to start..<br>..Press SPACE to bounce the ball (as some of you core Ball Runner fans know ;)<html/>", JLabel.CENTER);
        continueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textPanel.add(continueLabel);

        this.setLayout(new BorderLayout());
        gamePanel.add(this);

        frame.pack();
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                                    RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        super.paintComponent(g2d);
        g2d.drawImage(icon.getImage(), 0, 0, null);
        obstacle.draw(g2d, obstacleColor);
        ball.draw(g2d, ballColor);
    }

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // for picky mac users
		System.setProperty("apple.awt.application.name", "Ball Runner"); // mac header on mac menubar
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) { }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    class Ball {
        final int oX = 20;
        final int oY = 430;
        int x = oX;
        int y = oY;
        int radius = 15;
        final int originaldy = 10;
        int dy = originaldy;
        int tempBalldy = dy;

        public Ball() {
            timerUp = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moveUp();
                    repaint();
                }
            });

            spacePressedUp = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    timerUp.start();
                }
            };

            timerDown = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moveDown();
                    repaint();
                }
            });

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            getActionMap().put("pressed", spacePressedUp);
        }

        public void moveDown() {
            y+=dy;
            if(y == oY) {
                timerDown.stop();
                getActionMap().put("pressed", spacePressedUp);
                dy = tempBalldy;
            }
        }

        public void draw(Graphics g, Color color) {
            g.setColor(color);
            g.fillOval(x, y, 2*radius, 2*radius);
        }

        public void moveUp() {
            y-=dy;
            if(y < 130) {
                timerUp.stop();
                timerDown.start();
                getActionMap().remove("pressed");
            }
        }
    }

    class Obstacle {
        final int originalX = 900;
        int x = originalX;
        int y = 380;
        int oX = 0;
        int width = 40;
        final int height = 80;
        final int originalSpeed = 5;
        int speed = originalSpeed;

        public void draw(Graphics g, Color color) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        public void move() {
            oX = x;
            x-=speed;
            if(x + width <= 0) {
                x = originalX;
                speed+=1;
                currentLevel++;
                level.setText("Level: " + currentLevel);
                ball.tempBalldy+=1;
            }
        }
    }

}
