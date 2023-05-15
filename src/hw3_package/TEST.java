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
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.*;

public class TEST
{

	private JFrame frame;
	private CircleArea circleArea = new CircleArea();
	JPanel panel = new JPanel();
	game newGame = new game();
	
	int a = 0;
	boolean isValid = false;
	lock newLock = new lock();

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
		TEST newTest = new TEST();
		newTest.frame.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public TEST() 
	{
		initialize();
//	    frame.addKeyListener(new KeyListener() 
//	    {
//	    	public void keyTyped(KeyEvent e)
//	    	{
//	    		//if(e.getKeyChar() == '0')
//	    		//{
//	    		//while(true)
//	    		//{
//	    			
//	    			//while(newLock.isLocked() == true)
//	    			//{		
//	    				int b = 0;
//	    				int a = Character.getNumericValue(e.getKeyChar());
//	    				isValid = true;
//	    				inittMove(a);
//	    				canvas board = new canvas();
//	    				board = newGame.getCanvas();
//	    				int c = board.insertPlayer(a);
//	    				circleArea.setCircleColor(c, a, Color.MAGENTA);
//	    				newGame.playAI(board);
//	    				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
//	    				//b = board.insertAI(a);
//	    				circleArea.repaint();
//	    				
//	    				if(newGame.winCondition != 0)
//	    				{
//	    					return;
//	    				}
//	    				//panel.add(circleArea);
//	    				//}
//	    				//newLock.unlock();
//	    			//}
//	    		//}
//	    	}
//
//			@Override
//			public void keyPressed(KeyEvent e) 
//			{
//				// TODO Auto-generated method stub
////	    		if(e.getKeyCode() == KeyEvent.VK_0)
////	    		{
////	    			circleArea.setCircleColor(0, 0, Color.RED);
////	    			circleArea.repaint();
////	    			System.out.println("It worked");
////	    			//panel.add(circleArea);
////	    		}
//				
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				isValid = false;
//				
//			}
//			
//	    });

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
	    
	    JOptionPane.showConfirmDialog(frame, "hello");
	    
	    
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
				// TODO Auto-generated method stub
				newGame.difficulty = 1;
				running();
				//newGame.start();
				
			}
		});
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Medium");
	    mnNewMenu.add(mntmNewMenuItem);
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hard");
	    mnNewMenu.add(mntmNewMenuItem_1);
	    
	    mntmNewMenuItem_1.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				newGame.difficulty = 5;
				if(newGame.player == 2)
				{
					running_AI();
				}
				else if (newGame.player == 1)
				{
					running();
				}
				//newGame.start();
				
			}
		});
	    
	    JMenu mnNewMenu_1 = new JMenu("1st Player");
	    menuBar.add(mnNewMenu_1);
	    
	    JRadioButton rdbtnNewRadioButton = new JRadioButton("AI");
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
	    
	    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("You");
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
	
	
	public void running()
	{
		frame.addKeyListener(new KeyListener() 
	    {
	    	public void keyTyped(KeyEvent e)
	    	{
	    		//if(e.getKeyChar() == '0')
	    		//{
	    		//while(true)
	    		//{
	    			
	    			//while(newLock.isLocked() == true)
	    			//{		
	    				int b = 0;
	    				int a = Character.getNumericValue(e.getKeyChar());
	    				isValid = true;
	    				inittMove(a);
	    				canvas board = new canvas();
	    				board = newGame.getCanvas();
	    				int c = board.insertPlayer(a);
	    				circleArea.setCircleColor(c, a, Color.MAGENTA);
	    				newGame.playAI(board);
	    				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
	    				//b = board.insertAI(a);
	    				circleArea.repaint();
	    				
	    				if(newGame.winCondition != 0)
	    				{
	    					return;
	    				}
	    				//panel.add(circleArea);
	    				//}
	    				//newLock.unlock();
	    			//}
	    		//}
	    	}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				// TODO Auto-generated method stub
//	    		if(e.getKeyCode() == KeyEvent.VK_0)
//	    		{
//	    			circleArea.setCircleColor(0, 0, Color.RED);
//	    			circleArea.repaint();
//	    			System.out.println("It worked");
//	    			//panel.add(circleArea);
//	    		}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				isValid = false;
				
			}
			
	    });

	}
	
	public void running_AI()
	{
		//int flag = 0;
		canvas board = new canvas();
		//while(newGame.winCondition == 0)
		//{
			
			if(board.flag == 0)
			{
				newGame.playAI(board);
				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
				//b = board.insertAI(a);
				circleArea.repaint();
				
				if(newGame.winCondition != 0)
				{
					return;
				}
				board.flag = 1;
			}
			
			frame.addKeyListener(new KeyListener() 
			{
				public void keyTyped(KeyEvent e)
				{
					//if(e.getKeyChar() == '0')
					//{
					//while(true)
					//{
					
					//while(newLock.isLocked() == true)
					//{		
					int b = 0;
					int a = Character.getNumericValue(e.getKeyChar());
					isValid = true;
					inittMove(a);
					
					//board = newGame.getCanvas();
					int c = board.insertPlayer(a);
					circleArea.setCircleColor(c, a, Color.MAGENTA);
					board.flag = 0;
					
					newGame.playAI(board);
					circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
					//b = board.insertAI(a);
					circleArea.repaint();
					
					if(newGame.winCondition != 0)
					{
						return;
					}
					
					//panel.add(circleArea);
					//}
					//newLock.unlock();
					//}
					//}
				}
				
				@Override
				public void keyPressed(KeyEvent e) 
				{
					// TODO Auto-generated method stub
//	    		if(e.getKeyCode() == KeyEvent.VK_0)
//	    		{
//	    			circleArea.setCircleColor(0, 0, Color.RED);
//	    			circleArea.repaint();
//	    			System.out.println("It worked");
//	    			//panel.add(circleArea);
//	    		}
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					isValid = false;
					
				}
				
			});
		//}
		

	}
	


}
