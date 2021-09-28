import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;












public class Menu extends MouseAdapter
{
	private Game game;
	private Handler handler;
	public Menu(Game game,Handler handler) 
	{
		this.game = game;
		this.handler = handler;
	}
	public void mousePressed(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState != Game.STATE.Game)
		if(mouseOver(mx,my,210, 100, 150, 100)) 
		{
			game.gameState = Game.STATE.Game;
			//handler.addObject(new Player(300,300,ID.player, handler));
			//for(int i=0;i<hud.getLevel()*3;i++)
			//handler.addObject(new Boss1((Game.WIDTH/2)-48,0,ID.boss1,handler));
		}
		else if(mouseOver(mx,my,210, 300, 150, 100)) 
		{
			System.exit(0);
		}
		else if(mouseOver(mx,my,200, 200, 150, 100)) 
		{
			game.gameState = Game.STATE.Help;
		}
		else if(game.gameState == Game.STATE.End&&mouseOver(mx,my,100, 100, 100, 100)) 
		{
			game.gameState = Game.STATE.Menu;
		}
		else if(mouseOver(mx,my,100, 100, 100, 100)) 
		{
			game.gameState = Game.STATE.Menu;
		}
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	private boolean mouseOver(int mx, int my,int x,int y, int width,int height) 
	{
		if(mx >= x && mx <= x + width) 
		
			if(my>=y&&my<=y+height)
				return true;
			else 
				return false;
		else 
			return false;
			
	}
	public void render(Graphics g) 
	{
		if(game.gameState == Game.STATE.Menu) 
		{
			
		Font font = new Font("arial",1,50);
		Font font2 = new Font("arial",1,50);
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawString("Menu", 200, 50);
		g.setColor(Color.blue);
		g.setFont(font2);
		g.drawRect(210, 100, 150, 100);
		g.drawString("Play", 210, 150);
		
		g.drawRect(210, 200, 150, 100);
		g.drawString("Help", 210, 250);
		
		g.drawRect(210, 300, 150, 100);
		g.drawString("Exit", 210, 350);
		}
		else if(game.gameState == Game.STATE.Help) 
		{
			Font font3 = new Font("arial",1,50);
			g.setColor(Color.green);
			g.setFont(font3);
			g.drawRect(100, 100, 100, 100);
			g.drawString("Fuck You", 200, 50);
		}
		else if(game.gameState == Game.STATE.End) 
		{
			
			Font font4 = new Font("arial",1,50);
			g.setColor(Color.green);
			g.setFont(font4);
			g.drawRect(100, 100, 100, 100);
			g.drawString("Fuck You", 200, 50);
		}
	}
	public void tick() 
	{
	}
	
}
