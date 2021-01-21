package ball_runner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.Timer;
import javax.swing.plaf.*;
import java.awt.event.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.File;

class Main extends JPanel {

    JFrame frame;
    int width = 1024;
    int height = 576;
    JMenuBar menuBar;
    JMenu fileMenu, preferencesMenu;
    JMenuItem restartItem, colorBallItem, colorObstacleItem, soundController;
    JCheckBox bgSoundCheckbox;
    float checkboxValue;
    JDialog soundDialog;
    JColorChooser ballChooser, obstacleChooser;
    Color ballColor = Color.BLACK;
    Color obstacleColor = Color.BLACK;
    JPanel mainPanel, textPanel, gamePanel, soundPanel;
    JLabel level;
    int currentLevel = 1;
    Timer timerUp, timerDown;
    Action spacePressedUp;
    Ball ball;
    Obstacle obstacle = new Obstacle();
    Timer obstacleMove;
    boolean isCollided = false;
    CardLayout cl;
    int obstacleWidth = obstacle.width;
    static URL bg = Main.class.getResource("img/grass.jpg");
	static ImageIcon icon = new ImageIcon(bg);
    static URL exp = Main.class.getResource("img/explosion_nontransparent.png");
	static ImageIcon explosionIcon = new ImageIcon(exp);
    static URL gm = Main.class.getResource("img/gameOver2.png");
	static ImageIcon gmIcon = new ImageIcon(gm);
    SoundPlayer player;
    FloatControl gainControl;

    public Main() {
        player = new SoundPlayer();
        Thread playerThread = new Thread(player);
        playerThread.start();

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
                    Object[] options = {};
                    int option = JOptionPane.showConfirmDialog(frame, "Would you like to restart?");
                    if(option == 0) {
                        restart();
                    }
                }
            }
        });
    }

    void createAndShowGUI() {
        frame = new JFrame("Ball Runner Copyright \u00a9 2020 by Three Goats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);

        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		URL imageResource = Main.class.getResource("img/gear.png"); // URL: https://cdn.pixabay.com/photo/2012/05/04/10/57/gear-47203_1280.png
		Image image = defaultToolkit.getImage(imageResource);

		try {
			// this is new since JDK 9
			Taskbar taskbar = Taskbar.getTaskbar();
			// set icon for mac os (and other systems which do support this method)
			taskbar.setIconImage(image);
		} catch (UnsupportedOperationException e) {
			// set icon for windows (and other systems which do support this method)
			frame.setIconImage(image);
		}

        mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel);

        textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(150, 150, 150, 150));
        mainPanel.add("welcome", textPanel);
        cl = (CardLayout)(mainPanel.getLayout());
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
        restartItem.setAccelerator(KeyStroke.getKeyStroke('R',
                                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
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

        colorObstacleItem = new JMenuItem("Obstacle Color");
        colorObstacleItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obstacleChooser = new JColorChooser();
                obstacleChooser.setColor(Color.BLACK);
                obstacleChooser.setBorder(BorderFactory.createTitledBorder("Choose Obstacle Color"));
                obstacleChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent arg0) {
                        Color color = obstacleChooser.getColor();
                        obstacleColor = color;
                        repaint();
                    }
                });
                JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, obstacleChooser, null, null);
                dialog.setVisible(true);
            }
        });
        preferencesMenu.add(colorObstacleItem);

        soundController = new JMenuItem("Sound");
        soundDialog = new JDialog(frame, "Sound Controls");
        soundDialog.setVisible(false);
        soundPanel = new JPanel();
        bgSoundCheckbox = new JCheckBox("Enable Sound", true);
<<<<<<< HEAD
        bgSoundCheckbox.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == ItemEvent.SELECTED) {
                   gainControl.setValue(0.0f);
               }
               else {
                   gainControl.setValue(-80.0f);
               }
           }
        });
=======
>>>>>>> 5271d5922e1d1a2beb384a7edfabb30117eb01d3
        soundDialog.add(soundPanel);
        soundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        soundPanel.add(bgSoundCheckbox);
        soundDialog.pack();
        soundController.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundDialog.setVisible(true);
<<<<<<< HEAD
                //soundDialog.setVisible(true);
=======
                bgSoundCheckbox.addItemListener(new ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                       if(e.getStateChange() == ItemEvent.SELECTED) {
                           gainControl.setValue(0.0f);
                       }
                       else {
                           gainControl.setValue(-80.0f);
                       }
                   }
                });
>>>>>>> 5271d5922e1d1a2beb384a7edfabb30117eb01d3
            }
        });
        try {
            Desktop desktop = Desktop.getDesktop();

            desktop.setAboutHandler(e ->
                JOptionPane.showMessageDialog(frame, "About dialog")
            );
            desktop.setPreferencesHandler(e -> {
                soundDialog.setVisible(true);
            });
            desktop.setQuitHandler((e,r) -> {
            	int input = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?");
            	if(input == 0) {
            		System.exit(0);
                }
            });
        } catch(Exception e) {}
        preferencesMenu.add(soundController);

        menuBar.add(preferencesMenu);

        gamePanel = new JPanel(new BorderLayout());
        mainPanel.add("game", gamePanel);
        JButton button = new JButton("Continue");
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
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

    void restart() {
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
        if(isCollided) {
            g2d.drawImage(explosionIcon.getImage(), ball.x-2*ball.radius, ball.y-2*ball.radius, null);
            g2d.drawImage(gmIcon.getImage(), width/4, height/4, null);
        }
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
                    /*
                    new Thread(() -> {
                        player.playSound();
                    });
                    */
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

        private void moveDown() {
            y+=dy;
            if(y == oY) {
                timerDown.stop();
                getActionMap().put("pressed", spacePressedUp);
                dy = tempBalldy;
            }
        }

        private void draw(Graphics g, Color color) {
            g.setColor(color);
            g.fillOval(x, y, 2*radius, 2*radius);
        }

        private void moveUp() {
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

    class SoundPlayer implements Runnable {

        URL fileName = SoundPlayer.class.getResource("img/theme1.wav");
        URL boing = SoundPlayer.class.getResource("img/boing.wav");

        private synchronized void playBgSound() {
            /*
               Domain: filename of type String
               Range: void type, output: plays background music on its own thread
            */
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(fileName);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        synchronized void playSound() {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(boing));
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            playBgSound();
        }
    }

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // for picky mac users
		System.setProperty("apple.awt.application.name", "Ball Runner"); // mac header on mac menubar
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) { e.printStackTrace(); }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

}
