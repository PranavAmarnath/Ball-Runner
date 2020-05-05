public class Main {

    public static boolean gameOn = true;
    public static int score;
    private static final int w = 1728;
    private static final int h = 972;
    public static int ball_x = 50;
    public static int ball_y = (h - 150);
    public static DrawingBoard board;
    public static Ball ball;

    // TODO: Loading Problem at beginning
    // TODO: Need to synchronize spacebar with ballRun();

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 20, 5, board);
        board.getJFrame().addKeyListener(ball);
        board.clear();
        ball.draw();
        board.repaint();
//        ball.ballRun(board);
//        ball.ballRun(board);
//        ball.isCollided = true;
        ball.isHit();
    }

}