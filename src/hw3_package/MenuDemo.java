package hw3_package;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class MenuDemo extends JFrame implements ActionListener {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 250;
 
  private JPanel redPanel;
  private JPanel whitePanel;
  private JPanel bluePanel;
 
  public static void main(String args[]) {
    MenuDemo gui = new MenuDemo();
    gui.setVisible(true);
  }
 
  public MenuDemo() {
    super("Panel Demonstration with Menu Selection");
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
 
    JPanel biggerPanel = new JPanel();
    biggerPanel.setLayout(new GridLayout(1,3));
 
    redPanel = new JPanel();
    redPanel.setBackground(Color.LIGHT_GRAY);
    biggerPanel.add(redPanel);
 
    whitePanel = new JPanel();
    whitePanel.setBackground(Color.LIGHT_GRAY);
    biggerPanel.add(whitePanel);
 
    bluePanel = new JPanel();
    bluePanel.setBackground(Color.LIGHT_GRAY);
    biggerPanel.add(bluePanel);
 
    add(biggerPanel, BorderLayout.CENTER);
 
    JMenu colorMenu = new JMenu("Choose Color");
    colorMenu.setBackground(Color.LIGHT_GRAY);
    colorMenu.setMnemonic(KeyEvent.VK_C);
 
    JMenuItem redMenuItem = new JMenuItem("Red");
    redMenuItem.setBackground(Color.RED);
    redMenuItem.addActionListener(this);    
    redMenuItem.setMnemonic(KeyEvent.VK_R);
    colorMenu.add(redMenuItem);
 
    JMenuItem whiteMenuItem = new JMenuItem("White");
    whiteMenuItem.setBackground(Color.WHITE);
    whiteMenuItem.addActionListener(this);
    whiteMenuItem.setMnemonic(KeyEvent.VK_W);
    colorMenu.add(whiteMenuItem);
 
    JMenuItem blueMenuItem = new JMenuItem("Blue");
    blueMenuItem.setBackground(Color.BLUE);
    blueMenuItem.addActionListener(this);
    blueMenuItem.setMnemonic(KeyEvent.VK_B);
    colorMenu.add(blueMenuItem);
 
    JMenuBar bar = new JMenuBar();
    bar.add(colorMenu);
    setJMenuBar(bar);  
 
  }
 
  public void actionPerformed(ActionEvent e) {
    String menuString = e.getActionCommand();
    if(menuString.equals("Red") ) {
      redPanel.setBackground(Color.RED);
      whitePanel.setBackground(Color.LIGHT_GRAY);
      bluePanel.setBackground(Color.LIGHT_GRAY);
    }
    else if(menuString.equals("White") ) {
      redPanel.setBackground(Color.LIGHT_GRAY);
      whitePanel.setBackground(Color.WHITE);
      bluePanel.setBackground(Color.LIGHT_GRAY);
    }
    else if(menuString.equals("Blue") ) {
      redPanel.setBackground(Color.LIGHT_GRAY);
      whitePanel.setBackground(Color.LIGHT_GRAY);
      bluePanel.setBackground(Color.BLUE);
    }
  }
}