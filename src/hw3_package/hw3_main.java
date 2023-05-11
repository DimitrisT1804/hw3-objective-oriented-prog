package hw3_package;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class hw3_main 
{
	public static void main (String []args)
	{
		char array[][];
		//array = new char[7][6];
		int a, b;
		File file;
		int evaluationValue = 0;
		
		String whoPlays = new String("player");
		
		canvas kati = new canvas(); 	// dokimi gia tin klasi tou pinaka
//		kati.evaluate();
		Tree newTree = new Tree(3);
//		System.out.println(newTree.toDOTString());
//		
//		String Filepath = ("C:/Users/jimar/Desktop/Uni/6th semester/Objective Programming/hw3-objective-oriented-prog/test.txt");
//		
//		file = new File(Filepath);
//		try {
//			newTree.toFile(file);
//		} catch (TreeExceptions e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
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
					kati.insertPlayer(a);
					whoPlays = "AI";
					
					//evaluationValue = kati.evaluate(evaluationValue);
					//System.out.println("The values is " + evaluationValue);
					
					break;
				}
					
				case "AI":
				{
					kati.insertAI(a);
					kati.removeMove(a);
					whoPlays = "player";
					
					//evaluationValue = kati.evaluate(evaluationValue);
					//System.out.println("The values is " + evaluationValue);
					
					break;
				}
				
				default:
				{
					//kati.evaluate(evaluationValue);
					break;
				}
			}
					
		}
		
		evaluationValue = kati.evaluate(evaluationValue);
		System.out.println("The values is " + evaluationValue);
		
		
		
			
			
			
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

