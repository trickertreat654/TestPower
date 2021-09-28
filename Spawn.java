import java.util.ArrayList;
import java.util.Random;

public class Spawn
{
	private Handler handler;
	private Random randy = new Random();
	private HUD hud;
	private Game game;
	public static int scoreKeep = 0;
	private boolean start = true;

	public static int curve = 10;
	public static MazeRunner model;
	public static MazeRunner[] modelList;
	public static MazeRunner modelOld;
	public static ArrayList<MazeRunner> record = new ArrayList<MazeRunner>();
	public Spawn(Handler handler,HUD hud,Game game) 
	{
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	public void tick() 
	{
		if(start) 
		{
			//for(int i=10;i<=50;i+=5)
			//	handler.addObject(new Wall(i%6*50,i%6*50,ID.wall,handler));
			handler.addObject(new Wall(500,100,ID.wall,handler));
			handler.addObject(new Wall(900,100,ID.wall,handler));
			for(int i=0;i<50;i++)
				handler.addObject(new MazeRunner(randy.nextInt(200)+50,randy.nextInt(200)+550,ID.mazeRunner,handler));
			start=false;
		}

		scoreKeep++;
		if(scoreKeep>=300)
		{
			modelList = null;

			hud.setLevel(hud.getLevel()+1);
			scoreKeep=0;

			score();
			for(int i=0;i<modelList.length;i++)
				System.out.printf("\n\nMODEL%d GENERATION%d\n%s\n\n",i,hud.getLevel(), modelList[i]);
			//System.out.printf("\n\n\n\n\nMODEL\n%s\n\n\n\n\n\n\n\n", model);

			//for(int i=10;i<=50;i+=5)
			//handler.addObject(new Wall(i%6*50,i%6*50,ID.wall,handler));
			handler.clearEnemies();

			boolean pick = true;
			for(int i=0;i<modelList.length;i++)
			{
				if(modelList[i]==null)
				{
					pick=false;
					i=modelList.length;
				}
				else
					pick=true;
			}


			if(!pick)	
			{			
				if(record.size()>=500) 
				{
					for(int i=record.size()-500;i<record.size();i++)
						handler.addObject(new MazeRunner(randy.nextInt(200)+50,randy.nextInt(200)+550,ID.mazeRunner,mutate(record.get(i)),handler));
				}
				else
				{
					for(int i=0;i<50;i++)
						handler.addObject(new MazeRunner(randy.nextInt(200)+50,randy.nextInt(200)+550,ID.mazeRunner,handler));
				}
			}
			else
			{
				for(int k = 0,j=modelList.length; k<modelList.length;k++,j--) 
				{
					
					if(modelList[k].getScore()>300)
						
					for(int i=0;i<j*25;i++) 
						handler.addObject(new MazeRunner(randy.nextInt(200)+50,randy.nextInt(200)+550,ID.mazeRunner,mutate(modelList[k]),handler));
				}	

				if(record.size()>=100) 
				{
					//for(int i=record.size()-100;i<record.size();i++)
					//handler.addObject(new MazeRunner(900,250,ID.mazeRunner,mutate(record.get(i).getBrain()),handler));
				}
			}
			//	handler.addObject(new Wall((float)randy.nextInt(750),(float)randy.nextInt(550)+200,ID.wall,handler));
		}


	}
	public void score() 
	{
		float[] max = {0,0,0,0,0,0,0,0,0,0};
		MazeRunner[] tempModelList = new MazeRunner[10];
		MazeRunner tempModel = null;
		if(record.size()>=1500) 
		{
			for(int i = record.size()-1500;i<record.size();i++) 
			{
				for(int k=0;k<max.length;k++) 
				{

					if(record.get(i).getScore()>max[k]) 
					{
						tempModel = record.get(i);
						max[k] = tempModel.getScore();
						tempModelList[k] = tempModel;
						k=max.length;
					}
				}
			}
			for(int i = 0; i<tempModelList.length;i++) 
			{
				for(int k=tempModelList.length-1; k>i;k--) 
				{
					if(tempModelList[i].getScore()<tempModelList[k].getScore())
					{
						int swap = tempModelList[i].getScore();	
						tempModelList[i].setScore(tempModelList[k].getScore());
						tempModelList[k].setScore(swap);
					}

				}
			}
			modelList = tempModelList;
		}
		else
		{
			for(int i = 0;i<record.size();i++) 
			{
				for(int k=0;k<max.length;k++) 
				{

					if(record.get(i).getScore()>max[k]) 
					{
						tempModel = record.get(i);
						max[k] = tempModel.getScore();
						tempModelList[k] = tempModel;
						k=max.length;
					}
				}
			}
			for(int i = 0; i<tempModelList.length;i++) 
			{
				for(int k=tempModelList.length-1; k>i;k--) 
				{
					if(tempModelList[i].getScore()<tempModelList[k].getScore())
					{
						int swap = tempModelList[i].getScore();	
						tempModelList[i].setScore(tempModelList[k].getScore());
						tempModelList[k].setScore(swap);
					}

				}
			}
			modelList = tempModelList;
		}
	}
	public Neuron[] mutate(MazeRunner parent) 
	{
		Neuron[] tempBrain;
		if(randy.nextInt(20)+1==1)
		{
			if(parent.getBrain().length<5)
			tempBrain = new Neuron[parent.getBrain().length+1];
			else
				tempBrain = new Neuron[parent.getBrain().length];
		}
		else if(randy.nextInt(20)+1==2) 
		{
			if(parent.getBrain().length>1)
				tempBrain = new Neuron[parent.getBrain().length-1];
			else
				tempBrain = new Neuron[parent.getBrain().length];
		}
		else 
		{
			tempBrain = new Neuron[parent.getBrain().length];
		}
		Neuron[] parentBrain = parent.getBrain();
		for(int i = 0; i<tempBrain.length;i++)
		{
			if(i>=parentBrain.length)
				tempBrain[i] = new Neuron(randy.nextInt(5)+1,randy.nextInt(4)+1,randy.nextInt(4)+1,randy.nextInt(5)+1,parent);
			else
				tempBrain[i] = new Neuron(parentBrain[i].getWeight(),parentBrain[i].getReaction1(),parentBrain[i].getReaction2(),parentBrain[i].getPass(),parent);
		}

		for(int i = 0; i<tempBrain.length;i++) 
		{
			if(i<parentBrain.length) 
			{
				if(randy.nextInt(10)==1)
				{
					if(tempBrain[i].getReaction1()<4||tempBrain[i].getReaction1()==1)
						tempBrain[i].setReaction1(tempBrain[i].getReaction1()+1);
					else
						tempBrain[i].setReaction1(tempBrain[i].getReaction1()-1);
				}
				if(randy.nextInt(10)==2)
				{
					if(tempBrain[i].getReaction2()<4||tempBrain[i].getReaction2()==1)
						tempBrain[i].setReaction2(tempBrain[i].getReaction2()+1);
					else
						tempBrain[i].setReaction2(tempBrain[i].getReaction2()-1);
				}
				if(randy.nextInt(10)==3)
				{
					if(tempBrain[i].getReaction1()<4||tempBrain[i].getReaction1()==1)
						tempBrain[i].setReaction1(tempBrain[i].getReaction1()+1);
					else
						tempBrain[i].setReaction1(tempBrain[i].getReaction1()-1);
					if(tempBrain[i].getReaction2()<4||tempBrain[i].getReaction2()==1)
						tempBrain[i].setReaction2(tempBrain[i].getReaction2()+1);
					else
						tempBrain[i].setReaction2(tempBrain[i].getReaction2()-1);
				}
				switch(randy.nextInt(2)) 
				{
				case 0: tempBrain[i].setWeight(parentBrain[i].getWeight()+randy.nextInt(2));break;
				case 1: tempBrain[i].setWeight(parentBrain[i].getWeight()-randy.nextInt(2));break;
				}
				switch(randy.nextInt(2)) 
				{
				case 0: tempBrain[i].setPass(parentBrain[i].getPass()+randy.nextInt(2));break;
				case 1: tempBrain[i].setPass(parentBrain[i].getPass()-randy.nextInt(2));break;
				}
			}
		}
		return tempBrain;
	}
	public void setModel(MazeRunner winner) 
	{
		model = new MazeRunner(500,50,ID.mazeRunner,winner.getBrain(),handler);
	}
	public int getScoreKeep() 
	{
		return scoreKeep;
	}
}
