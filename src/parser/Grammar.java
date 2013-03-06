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
 * * Project: L-Systems* File: Grammar.java** Created on: 26 févr. 2013* Author: xinouch*
 * *****************************************************
 */

package parser;

import java.util.ArrayList;


/**
 * Represent a grammar. A grammar contains a list of rules, a list of usable symbols, and an axiom (which can be just a symbol or a list of
 * symbols). It also has an attribute "angle" that gives the angle to turn (default is 90°).
 * <p>
 * Before using a Grammar, you have to setup the list of usable symbols, the axiom and the list of rules.
 * </p>
 * 
 * @author xinouch
 */
public class Grammar
{
	/** La grammaire est de type DOL */
	public static final int TYPE_DOL = 1;
	/** La grammaire est de type SOL */
	public static final int TYPE_SOL = 2;
	/** La grammaire est de type DXL */
	public static final int TYPE_DXL = 3;
	/** La grammaire est de type SXL */
	public static final int TYPE_SXL = 4;

	/** The alphabet of the grammar */
	private ListSymbols usableSymbols = new ListSymbols();
	/** The axiom (one symbol or a phrase) */
	private ListSymbols axiom = new ListSymbols();
	/** The list of rules */
	private ArrayList<Rule> rules = new ArrayList<Rule>();
	/** The angle to turn in turtle interpretation (default is 90°) */
	private int angle = 90;
	/** The type of the grammar (one of the constant class value, 0 if unknown) */
	private int type = 0;
	/** The name of the grammar (given by the user) */
	private String name = "";

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
	public ListSymbols getAxiom ()
	{
		return axiom;
	}

	/**
	 * @param axiome the axiome to set
	 */
	public void setAxiom (ListSymbols axiome)
	{
		this.axiom = axiome;
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

	/**
	 * @return the type (one of the constant class value, 0 if unknown)
	 */
	public int getType ()
	{
		return type;
	}

	/**
	 * @param type the type to set (one of the constant class value, 0 if unknown)
	 */
	public void setType (int type)
	{
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName (String name)
	{
		this.name = name;
	}

	@Override
	public String toString ()
	{
		String s = "";

		s += name + "\n";
		switch (type)
		{
			case TYPE_DOL:
				s += "DOL\n";
				break;
			case TYPE_SOL:
				s += "SOL\n";
				break;
			case TYPE_DXL:
				s += "DXL\n";
				break;
			case TYPE_SXL:
				s += "SXL\n";
				break;
		}

		s += "{\n";
		// usable symbols
		s += "\tSYMBOLS\n\t{\n";
		for (int i = 0; i < usableSymbols.size(); i++)
		{
			if (usableSymbols.get(i).getInterpretation() != Symbol.S_NULLCHARACTER)
				s += "\t\t" + usableSymbols.get(i) + "\n";
		}
		s += "\t}\n";
		// axiom
		s += "\tAXIOM: " + axiom + "\n";
		// angle
		s += "\tANGLE: " + angle + "\n";
		// rules
		s += "\tRULES\n\t{\n";
		for (int i = 0; i < rules.size(); i++)
			s += "\t\t" + rules.get(i) + "\n";
		s += "\t}\n";

		s += "}\n\n";

		return s;
	}
}
