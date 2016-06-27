import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform 
{
	private int dx;
	private int x, y, width, height;
	Image plat;
	URL url;
	float frame = 0;
	
	public Platform() 
	{
		dx = -1;
		x = 300;
		y = 300;
		width = 120;
		height = 40;
		plat = Pictures.platform;
	}
	
	public Platform(int x, int y)
	{
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = -1; 
		plat = Pictures.platform;
	}
	
	
	public void update(StartingPoint sp, Ball b)
	{
		int tester = (int)(frame + .1);
		if(tester < 3)
			frame += .1;
		else frame = 0;
		
		x += dx;
		checkForCollision(b);
		if( x < 0 - width)
		{
			Random r = new Random(); 
			y = sp.getHeight() - 40 - r.nextInt(400);
			x = sp.getWidth() + r.nextInt(300);
		}
	}
	
	private void checkForCollision(Ball b) 
	{
		int ballX = b.getX();
		int ballY = b.getY();
		int radius = b.getRadius();
		
		if(ballY + radius > this.y && ballY + radius < this.y + this.height)
		{
			if(ballX > this.x && ballX < this.x + this.width)
			{
				double newDY = b.getGameDy();
				b.setY(this.y - radius);
				b.setDy(newDY);
			}
		}
	}



	public void paint(Graphics g)
	{
		g.drawImage(plat, x, y, x + width, y + height, 0, 40 * (int)frame, 120, 40 * (int)frame + 40, Pictures.sp);
	}
}
