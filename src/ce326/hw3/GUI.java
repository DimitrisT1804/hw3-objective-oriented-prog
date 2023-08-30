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

public class GUI
{

	public JFrame frame;
	private CircleArea circleArea = new CircleArea();
	JPanel panel = new JPanel();
	canvas board = new canvas();
	game newGame = new game(board);
	
	JSONObject jsonObject = new JSONObject();
	
	JSONArray movementsArray = new JSONArray();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss");
	String time = dateFormat.format(new Date());
	
	history newHistory = new history();
	
	DefaultListModel<String> listModel = new DefaultListModel<>();
	
	JList historyList = new JList<>(listModel);
	
	JScrollPane scroll = new JScrollPane(historyList);
	
	private boolean isReplaying = false;
	
	int a = 0;
	int wining = 0;
	int mouseClicked = 0;
	int MousePos = -1;
	
	ListSelectionListener listListener = new ListSelectionListener() 
    {
		
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			// TODO Auto-generated method stub
            if (!e.getValueIsAdjusting()) 
            {
                String selectedValue = historyList.getSelectedValue().toString();
                System.out.println("Selected: " + selectedValue);
                
                panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                selectedValue = "output/"+selectedValue;
                
                isReplaying = true;
                
                readHistory(selectedValue);
            }
			
		}
    };

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
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                stopReplay();
				
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
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                stopReplay();
				
				
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
//				newGame.clear();
//				circleArea.clear();
//				circleArea.repaint();
//				board.clear();
//				newGame.difficulty = 5;
				
				historyList.removeListSelectionListener(listListener);;
				
				board = new canvas();
				newGame = new game(board);
				circleArea.clear();
				circleArea.repaint();
				
				panel.remove(scroll);
                panel.add(circleArea);
                frame.add(panel);
                frame.setVisible(true);
                
                stopReplay();
				
				
				newGame.difficulty = 5;
				
				/*new implement */
				
				
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				String time = dateFormat.format(new Date());
				
				//jsonObject.put("start time ", time);
				
				if(rdbtnNewRadioButton.isSelected())
				{		
					newGame.clear();
					board.clear();
					//mntmNewMenuItem_1.removeActionListener(mntmNewMenuItem_1.getActionListeners()[0]);
					frame.removeKeyListener(keyListener);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
					running_AI();
				}
				else if (rdbtnNewRadioButton_1.isSelected())
				{
					newGame.clear();
					board.clear();
					
					frame.removeKeyListener(KeyListenerPlayer);
					circleArea.removeMouseListener(MouseClicked);
					circleArea.removeMouseMotionListener(Mouseadapter);
					
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
//    			System.out.println("Its pressed");
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
	    
	    HistoryButton.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		System.out.println("History menu clicked!");
	    		
				newGame.clear();
				circleArea.clear();
				circleArea.repaint();
				board.clear();
				
				
				
				panel.setLayout(new BorderLayout());
				
//	            listModel.addElement("Item 1 kai kati allo gia na tsekaro ti fasi me ton xoro");
//	            listModel.addElement("Item 2");
//	            listModel.addElement("Item 3");
//	            listModel.addElement("Item 4");
//	            listModel.addElement("Item 5");
//	            listModel.addElement("Item 6");
//	            listModel.addElement("Item 7");
//	            listModel.addElement("Item 8");
//	            listModel.addElement("Item 9");
//	            listModel.addElement("Item 10");
//	            listModel.addElement("Item 11");
//	            listModel.addElement("Item 12");
				
				listModel.clear();
				historyList = new JList<>(listModel);
				scroll = new JScrollPane(historyList);
	            

				File directory = new File("output");

	            if(directory.isDirectory())
	            {
	            	File[] files = directory.listFiles();
	            	
	            	if(files != null)
	            	{
	            		for(File file : files)
	            		{
	            			if(file.isFile())
	            			{
	            				if(file.getName().contains(".json"))
	            					listModel.addElement(file.getName());
	            				
	            			}
	            		}
	            	}
	            	
	            	
	            }
				
//				JList historyList = new JList<>(listModel);
				
				panel.remove(circleArea);
				
				frame.setVisible(false);
				
//				JScrollPane scroll = new JScrollPane(historyList);
				
	            panel.add(scroll, BorderLayout.CENTER);

	            // Add your panel to the frame
	            frame.add(panel);

	            // Set the size of the frame
	            //frame.setSize(600, 600);

	            // Make the frame visible
	            frame.setVisible(true);
				
	            historyList.addListSelectionListener(listListener);
				
	
	    		
	    		//readHistory(selected);
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
					writeHistory(movementsArray , 1, a);
					wining = board.checkWin();
					circleArea.setCircleColor(c, a, Color.RED);
					
					if(wining == 1)
					{
						JOptionPane.showMessageDialog(frame, "Winner: AI!");
						wining = 0;
						winConditionHandlePlayer();
						
//						jsonObject.put("movements", movementsArray);
//						
//						jsonObject.put("Winner ", "AI");
//						
//						// Write JSON to a file
//						String JsonName = "output/"+time.toString()+"_L:Hard" +"_W:AI"+".json";
////						String JsonName = "output/"+time.toString()+"_L:Hard" +"_W:AI"+".json";
//						
//						try (FileWriter fileWriter = new FileWriter(JsonName)) {
//						    fileWriter.write(jsonObject.toString(4)); // Indentation of 4 spaces
//						} catch (IOException e4) {
//						    e4.printStackTrace();
//						}
						
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
					
					newGame.playAI();
					writeHistory(movementsArray , 2, newGame.b);
					
					circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
					circleArea.repaint();
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
				//circleArea.repaint();
				newGame.playAI();
				writeHistory(movementsArray , 2, newGame.b);
				circleArea.setCircleColor(newGame.d, newGame.b, Color.YELLOW);
				circleArea.repaint();
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
	
	public void running_AI()
	{

		frame.addKeyListener(keyListener);
		circleArea.addMouseMotionListener(Mouseadapter);
		circleArea.addMouseListener(MouseClicked);
		
		board.insertAI(3);
		writeHistory(movementsArray, 2, 3);
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
	
	
	ActionListener TimerActions = new ActionListener() 
    {
    	private int currentIndex = 0; 

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (!isReplaying) 
            {
                ((Timer) e.getSource()).stop();
                return;
            }
        	
            if (currentIndex < movementsArray.length()) 
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
                return;
            }
        }
    };
	
	
	public void readHistory(String filename)		// reads and shows the history from the JSON FILE
	{
		try(FileReader fileReader = new FileReader(filename))
		{
			JSONTokener tokener = new JSONTokener(fileReader);
			JSONObject jsonObject = new JSONObject(tokener);
			
            //String startTime = jsonObject.getString("start_time");
            //System.out.println("Start Time: " + startTime);
			
            JSONArray movementsArray = jsonObject.getJSONArray("movements");

            Timer displayTimer = new Timer(3000, new ActionListener() 
            {
            	private int currentIndex = 0; 

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (!isReplaying) 
                    {
                        ((Timer) e.getSource()).stop();
                        return;
                    }
                	
                    if (currentIndex < movementsArray.length()) 
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
                        return;
                    }
                }
            });
            
            displayTimer.setInitialDelay(0); // Start immediately
            displayTimer.start();
            if(!isReplaying)
            {
            	displayTimer.stop();
            	
            }
            
            //displayTimer.removeActionListener(TimerActions);
        } 
		catch (IOException e3) 
		{
            e3.printStackTrace();
        }

	}
	
	public void stopReplay() 
	{
	    isReplaying = false;
	}
	
	public void writeHistory(JSONArray movementsArray ,int player, int column)
	{		
		movementsArray.put(new JSONObject().put("player", player).put("column", column));
	}
}
