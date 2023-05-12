package hw3_package;

public class canvas 
{
	char[][] array = new char[7][6];
	
	
	public canvas()
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				array[i][j] = ' ';
			}
		}
	}
	
	public int evaluate(int evaluationValue)
	{	
		int yellows = 0, red = 0;
		//int evaluationValue = 0;

		
		
		
		//for(int j = 0; j < 4; j++)
		//{
			//System.out.println(array_4);
		//}
			
		// new try
		for(int k = 5; k >= 0; k--)
		{	
			for (int i = 0; i < 4; i++)
			{
				for (int j = i; j < i+4; j++)
				{
					
					if(array[j][k] == 'O')
						yellows = yellows + 1;
					else if (array[j][k] == 'X')
						red = red + 1;
					
					//System.out.print(array[j][k]);
					
				}
				if(yellows != 0 && red != 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
					//System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
				else
				{					
					if(red == 4 && yellows == 4)
						evaluationValue = evaluationValue + 2000;
					else
					{											
						if (yellows == 1)
							evaluationValue = evaluationValue + 1;
						else if (yellows == 2)
							evaluationValue = evaluationValue + 4;
						else if (yellows == 3)
							evaluationValue = evaluationValue + 16;
						else if (yellows == 4)
							evaluationValue = evaluationValue + 1000;
						
						if (red == 1)
							evaluationValue = evaluationValue - 1;
						else if (red == 2)
							evaluationValue = evaluationValue - 4;
						else if (red == 3)
							evaluationValue = evaluationValue - 16;
						else if (red == 4)
							evaluationValue = evaluationValue - 1000;
					}
					
				}
					//System.out.println("Only empty on line");
				
				
				//System.out.println(evaluationValue);
				yellows = 0;
				red = 0;
			}
		}
		
		// katakorifa
		for(int k = 6; k >= 0; k--)
		{	
			for (int i = 0; i < 3; i++)
			{
				for (int j = i; j < i+4; j++)
				{
					
					if(array[k][j] == 'O')
						yellows = yellows + 1;
					else if (array[k][j] == 'X')
						red = red + 1;
					
					//System.out.print(array[k][j]);
					
				}
				
				if(yellows != 0 && red != 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
					//System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
				else
				{											
					if (yellows == 1)
						evaluationValue = evaluationValue + 1;
					else if (yellows == 2)
						evaluationValue = evaluationValue + 4;
					else if (yellows == 3)
						evaluationValue = evaluationValue + 16;
					else if (yellows == 4)
						evaluationValue = evaluationValue + 1000;
					
					if (red == 1)
						evaluationValue = evaluationValue - 1;
					else if (red == 2)
						evaluationValue = evaluationValue - 4;
					else if (red == 3)
						evaluationValue = evaluationValue - 16;
					else if (red == 4)
						evaluationValue = evaluationValue - 1000;
				}
					//System.out.println("Only empty on line");
				
				
				//System.out.println(evaluationValue);
				yellows = 0;
				red = 0;
			}
		}
		
		for(int k = 0; k < 3; k++) 
		{	
		    // Check diagonals starting from the top row
		    for (int i = 0; i < 4; i++) 
		    {
		        for (int j = 0; j < 4; j++)
		        {
		            int row = k + j;
		            int col = i + j;
		            if(row < 7 && col < 6)
		            {		            	
		            	if (array[row][col] == 'O')
		            		yellows++;
		            	else if (array[row][col] == 'X')
		            		red++;
		            }
		        }
				if(yellows != 0 && red != 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
					//System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
				else
				{										
					if (yellows == 1)
						evaluationValue = evaluationValue + 1;
					else if (yellows == 2)
						evaluationValue = evaluationValue + 4;
					else if (yellows == 3)
						evaluationValue = evaluationValue + 16;
					else if (yellows == 4)
						evaluationValue = evaluationValue + 1000;
					
					if (red == 1)
						evaluationValue = evaluationValue - 1;
					else if (red == 2)
						evaluationValue = evaluationValue - 4;
					else if (red == 3)
						evaluationValue = evaluationValue - 16;
					else if (red == 4)
						evaluationValue = evaluationValue - 1000;
				}
				yellows = 0;
				red = 0;
		    }
		}
		
		for(int k = 3; k < 6; k++) 
		{	
		    // Check diagonals starting from the bottom row
		    for (int i = 3; i < 7; i++) 
		    {
		        for (int j = 0; j < 4; j++)
		        {
		            int row = k - j;
		            int col = i - j;
		            if(row < 7 && col < 6)
		            {		            	
		            	if (array[row][col] == 'O')
		            		yellows++;
		            	else if (array[row][col] == 'X')
		            		red++;
		            }
		        }
				if(yellows != 0 && red != 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
					//System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
				{
					yellows = 0;
					red = 0;
					break;
				}
				else
				{					
				
					if (yellows == 1)
						evaluationValue = evaluationValue + 1;
					else if (yellows == 2)
						evaluationValue = evaluationValue + 4;
					else if (yellows == 3)
						evaluationValue = evaluationValue + 16;
					else if (yellows == 4)
						evaluationValue = evaluationValue + 1000;
					
					if (red == 1)
						evaluationValue = evaluationValue - 1;
					else if (red == 2)
						evaluationValue = evaluationValue - 4;
					else if (red == 3)
						evaluationValue = evaluationValue - 16;
					else if (red == 4)
						evaluationValue = evaluationValue - 1000;
					
				}
				yellows = 0;
				red = 0;
		    }
		}
		        
		
		//System.out.println("The value clear is: " + evaluationValue);
		return evaluationValue;
	}
	
	public void insertPlayer(int a)
	{
		for(int i = 5; i >= 0; i--)
		{
			if(array[a][i] != 'X' && array[a][i] != 'O' )
			{
				array[a][i] = 'X';
				break;
			}
		}
		
		//esto oti O einai ta poulia tou AI kai X tou paikti
		//System.out.println("Player");
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
	}
	
	public void insertAI(int a)
	{
		for(int i = 5; i >= 0; i--)
		{
			if(array[a][i] != 'O' && array[a][i] != 'X')
			{
				array[a][i] = 'O';
				break;
			}
		}
		
		//System.out.println("AI");
		//esto oti O einai ta poulia tou AI kai X tou paikti
		
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
	}
	
	public void removeMove(int a)
	{
		int pos = 0, i;
		for(i = 5; i >= 0; i--)
		{
			pos = i;
			if(array[a][i] == ' ')
			{
				//System.out.println(array[a][i]);
				pos = i;
				break;
			}
		}
//		if (pos == 5)
//		{
//			array[a][pos] = ' ';
//		}
//		else if(pos != 0)
//			array[a][pos+1] = ' ';
//		else
//			array[a][pos] = ' ';
		if(i == -1)
			array[a][0] = ' ';
		else
			array[a][pos+1] = ' ';
		
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
	}
	
	public boolean isValid(int a)
	{
		int i;
		for(i = 5; i >= 0; i--)
		{
			if(array[a][i] != 'O' && array[a][i] != 'X')
			{
				//array[a][i] = 'O';
				break;
			}
		}
		
		if (i >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
}
