import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class MazeRunner extends GameObject
{
	private Handler handler;
	private Random randy = new Random();
	private int IDNumber;
	public static int number;
	private int score = 1000;
	private ArrayList<MazeRunner> genes = new ArrayList<MazeRunner>();
	private Neuron[] brain;
	private boolean stop;
	public int eyes = 0;
	public MazeRunner(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		createBrain();
		velX = thinkX((int)Wall.spotX);
		if(velX>30)
			velX=(float) randy.nextInt(10);
		else if(velX<-30)
			velX=-(float) randy.nextInt(10);
		velY= thinkY((int)Wall.spotY);
		if(velY>30)
			velY=(float) randy.nextInt(10);
		else if(velY<-30)
			velY=-(float) randy.nextInt(10);
		number++;
		IDNumber = number;
		this.handler = handler;

	}
	public MazeRunner(float x, float y, ID id,Neuron[] brain, Handler handler) {
		super(x, y, id);
		copyBrain(brain);
		velX = thinkX((int)Wall.spotX);
		if(velX>30)
			velX=(float) randy.nextInt(10);
		if(velX<-30)
			velX=-(float) randy.nextInt(10);
		velY= thinkY((int)Wall.spotY);
		if(velY>30)
			velY=(float) randy.nextInt(10);
		if(velY<-30)
			velY=-(float) randy.nextInt(10);
		number++;
		IDNumber = number;
		this.handler = handler;

	}
	public Neuron[] getBrain() 
	{
		return brain;
	}

	private void createBrain() 
	{
		Neuron[] tempBrain = new Neuron[randy.nextInt(5)+1];
		for(int i = 0; i<tempBrain.length;i++)
		{
			tempBrain[i] = new Neuron(randy.nextInt(5)+1,randy.nextInt(4)+1,randy.nextInt(4)+1,randy.nextInt(5)+1,this);
		}
		brain = tempBrain;
	}
	private void copyBrain(Neuron[] parentBrain) 
	{
		Neuron[] tempBrain = new Neuron[parentBrain.length];
		for(int i = 0; i<tempBrain.length;i++)
		{
			tempBrain[i] = new Neuron(parentBrain[i].getWeight(),parentBrain[i].getReaction1(),parentBrain[i].getReaction2(),parentBrain[i].getPass(),this);
		}
		brain = tempBrain;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,5,5);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		/*
		if(Wall.spotX-x<200&&Wall.spotX-x>-200)
			score-=0;
		else
			score+=0;
		if(Wall.spotY-y<200&&Wall.spotY-y>-200)
			score-=0;
		else
			score+=0;
			
		if((Wall.spotX-x<200&&Wall.spotX-x>-200)&&(Wall.spotY-y<200&&Wall.spotY-y>-200))
			score-=100;
		else
			score+=1;
		*/
		
		if(x>500&&x<1100)
			score+=1000;
		else
			score-=1;
		if(y<300)
			score+=x/25;
		else
			score-=1;
			
		if(x>800&&y>100)
			score+=y;
		
		
		
		if(Wall.spotX*velX>=0&&Wall.spotY*velY>=0)
			eyes = 1;
		else if(Wall.spotX*velX>=0)
			eyes = 2;
		else if(Wall.spotY*velY>=0)
			eyes = 3;
		else
			eyes = 4;
		
		
		if(velX==0&&velY==0) 
		{
			if(stop) 
			{
				log();
				handler.removeObject(this);
				stop=false;
			}
			stop=true;
		}
		//||(velX<1&&velX>-1)||(velY<1&&velY>-1)
		if(score<-200)
		{
			log();
			handler.removeObject(this);
			MazeRunner.number--;
		}	
		velX = thinkX((int)Wall.spotX);
		if(velX>30)
			velX=(float) randy.nextInt(10);
		if(velX<-30)
			velX=-(float) randy.nextInt(10);
		velY= thinkY((int)Wall.spotY);
		if(velY>30)
			velY=(float) randy.nextInt(10);
		if(velY<-30)
			velY=-(float) randy.nextInt(10);
		if(y <=0||y>=Game.HEIGHT-65) 
		{
			log();
			handler.removeObject(this);
			velY*=-1;
			score-=10;
		}
		if(x <=0||x>=Game.WIDTH-65) 
		{
			log();
			handler.removeObject(this);
			velX*=-1;
			score-=10;
		}
		//handler.addObject(new Trail(x,y,ID.trail,Color.red,30,30,.05f,handler));
	}
	public void log() 
	{
		Spawn.record.add(this);
	}


	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		switch(brain.length) 
		{
		case 1: g.setColor(Color.red);break;
		case 2: g.setColor(Color.blue);break;
		case 3: g.setColor(Color.green);break;
		case 4: g.setColor(Color.yellow);break;
		case 5: g.setColor(Color.magenta);break;
		}
		
		g.fillRect((int)x,(int)y, 20, 20);

	}
	public float thinkX(int input) 
	{
		int output = input;
		for(int i = 0; i<brain.length;i++) 
		{
			brain[i].recieve(output);
			output = brain[i].getInfo();
		}
		return (float) output;
	}
	public float thinkY2(int input) 
	{
		int output = input;
		for(int i = 0; i<brain.length;i++) 
		{
			brain[i].recieve(output);
			output = brain[i].getInfo();
		}
		return (float) output;
	}


	public float thinkY(int input) 
	{
		int output = input;
		for(int i = brain.length-1; i>=0;i--) 
		{
			brain[i].recieve(output);
			output = brain[i].getInfo();
		}
		return (float) output;
	}

	public String toString() 
	{
		String neuralNetwork = "";
		for(int i = 0; i<brain.length;i++)
			neuralNetwork+=brain[i]+"\n";
		return String.format("Number: %d Score: %d\nX: %.2f - Y: %.2f - VelX: %.2f - VelY: %.2f\nNeural Network\n%s",
				IDNumber,score,x,y,velX,velY,neuralNetwork);
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	public int getScore() 
	{
		return score;
	}
}
