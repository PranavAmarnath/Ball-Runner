import java.awt.*;
import java.io.File;

public class Main {

    // NOTE: THIS CLASS USES JDK 14. WHEN RUNNING, USE JDK 14. DO NOT USE JDK 9 OR PREVIOUS VERSIONS OF JAVA TO JDK 9.

	private static int index;
    public static boolean gameOn = true;
    public static int score;
    private static final int w = 1024;
    private static final int h = 576;
    public static int ball_x = 50;
    public static int ball_y = (h - 150);
    public static int obstacles_height = 100;
    public static int obstacles_width = obstacles_height/2;
    public static DrawingBoard board;
    public static Ball ball;
    public static Obstacle[] obstacles = new Obstacle[10];
    //public static obstaclessManager manager;
    private static SoundPlayer sound;
    //private static File boing;

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 15, 200);
        sound = new SoundPlayer();
        Thread t = new Thread(sound);
        t.start();
        //boing = new File("boing.wav");
        for(int i=0; i<obstacles.length;i++) obstacles[i] = new Obstacle(800, 360, obstacles_width, obstacles_height);
        index = (int) (Math.random()*obstacles.length);
        board.getJFrame().addKeyListener(ball);
        while(gameOn) {
            board.clear();
            ball.draw();
            obstacles[index].drawOb();
            obstacles[index].move();
            board.repaint();
            if(ball.ballOn) {
                //sound.playSound(boing);
                board.clear();
                ball.draw();
                obstacles[index].drawOb();
                obstacles[index].move();
                ball.move();
                board.clear();
                ball.draw();
                obstacles[index].drawOb();
                obstacles[index].move();
                board.repaint();
                for(int i = 0; i < 10; i++) {
                    obstacles[index].drawOb();
                    obstacles[index].move();
                    ball.draw();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) { }
                    board.clear();
                    board.repaint();
                }
                ball.moveBack();
                board.clear();
                ball.draw();
                obstacles[index].drawOb();
                obstacles[index].move();
                board.repaint();
                ball.ballOn = false;
            }
            ball.isHit();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
            if(obstacles[index].getX()<board.getX()) {
            	index = (int) (Math.random()*obstacles.length);
            	obstacles[index].drawOb();
                obstacles[index].move();
                board.repaint();
            }
        }
    }
    
    public static int getIndex() {
    	return index;
    }

}