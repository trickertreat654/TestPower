import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1 extends GameObject
{
	private Handler handler;
private Random randy = new Random();
private int timer =50;
	public Boss1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX =0;
		velY= 1;
		this.handler = handler;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,200,200);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		/*
		if(y <=0||y>=Game.HEIGHT-65)
			velY*=-1;
			*/
		if(x <=0||x>=Game.WIDTH-200)
			velX*=-1;
			
		if(timer<0) 
		{
			velY=0;
			int spawn = randy.nextInt(100);
			if(spawn==0)
				handler.addObject(new Boss1Bullet(x+100,y+100,ID.boss1Bullet,handler));
		}
			else
			timer--;
		if(timer==0)
			velX=2;
		
		
		//handler.addObject(new Trail(x,y,ID.trail,Color.red,200,200,.05f,handler));
	}

	
	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y, 200, 200);
		
	}

}
