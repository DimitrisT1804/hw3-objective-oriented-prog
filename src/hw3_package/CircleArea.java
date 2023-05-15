package hw3_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class CircleArea extends JComponent 
{

    /**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	final static int ROWS = 6;
    final static int COLUMNS = 7;
    private final int CIRCLE_DIAMETER = 100;
    private static int CIRCLE_GAP = 20; // gap between circles
    private static int BORDER_SIZE = 50;
    private Color[][] circleColors;
    public static int CELL_SIZE = 20;
    
    
    
    
    public CircleArea() 
    {
        circleColors = new Color[ROWS][COLUMNS];
        // Initialize all circles to white
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                circleColors[row][column] = Color.WHITE;
            }
            
        }
        addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / CELL_SIZE;
                int col = e.getX() / CELL_SIZE;
                if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
                    setCircleColor(row, col, Color.RED); // Change the color of the clicked circle to red
                }
            }
        });
    }
    
    public void setCircleColor(int row, int column, Color color) 
    {
        circleColors[row][column] = color;
        repaint();
    }
    

    
    public int getRadius()
    {
    	return CIRCLE_DIAMETER;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the border around the circles
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the circles
        //g.setColor(Color.WHITE);
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int x = BORDER_SIZE + column * (CIRCLE_DIAMETER + CIRCLE_GAP);
                int y = BORDER_SIZE + row * (CIRCLE_DIAMETER + CIRCLE_GAP);
                g.setColor(circleColors[row][column]);
                g.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            }
        }
    }
    
    public void clear()
    {
        for (int row = 0; row < ROWS; row++) 
        {
            for (int column = 0; column < COLUMNS; column++) 
            {
                circleColors[row][column] = Color.WHITE;
            }
        }
    }

    
    
    @Override
    public Dimension getPreferredSize() {
        // Calculate the preferred size based on the number of rows and columns
        int width = COLUMNS * (CIRCLE_DIAMETER + CIRCLE_GAP) - CIRCLE_GAP + 2 * BORDER_SIZE;
        int height = ROWS * (CIRCLE_DIAMETER + CIRCLE_GAP) - CIRCLE_GAP + 2 * BORDER_SIZE;
        return new Dimension(width, height);
    }
}