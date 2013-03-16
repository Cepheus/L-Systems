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
 * * Project: L-Systems* File: ListSymbols.java** Created on: 26 févr. 2013* Author: xinouch*
 * *****************************************************
 */

package parser;

import java.util.ArrayList;


/**
 * Represent a list of symbols with specific methods to help the finding of a specific symbol.
 * 
 * @author xinouch
 */
public class ListSymbols implements Cloneable
{
	/** List of the symbols */
	private ArrayList<Symbol> symbols = new ArrayList<Symbol>();

	/**
	 * default constructor
	 */
	public ListSymbols ()
	{
	}

	@SuppressWarnings ("unchecked")
	@Override
	public ListSymbols clone ()
	{
		ListSymbols list = new ListSymbols();
		list.setSymbols((ArrayList<Symbol>) symbols.clone());

		return list;
	}

	/**
	 * return the symbol if it exists in the list, null otherwise
	 * 
	 * @param sym the symbol to find
	 * @return the symbol if it exists in the list, null otherwise
	 */
	public Symbol find (char sym)
	{
		final int size = symbols.size();
		int i = 0;

		while (i < size)
		{
			if (symbols.get(i).getCharacter() == sym)
				return symbols.get(i);
			i++;
		}
		return null;
	}

	/**
	 * return true if the symbol exists in the list, false otherwise
	 * 
	 * @param sym the symbol to find
	 * @return true if the symbol exists in the list, false otherwise
	 */
	public boolean contains (char sym)
	{
		final int size = symbols.size();
		int i = 0;

		while (i < size)
		{
			if (symbols.get(i).getCharacter() == sym)
				return true;
			i++;
		}
		return false;
	}

	/**
	 * return true if the symbol exists in the list, false otherwise/ Equivalent to call containSymbol(sym.getCharacter()).
	 * 
	 * @param sym the symbol to find
	 * @return true if the symbol exists in the list, false otherwise
	 */
	public boolean contains (Symbol sym)
	{
		return contains(sym.getCharacter());
	}

	/**
	 * append the symbol
	 * 
	 * @param sym
	 */
	public void append (Symbol sym)
	{
		symbols.add(sym);
	}

	/**
	 * add the element at the given index. put the element at the given index, so the actual element at index will be at index+1 after this
	 * function.
	 * 
	 * @param index
	 * @param sym
	 */
	public void add (int index, Symbol sym)
	{
		symbols.add(index, sym);
	}

	/**
	 * remove all the occurence of sym in the list
	 * 
	 * @param sym
	 * @return true if the list has changed (there were some occurences of sym).
	 */
	public boolean remove (Symbol sym)
	{
		boolean retour = false;

		for (int i = symbols.size() - 1; i >= 0; i--)
		{
			if (symbols.get(i).getCharacter() == sym.getCharacter())
			{
				symbols.remove(i);
				i--;
				retour = true;
			}
		}

		return retour;
	}

	/**
	 * remove the symbol at the given index
	 * 
	 * @param index
	 */
	public void remove (int index)
	{
		symbols.remove(index);
	}

	/**
	 * change the symbol at index by the symbol sym
	 * 
	 * @param index
	 * @param sym
	 */
	public void set (int index, Symbol sym)
	{
		symbols.set(index, sym);
	}

	@Override
	public String toString ()
	{
		String str = "";
		final int size = symbols.size();

		for (int i = 0; i < size; i++)
			str += symbols.get(i).getCharacter();

		return str;
	}

	/**
	 * return the symbol at the given index
	 * 
	 * @param index
	 * @return the symbol at the given index
	 */
	public Symbol get (int index)
	{
		return symbols.get(index);
	}

	/**
	 * @return the symbols
	 */
	public ArrayList<Symbol> getSymbols ()
	{
		return symbols;
	}

	/**
	 * @param symbols the symbols to set
	 */
	public void setSymbols (ArrayList<Symbol> symbols)
	{
		this.symbols = symbols;
	}

	/**
	 * @return the size of the list
	 */
	public int size ()
	{
		return symbols.size();
	}
}
