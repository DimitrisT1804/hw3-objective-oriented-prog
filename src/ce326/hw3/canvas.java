package ce326.hw3;

/* A class that represents the board of the game with 7x6 dimensions */
public class canvas 
{
	// for the game create a 2d array char to represent the canvas, X is the player and O the AI
	char[][] array = new char[7][6];
	int flag = 0;
	
	// Constructor with default ' ' in each pos
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
	
	// find the row that should add for specific column
	public int insertPlayer(int a)
	{
		int pos = -100;
		for(int i = 5; i >= 0; i--)
		{
			if(array[a][i] != 'X' && array[a][i] != 'O' )
			{
				array[a][i] = 'X';
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	// find the row that should add for specific column
	public int insertAI(int a)
	{
		int pos = -100;
		if(isValid(a))
		{
			for(int i = 5; i >= 0; i--)
			{
				if(array[a][i] != 'O' && array[a][i] != 'X')
				{
					array[a][i] = 'O';
					pos = i;
					break;
				}
			}
			
		}
		return pos;
	}
	
	// remove the move for specific column
	public void removeMove(int a)
	{
		int pos = 0, i;
		for(i = 5; i >= 0; i--)
		{
			pos = i;
			if(array[a][i] == ' ')
			{
				pos = i;
				break;
			}
		}
		if(i == -1)
			array[a][0] = ' ';
		else
			array[a][pos+1] = ' ';
	}
	
	// checks if a move is valid, or if the column is full
	public boolean isValid(int a)
	{
		int i;
		for(i = 5; i >= 0; i--)
		{
			if(array[a][i] != 'O' && array[a][i] != 'X')
			{
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
	
	// check if there is any winner which means there is 4 in a row or vertical or diagonal
	public int checkWin() 
	{
	    int yellows = 0, reds = 0;

	    // Check rows
	    for (int i = 0; i < 7; i++) 
	    {
	        for (int j = 0; j < 6 - 3; j++) 
	        {
	            yellows = 0;
	            reds = 0;
	            for (int k = j; k < j + 4; k++) 
	            {
	                if (array[i][k] == 'O')
	                    yellows++;
	                else if (array[i][k] == 'X')
	                    reds++;
	            }
	            
	            if(yellows == 4)
	            	return 1;
	            else if (reds == 4)
	            	return 2;
	        }
	    }


	    // Check columns
	    for (int i = 0; i < 7 - 3; i++) {
	        for (int j = 0; j < 6; j++) {
	            yellows = 0;
	            reds = 0;
	            for (int k = i; k < i + 4; k++) {
	                if (array[k][j] == 'O')
	                    yellows++;
	                else if (array[k][j] == 'X')
	                    reds++;
	            }
	            if(yellows == 4)
	            	return 1;
	            else if (reds == 4)
	            	return 2;
	        }
	    }


	    // Check diagonals
	    for (int i = 0; i < 7 - 3; i++) {
	        for (int j = 0; j < 6 - 3; j++) {
	            yellows = 0;
	            reds = 0;
	            for (int k = 0; k < 4; k++) {
	                if (array[i+k][j+k] == 'O')
	                    yellows++;
	                else if (array[i+k][j+k] == 'X')
	                    reds++;
	            }

	            if(yellows == 4)
	            	return 1;
	            else if (reds == 4)
	            	return 2;
	        }
	    }


	    // Check reverse diagonals
	    for (int i = 0; i < 7 - 3; i++) {
	        for (int j = 3; j < 6; j++) {
	            yellows = 0;
	            reds = 0;
	            for (int k = 0; k < 4; k++) {
	                if (array[i+k][j-k] == 'O')
	                    yellows++;
	                else if (array[i+k][j-k] == 'X')
	                    reds++;
	            }

	            if(yellows == 4)
	            	return 1;
	            else if (reds == 4)
	            	return 2;
	        }
	    }
	    return 0;
	}
	
	// check if there is any winner which means there is 4 in a row or vertical or diagonal: second method
	public int evaluateTwo() 
	{
	    int score = 0;
	    int yellows, reds;

	    // Check rows
	    for (int i = 0; i < 7; i++) 
	    {
	        for (int j = 0; j < 6 - 3; j++) 
	        {
	            yellows = 0;
	            reds = 0;
	            for (int k = j; k < j + 4; k++) 
	            {
	                if (array[i][k] == 'O')
	                    yellows++;
	                else if (array[i][k] == 'X')
	                    reds++;
	            }
	            if (yellows > 0 && reds == 0) 
	            {
	            	if(yellows == 4)
	            		score += 1000;
	            	else
	            		score += Math.pow(4, yellows-1);	// it evaluates for 1: 1 2:4 3:16 4:1000
	            		
	            }
	            else if (reds > 0 && yellows == 0) 
	            {
	            	if(reds ==4)
	            		score -= 1000;
	            	else
	            		score -= Math.pow(4, reds-1);
	            		
	            }
	        }
	    }

	    // Check columns
	    for (int i = 0; i < 7 - 3; i++) 
	    {
	        for (int j = 0; j < 6; j++) 
	        {
	            yellows = 0;
	            reds = 0;
	            for (int k = i; k < i + 4; k++) 
	            {
	                if (array[k][j] == 'O')
	                    yellows++;
	                else if (array[k][j] == 'X')
	                    reds++;
	            }
	            if (yellows > 0 && reds == 0) 
	            {
	            	if(yellows == 4)
	            		score += 1000;
	            	else
	            		score += Math.pow(4, yellows-1);	// it evaluates for 1: 1 2:4 3:16 4:1000
	            		
	            }
	            else if (reds > 0 && yellows == 0) 
	            {
	            	if(reds ==4)
	            		score -= 1000;
	            	else
	            		score -= Math.pow(4, reds-1);
	            		
	            }
	        }
	    }

	    // Check diagonals
	    for (int i = 0; i < 7 - 3; i++) 
	    {
	        for (int j = 0; j < 6 - 3; j++) 
	        {
	            yellows = 0;
	            reds = 0;
	            for (int k = 0; k < 4; k++) 
	            {
	                if (array[i+k][j+k] == 'O')
	                    yellows++;
	                else if (array[i+k][j+k] == 'X')
	                    reds++;
	            }
	            if (yellows > 0 && reds == 0) 
	            {
	            	if(yellows == 4)
	            		score += 1000;
	            	else
	            		score += Math.pow(4, yellows-1);	// it evaluates for 1: 1 2:4 3:16 4:1000
	            		
	            }
	            else if (reds > 0 && yellows == 0) 
	            {
	            	if(reds ==4)
	            		score -= 1000;
	            	else
	            		score -= Math.pow(4, reds-1);
	            		
	            }
	        }
	    }

	    // Check reverse diagonals
	    for (int i = 0; i < 7 - 3; i++) 
	    {
	        for (int j = 3; j < 6; j++) 
	        {
	            yellows = 0;
	            reds = 0;
	            for (int k = 0; k < 4; k++) 
	            {
	                if (array[i+k][j-k] == 'O')
	                    yellows++;
	                else if (array[i+k][j-k] == 'X')
	                    reds++;
	            }
	            if (yellows > 0 && reds == 0) 
	            {
	            	if(yellows == 4)
	            		score += 1000;
	            	else
	            		score += Math.pow(4, yellows-1);	// it evaluates for 1: 1 2:4 3:16 4:1000
	            		
	            }
	            else if (reds > 0 && yellows == 0) 
	            {
	            	if(reds ==4)
	            		score -= 1000;
	            	else
	            		score -= Math.pow(4, reds-1);
	            		
	            }
	        }
	    }
	    return score;
	}
	
	// get the amount of the available cells for specific column
	public int getAvailableCells(int a)
	{
		int count = 0;
		for(int i = 0; i < 6; i++)
		{
			if(array[a][i] == ' ')
			{
				count++ ;
			}
		}
		
		return count;
	}
	
	// clear the board to reset
	public void clear()
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				array[i][j] = ' ';
			}
		}	
	}
	
}
