package ce326.hw3;

import java.util.ArrayList;
import java.util.Scanner;

// class that make all the settings for the game
public class game
{
	int difficulty = 5;
	int player = 0;
	canvas currentBoard = new canvas();
	int winCondition = 0;	// check who is the winner
	int b = -1, d = -2;		// variables that represent the rows and columns
	
	// Constructor
	public game(canvas board)
	{
		currentBoard = board;
	}
	
	// re-initialize all the values
	public void clear()
	{
		currentBoard.clear();
		b = -1; 
		d = -1;
		winCondition = 0;
	}
	
	public void setDifficulty(int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	// set who is the player
	public void setPlayer(int player)
	{
		this.player = player;
	}
	
	// return the current canvas
	public canvas getCanvas()
	{
		return currentBoard;
	}
	
	// insert for player
	public void insertPlayer(int a)
	{
		if(a > 6)
			System.out.println("ERROR");
		
		currentBoard.insertPlayer(a);
		
		winCondition = currentBoard.checkWin();
		// check if there is a winner after each move
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
	
	//insert for AI using the method from the board
	public void instertAI(int a)
	{
		if(a > 6)
			System.out.println("ERROR");
		
		currentBoard.insertAI(a);
	}
	
	// start the game on terminal and just represent the canvas with scheme
	public void start()
	{
		int a = 0, b = 0;
		
		canvas newCanvas = new canvas();
		TreeAdvanced newTree;
		
		int winCondition;
		
		switch(player)
		{
			case (1):	// choose the first player
			{
				while(true)
				{
					Scanner sc = new Scanner(System.in);
					a = sc.nextInt();
					if(a > 6)
						break;
					
					currentBoard.insertPlayer(a);
					
					winCondition = currentBoard.checkWin();
					if(winCondition == 1)
					{
						//System.out.println("Winner: AI");
					}
					else if(winCondition == 2)
					{
						//System.out.println("Winner: Player");
					}
					
					
					
					newTree = new TreeAdvanced(difficulty, currentBoard);	// create the tree for the AI move
					newCanvas = currentBoard;
					int pos = -1;
					newTree.addEvaluation(newTree.root, newCanvas, currentBoard, pos);
					

					newTree.minMax();
					newTree.isMinMax = true;
					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
					optPath = newTree.optimalPath();
					
					b = optPath.get(0); // get the best move fo the AI after solving MIN-MAX with pruning
					//System.out.println("First move is: "+b+"      ");
					
					if(b >= 0 && b <= 6)
						currentBoard.insertAI(b);
					
					System.out.println("AI");
	
					winCondition = currentBoard.checkWin();
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
					newTree = new TreeAdvanced(difficulty, currentBoard);
					System.out.println("The size of tree is: " + newTree.size());
					newCanvas = currentBoard;
					int pos = -1;
					newTree.addEvaluation(newTree.root, newCanvas, currentBoard, pos);
					
					
					newTree.minMax();
					newTree.isMinMax = true;
					ArrayList<Integer> optPath = new ArrayList<Integer>(0);
					optPath = newTree.optimalPath();
					
					b = optPath.get(0);
					System.out.println("First move is: "+b+"      ");
					
					if(b >= 0 && b <= 6)
						currentBoard.insertAI(b);
					
					System.out.println("AI");
					for (int i = 0; i < 6; i++)
					{
						for (int j = 0; j < 7; j++)
						{
							System.out.print("| " + currentBoard.array[j][i] + " ");
						}
						System.out.println("|");
						System.out.println("-----------------------------");
					}
					
					winCondition = currentBoard.checkWin();
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
					
					currentBoard.insertPlayer(a);
					
					System.out.println("Player");
					for (int i = 0; i < 6; i++)
					{
						for (int j = 0; j < 7; j++)
						{
							System.out.print("| " + currentBoard.array[j][i] + " ");
						}
						System.out.println("|");
						System.out.println("-----------------------------");
					}
					
					winCondition = currentBoard.checkWin();
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
	
	// make all the evaluation for the AI and choose the best move
	public void playAI()
	{
		TreeAdvanced newTree;
		canvas newCanvas;
		newTree = new TreeAdvanced(difficulty, currentBoard);	// create the tree
		newCanvas = currentBoard;
		int pos = -1;
		newTree.addEvaluation(newTree.root, newCanvas, currentBoard, pos);
		
		
		newTree.minMax();
		newTree.isMinMax = true;	
		ArrayList<Integer> optPath = new ArrayList<Integer>(0);
		optPath = newTree.optimalPath();	// solve for MIN MAX with pruning
		
		b = optPath.get(0);	// choose the best move and add it to b
		
		if(b >= 0 && b <= 6)	// if it is in the limits
		{
			d = currentBoard.insertAI(b);	// play the move
		}
		if(d == -100)
		{
			while(d == -100)
			{				
				b++;
				d = currentBoard.insertAI(b);
			}
		}
		
		winCondition = currentBoard.checkWin();		// check for winner
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
	
	
	// player plays first
	public void playPlayer()
	{
		TreeAdvanced newTree;
		canvas newCanvas;
		newTree = new TreeAdvanced(difficulty, currentBoard);
		newCanvas = currentBoard;
		int pos = -1;
		newTree.addEvaluation(newTree.root, newCanvas, currentBoard, pos);
		
		
		newTree.minMax();
		newTree.isMinMax = true;
		ArrayList<Integer> optPath = new ArrayList<Integer>(0);
		optPath = newTree.optimalPath();
		
		b = optPath.get(0);
		
		if(b >= 0 && b <= 6)
			d = currentBoard.insertAI(b);
		
		
		winCondition = currentBoard.checkWin();
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
