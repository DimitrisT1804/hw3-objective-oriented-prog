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
		canvas newCanvas = new canvas();
//		kati.evaluate();
		//Tree newTree = new Tree(3);
		Tree newTree;
		//System.out.println(newTree.toDOTString());
//		
		String Filepath = ("C:\\Users\\jimar\\Desktop\\Uni\\6th semester\\Objective Programming\\hw3-objective-oriented-prog\\test.dot");
//		
		file = new File(Filepath);
//		try {
//			newTree.toFile(file);
//		} catch (TreeExceptions e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//		newTree.toDotFile(file);
//	} catch (TreeExceptions e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

		
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
					
					System.out.println("Player");
					for (int i = 0; i < 6; i++)
					{
						for (int j = 0; j < 7; j++)
						{
							System.out.print("| " + kati.array[j][i] + " ");
						}
						System.out.println("|");
						System.out.println("-----------------------------");
					}
					
					
					
					
					
					
					
					newTree = new Tree(5);
					newCanvas = kati;
					newTree.addEvaluation(newTree.root, newCanvas, kati);
					
					//kati.insertAI(a);
					//kati.removeMove(a);
					whoPlays = "player";
//					for (int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < 7; j++)
//						{
//							System.out.print("| " + newCanvas.array[j][i] + " ");
//						}
//						System.out.println("|");
//						System.out.println("-----------------------------");
//					}
					newTree.minMax();
					newTree.isMinMax = true;
					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
					optPath = newTree.optimalPath();
					
					b = optPath.get(0);
					System.out.println("Correct move is: "+b+optPath);
					kati.insertAI(b);
					
					System.out.println("AI");
					for (int i = 0; i < 6; i++)
					{
						for (int j = 0; j < 7; j++)
						{
							System.out.print("| " + kati.array[j][i] + " ");
						}
						System.out.println("|");
						System.out.println("-----------------------------");
					}
					
					//evaluationValue = kati.evaluate(evaluationValue);
					//System.out.println("The values is " + evaluationValue);
					
					break;
				}
//					
//				case "AI":
//				{
//					newTree = new Tree(5);
//					newCanvas = kati;
//					newTree.addEvaluation(newTree.root, newCanvas, kati);
//					
//					//kati.insertAI(a);
//					//kati.removeMove(a);
//					whoPlays = "player";
////					for (int i = 0; i < 6; i++)
////					{
////						for (int j = 0; j < 7; j++)
////						{
////							System.out.print("| " + newCanvas.array[j][i] + " ");
////						}
////						System.out.println("|");
////						System.out.println("-----------------------------");
////					}
//					newTree.minMax();
//					newTree.isMinMax = true;
//					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
//					optPath = newTree.optimalPath();
//					
//					b = optPath.get(0);
//					System.out.println("Correct move is: "+b+optPath);
//					kati.insertAI(b);
//					
//					System.out.println("AI");
//					for (int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < 7; j++)
//						{
//							System.out.print("| " + kati.array[j][i] + " ");
//						}
//						System.out.println("|");
//						System.out.println("-----------------------------");
//					}
//					
//					//evaluationValue = kati.evaluate(evaluationValue);
//					//System.out.println("The values is " + evaluationValue);
//					
////					try {
////						newTree.toDotFile(file);
////					} catch (TreeExceptions e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//					
//					break;
//				}
//				
//				default:
//				{
//					//kati.evaluate(evaluationValue);
//					break;
//				}
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

