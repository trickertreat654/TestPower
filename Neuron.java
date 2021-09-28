
public class Neuron
{
	private int weight;
	private int reaction1;
	private int reaction2;
	private int pass;
	private int info = 0;
	private MazeRunner self;
	public Neuron(int weight, int reaction1, int reaction2, int pass, MazeRunner self) 
	{
		
		this.weight = weight;
		this.reaction1 = reaction1;
		this.reaction2 = reaction2;
		this.pass = pass;
		this.self = self;
	}
	public String toString() 
	{
		return String.format("Weight: %d - Reaction1: %d - Reaction2: %d - Pass: %d - Info: %d",
				weight, reaction1,reaction2,pass,info);
	}
	public void setWeight(int weight) 
	{
		if(weight==0) 
		{
			this.weight = 1;
		}
		else
		this.weight = weight;
	}
	public int getWeight() 
	{
		return weight;
	}
	public void setReaction1(int reaction1) 
	{
		this.reaction1 = reaction1;
	}
	public int getReaction1() 
	{
		return reaction1;
	}
	public void setReaction2(int reaction2) 
	{
		this.reaction2 = reaction2;
	}
	public int getReaction2() 
	{
		return reaction2;
	}
	public void setPass(int pass) 
	{
		if(pass==0)
			this.pass = 1;
		else
		this.pass = pass;
	}
	public int getPass() 
	{
		return pass;
	}
	public void setInfo(int info) 
	{
		this.info = info;
	}
	public int getInfo() 
	{
		return info;
	}
	public void recieve(int i) 
	{
		info = 0;
		if(self.eyes==reaction1)
		{
			if(weight>0)
				weight*=-1;
			
		}
		else
		{
			if(weight<0)
				weight*=-1;
		}
		switch((reaction1)) 
		{
		case 1: info = (int) (i+weight+self.getX());break; 
		case 2: info = (int) (i-weight-self.getX());break; 
		case 3: info = (int) (i*weight*self.getX());break; 
		case 4: info = (int) (i/weight/self.getX());break; 
		}
		send(info);
	}
	public void send(int i) 
	{
		if(self.eyes==reaction2)
		{
			if(weight>0)
				weight*=-1;
			
		}
		else
		{
			if(weight<0)
				weight*=-1;
		}
		switch((reaction2)) 
		{
		case 1: info = (int) (i+weight+self.getY());break; 
		case 2: info = (int) (i-weight-self.getY());break; 
		case 3: info = (int) (i*weight*self.getY());break; 
		case 4: info = (int) (i/weight/self.getY());break;
		}
	}

}
