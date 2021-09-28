import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullet extends GameObject
{
	private Handler handler;
private Random randy = new Random();
	public Boss1Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = (randy.nextInt(3- -3)+3);
		velY= randy.nextInt(5)+1;
		this.handler = handler;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		if(y==Game.HEIGHT)
			handler.removeObject(this);
	//	if(y <=0||y>=Game.HEIGHT-65)
		//	velY*=-1;
		if(x <=0||x>=Game.WIDTH-65)
			velX*=-1;
		handler.addObject(new Trail(x,y,ID.trail,Color.yellow,30,30,.05f,handler));
	}

	
	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, 20, 20);
		
	}

}
