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
import javax.swing.JRadioButton;

import java.awt.*;

public class TEST implements Runnable
{

	private JFrame frame;
	private CircleArea circleArea = new CircleArea();
	JPanel panel = new JPanel();
	game newGame = new game();
	
	int a = 0;
	boolean isValid = false;

	/**
	 * Launch the application.
	 */
	public void run()
	{
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TEST window = new TEST();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		//newGame.start();
		//System.out.println("Hello");
		//newGame.start();
		while(isValid)
			newGame.insertPlayer(a);
			
	}
	
	public static void main(String args[])
	{
		try
		{
			(new Thread (new TEST())).start();
			TEST kati = new TEST();
			kati.frame.setVisible(true);
			//window.frame.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public TEST() 
	{
		initialize();
//		circleArea.setCircleColor(0, 0, Color.red);
//		circleArea.repaint()
		 //panel.requestFocusInWindow();
//		panel.addKeyListener(new KeyListener() 
//	    {
//	    	public void keyTyped(KeyEvent e)
//	    	{
//	    		if(e.getKeyChar() == '0')
//	    		{
//	    			circleArea.setCircleColor(0, 0, Color.RED);
//	    			panel.add(circleArea);
//	    			System.out.println("Its pressed");
//	    		}
//	    	}
//
//			@Override
//			public void keyPressed(KeyEvent e) 
//			{
//				// TODO Auto-generated method stub
//	    		if(e.getKeyChar() == '0')
//	    		{
//	    			circleArea.setCircleColor(0, 0, Color.RED);
//	    			panel.add(circleArea);
//	    			System.out.println("Its pressed");
//	    		}
//				
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
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
	    
	    panel.requestFocusInWindow();
	    frame.addKeyListener(new KeyListener() 
	    {
	    	public void keyTyped(KeyEvent e)
	    	{
	    		//if(e.getKeyChar() == '0')
	    		//{
	    		int b = 0;
	    		int a = Character.getNumericValue(e.getKeyChar());
	    		isValid = true;
	    		inittMove(a);
	    		canvas board = new canvas();
	    		board = newGame.getCanvas();
	    		b = board.insertAI(a);
	    			circleArea.setCircleColor(b, a, Color.RED);
	    			circleArea.repaint();
	    			//panel.add(circleArea);
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
	    
	    //panel.add(circleArea);
	    
	    JMenuBar menuBar = new JMenuBar();
	    frame.setJMenuBar(menuBar);
	    
	    JMenu mnNewMenu = new JMenu("New Game");
	    menuBar.add(mnNewMenu);
	    
	    // it creates a border around button
	    Border buttonBorder = BorderFactory.createRaisedBevelBorder();
	    mnNewMenu.setBorder(buttonBorder);
	    
	    JMenuItem mntmTrivia = new JMenuItem("Trivia");
	    mnNewMenu.add(mntmTrivia);
	    
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
				newGame.start();
				
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
	
	


}
