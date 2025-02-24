package ce326.hw3;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import org.json.*;
import java.io.*;
import java.util.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;

import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.TimerTask;

// class that has all the methods for the GUI of the game and starts the game
public class GUI
{
	public JFrame frame;		// the frame 
	private CircleArea circleArea = new CircleArea();	// create the circles
	JPanel panel = new JPanel();
	canvas board = new canvas();		// create the canvas
	game newGame = new game(board);		// create the game
	
	JSONObject jsonObject = new JSONObject();	// json Object to read the history
	
	JSONArray movementsArray = new JSONArray();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss");
	String time = dateFormat.format(new Date());	// get the date
	
	history newHistory = new history();	// create new History
	
	DefaultListModel<String> listModel = new DefaultListModel<>();
	
	JList historyList = new JList<>(listModel);		// history list to make a list with the old games
	
	JScrollPane scroll = new JScrollPane(historyList);	// make scrollable
	
	private boolean isReplaying = false;
	
	int a = 0;
	int wining = 0;
	int mouseClicked = 0;
	int MousePos = -1;
	
	// actionListener for the list
	ListSelectionListener listListener = new ListSelectionListener() 
    {
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			// TODO Auto-generated method stub
            if (!e.getValueIsAdjusting()) 
            {
            	if(historyList.getSelectedValue() != null)
            	{
                    String selectedValue = historyList.getSelectedValue().toString();
                    
                    panel.remove(scroll);
                    panel.add(circleArea);
                    frame.add(panel);
                    frame.setVisible(true);
                    
                    selectedValue = "connect4/"+selectedValue;
                    
                    isReplaying = true;
                    
                    readHistory(selectedValue);
            	}
            }	
		}
    };

    // constructor
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
	    
	    panel.requestFocusInWindow();
	    
	    File newDirectory = new File("connect4");
	    
	    if(newDirectory.exists() && newDirectory.isDirectory())		// create folder connect4 if it does not exists
	    	System.out.println("Directory exists!");
	    else
	    {
	    	if(newDirectory.mkdir())
	    		System.out.println("Directory created!");
	    }
	    
	    
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
	    
	    // action Listener for button Trivia
	    mntmTrivia.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                frame.requestFocus();
                
                stopReplay();
				
				newGame.difficulty = 1;
				if(rdbtnNewRadioButton.isSelected())
				{	
					// removes all listeners before start again
					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);	// to don't call more times when runningAI runs
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					runningPlayer();
				}
			}
		});
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Medium");
	    mnNewMenu.add(mntmNewMenuItem);
	    
	 // action Listener for button Medium level
	    mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                frame.requestFocus();
                
                stopReplay();
				
				
				newGame.difficulty = 3;
				if(rdbtnNewRadioButton.isSelected())
				{	
					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					runningPlayer();
				}
				
			}
		});
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hard");
	    mnNewMenu.add(mntmNewMenuItem_1);
	    
	 // action Listener for button Hard level
	    mntmNewMenuItem_1.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				historyList.removeListSelectionListener(listListener);;
				
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                frame.requestFocus();
                
                stopReplay();
				
				
				newGame.difficulty = 5;
				
				if(rdbtnNewRadioButton.isSelected())
				{		
					// re-initialize all before start again
					newGame.clear();
					board.clear();

					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					running_AI();	// call running-AI to play first
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					newGame.clear();
					board.clear();
					
					frame.removeKeyListener(keyListener);
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					runningPlayer();	// call player to play first
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
	    JMenuItem HistoryButton = new JMenuItem("Show History");
	    mnNewMenu_2.add(HistoryButton);
	    
	    // Listener for the button "Show History"
	    HistoryButton.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		historyList.removeListSelectionListener(listListener);
	    		stopReplay();	// stop replaying previous game 
	    		
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				
				frame.removeKeyListener(keyListener);
				frame.removeKeyListener(KeyListenerPlayer);
				circleArea.removeMouseListener(MouseClicked);
				circleArea.removeMouseMotionListener(Mouseadapter);
				
				
				
				panel.setLayout(new BorderLayout());
				
				listModel.clear();
	            
				historyList = new JList<>(listModel);	// create new List
				scroll = new JScrollPane(historyList);
				

				File directory = new File("connect4");

	            if(directory.isDirectory())
	            {
	            	File[] files = directory.listFiles();
	            	
	            	if(files != null)
	            	{
	            		for(File file : files)
	            		{
	            			if(file.isFile())
	            			{
	            				if(file.getName().contains(".json"))	// conclude only files that are json
	            					listModel.addElement(file.getName());
	            			}
	            		}
	            	}
	            	
	            	
	            }
	            
	            // sort the list
	            ArrayList<String> sortedData = new ArrayList<>();
	            for (int i = 0; i < listModel.getSize(); i++) {
	                sortedData.add(listModel.getElementAt(i));
	            }

	            Collections.sort(sortedData, Collections.reverseOrder());
	            listModel.clear();

	            for (String item : sortedData) {
	                listModel.addElement(item);
	            }
	            
	            // remove circles and panel and replace it with history list
				panel.remove(circleArea);
				
				frame.setVisible(false);
				
	            panel.add(scroll, BorderLayout.CENTER);

	            frame.add(panel);

	            frame.setVisible(true);
	            
	            frame.removeKeyListener(keyListener);
				frame.removeKeyListener(KeyListenerPlayer);	            
				
	            historyList.addListSelectionListener(listListener);
	    	}
	    });
	    
	    JMenu mnNewMenu_3 = new JMenu("Help");	    
	    menuBar.add(mnNewMenu_3);
	    
	    JMenuItem helpButton = new JMenuItem("Press for Help");
	    mnNewMenu_3.add(helpButton);
	    
	    helpButton.addActionListener(new ActionListener() 
	    {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// just show a message guide for game
				JOptionPane.showMessageDialog(frame, "Press 0-6 to add a piece in the board, \nOR double click in a column!");
			}
		});
	}
	
	// Keylistener if the player plays first
	KeyListener KeyListenerPlayer = new KeyListener()
	{
    	public void keyTyped(KeyEvent e)
    	{
				int a = Character.getNumericValue(e.getKeyChar());
				if(a < 0 || a > 6)	// check the move
				{
					JOptionPane.showMessageDialog(frame, "Invalid Move!");
				}
				else
				{
					int c = board.insertPlayer(a);
					if(c != -100)
					{
						writeHistory(movementsArray , 1, a);
						wining = board.checkWin();
						circleArea.setCircleColor(c, a, Color.RED);
						
						if(wining == 1)
						{
							JOptionPane.showMessageDialog(frame, "Winner: AI!");
							wining = 0;
							winConditionHandlePlayer();
							
							newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
							
							return;
						}
						else if (wining == 2)
						{
							JOptionPane.showMessageDialog(frame, "Winner: Player!");
							wining = 0;
							winConditionHandlePlayer();
							
							// write move in history
							newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
							
							return;
						}
						
						newGame.playAI();	// call the AI to play
						writeHistory(movementsArray , 2, newGame.b);	// write the move in history
						
						circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
						circleArea.repaint();	 // reload the color on circles
						wining = board.checkWin();
						
						if(wining == 1)
						{
							JOptionPane.showMessageDialog(frame, "Winner: AI!");
							wining = 0;
							winConditionHandlePlayer();
								
							newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
							
							return;
						}
						else if (wining == 2)
						{
							JOptionPane.showMessageDialog(frame, "Winner: Player!");
							wining = 0;
							winConditionHandlePlayer();
							
							newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
							
							return;
						}
					}
					else
					{
						// show a more complex message
						String message = "<html><body style='width: 200px; text-align: center;'>" +
				                 "<h1 style='color: #FFA500;'>Invalid Move!</h1>" +
				                 "<p style='font-size: 16px;'>Try Again!</p>" +
				                 "</body></html>";
						JOptionPane.showMessageDialog(frame, message);
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

	
	// if player plays first add listeners for keyboard and for mouse
	public void runningPlayer()
	{
		frame.addKeyListener(KeyListenerPlayer);
		circleArea.addMouseMotionListener(Mouseadapter);
		circleArea.addMouseListener(MouseClicked);

	}
	
	// Key listener for AI plays first
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
				if(c != -100)
				{
					circleArea.setCircleColor(c, a, Color.RED);
					writeHistory(movementsArray , 1, a);
					board.flag = 0;
					wining = board.checkWin();
					
					if(newGame.winCondition == 1)
					{
						JOptionPane.showMessageDialog(frame, "Winner: AI!");
						wining = 0;
						winConditionHandle();
						
						newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
						
						return;
					}
					else if (newGame.winCondition == 2)
					{
						JOptionPane.showMessageDialog(frame, "Winner: Player!");
						wining = 0;
						winConditionHandle();
						
						newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
						
						return;
					}
					newGame.playAI();
					writeHistory(movementsArray , 2, newGame.b);
					if(newGame.d == -100)
						newGame.b++;
					circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
					circleArea.repaint();
					wining = board.checkWin();
					
					if(newGame.winCondition == 1)
					{
						JOptionPane.showMessageDialog(frame, "Winner: AI!");
						wining = 0;
						winConditionHandle();
						
						newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
						
						return;	// finish only when there is a winner
					}
					else if (newGame.winCondition == 2)
					{
						JOptionPane.showMessageDialog(frame, "Winner: Player!");
						wining = 0;
						winConditionHandle();
						
						newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
						
						return;
					}
				}
				else
				{
					String message = "<html><body style='width: 200px; text-align: center;'>" +
			                 "<h1 style='color: #FFA500;'>Invalid Move!</h1>" +
			                 "<p style='font-size: 16px;'>Try Again!</p>" +
			                 "</body></html>";
					JOptionPane.showMessageDialog(frame, message);
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
			// calculate the pos of mouse for each collumn
			if(e.getX() > 55 && e.getX() < 150)
			{
				MousePos = 0;
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
		    	  
		    	  writeHistory(movementsArray , 1, a);
		    	  
		    	  
		    	  board.flag = 0;
		    	  wining = board.checkWin();
		    	  
		    	  if(newGame.winCondition == 1)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: AI!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
		    		  
		    		  return;
		    	  }
		    	  else if (newGame.winCondition == 2)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: Player!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
		    		  
		    		  return;
		    	  }
		    	  circleArea.repaint();
		    	  newGame.playAI();
		    	  circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
		    	  
		    	  writeHistory(movementsArray , 2, newGame.b);
		    	  
		    	  circleArea.repaint();
		    	  
		    	  if(newGame.winCondition == 1)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: AI!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  newHistory.writeHistory(jsonObject, movementsArray, "AI", time, newGame.difficulty);
		    		  
		    		  return;
		    	  }
		    	  else if (newGame.winCondition == 2)
		    	  {
		    		  JOptionPane.showMessageDialog(frame, "Winner: Player!");
		    		  wining = 0;
		    		  winConditionHandle();
		    		  
		    		  newHistory.writeHistory(jsonObject, movementsArray, "P", time, newGame.difficulty);
		    		  
		    		  return;
		    	  }
			}	
		}
	};
	
	// AI plays first and adds the correcr keyListeners and MouseListeners
	public void running_AI()
	{

		frame.addKeyListener(keyListener);
		circleArea.addMouseMotionListener(Mouseadapter);
		circleArea.addMouseListener(MouseClicked);
		
		board.insertAI(3);	// play always first move in middle
		writeHistory(movementsArray, 2, 3);
		circleArea.setCircleColor(5, 3, Color.YELLOW);
		circleArea.repaint();
	}
	
	// if there is a winner remove Listeners to stop the game
	public void winConditionHandle()
	{
		frame.removeKeyListener(keyListener);
		frame.removeKeyListener(KeyListenerPlayer);
		circleArea.removeMouseListener(MouseClicked);
		circleArea.removeMouseMotionListener(Mouseadapter);
	}
	
	public void winConditionHandlePlayer()
	{
		frame.removeKeyListener(keyListener);
		frame.removeKeyListener(KeyListenerPlayer);
		circleArea.removeMouseListener(MouseClicked);
		circleArea.removeMouseMotionListener(Mouseadapter);
	}	
	
