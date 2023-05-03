package hw3_package;
import java.util.*;

public class hw3_main 
{
	public static void main (String []args)
	{
		char array[][];
		array = new char[7][6];
		int a, b;
		
		String whoPlays = new String("player");
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				array[i][j] = ' ';
			}
		}
		
		while(true)
		{		
			Scanner sc = new Scanner(System.in);
			a = sc.nextInt();
			if(a > 6)
				break;
			
			switch (whoPlays)
			{
				case "player":
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
					whoPlays = "AI";
					
					break;
				}
					
					case "AI":
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
						whoPlays = "player";
						
						break;
					}
					
					default:
					{
						break;
					}
				}
					
			}
			
			
			
//			for(int i = 5; i >= 0; i--)
//			{
//				if(array[a][i] != 'X')
//				{
//					array[a][i] = 'X';
//					break;
//				}
//			}
//			
//			//esto oti O einai ta poulia tou AI kai X tou paikti
//			
//			for (int i = 0; i < 6; i++)
//			{
//				for (int j = 0; j < 7; j++)
//				{
//					System.out.print("| " + array[j][i] + " ");
//				}
//				System.out.println("|");
//				System.out.println("-----------------------------");
//			}						
		}
	}

