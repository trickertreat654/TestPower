import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private int speed = 10;
	public KeyInput(Handler handler) 
	{
		
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
	}
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) 
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.player)
			{
				if(key == KeyEvent.VK_W ) 
				{
					tempObject.setVelY(-speed);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S )
				{
					tempObject.setVelY(speed);
					keyDown[1] = true;
				}
					;
				if(key == KeyEvent.VK_D )
				{
					tempObject.setVelX(speed);
					keyDown[2] = true;
				}
					
				if(key == KeyEvent.VK_A )
				{
					tempObject.setVelX(-speed);
					keyDown[3] = true;
				}
					

			}
			else if(tempObject.getID() == ID.player2) 
			{
				if(key == KeyEvent.VK_UP ) keyDown[0]=false; 
				//	tempObject.setVelY(tempObject.getVelY()-5);
				if(key == KeyEvent.VK_DOWN ) keyDown[1]= false;
					//tempObject.setVelY((tempObject.getVelY()+5));
				if(key == KeyEvent.VK_RIGHT ) keyDown[2]=false;
					//tempObject.setVelX((tempObject.getVelX()+5));
				if(key == KeyEvent.VK_LEFT ) keyDown[3]=false;
					//tempObject.setVelX((tempObject.getVelX()-5));
			}
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) 
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.player)
			{
				if(key == KeyEvent.VK_W )keyDown[0]=false;
					//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S )keyDown[1]=false;
					//tempObject.setVelY(0);
				if(key == KeyEvent.VK_D )keyDown[2]=false;
					//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A )keyDown[3]=false;
					//tempObject.setVelX(0);
				
				if(!keyDown[0]&&!keyDown[1])tempObject.setVelY(0);
				if(!keyDown[2]&&!keyDown[3])tempObject.setVelX(0);

			}
			else if(tempObject.getID() == ID.player2) 
			{
				if(key == KeyEvent.VK_UP )
					tempObject.setVelY(tempObject.getVelY()+5);
				if(key == KeyEvent.VK_DOWN )
					tempObject.setVelY((tempObject.getVelY()-5));
				if(key == KeyEvent.VK_RIGHT )
					tempObject.setVelX((tempObject.getVelX()-5));
				if(key == KeyEvent.VK_LEFT )
					tempObject.setVelX((tempObject.getVelX()+5));
			}
		}
	}
}
