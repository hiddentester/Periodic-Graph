/****************************************
	File Name:		PeriodicGraph.java
	Name:				hiddentester
	Version:			1.0.16110502
	Description:	This program runs code.
 ****************************************/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PeriodicGraph {
	//Program information
	private static final String TITLE = "Periodic Graph";
	private static final String VERSION = "1.0.16110502";
	private static final int ELEMENTS = 118;
	private double maxY;
	
	//Window scaling variables
	private static final int PADDING = 50;
	private static double xScale, yScale;

	Drawing draw = new Drawing();
	JLabel titleBar = new JLabel();
	
	//Data
	private ArrayList data;

	//Constructor
	public PeriodicGraph() {
	   JFrame frame = new JFrame (TITLE + " " + VERSION);

	   //Initialize window properties
	   frame.add(draw);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(300, 225));
	   frame.setVisible(true);
		frame.setBackground(Color.white);
		frame.setAlwaysOnTop(false);

	   //Initialize title bar
	   frame.add(titleBar, "North");
	   titleBar.setText(TITLE + " " + VERSION);
	   titleBar.setFont(new Font("Serif", Font.BOLD,30));
	   titleBar.setForeground(Color.black);
	   titleBar.setHorizontalAlignment(SwingConstants.CENTER);
	} // PeriodicGraph constructor
	
	//Drawing class
	class Drawing extends JComponent {			
		public void paint(Graphics g) {
			//Fill background
				g.setColor(Color.black);
		 	  	g.fillRect(0, 0, getWidth(), getHeight());
			
			//Draw graph
			if (data != null && !data.isEmpty()) {
		 		//Update scaling factors based on the size of the window
		 		xScale = (getWidth() - 2 * PADDING) / (double)ELEMENTS;
		 		yScale = (getHeight() - 2 * PADDING - 50) / maxY;
				
				//Draw axes
				g.setColor(Color.red);
				g.drawLine(PADDING,
					getHeight() - 2 * PADDING,
					getWidth() - 2 * PADDING,
					getHeight() - 2 * PADDING);
				g.drawLine(PADDING,
					PADDING,
					PADDING,
					getHeight() - 2 * PADDING);
				
			  	//Draw data points
			  	for (int i = 0; i < data.size(); i++) {
					if ((double)data.get(i) != -1) {
						//Draw lines
						g.setColor(Color.green);
						if (i > 0 && (double)data.get(i - 1) != -1.0) {
							g.drawLine(PADDING + (int)((i) * xScale),
								(int)(getHeight() - (2 * PADDING) - (double)data.get(i) * yScale),
								PADDING + (int)((i - 1) * xScale),
								(int)(getHeight() - (2 * PADDING) - (double)data.get(i - 1) * yScale));
						} //if structure
						
						//Draw points
						g.setColor(Color.yellow);
						g.fillRect(PADDING + (int)((i) * xScale) - 2,
							(int)(getHeight() - (2 * PADDING) - (double)data.get(i) * yScale) - 2,
							4,
							4);
					}//if structure
			 	} //for loop
			} //if structure
	   } // paint method
	} // Drawing class
	
	public void loadData (String dataSet) {
		String input;
		BufferedReader in;
		data = new ArrayList();
		
		//Load data from file
		try {
			in = new BufferedReader(new FileReader(dataSet + ".txt"));
				
	      while ((input = in.readLine()) != null) {
	         data.add(Double.parseDouble(input));
	      } //while loop
				
			in.close();
			maxY = (double)data.get(0);
			
			for (int i = 1; i < data.size(); i++) {
				maxY = Math.max(maxY, (double)data.get(i));
			} //for loop
			
			//Update Title
			titleBar.setText(dataSet);
      } catch (Exception e) {
			titleBar.setText(TITLE + " " + VERSION);
         System.err.println("There was a problem with the file.");
         System.err.println("Exception: " + e.getMessage());
      } //try-catch structure
	} //loadData method
	
	//Main method
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
	   PeriodicGraph graph = new PeriodicGraph();
		
		while (true) {
			graph.loadData(sc.nextLine());
			graph.draw.repaint();
		} //while loop
	} // main method
} // PeriodicGraph class