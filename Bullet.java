import javafx.scene.canvas.GraphicsContext;

public class Bullet
{
    private double x;
    private double y;
    private double rotation;
    private double velocity;
    private double yChange;
    private double xChange;
    
    public Bullet(double x, double y, double rotation, double velocity)
    {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.velocity = velocity;
        xChange = velocity * Math.cos(rotation);
        yChange = velocity * Math.sin(rotation);
    }
    
    public void process(double elapsedTime)
    {
        x+= elapsedTime * xChange;
        y+= elapsedTime * yChange;
        
        if(x < 0)
        xChange = Math.abs(xChange*2/3);
        
        if(x > 800)
        xChange = -Math.abs(xChange*2/3);
        
        if(y < 0)
        yChange = Math.abs(yChange*2/3);
        
        if(y > 600)
        yChange = -Math.abs(yChange*2/3);
        
        xChange*= (1 - .01 * elapsedTime);
        yChange+= 9.8;
    }
    
    public void draw(GraphicsContext g)
    {
        g.fillOval(x - 4, y - 4, 8, 8);
    }
}