package hw3_package;
import java.util.*;

public class hw3_main 
{
	public static void main (String []args)
	{
		char array[][];
		array = new char[7][6];
		int a, b;
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				array[i][j] = ' ';
			}
		}
		
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		
		array[a][b] = 'X';
		//while(true)
		//{			
			//esto oti 1 einai ta poulia tou AI kai 2 tou paikti
			
			for (int i = 0; i < 6; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					System.out.print("| " + array[j][i] + " ");
				}
				System.out.println("|");
				System.out.println("-----------------------------");
			}
		//}
		
		
//		for(int i = 0; i < 7; i++)
//		{
//			for(int j = 0; j < 6; j++)
//			{
//				System.out.println(array[i][j]);
//			}
//		}
	}
}
