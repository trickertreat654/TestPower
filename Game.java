import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = -6112428091888191314L;

	public static final int WIDTH = 1400, HEIGHT = WIDTH/11*6;
			//WIDTH/11*6;
	// WIDTH = 640, HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End
	}
	
	public STATE gameState = STATE.Menu;
	public Game() 
	{
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler) );
		this.addMouseListener(menu);
		
		new Window(WIDTH,HEIGHT,"This is the Game",this);
		hud = new HUD();
		spawner = new Spawn(handler,hud,this);
		
		Random randy = new Random();
		if(gameState == STATE.Game||gameState == STATE.Game) 
		{
			//handler.addObject(new Player(300,300,ID.player, handler));
			//for(int i=0;i<hud.getLevel()*3;i++)
			//handler.addObject(new Boss1((Game.WIDTH/2)-48,0,ID.boss1,handler));
		}	

	}
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() 
	{
		try 
		{
			thread.join();
			running = false;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static float clamp(float var,float min,float max) 
	{
		if(var>=max)
			return var =max;
		else if(var<=min)
			return var = min;
		return var;
		
			
	}
	public void run() 
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();

	}
	private void tick() 
	{
		handler.tick();	
		
		
		if(HUD.HEALTH==0) 
		{
			HUD.HEALTH = 200;
			handler.clearEnemies();
			hud.setLevel(1);
			hud.setScore(0);
			gameState = STATE.End;
		}
			if(gameState == STATE.Game) 
		{
		hud.tick();
	spawner.tick();
		}
		else if(gameState == STATE.Menu||gameState == STATE.Help||gameState == STATE.End)
			menu.tick();
		
	}
	private void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) 
		{
		this.createBufferStrategy(3);
		return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(gameState == STATE.Game) 
		{
		hud.render(g);
		}
		else if(gameState == STATE.Menu||gameState ==STATE.Help||gameState == STATE.End)
			menu.render(g);
		
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) 
	{
		new Game();
	}
}
