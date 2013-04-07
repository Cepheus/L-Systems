/* ******************************************************
 * * Copyright (C) 2013 xinouch** This file is part of L-Systems** L-Systems is free software: you can redistribute it and/or modify* it
 * under the terms of the GNU General Public License as published by* the Free Software Foundation, either version 3 of the License, or* (at
 * your option) any later version.** This program is distributed in the hope that it will be useful,* but WITHOUT ANY WARRANTY; without even
 * the implied warranty of* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the* GNU General Public License for more details.** You
 * should have received a copy of the GNU General Public License* along with this program. If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************
 */
/* ******************************************************
 * *
 * * Project: L-Systems* File: Symbol.java** Created on: 26 févr. 2013* Author: xinouch*
 * *****************************************************
 */

package parser;

/**
 * Represent a symbol of the grammar. A symbol is represented by a character (char) and an interpretation (int). This interpretation can be
 * one of the static values of this class, or a personalized one. If you use personalized interpretation, please make sure the int is
 * strictly greater than 20. an intepretation of 0 is invalid.
 * 
 * @author xinouch
 */
public class Symbol
{
	/** Turtle interpretation: make the turtle go forward */
	public static final int S_UNDETERMINATE = 0;
	/** Turtle interpretation: make the turtle go forward */
	public static final int S_FORWARD = 1;
	/** Turtle interpretation: make the turtle turn left */
	public static final int S_TURNLEFT = 2;
	/** Turtle interpretation: make the turtle turn right */
	public static final int S_TURNRIGHT = 3;
	/** Turtle interpretation: make the turtle turn up (3D) */
	public static final int S_TURNUP = 4;
	/** Turtle interpretation: make the turtle turn down (3D) */
	public static final int S_TURNDOWN = 5;
	/** Turtle interpretation: make the turtle roll on the left */
	public static final int S_ROLLLEFT = 6;
	/** Turtle interpretation: make the turtle roll on the right */
	public static final int S_ROLLRIGHT = 7;
	/** Turtle interpretation: make the turtle turn 180° */
	public static final int S_ABOUTTURN = 8;
	/** Turtle interpretation: save current position */
	public static final int S_SAVEPOSITION = 9;
	/** Turtle interpretation: restore last saved position */
	public static final int S_RESTOREPOSITION = 10;
	/** Special case for null character 'ε' */
	public static final int S_NULLCHARACTER = 11;

	/** Symbol */
	private char character = '\0';
	/** Interpretation */
	private int interpretation = 0;
	/** The name of the symbol */
	private String name = "";

	/**
	 * default constructor
	 */
	public Symbol ()
	{
	}

	/**
	 * cnstructor
	 * 
	 * @param car
	 */
	public Symbol (char car)
	{
		character = car;
	}

	/**
	 * constructor
	 * 
	 * @param car
	 * @param inter
	 */
	public Symbol (char car, int inter)
	{
		character = car;
		interpretation = inter;
	}

	/**
	 * @brief return the list of the standard symbols.
	 * The last value is the symbol S_NULLCHARACTER. it can easily be removed, and it is the only one that have a character (ε)
	 * @return the list of the standard symbols
	 */
	public static ListSymbols standardsSymbols ()
	{
		ListSymbols ls = new ListSymbols();
		Symbol sym;

		sym = new Symbol();
		sym.setInterpretation(S_FORWARD);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_TURNLEFT);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_TURNRIGHT);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_TURNUP);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_TURNDOWN);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_ROLLLEFT);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_ROLLRIGHT);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_ABOUTTURN);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_SAVEPOSITION);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_RESTOREPOSITION);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_UNDETERMINATE);
		ls.append(sym);

		sym = new Symbol();
		sym.setInterpretation(S_NULLCHARACTER);
		sym.setCharacter('ε');
		ls.append(sym);

		return ls;
	}

	@Override
	public String toString ()
	{
		String s = "";

		s += String.valueOf(character);
		if ((interpretation > 0) && (interpretation != S_NULLCHARACTER) && (interpretation < 20))
			s += ": " + name;

		return s;
	}

	/**
	 * @return the character
	 */
	public char getCharacter ()
	{
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter (char character)
	{
		this.character = character;
	}

	/**
	 * @return the interpretation
	 */
	public int getInterpretation ()
	{
		return interpretation;
	}

	/**
	 * update the interpretation and the name
	 * 
	 * @param interpretation the interpretation to set
	 */
	public void setInterpretation (int interpretation)
	{
		this.interpretation = interpretation;
		findNameFromInterpretation();
	}

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * THIS MUST BE CALLED ONLY WHEN DEFINE NEW SYMBOLS!
	 * 
	 * @param name the name to set
	 */
	public void setName (String name)
	{
		this.name = name;
	}

	/**
	 * Update the name in function of the interpretation. if the interpretation is not one of the constat class value, the name is set to
	 * "".
	 */
	private void findNameFromInterpretation ()
	{
		switch (interpretation)
		{
			case S_FORWARD:
				name = "FORWARD";
				break;
			case S_TURNLEFT:
				name = "TURNLEFT";
				break;
			case S_TURNRIGHT:
				name = "TURNRIGHT";
				break;
			case S_TURNUP:
				name = "TURNUP";
				break;
			case S_TURNDOWN:
				name = "TURNDOWN";
				break;
			case S_ROLLLEFT:
				name = "ROLLLEFT";
				break;
			case S_ROLLRIGHT:
				name = "ROLLRIGHT";
				break;
			case S_ABOUTTURN:
				name = "ABOUTTURN";
				break;
			case S_SAVEPOSITION:
				name = "SAVEPOSITION";
				break;
			case S_RESTOREPOSITION:
				name = "RESTOREPOSITION";
				break;
			case S_UNDETERMINATE:
				name = "UNDETERMINATE";
				break;
			default:
				name = "UNDETERMINATE";
				break;
		}
	}
}
