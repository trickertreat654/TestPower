import java.awt.Graphics;
import java.util.LinkedList;

public class Handler
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public void tick() 
	{
		for(int i =0;i<object.size();i++) 
		{
			GameObject tempObject = object.get(i);
			if(object.get(i).getID()==ID.wall) 
			{
				for(int j=0;j<object.size();j++) 
				{
					
					if(object.get(j).getID()==ID.wall&&j!=i)
					{
				object.remove(object.get(j));
					}
				}
			}
			tempObject.tick();
		}
			
	}
	public void render(Graphics g) 
	{
		for(int i=0;i<object.size();i++) 
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	public void clearEnemies() 
	{
		
		for(int i=0;i<object.size();i++) 
		{
			
			if(object.get(i).getID()==ID.wall) 
			{
				//object.remove(object.get(i));
				
			}
				//if(i!=object.size()) 
			//{
			else if(object.get(i).getID()==ID.mazeRunner) 
			{
				MazeRunner tempMazeRunner = (MazeRunner) object.get(i);
				tempMazeRunner.log();
				removeObject(object.get(i));
				MazeRunner.number--;
			//}
			}
			
				
		}
	}
	public void addObject(GameObject object) 
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object) 
	{
		this.object.remove(object);
	}
}
