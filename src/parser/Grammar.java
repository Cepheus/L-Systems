/* ******************************************************
 **	Copyright (C) 2013  xinouch
 **
 **	This file is part of L-Systems
 **
 **	L-Systems is free software: you can redistribute it and/or modify
 **	it under the terms of the GNU General Public License as published by
 **	the Free Software Foundation, either version 3 of the License, or
 **	(at your option) any later version.
 **
 **	This program is distributed in the hope that it will be useful,
 **	but WITHOUT ANY WARRANTY; without even the implied warranty of
 **	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 **	GNU General Public License for more details.
 **
 **	You should have received a copy of the GNU General Public License
 **	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ****************************************************** */
/* ******************************************************
 **
 **          Project: L-Systems
 **             File: Grammar.java
 **
 **       Created on: 26 févr. 2013
 **           Author: xinouch
 **
 ****************************************************** */

package parser;

import java.util.ArrayList;

/**
 * Represent a grammar.
 * A grammar contains a list of rules, a list of usable symbols, and an axiom (which can be just a symbol or
 * a list of symbols). It also has an attribute "angle" that gives the angle to turn (default is 90°).
 * <p>Before using a Grammar, you have to setup the list of usable symbols, the axiom and the list of rules.</p>
 * @author xinouch
 *
 */
public class Grammar
{
	/** The alphabet of the grammar */
	private ListSymbols usableSymbols = new ListSymbols();
	/** The axiom (one symbol or a phrase) */
	private ListSymbols axiome = new ListSymbols();
	/** The list of rules */
	private ArrayList<Rule> rules = new ArrayList<Rule>();
	/** The angle to turn in turtle interpretation (default is 90°) */
	private int angle = 90;
	
	/**
	 * @return the usableSymbols
	 */
	public ListSymbols getUsableSymbols ()
	{
		return usableSymbols;
	}
	
	/**
	 * @param usableSymbols the usableSymbols to set
	 */
	public void setUsableSymbols (ListSymbols usableSymbols)
	{
		this.usableSymbols = usableSymbols;
	}
	
	/**
	 * @return the axiome
	 */
	public ListSymbols getAxiome ()
	{
		return axiome;
	}
	
	/**
	 * @param axiome the axiome to set
	 */
	public void setAxiome (ListSymbols axiome)
	{
		this.axiome = axiome;
	}
	
	/**
	 * @return the rules
	 */
	public ArrayList<Rule> getRules ()
	{
		return rules;
	}
	
	/**
	 * @param rules the rules to set
	 */
	public void setRules (ArrayList<Rule> rules)
	{
		this.rules = rules;
	}
	
	/**
	 * @return the angle
	 */
	public int getAngle ()
	{
		return angle;
	}
	
	/**
	 * @param angle the angle to set
	 */
	public void setAngle (int angle)
	{
		this.angle = angle;
	}
}
