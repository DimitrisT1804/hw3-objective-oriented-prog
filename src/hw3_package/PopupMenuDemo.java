package hw3_package;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
 
/* PopupMenuDemo.java requires images/middle.gif. */
 
/*
 * Like MenuDemo, but with popup menus added.
 */
public class PopupMenuDemo implements ActionListener {
  JTextArea output;
  JScrollPane scrollPane;
  String newline = "\n";
 
  public Container createContentPane() {
    //Create the content-pane-to-be.
    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);
 
    //Create a scrolled text area.
    output = new JTextArea(5, 30);
    output.setEditable(false);
    scrollPane = new JScrollPane(output);
 
    //Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.CENTER);
 
    return contentPane;
  }
 
  public void createPopupMenu() {
    JMenuItem menuItem;
 
    //Create the popup menu.
    JPopupMenu popup = new JPopupMenu();
    menuItem = new JMenuItem("A popup menu item");
    menuItem.addActionListener(this);
    popup.add(menuItem);
    menuItem = new JMenuItem("Another popup menu item");
    menuItem.addActionListener(this);
    popup.add(menuItem);
 
    //Add listener to the text area so the popup menu can come up.
    MouseListener popupListener = new PopupListener(popup);
    output.addMouseListener(popupListener);
  }
 
  public void actionPerformed(ActionEvent e) {
    JMenuItem source = (JMenuItem)(e.getSource());
    String s = "Action event detected."
           + newline
           + "  Event source: " + source.getText()
           + " (an instance of " + getClassName(source) + ")";
    output.append(s + newline);
    output.setCaretPosition(output.getDocument().getLength());
  }
 
  // Returns just the class name -- no package info.
  protected String getClassName(Object o) {
    String classString = o.getClass().getName();
    int dotIndex = classString.lastIndexOf(".");
    return classString.substring(dotIndex+1);
  }
 
  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = PopupMenuDemo.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
 
  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("PopupMenuDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    //Create/set menu bar and content pane.
    PopupMenuDemo demo = new PopupMenuDemo();
    frame.setContentPane(demo.createContentPane());
 
    //Create and set up the popup menu.
    demo.createPopupMenu();
 
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
  }
 
  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
 
  class PopupListener extends MouseAdapter {
    JPopupMenu popup;
 
    PopupListener(JPopupMenu popupMenu) {
      popup = popupMenu;
    }
 
    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }
 
    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }
 
    private void maybeShowPopup(MouseEvent e) {
      if (e.isPopupTrigger()) {
        popup.show(e.getComponent(),
               e.getX(), e.getY());
      }
    }
  }
}