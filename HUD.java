import java.awt.Color;
import java.awt.Graphics;

public class HUD 
{
	public static float HEALTH = 200;
	private float greenValue = 255;
	private int score=0;
	private int level=1;
	public void tick()
	{
		
		HEALTH = Game.clamp(HEALTH, 0, 200);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH;
		score++;
		
		
	}
	public void render(Graphics g) 
	{
		/*
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, (int)greenValue,0));
		g.fillRect(15, 15, (int)HEALTH, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		*/
		g.setColor(Color.white);
		g.drawString("Time: "+score, 10, 64);
		g.drawString("Generation: "+level, 10, 80);
		
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	public void setLevel(int level) 
	{
		this.level = level;
	}
	public int getScore() 
	{
		return score;
	}
	public int getLevel() 
	{
		return level;
	}
}
