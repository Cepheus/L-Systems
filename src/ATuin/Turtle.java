/* ******************************************************
 * * Copyright (C) 2013 Cepheus This file is part of L-Systems** L-Systems is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by* the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should
 * have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************
 */
/* ******************************************************
 * *
 * * Project: L-Systems File: Turtle.java Created on: 08 march. 2013 Author: Cepheus *****************************************************
 */

package ATuin;

import parser.ListSymbols;


/**
 * Default class of a turtle interpretation. This class factorise the differents turtle interpretation. If you wish to create a new turtle
 * interpretation you should extends this class and implements its methods.
 * 
 * @author Caelum
 */
public abstract class Turtle
{

        /** The name of the turtle */
        protected String name;
    
	/** The list of symbols to interpret */
	public ListSymbols symbols;

	/** The Drawer of the 3D scene */
	protected Drawer drawer;

	/**
	 * Default constructor.
	 */
	public Turtle ()
	{

	}

	/**
	 * Constructor.
	 * 
	 * @param drawer The object drawer of the scene
	 */
	public Turtle (Drawer drawer)
	{
		this();
		this.drawer = drawer;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param drawer The object drawer of the scene
	 * @param symbols The symbols to represent
	 */
	public Turtle (Drawer drawer, ListSymbols symbols)
	{
		this(drawer);
		this.symbols = symbols;
	}

	/**
	 * Checks if the interpretation can interpret all the symbols.
	 * 
	 * @return true if all is ok, false if all symbols can't be interpreted
	 */
	public abstract boolean checkSymbols ();

	/**
	 * Draws the list of symbols depending of the turtle's interpretation.
	 * @throws BadInterpretationException 
	 */
	public abstract void drawSymbols () throws BadInterpretationException;

	/**
	 * @param drawer the drawer to set
	 */
	public void setDrawer (Drawer drawer)
	{
		this.drawer = drawer;
	}

	/**
	 * @param symbols the symbols to set
	 */
	public void setSymbols (ListSymbols symbols)
	{
		this.symbols = symbols;
	}
        
	/**
	 * @return the name
	 */
	public String getName ()
	{
		return name;
	}

}
