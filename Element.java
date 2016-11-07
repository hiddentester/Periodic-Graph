/*************************************************
	File Name:		Element.java
	Name:				hiddentester
	Version:			1.0.16110501
	Description:	This program defines an element.
 *************************************************/

import java.util.ArrayList;

public class Element {
	private String name, symbol;
	private int protons, group, period, radius;
	private double mass, ionization;
	
	//Blank Element
	public Element () {
		protons = -1;
		group = -1;
		period = -1;
		radius = -1;
		mass = -1;
		ionization = -1;
	} //Element constructor
	
	//Mutators
	public void setName (String name) {
		this.name = name;
	} //setName method
	
	public void setSymbol (String symbol) {
		this.symbol = symbol;
	} //setSymbol method
	
	public void setProtons (int protons) {
		this.protons = protons;
	} //setProtons method
	
	public void setGroup (int group) {
		this.group = group;
	} //setGroup method
	
	public void setPeriod (int period) {
		this.period = period;
	} //setPeriod method
	
	public void setMass (double mass) {
		this.mass = mass;
	} //setMass method
	
	public void setRadius (int radius) {
		this.radius = radius;
	} //setRadius method
	
	public void setIonization (double ionization) {
		this.ionization = ionization;
	} //setIonization method
	
	//Accessors
	public String getName () {
		return name;
	} //getName method
	
	public String getSymbol () {
		return symbol;
	} //getSymbol method
	
	public int getProtons () {
		return protons;
	} //getProtons method
	
	public int getGroup () {
		return group;
	} //getGroup method
	
	public int getPeriod () {
		return period;
	} //getPeriod method
	
	public double getMass () {
		return mass;
	} //getMass method
	
	public int getRadius () {
		return radius;
	} //getRadius method
	
	public double getIonization () {
		return ionization;
	} //getIonization method
} //Element class