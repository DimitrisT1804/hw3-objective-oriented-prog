package hw3_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.*;

public class test2
{

	public JFrame frame;
	private CircleArea circleArea = new CircleArea();
	JPanel panel = new JPanel();
	game newGame = new game();
	
	int a = 0;
	boolean isValid = false;
	lock newLock = new lock();
	boolean continueRunning = true;
	
	canvas board = new canvas();
	int wining = 0;

	/**
	 * Launch the application.
	 */

	
	public static void main(String args[])
	{

			
//		try
//		{
//			(new Thread (new TEST())).start();
//			
//			
//			//(new Thread(new game())).start();
//			//window.frame.setVisible(true);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		test2 newTest = new test2();
		newTest.frame.setVisible(true);
		

	}

	/**
	 * Create the application.
	 */
	public test2() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public synchronized void inittMove(int move)
	{
		a = move;
	}
	

	private void initialize() 
	{
	    frame = new JFrame();
	    frame.setBounds(100, 100, 1104, 881);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    
	    frame.getContentPane().add(panel, BorderLayout.CENTER);
	    //panel.setBackground(Color.WHITE);
	    //CircleArea circleArea = new CircleArea();
	    //circleArea.setForeground(new Color(51, 51, 0));
	    //circleArea.setBackground(new Color(51, 255, 51));
	    //circleArea.setPreferredSize(circleArea.getPreferredSize());
	    panel.add(circleArea);
	    //frame.setVisible(true);
	    
	    panel.requestFocusInWindow();
//	    JOptionPane.showMessageDialog(frame, "HELLO THERE");
	    //frame.setVisible(true);
	    
	    
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
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
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
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
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
				// TODO Auto-generated method stub
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				newGame.difficulty = 5;
				if(rdbtnNewRadioButton.isSelected())
				{	
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					runningPlayer();
				}
				//newGame.start();
				
			}
		});
	    
	    JMenu mnNewMenu_1 = new JMenu("1st Player");
	    menuBar.add(mnNewMenu_1);
	    
	    //ImageIcon image = new ImageIcon("kati.jpg");
	    
	    mnNewMenu_1.add(rdbtnNewRadioButton);
	    
	    rdbtnNewRadioButton.addActionListener(new ActionListener()
	    {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				newGame.player = 2;
    			//circleArea.setCircleColor(0, 1, Color.RED);
    			//panel.add(circleArea);
    			//circleArea.repaint();
    			//circleArea.paintComponent(circleArea);
    			System.out.println("Its pressed");
			}
	    	
	    });
	    
	    
	    mnNewMenu_1.add(rdbtnNewRadioButton_1);
	    
	    rdbtnNewRadioButton_1.addActionListener(new ActionListener()
	    {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				newGame.player = 1;
			}
	    	
	    });
	    
	    JMenu mnNewMenu_2 = new JMenu("History");
	    menuBar.add(mnNewMenu_2);
	    
	    JMenu mnNewMenu_3 = new JMenu("Help");
	    menuBar.add(mnNewMenu_3);
	}
	
	KeyListener KeyListenerPlayer = new KeyListener()
	{
    	public void keyTyped(KeyEvent e)
    	{

				int b = 0;
				int a = Character.getNumericValue(e.getKeyChar());
				isValid = true;
				int c = board.insertPlayer(a);
				wining = board.checkWin();
				circleArea.setCircleColor(c, a, Color.MAGENTA);
    				
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
    				
    				newGame.playAI(board);
    				
    				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
    				//b = board.insertAI(a);
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

		@Override
		public void keyPressed(KeyEvent e) 
		{

			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			isValid = false;
			
		}
		
    };

	
	
	public void runningPlayer()
	{
		frame.addKeyListener(KeyListenerPlayer);

	}
	
	KeyListener keyListener = new KeyListener()
	{
		public void keyTyped(KeyEvent e)
		{

			int b = 0;
			int a = Character.getNumericValue(e.getKeyChar());
			isValid = true;
			inittMove(a);
			
			//board = newGame.getCanvas();
			int c = board.insertPlayer(a);
			circleArea.setCircleColor(c, a, Color.MAGENTA);
			board.flag = 0;
			wining = board.checkWin();
			
			if(newGame.winCondition == 1)
			{
				JOptionPane.showMessageDialog(frame, "Winner: AI!");
				continueRunning = false;
				wining = 0;
				winConditionHandle();
				
				return;
			}
			else if (newGame.winCondition == 2)
			{
				JOptionPane.showMessageDialog(frame, "Winner: Player!");
				continueRunning = false;
				wining = 0;
				winConditionHandle();
				return;
			}
			
			newGame.playAI(board);
			circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
			//b = board.insertAI(a);
			circleArea.repaint();
			
			if(newGame.winCondition == 1)
			{
				JOptionPane.showMessageDialog(frame, "Winner: AI!");
				continueRunning = false;
				//panel.removeKeyListener(null);
				//initialize();
				//System.exit(0);
				//frame.removeKeyListener(keyListener);
				wining = 0;
				winConditionHandle();
				
				return;
			}
			else if (newGame.winCondition == 2)
			{
				JOptionPane.showMessageDialog(frame, "Winner: Player!");
				continueRunning = false;
				wining = 0;
				winConditionHandle();
				//initialize();
				//System.exit(0);
				//frame.removeKeyListener(keyListener);
				return;
			}
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
	
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			isValid = false;
			
		}
		
	};
	
	public void running_AI()
	{

		frame.addKeyListener(keyListener);
			if(board.flag == 0)
			{
				board.insertAI(3);
				circleArea.setCircleColor(5, 3, Color.YELLOW);
				circleArea.repaint();
			}
		
	}
	
	public void winConditionHandle()
	{
		frame.removeKeyListener(keyListener);
	}
	
	public void winConditionHandlePlayer()
	{
		frame.removeKeyListener(KeyListenerPlayer);
	}

}
