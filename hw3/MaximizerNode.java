package ce326.hw3;

public class MaximizerNode extends TreeNode
{
	// Constructor
	public MaximizerNode()
	{
		
	}
	
	double getMax()
	{
		double max = super.ChildrenArray[0].getValue();
		for (int i = 0; i < super.ChildrenArray.length; i++)
		{
			if(ChildrenArray[i].visited == 1)	// only if is visited
			{				
				if(ChildrenArray[i].getValue() > max)
				{
					max = ChildrenArray[i].getValue();
				}
			}
		}
		if(super.ChildrenArray.length == 0)
		{
			System.out.println("Error no children");
		}
		return max;
	}
}
