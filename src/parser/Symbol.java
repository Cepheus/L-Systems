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

	@Override
	public String toString ()
	{
		String s = "";

		s += String.valueOf(character);
		if ((interpretation != 0) && (interpretation != S_NULLCHARACTER))
		{
			s += ": ";
			switch (interpretation)
			{
				case S_FORWARD:
					s += "FORWARD";
					break;
				case S_TURNLEFT:
					s += "TURNLEFT";
					break;
				case S_TURNRIGHT:
					s += "TURNRIGHT";
					break;
				case S_TURNUP:
					s += "TURNUP";
					break;
				case S_TURNDOWN:
					s += "TURNDOWN";
					break;
				case S_ROLLLEFT:
					s += "ROLLLEFT";
					break;
				case S_ROLLRIGHT:
					s += "ROLLRIGHT";
					break;
				case S_ABOUTTURN:
					s += "ABOUTTURN";
					break;
				case S_SAVEPOSITION:
					s += "SAVEPOSITION";
					break;
				case S_RESTOREPOSITION:
					s += "RESTOREPOSITION";
					break;
			}
		}

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
	 * @param interpretation the interpretation to set
	 */
	public void setInterpretation (int interpretation)
	{
		this.interpretation = interpretation;
	}
}
