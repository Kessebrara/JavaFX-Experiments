import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.stage.WindowEvent;
import java.util.Random;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.input.MouseEvent;


public class NextGen extends Application
{
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    Random random = new Random();
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    
    Bullet[] bullets = new Bullet[1024];
    int bulletCount = 0;
    
    static long lastNanoTime = System.nanoTime();
    
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        
        stage.setTitle("Incredible Next Gen Title");
        
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e){}
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String key = e.getCode().toString();
                input.remove(key);
            }
        });
        scene.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                double rotation = Math.atan2(e.getY() - 300, e.getX() - 400);
                bullets[bulletCount] = new Bullet(400, 300, rotation, 400);      
                bulletCount++;        
                
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
            }
        });
        
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        Font font = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        g.setFont(font);
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                
                double fps = 1.0 / elapsedTime;
                

                // handle input
                
                // game logic
                
                // collision detection            
                
                // render 
                g.setFill(Color.DARKGRAY);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                
                g.setFill(Color.RED);
                for(int i = 0; i < bulletCount; i++)
                {
                    bullets[i].process(elapsedTime);
                    bullets[i].draw(g);
                }
                
                g.fillText("Elapsed Time = "+elapsedTime, 16, 16);
                g.strokeText("Frames Per Second = "+fps, 16, 40);
                
            }
        }.start();

        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent e)
            {
                System.exit(1);
            }
        });
    }
}