package ce326.hw3;

public class canvas 
{
	char[][] array = new char[7][6];
	int flag = 0;
	
	
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
	
	public int insertPlayer(int a)
	{
		int pos = -1;
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
	
	public int insertAI(int a)
	{
		int pos = -1;
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