//	// ActionListener for timer, to wait 3 seconds to replay each move for history
//	ActionListener TimerActions = new ActionListener() 
//    {
//    	private int currentIndex = 0; 
//
//        @Override
//        public void actionPerformed(ActionEvent e) 
//        {
//            if (!isReplaying) 	
//            {
//                ((Timer) e.getSource()).stop();
//                return;
//            }
//        	
//            if (currentIndex < movementsArray.length()) 	// until the json finish
//            {
//                JSONObject jsonMovement = movementsArray.getJSONObject(currentIndex);
//                int player = jsonMovement.getInt("player");
//                int column = jsonMovement.getInt("column");
//
//                if (player == 1) {
//                    int c = board.insertPlayer(column);
//                    circleArea.setCircleColor(c, column, Color.RED);
//                } else if (player == 2) {
//                    int c = board.insertAI(column);
//                    circleArea.setCircleColor(c, column, Color.YELLOW);
//                }
//
//                circleArea.repaint();
//                panel.repaint();
//                frame.repaint();
//
//                currentIndex++;
//            } 
//            else 
//            {
//                ((Timer) e.getSource()).stop();
//                return;
//            }
//        }
//    };	
	
    // reads and shows the history from the JSON FILE
	public void readHistory(String filename)		
	{
		try(FileReader fileReader = new FileReader(filename))
		{
			JSONTokener tokener = new JSONTokener(fileReader);
			JSONObject jsonObject = new JSONObject(tokener);

			
            JSONArray movementsArray = jsonObject.getJSONArray("movements");

            // ActionListener for timer, to wait 3 seconds to replay each move for history
            Timer displayTimer = new Timer(3000, new ActionListener() 
            {
            	private int currentIndex = 0; 

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (!isReplaying)  // if starts new game stop replaying the moves
                    {
                        ((Timer) e.getSource()).stop();
                        return;
                    }
                	
                    if (currentIndex < movementsArray.length()) 	// until the json finish
                    {
                        JSONObject jsonMovement = movementsArray.getJSONObject(currentIndex);
                        int player = jsonMovement.getInt("player");
                        int column = jsonMovement.getInt("column");

                        if (player == 1) {
                            int c = board.insertPlayer(column);
                            circleArea.setCircleColor(c, column, Color.RED);
                        } else if (player == 2) {
                            int c = board.insertAI(column);
                            circleArea.setCircleColor(c, column, Color.YELLOW);
                        }

                        circleArea.repaint();
                        panel.repaint();
                        frame.repaint();

                        currentIndex++;
                    } 
                    else 
                    {
                        ((Timer) e.getSource()).stop();
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
                        
                        return;
                    }
                }
            });
            
            displayTimer.setInitialDelay(0); // Start immediately
            displayTimer.start();
            if(!isReplaying)
            {
            	displayTimer.stop();	
            	return;
            }
        } 
		catch (IOException e3) 
		{
            e3.printStackTrace();
        }

	}
	
	public void stopReplay() 
	{
		historyList.removeListSelectionListener(listListener);
	    isReplaying = false;
	}
	
	// not used cause of class history
	public void writeHistory(JSONArray movementsArray , int player, int column)
	{		
		movementsArray.put(new JSONObject().put("player", player).put("column", column));
	}
}
