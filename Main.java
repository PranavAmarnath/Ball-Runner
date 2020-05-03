public class Main {

    public static boolean gameOn = true;
    public static int score;
    public static int ball_x = 20;
    public static int ball_y = 700;
    private static int steps = 20;
    public static DrawingBoard board = new DrawingBoard(1920, 1000);
    public static Ball ball = new Ball(ball_x, ball_y, 20, 10);

    public static void main(String[] args) {
        board.getJFrame().addKeyListener(ball);
        board.clear();
        ball.draw();
        run();
    }

    public static void run() {
        board.getJFrame().addKeyListener(ball);
        for (int i = 0; i < steps; i++) {
            ball.move();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
            board.clear();
            ball.draw();
        }
        board.repaint();
        for (int i = 0; i < steps; i++) {
            ball.moveBack();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
            board.clear();
            ball.draw();
        }
        board.repaint();
    }

}