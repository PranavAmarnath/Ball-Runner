import java.awt.*;

public class ObstacleManager implements Runnable{

    private int obstacle_height = 100;
    private int obstacle_width = obstacle_height/2;
    Obstacle obstacle = new Obstacle(800, 350, obstacle_width, obstacle_height);
    
    
    //obstacles[1] = new Obstacle(800, 350, obstacle_width, obstacle_height);


    public void run() {
        obstacle.drawOb();
    }

}