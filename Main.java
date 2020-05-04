public class Main {

    public static boolean gameOn = true;
    public static int score;
    public static int ball_x = 50;
    public static int ball_y = 850;
    private static int steps = 40;
    public static DrawingBoard board = new DrawingBoard(1920, 1000);
    public static Ball ball = new Ball(ball_x, ball_y, 20, 5);

    // Loading Problem at beginning -> have to fix
    // Need to synchronize spacebar with ballRun();

    public static void main(String[] args) {
        //board.clear();
        ballRun();
        ballRun();
        ball.isCollided = true;
        ball.isHit();
    }

    public static void ballRun() {
        board.clear();
        ball.draw();
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