import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private Image i;
	private Graphics doubleG; 
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = .3;
	URL url;
	Image city;
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public void init() 
	{
		setSize(WIDTH, HEIGHT);
		addKeyListener(this);
		try
		{
			url = getDocumentBase();
		}
		catch (Exception e)
		{
			
		}
		city = getImage(url,"Images/World.png");
		Pictures p = new Pictures(this);
	}
	
	@Override
	public void start() 
	{
		score = 0;
		b = new Ball();
		for(int i = 0; i < p.length; i++)
		{
			Random r = new Random();
			p[i] = new Platform(this.getWidth() + 200 * i, this.getHeight() - 40 - r.nextInt(400));
		}
		
		for(int i = 0; i < item.length; i++)
		{
			Random r = new Random();
			 switch(r.nextInt(5))
			 {
			 case 0:
				 item[i] = new GravUp(getWidth() + 2000 * i);
				 break;
			 case 1:
				 item[i] = new GravDown(getWidth() + 2000 * i);
				 break;
			 case 2:
				 item[i] = new AgilUp(getWidth() + 2000 * i);
				 break;
			 case 3:
				 item[i] = new AgilDown(getWidth() + 2000 * i); 
				 break;
			 case 4:
				 item[i] = new ScorePlus(getWidth() + 2000 * i, this); 
				 break;
				 
			 }
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() 
	{
		 while(true)
		 {
			 if(cityX > getWidth() * - 1)
			 {
				 cityX -= cityDx;
			 }
			 else cityX = 0;
			 score++;
			 
			 Random r = new Random();
			 
			 for(int i = 0; i < item.length; i++)
			 {
				 if(item[i].isCreateNew())
				 {
					 item[i] = null;
					 switch(r.nextInt(5))
					 {
					 case 0:
						 item[i] = new GravUp(this.getWidth() + 10 * r.nextInt(500));
						 break;
					 case 1:
						 item[i] = new GravDown(this.getWidth() + 10 * r.nextInt(500));
						 break;
					 case 2:
						 item[i] = new AgilUp(this.getWidth() + 10 * r.nextInt(500));
						 break;
					 case 3:
						 item[i] = new AgilDown(this.getWidth() + 10 * r.nextInt(500)); 
						 break;
					 case 4:
						 item[i] = new ScorePlus(this.getWidth() + 10 * r.nextInt(500), this); 
						 break;
						 
					 }
					 item[i].setCreateNew(false);
					 
				 }
			 }
			 
			 
			 b.update(this);
			 for(int i = 0; i < p.length; i++)
				 p[i].update(this, b);
			 for(int i = 0; i < item.length; i++)
				 item[i].update(this, b);
			 repaint();
			 try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	@Override
	public void stop() 
	{

	}
	
	@Override
	public void destroy() 
	{

	}
	
	@Override
	public void update(Graphics g) 
	{
		if(i == null)
		{	i = createImage(this.getSize().width, this.getSize().height );
			doubleG = i.getGraphics();
		}
		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		g.setColor(new Color(15, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int)cityX, 0, this);
		g.drawImage(city, (int)cityX + getWidth(), 0, this);
		
		for(int i = 0; i < p.length; i++)
			 p[i].paint(g);
		for(int i = 0; i < item.length; i++)
			 item[i].paint(g); 	
		b.paint(g);
		
		String s = Integer.toString(score);
		
		Font font = new Font("Hack", Font.BOLD, 32);
		g.setFont(font);
		
		g.setColor(Color.black);
		g.drawString(s, this.getWidth() - 150 + 2, 50 + 2);
		
		g.setColor(new Color(198, 226, 255));
		g.drawString(s, this.getWidth() - 150, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A:
			b.moveLeft();
			break;
		case KeyEvent.VK_D:
			b.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
