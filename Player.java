import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject 
{
private Handler handler;
	public Player(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		Random randy = new Random();


	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,32,32);
	}

	public void tick() 
	{

		x+=velX;
		y+=velY;
		
		if(HUD.HEALTH<=1)
		{
			handler.removeObject(this);
			}
		x= Game.clamp(x, 0, Game.WIDTH-50);
		y= Game.clamp(y, 0, Game.HEIGHT-50);
		handler.addObject(new Trail(x,y,ID.playerTrail,Color.white,32,32,.08f, handler));
		/*
		if(y <=0||y>=Game.HEIGHT-65)
			velY*=-1;
		if(x <=0||x>=Game.WIDTH-65)
			velX*=-1;
		 */
		collition();
	}
	public void collition() 
	{
		for(int i=0;i<handler.object.size();i++) 
		{
			GameObject tempObject= handler.object.get(i);
			if(tempObject.getID() == ID.basicEnemy
					//||tempObject.getID()==ID.trail
					) 
			{
				if(getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=2;
			}
			else if(tempObject.getID()==ID.trail) {
				if(getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=.01;
				
			}
			else if(tempObject.getID()==ID.boss1) {
				if(getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=2;
			}
			else if(tempObject.getID()==ID.boss1Bullet) {
				if(getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=.1;
			}
		}
	}


	public void render(Graphics g) 
	{
		if(id == ID.player) {
			g.setColor(Color.white);
			g.fillRect((int)x,(int) y, 50, 32);
		}
		else {
			g.setColor(Color.red);
			g.fillRect((int)x,(int) y, 50, 32);
		}

	}

}
