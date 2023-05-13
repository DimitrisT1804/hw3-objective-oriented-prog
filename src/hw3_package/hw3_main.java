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
		int a = 0, b = 0;
		File file;
		int evaluationValue = 0;
		
		//String whoPlays = new String("player");
		
		canvas kati = new canvas(); 	// dokimi gia tin klasi tou pinaka
		canvas newCanvas = new canvas();
//		kati.evaluate();
		//Tree newTree = new Tree(3);
		TreeAdvanced newTree;
		//System.out.println(newTree.toDOTString());
//		
		String Filepath = ("C:\\Users\\jimar\\Desktop\\Uni\\6th semester\\Objective Programming\\hw3-objective-oriented-prog\\test.dot");
//		
		file = new File(Filepath);
//		try {
//		newTree.toDotFile(file);
//	} catch (TreeExceptions e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
		int winCondition;
		String whoPlays = new String();

		
		//while(true)
		//{		

			
			Scanner sc = new Scanner(System.in);
			whoPlays = sc.next();

			
			switch (whoPlays)
			{
				case "player":
				{
					while(true)
					{
						a = sc.nextInt();
						if(a > 6)
							break;
						
						kati.insertPlayer(a);
						//whoPlays = "AI";
						
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
						
						//System.out.println("check: "+ kati.getAvailableCells(0));
						
//						winCondition = kati.checkWin();
						winCondition = kati.checkWin();
						if(winCondition == 1)
						{
							System.out.println("Winner: AI");
							return;
						}
						else if(winCondition == 2)
						{
							System.out.println("Winner: Player");
							return;
						}
						
						
						
						int[] fullColumn = new int[6];
						
						
						
						newTree = new TreeAdvanced(5, kati);
						newCanvas = kati;
						int pos = -1;
						newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
						
//					try {
//						newTree.toDotFile(file);
//					} catch (TreeExceptions e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//						
						//kati.insertAI(a);
						//kati.removeMove(a);
						//whoPlays = "player";
//					for (int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < 7; j++)
//						{
//							System.out.print("| " + newCanvas.array[j][i] + " ");
//						}
//						System.out.println("|");
//						System.out.println("-----------------------------");
//					}
						
						ArrayList<Integer> smallList = new ArrayList<Integer>(6);
						
						TreeLeaves NodeMove;
						
						newTree.minMax();
						newTree.isMinMax = true;
						ArrayList<Integer> optPath = new ArrayList<Integer>(0);
						optPath = newTree.optimalPath();
						
						
//					
//					//b = optPath.get(0);
//					
//					List<Integer> firstFive = optPath.subList(0, 5);
//					
//					Integer[] pathArray = firstFive.toArray(new Integer[0]);
//					
//					NodeMove = newTree.getNodeByPath(newTree.root, pathArray);
//					b = NodeMove.move;
						
						b = optPath.get(0);
						System.out.println("First move is: "+b+"      ");
						for(int i = 0; i < 6; i++)
						{
							for (int j = 0; j < newTree.fullColumn.length; j++) 
							{
								if (newTree.fullColumn[j] == b)
								{
									System.out.print(newTree.fullColumn[j]);
									b = b + 1;
									break;
								}
							}	
						}
						
						System.out.println("Correct move is: "+b+optPath);
						if(b >= 0 && b <= 6)
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
						
						//winCondition = kati.checkWin();
						winCondition = kati.checkWin();
						if(winCondition == 1)
						{
							System.out.println("Winner: AI");
							return;
						}
						else if(winCondition == 2)
						{
							System.out.println("Winner: Player");
							return;
						}
						
					try {
						newTree.toDotFile(file);
					} catch (TreeExceptions e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						//evaluationValue = kati.evaluate(evaluationValue);
						//System.out.println("The values is " + evaluationValue);
						
					}
					break;
				}
					
			case "AI":
			{
				while(a != 10 && b != 10)
				{
					
					int[] fullColumn = new int[6];
					
					
					
					
//					newTree = new TreeAdvanced(5, kati, fullColumn);
//					newCanvas = kati;
//					int pos = -1;
//					newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
					
//				try {
//					newTree.toDotFile(file);
//				} catch (TreeExceptions e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
					
					//kati.insertAI(a);
					//kati.removeMove(a);
					//whoPlays = "player";
//				for (int i = 0; i < 6; i++)
//				{
//					for (int j = 0; j < 7; j++)
//					{
//						System.out.print("| " + newCanvas.array[j][i] + " ");
//					}
//					System.out.println("|");
//					System.out.println("-----------------------------");
//				}
					
//					ArrayList<Integer> smallList = new ArrayList<Integer>(6);
//					
//					TreeLeaves NodeMove;
//					
//					newTree.minMax();
//					newTree.isMinMax = true;
//					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
//					optPath = newTree.optimalPath();
					
					
//				
//				//b = optPath.get(0);
//				
//				List<Integer> firstFive = optPath.subList(0, 5);
//				
//				Integer[] pathArray = firstFive.toArray(new Integer[0]);
//				
//				NodeMove = newTree.getNodeByPath(newTree.root, pathArray);
//				b = NodeMove.move;
					
//					b = optPath.get(0);
//					for(int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < fullColumn.length; j++) 
//						{
//							if (fullColumn[j] == b)
//							{
//								b = b + 1;
//								break;
//							}
//						}	
//					}
//					
//					System.out.println("Correct move is: "+b+optPath);
					b = sc.nextInt();
					if(b >= 0 && b <= 6)
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
					
					winCondition = kati.checkWin();
					if(winCondition == 1)
					{
						System.out.println("Winner: AI");
						return;
					}
					else if(winCondition == 2)
					{
						System.out.println("Winner: Player");
						return;
					}
					
					a = sc.nextInt();
					if(a > 6)
						break;
					
					kati.insertPlayer(a);
					//whoPlays = "AI";
					
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
					
					winCondition = kati.checkWin();
					if(winCondition == 1)
					{
						System.out.println("Winner: AI");
						return;
					}
					else if(winCondition == 2)
					{
						System.out.println("Winner: Player");
						return;
					}
					
				}
				//}
				 kati.evaluateTwo();
					
		}
		
	}					
	}
}

