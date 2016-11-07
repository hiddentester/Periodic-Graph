/****************************************
	File Name:		PeriodicGraph.java
	Name:				hiddentester
	Version:			1.0.161106a
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
	private static final String VERSION = "1.0.161106a";
	private static final String[] DATASETS = {"Atomic Radii", "Groups", "Periods", "Ionization Energies", "Protons"};
	private static final int ELEMENTS = 118;
	private double[] dataset = new double[ELEMENTS];
	private double maxY;
	
	//Window scaling variables
	private static final int PADDING = 50;
	private static double xScale, yScale;

	Drawing draw = new Drawing();
	JLabel titleBar = new JLabel();
	
	//Data
	private ArrayList elementList = new ArrayList();
	private String xAxis, yAxis;

	//Constructor
	public PeriodicGraph() {
	   JFrame frame = new JFrame (TITLE + " " + VERSION);

	   //Initialize window properties
	   frame.add(draw);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(400, 350));
	   frame.setVisible(true);
		frame.setBackground(Color.white);
		frame.setAlwaysOnTop(false);

	   //Initialize title bar
	   frame.add(titleBar, "North");
	   titleBar.setText(TITLE + " " + VERSION);
	   titleBar.setFont(new Font("Serif", Font.BOLD,30));
	   titleBar.setForeground(Color.black);
	   titleBar.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Load data and initialize graph
		loadData();
		setYAxis(DATASETS[3]);
	} // PeriodicGraph constructor
	
	//Drawing class
	class Drawing extends JComponent {
		public void paint(Graphics g) {
			//Fill background
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//Draw graph
			if (elementList != null && !elementList.isEmpty()) {
		 		//Update scaling factors based on the size of the window
		 		xScale = (getWidth() - 2 * PADDING) / (double)(ELEMENTS + 1);
		 		yScale = (getHeight() - 2 * PADDING - 50) / (maxY + 1);
				
				//Draw axes
				g.setColor(Color.red);
				g.drawLine(PADDING,
					getHeight() - PADDING,
					getWidth() - PADDING,
					getHeight() - PADDING);
				g.drawLine(PADDING,
					PADDING,
					PADDING,
					getHeight() - PADDING);
				
			  	//Draw data points
			  	for (int i = 0; i < dataset.length; i++) {
					if (dataset[i] != -1) {
						//Draw lines
						g.setColor(Color.green);
						if (i > 0 && dataset[i - 1] != -1) {
							g.drawLine(PADDING + (int)((i + 1) * xScale),
								(int)(getHeight() - PADDING - (dataset[i] + 1) * yScale),
								PADDING + (int)(i * xScale),
								(int)(getHeight() - PADDING - (dataset[i - 1] + 1) * yScale));
						} //if structure
						
						//Draw points
						g.setColor(Color.yellow);
						
						g.fillRect(PADDING + (int)((i + 1) * xScale) - 2,
							(int)(getHeight() - PADDING - (dataset[i] + 1) * yScale) - 2,
							4,
							4);
					}//if structure
			 	} //for loop
			} //if structure
	   } // paint method
	} // Drawing class
	
	public void loadData () {
		String input;
		BufferedReader in;
		
		for (int i = 0; i < ELEMENTS; i++) {
			elementList.add(new Element());
		} //for loop
				
		//Load data from file
		for (int i = 0; i < ELEMENTS; i++) {
			try {
			in = new BufferedReader(new FileReader("Elements/" + (i + 1) + ".txt"));
				Element cur = ((Element)elementList.get(i));
				cur.setName(in.readLine());
				cur.setSymbol(in.readLine());
				cur.setGroup(Integer.parseInt(in.readLine()));
				cur.setPeriod(Integer.parseInt(in.readLine()));
				cur.setRadius(Integer.parseInt(in.readLine()));
				cur.setIonization(Double.parseDouble(in.readLine()));
			in.close();
			} catch (IOException e) {
				System.err.println("Elements/" + (i + 1) + ".txt could not be found.");
			} catch (Exception e) {
				System.err.println("There was a problem with Elements/" + (i + 1) + ".txt: " + e.getMessage());
			} //try-catch structure
		} //for loop
	} //loadData method
	
	public void setXAxis (String xAxis) {
		this.xAxis = xAxis;
	} //setXAxis method
	
	public void setYAxis (String yAxis) {
		//Copy dataset
		if (elementList != null && !elementList.isEmpty()) {
			//Atomic Radii
			if (yAxis.equals(DATASETS[0])) {
				for (int i = 0; i < ELEMENTS; i++) {
					dataset[i] = ((Element)elementList.get(i)).getRadius();
				} //for loop
				titleBar.setText(DATASETS[0]);
			//Group
			} else if (yAxis.equals(DATASETS[1])) {
				for (int i = 0; i < ELEMENTS; i++) {
					dataset[i] = ((Element)elementList.get(i)).getGroup();
				} //for loop
				titleBar.setText(DATASETS[1]);
			//Period
			} else if (yAxis.equals(DATASETS[2])) {
				for (int i = 0; i < ELEMENTS; i++) {
					dataset[i] = ((Element)elementList.get(i)).getPeriod();
				} //for loop
				titleBar.setText(DATASETS[2]);
			//Ionization Energies
			} else if (yAxis.equals(DATASETS[3])) {
				for (int i = 0; i < ELEMENTS; i++) {
					dataset[i] = ((Element)elementList.get(i)).getIonization();
				} //for loop
				titleBar.setText(DATASETS[3]);
			//Protons
			} else {
				for (int i = 0; i < ELEMENTS; i++) {
					dataset[i] = i;
				} //for loop
				titleBar.setText(DATASETS[4]);
			} //if structure
			
			//Find maximum y value
			maxY = dataset[0];
			
			for (int i = 1; i < ELEMENTS; i++) {
				maxY = Math.max(maxY, dataset[i]);
			} //for loop
		} //if structure
	} //setYAxis method
	
	public String getXAxis () {
		return xAxis;
	} //setXAxis method
	
	public String getYAxis () {
		return yAxis;
	} //setXAxis method
	
	//Main method
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
	   PeriodicGraph graph = new PeriodicGraph();
		
		while (true) {
			graph.setYAxis(sc.nextLine());
			graph.draw.repaint();
		} //while loop
	} // main method
} // PeriodicGraph class