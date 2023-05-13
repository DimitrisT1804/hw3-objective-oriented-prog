package hw3_package;

/* This is the class of the tree leaves */

public class TreeLeaves 
{
	private double NodeValue; 		// it is private
	public boolean isPruned = false;
	int move = -2;
	
	// Constructor
	public TreeLeaves()
	{
		
	}
	
	// Constructor that initializes the NodeValue
	public TreeLeaves(double NodeValue)
	{
		this.NodeValue = NodeValue;
	}
	
	/*Because NodeValue must be private, i use this method to get the value of each node */
	public double getValue()
	{
		return NodeValue;
	}
	
	public void SetValue(double NodeValue)
	{
		this.NodeValue = NodeValue;
	}
	
	public void setMove(int newMove)
	{
		move = newMove;
	}
}
