package ce326.hw3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI
{

	public JFrame frame;
	private CircleArea circleArea = new CircleArea();
	JPanel panel = new JPanel();
	canvas board = new canvas();
	game newGame = new game(board);
	
	int a = 0;
	int wining = 0;
	int mouseClicked = 0;
	int MousePos = -1;

	public GUI() 
	{
		initialize();
	}

	private void initialize() 
	{
	    frame = new JFrame();
	    frame.setBounds(400, 100, 935, 865);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    
	    frame.getContentPane().add(panel, BorderLayout.CENTER);
	    panel.add(circleArea);
	    //frame.setSize(935, 865);	// size of panel
	    
	    panel.requestFocusInWindow();
	    
	    
	    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("You");
	    JRadioButton rdbtnNewRadioButton = new JRadioButton("AI", true);
	    
	    /* We add it in a group so that only one can be selected */
	    ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdbtnNewRadioButton);
	    buttonGroup.add(rdbtnNewRadioButton_1);
	    
	    
	    
	    JMenuBar menuBar = new JMenuBar();
	    frame.setJMenuBar(menuBar);
	    
	    JMenu mnNewMenu = new JMenu("New Game");
	    menuBar.add(mnNewMenu);
	    
	    // it creates a border around button
	    Border buttonBorder = BorderFactory.createRaisedBevelBorder();
	    mnNewMenu.setBorder(buttonBorder);
	    
	    JMenuItem mntmTrivia = new JMenuItem("Trivia");
	    mnNewMenu.add(mntmTrivia);
	    
	    mntmTrivia.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				newGame.difficulty = 1;
				if(rdbtnNewRadioButton.isSelected())
				{	
					frame.removeKeyListener(keyListener);	// to dont call more times when runningAI runs
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					frame.removeKeyListener(KeyListenerPlayer);
					runningPlayer();
				}
				
			}
		});
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Medium");
	    mnNewMenu.add(mntmNewMenuItem);
	    
	    mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				newGame.difficulty = 3;
				if(rdbtnNewRadioButton.isSelected())
				{	
					frame.removeKeyListener(keyListener);
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					frame.removeKeyListener(KeyListenerPlayer);
					runningPlayer();
				}
				
			}
		});
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hard");
	    mnNewMenu.add(mntmNewMenuItem_1);
	    
	    mntmNewMenuItem_1.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				newGame.difficulty = 5;
				if(rdbtnNewRadioButton.isSelected())
				{		
					newGame.clear();
					board.clear();
					//mntmNewMenuItem_1.removeActionListener(mntmNewMenuItem_1.getActionListeners()[0]);
					frame.removeKeyListener(keyListener);
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					frame.removeKeyListener(KeyListenerPlayer);
					runningPlayer();
				}
				
			}
		});
	    
	    JMenu mnNewMenu_1 = new JMenu("1st Player");
	    menuBar.add(mnNewMenu_1);
	    
	    mnNewMenu_1.add(rdbtnNewRadioButton);
	    
	    rdbtnNewRadioButton.addActionListener(new ActionListener()
	    {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				newGame.player = 2;
    			System.out.println("Its pressed");
			}
	    	
	    });
	    
	    
	    mnNewMenu_1.add(rdbtnNewRadioButton_1);
	    
	    rdbtnNewRadioButton_1.addActionListener(new ActionListener()
	    {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				newGame.player = 1;
			}
	    	
	    });
	    
	    JMenu mnNewMenu_2 = new JMenu("History");
	    menuBar.add(mnNewMenu_2);
	    
	    JMenu mnNewMenu_3 = new JMenu("Help");	    
	    menuBar.add(mnNewMenu_3);
	    
	    JMenuItem helpButton = new JMenuItem("Press for Help");
	    mnNewMenu_3.add(helpButton);
	    
	    helpButton.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, "Press 0-6 to add a piece in the board, \nOR double click in a column!");
			}
		});
	}
	
	KeyListener KeyListenerPlayer = new KeyListener()
	{
    	public void keyTyped(KeyEvent e)
    	{
				int a = Character.getNumericValue(e.getKeyChar());
				if(a < 0 || a > 6)
				{
					JOptionPane.showMessageDialog(frame, "Invalid Move!");
				}
				else
				{
					int c = board.insertPlayer(a);
					wining = board.checkWin();
					circleArea.setCircleColor(c, a, Color.RED);
					
					if(wining == 1)
					{
						JOptionPane.showMessageDialog(frame, "Winner: AI!");
						wining = 0;
						winConditionHandlePlayer();
						return;
					}
					else if (wining == 2)
					{
						JOptionPane.showMessageDialog(frame, "Winner: Player!");
						wining = 0;
						winConditionHandlePlayer();
						return;
					}
					
					newGame.playAI();
					
					circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
					circleArea.repaint();
					wining = board.checkWin();
					
					if(wining == 1)
					{
						JOptionPane.showMessageDialog(frame, "Winner: AI!");
						wining = 0;
						winConditionHandlePlayer();
						
						return;
					}
					else if (wining == 2)
					{
						JOptionPane.showMessageDialog(frame, "Winner: Player!");
						wining = 0;
						winConditionHandlePlayer();
						return;
					}
				}

    	}

		@Override
		public void keyPressed(KeyEvent e) 
		{

			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{

		}
		
    };

	
	
	public void runningPlayer()
	{
		frame.addKeyListener(KeyListenerPlayer);
		circleArea.addMouseMotionListener(Mouseadapter);
		circleArea.addMouseListener(MouseClicked);

	}
	
	KeyListener keyListener = new KeyListener()
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
			int a = Character.getNumericValue(e.getKeyChar());	
			if(a < 0 || a > 6)
			{
				String message = "<html><body style='width: 200px; text-align: center;'>" +
		                 "<h1 style='color: #FFA500;'>Invalid Move!</h1>" +
		                 "<p style='font-size: 16px;'>Try Again!</p>" +
		                 "</body></html>";
				JOptionPane.showMessageDialog(frame, message);
			}
			else
			{
				int c = board.insertPlayer(a);
				circleArea.setCircleColor(c, a, Color.RED);
				board.flag = 0;
				wining = board.checkWin();
				
				if(newGame.winCondition == 1)
				{
					JOptionPane.showMessageDialog(frame, "Winner: AI!");
					wining = 0;
					winConditionHandle();
					
					return;
				}
				else if (newGame.winCondition == 2)
				{
					JOptionPane.showMessageDialog(frame, "Winner: Player!");
					wining = 0;
					winConditionHandle();
					return;
				}
				//circleArea.repaint();
				newGame.playAI();
				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
				circleArea.repaint();
				wining = board.checkWin();
				
				if(newGame.winCondition == 1)
				{
					JOptionPane.showMessageDialog(frame, "Winner: AI!");
					wining = 0;
					winConditionHandle();
					
					return;
				}
				else if (newGame.winCondition == 2)
				{
					JOptionPane.showMessageDialog(frame, "Winner: Player!");
					wining = 0;
					winConditionHandle();
					return;
				}
			}
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
	
		}
		
		@Override
		public void keyReleased(KeyEvent e) 
		{
			
		}
		
	};
	
	MouseAdapter MouseClicked = new MouseAdapter()
	{
      public void mousePressed(MouseEvent e) 
      {
    	  mouseClicked += 1;
      }
      
      public void mouseReleased(MouseEvent e)
      {
    	  
      }		
	};
	
	MouseAdapter Mouseadapter = new MouseAdapter()
	{
		@Override
		public void mouseMoved(MouseEvent e)
		{
			//System.out.println("X is: " + e.getX());
			if(e.getX() > 55 && e.getX() < 150)
			{
				MousePos = 0;
			//circleArea.addMouseMotionListener();
			}
			else if(e.getX() > 170 && e.getX() < 270)
			{
				MousePos = 1;
			}
			else if(e.getX() > 300 && e.getX() < 380)
			{
				MousePos = 2;
			}
			else if(e.getX() > 406 && e.getX() < 500)
			{
				MousePos = 3;
			}
			else if(e.getX() > 530 && e.getX() < 620)
			{
				MousePos = 4;
			}
			else if(e.getX() > 650 && e.getX() < 740)
			{
				MousePos = 5;
			}
			else if(e.getX() > 780 && e.getX() < 860)
			{
				MousePos = 6;
			}
			else
			{
				MousePos = -1;
				mouseClicked = 0;
			}
			
			if(mouseClicked == 2 && MousePos > -1)
			{
				mouseClicked = 0;
		    	  a = MousePos;
		    	  int c = board.insertPlayer(a);
		    	  circleArea.setCircleColor(c, a, Color.RED);
		    	  board.flag = 0;
		    	  wining = board.checkWin();
		    	  
		    	  if(newGame.winCondition == 1)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: AI!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  return;
		    	  }
		    	  else if (newGame.winCondition == 2)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: Player!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  return;
		    	  }
		    	  circleArea.repaint();
		    	  newGame.playAI();
		    	  circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
		    	  circleArea.repaint();
		    	  
		    	  if(newGame.winCondition == 1)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: AI!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  return;
		    	  }
		    	  else if (newGame.winCondition == 2)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: Player!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  return;
		    	  }
			}
			
		}
	};
	
	public void running_AI()
	{

		frame.addKeyListener(keyListener);
		circleArea.addMouseMotionListener(Mouseadapter);
		circleArea.addMouseListener(MouseClicked);
		
		board.insertAI(3);
		circleArea.setCircleColor(5, 3, Color.YELLOW);
		circleArea.repaint();
	}
	
	public void winConditionHandle()
	{
		frame.removeKeyListener(keyListener);
		circleArea.removeMouseListener(MouseClicked);
		circleArea.removeMouseMotionListener(Mouseadapter);
	}
	
	public void winConditionHandlePlayer()
	{
		frame.removeKeyListener(KeyListenerPlayer);
		circleArea.removeMouseListener(MouseClicked);
		circleArea.removeMouseMotionListener(Mouseadapter);
	}	
}
