import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Item 
{
	private int x, y, dx, radius;
	private StartingPoint sp;
	private boolean createNew = false;
	
	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}
	
	public boolean isCreateNew() {
		return createNew;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public StartingPoint getSp() {
		return sp;
	}

	public void setSp(StartingPoint sp) {
		this.sp = sp;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	public Item(int x) 
	{
		this.x = x;
		Random r = new Random();
		radius = 10;
		y = r.nextInt(400) + radius;
		dx = -2;
	}
	
	public void update(StartingPoint sp, Ball b)
	{
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		if(x < 0 - radius)
		{
			createNew = true;
		}
	}
	
	private void checkForCollision(Ball b)
	{
		int ballX = b.getX();
		int ballY = b.getY();
		int ballR = b.getRadius();
		
		int a = x - ballX;
		int bb = y - ballY;
		int collide = radius + ballR;
		// distance between objects centers
		double c = Math.sqrt(((double)a*a) + ((double)bb*bb) );
		
		if(c < collide)
		{
			performAction(b);
			createNew = true;
		}
	}
	
	public void performAction(Ball b) 
	{
		
	}

	public void paint(Graphics g)
	{
		//g.setColor(Color.RED);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
	
}
