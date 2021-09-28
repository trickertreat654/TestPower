import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Wall extends GameObject
{
	private Handler handler;
	public static float spotX = 0;
	public static float spotY = 0;
private Random randy = new Random();

	public Wall(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		spotX = x;
		spotY = y;
		velX = (float)randy.nextInt(10)-5;
		velY= (float) randy.nextInt(10)-5;
		this.handler = handler;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,100,400);
	}
	
	public void tick()
	{
		//x+=velX;
		//y+=velY;
		spotX=y;
		spotY=x;
				
				//velX
				//velY
		if(y <=0||y>=Game.HEIGHT-65)
			velY*=-1;
		if(x <=0||x>=Game.WIDTH-65)
			velX*=-1;
		//velX = (float)randy.nextInt(10)-5;
		//velY= (float) randy.nextInt(10)-5;
		//handler.addObject(new Trail(x,y,ID.trail,Color.red,30,30,.05f,handler));
		
		//if(Spawn.scoreKeep>=500)
			//handler.removeObject(this);
		collition();
	}
	public void collition() 
	{
		for(int i=0;i<handler.object.size();i++) 
		{
			if(handler.object.get(i) instanceof MazeRunner) 
			{
			MazeRunner tempObject = (MazeRunner) handler.object.get(i);
			if(tempObject.getID() == ID.mazeRunner
					//||tempObject.getID()==ID.trail
					) 
			{
				if(getBounds().intersects(tempObject.getBounds()))
					{
					//tempObject.setScore(tempObject.getScore());				
					tempObject.log();
					handler.removeObject(tempObject);
					
				//	System.out.printf("%s\n",tempObject);
					
					}
			}
			}
		}
	}
	
	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y, 300, 800);
		
	}

}
