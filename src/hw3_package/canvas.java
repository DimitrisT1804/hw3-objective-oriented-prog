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
		char [] array_1 = new char[4];
		char [] array_2 = new char[4];
		char [] array_3 = new char[4];
		char [] array_4 = new char[4];
		
		int yellows = 0, red = 0;
		//int evaluationValue = 0;

		
		array_1[0] = array[0][0];
		array_1[1] = array[1][0];
		array_1[2] = array[2][0];
		array_1[3] = array[3][0];
		
		array_2[0] = array[1][0];
		array_2[1] = array[2][0];
		array_2[2] = array[3][0];
		array_2[3] = array[4][0];
		
		array_3[0] = array[2][0];
		array_3[1] = array[3][0];
		array_3[2] = array[4][0];
		array_3[3] = array[5][0];
		
		array_4[0] = array[3][0];
		array_4[1] = array[4][0];
		array_4[2] = array[5][0];
		array_4[3] = array[6][0];
		
		
		
		//for(int j = 0; j < 4; j++)
		//{
			System.out.println(array_4);
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
					
					System.out.print(array[j][k]);
					
				}
				if(yellows != 0 && red != 0)
					System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
					System.out.println("Only empty on line");
				else if (yellows == 1)
					evaluationValue = evaluationValue + 1;
				else if (yellows == 2)
					evaluationValue = evaluationValue + 4;
				else if (yellows == 3)
					evaluationValue = evaluationValue + 16;
				
				
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
					
					System.out.print(array[k][j]);
					
				}
				if(yellows != 0 && red != 0)
					System.out.println("There is red and yellow on line");
				else if (yellows == 0 && red == 0)
					System.out.println("Only empty on line");
				else if (yellows == 1)
					evaluationValue = evaluationValue + 1;
				else if (yellows == 2)
					evaluationValue = evaluationValue + 4;
				else if (yellows == 3)
					evaluationValue = evaluationValue + 16;
				
				
				//System.out.println(evaluationValue);
				yellows = 0;
				red = 0;
			}
		}
		
		
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
		System.out.println("Player");
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				System.out.print("| " + array[j][i] + " ");
			}
			System.out.println("|");
			System.out.println("-----------------------------");
		}
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
		
		System.out.println("AI");
		//esto oti O einai ta poulia tou AI kai X tou paikti
		
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				System.out.print("| " + array[j][i] + " ");
			}
			System.out.println("|");
			System.out.println("-----------------------------");
		}
	}
	
	public void removeMove(int a)
	{
		int pos = 0;
		for(int i = 5; i >= 0; i--)
		{
			if(array[a][i] == ' ');
			{
				pos = i;
				break;
			}
		}
		array[a][pos] = ' ';
		
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
	
}
