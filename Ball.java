import java.awt.Color;
import java.awt.Graphics;

public class Ball 
{
	private double dx = 0;
	private double dy = 0;
	private double gameDy = -75;
	private int radius = 20;
	private int x = 400;
	private int y = 25;
	private double gravity = 15;
	private double energyLoss = 1d;
	private double xFriction = .9d;
	private double dt = .2d;
	private int agility = 3;
	private int maxSpeed = 20;
	
	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Ball() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Ball(int i, int j)
	{
		x = i;
		y = j;
	}
	
	
	public double getGameDy() {
		return gameDy;
	}
	
	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void moveRight()
	{
		if( dx + agility < maxSpeed)
			dx += agility;
	}

	public void moveLeft()
	{
		if( dx - agility > - maxSpeed)
			dx -= agility;
	}
	public void update(StartingPoint sp)
	{
		if( x + dx > sp.getWidth() - radius - 1)
		 {
			 x = sp.getWidth() - radius - 1;
			 dx = - dx;
		 }
		 else if(x + dx < 0 + radius)
		 {
			 x = 0 + radius;
			 dx = - dx;
		 }
		 else x += dx;		
		 
		 if( y == sp.getHeight() - radius - 1)
		 {
			 dx *= xFriction;
			 if(Math.abs(dx) < .8)
				 dx = 0;
		 }
		 
		 if(y > sp.getHeight() - radius -1)
		 {
			 y = sp.getHeight() - radius - 1;
			 dy *= energyLoss;
			 dy = gameDy;
		 }
		 else
		 {	
			 //Velocity formula
			 dy += gravity * dt;
			 // Position Formula
			 y += dy * dt + .5 * gravity * dt * dt;
		 }	
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
}
