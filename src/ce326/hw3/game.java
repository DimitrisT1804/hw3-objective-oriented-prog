package ce326.hw3;

import java.util.ArrayList;
import java.util.Scanner;

public class game
{
	int difficulty = 5;
	int player = 0;
	canvas kati = new canvas();
	int winCondition = 0;
	int b = -1, d = -1;
	
	
	public game(canvas board)
	{
		kati = board;
	}
	
	public void clear()
	{
		kati.clear();
		b = -1; 
		d = -1;
		winCondition = 0;
	}
	
	public void setDifficulty(int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setPlayer(int player)
	{
		this.player = player;
	}
	
	public canvas getCanvas()
	{
		return kati;
	}
	
	public void insertPlayer(int a)
	{
		if(a > 6)
			System.out.println("ERROR");
		
		kati.insertPlayer(a);
		
//		System.out.println("Player");
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + kati.array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
		
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
	
	
	public void start()
	{
		int a = 0, b = 0;
		
		canvas newCanvas = new canvas();
		TreeAdvanced newTree;

		
		int winCondition;
		
		switch(player)
		{
			case (1):
			{
				while(true)
				{
					Scanner sc = new Scanner(System.in);
					a = sc.nextInt();
					if(a > 6)
						break;
					
					kati.insertPlayer(a);
					
//					System.out.println("Player");
//					for (int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < 7; j++)
//						{
//							System.out.print("| " + kati.array[j][i] + " ");
//						}
//						System.out.println("|");
//						System.out.println("-----------------------------");
//					}
					
					winCondition = kati.checkWin();
					if(winCondition == 1)
					{
						//System.out.println("Winner: AI");
					}
					else if(winCondition == 2)
					{
						//System.out.println("Winner: Player");
					}
					
					
					
					newTree = new TreeAdvanced(difficulty, kati);
					System.out.println("The size of tree is: " + newTree.size());
					newCanvas = kati;
					int pos = -1;
					newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
					

					newTree.minMax();
					newTree.isMinMax = true;
					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
					optPath = newTree.optimalPath();
					
					b = optPath.get(0);
					System.out.println("First move is: "+b+"      ");
					
					if(b >= 0 && b <= 6)
						kati.insertAI(b);
					
					System.out.println("AI");
//					for (int i = 0; i < 6; i++)
//					{
//						for (int j = 0; j < 7; j++)
//						{
//							System.out.print("| " + kati.array[j][i] + " ");
//						}
//						System.out.println("|");
//						System.out.println("-----------------------------");
//					}
	
					winCondition = kati.checkWin();
					if(winCondition == 1)
					{
						//System.out.println("Winner: AI");
					}
					else if(winCondition == 2)
					{
						//System.out.println("Winner: Player");
					}
					
				}
			}
			
			case (2):
			{
				while (true)
				{			
					newTree = new TreeAdvanced(difficulty, kati);
					System.out.println("The size of tree is: " + newTree.size());
					newCanvas = kati;
					int pos = -1;
					newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
					
					
					newTree.minMax();
					newTree.isMinMax = true;
					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
					optPath = newTree.optimalPath();
					
					b = optPath.get(0);
					System.out.println("First move is: "+b+"      ");
					
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
					
					Scanner sc = new Scanner(System.in);
					a = sc.nextInt();
					if(a > 6)
						break;
					
					kati.insertPlayer(a);
					
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
					}
					else if(winCondition == 2)
					{
						System.out.println("Winner: Player");
					}
				}
			}
			
			
		}
		

	}
	
	
	public void playAI()
	{
		TreeAdvanced newTree;
		canvas newCanvas;
		newTree = new TreeAdvanced(difficulty, kati);
		//System.out.println("The size of tree is: " + newTree.size());
		newCanvas = kati;
		int pos = -1;
		newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
		
		
		newTree.minMax();
		newTree.isMinMax = true;
		ArrayList<Integer> optPath = new ArrayList<Integer>(0);
		optPath = newTree.optimalPath();
		
		b = optPath.get(0);
		//System.out.println("First move is: "+b+"      ");
		
		if(b >= 0 && b <= 6)
			d = kati.insertAI(b);
		
//		System.out.println("AI");
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + kati.array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
		
		winCondition = kati.checkWin();
		if(winCondition == 1)
		{
			//System.out.println("Winner: AI");
			return;
		}
		else if(winCondition == 2)
		{
			//System.out.println("Winner: Player");
			return;
		}
		return;
	}
	
	
	public void playPlayer()
	{
		TreeAdvanced newTree;
		canvas newCanvas;
		newTree = new TreeAdvanced(difficulty, kati);
		//System.out.println("The size of tree is: " + newTree.size());
		newCanvas = kati;
		int pos = -1;
		newTree.addEvaluation(newTree.root, newCanvas, kati, pos);
		
		
		newTree.minMax();
		newTree.isMinMax = true;
		ArrayList<Integer> optPath = new ArrayList<Integer>(0);
		optPath = newTree.optimalPath();
		
		b = optPath.get(0);
		//System.out.println("First move is: "+b+"      ");
		
		if(b >= 0 && b <= 6)
			d = kati.insertAI(b);
		
//		System.out.println("AI");
//		for (int i = 0; i < 6; i++)
//		{
//			for (int j = 0; j < 7; j++)
//			{
//				System.out.print("| " + kati.array[j][i] + " ");
//			}
//			System.out.println("|");
//			System.out.println("-----------------------------");
//		}
		
		winCondition = kati.checkWin();
		if(winCondition == 1)
		{
			//System.out.println("Winner: AI");
			return;
		}
		else if(winCondition == 2)
		{
			//System.out.println("Winner: Player");
			return;
		}
	}
	
}
