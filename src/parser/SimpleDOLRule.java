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
 **             File: SimpleDOLContextFreeRule.java
 **
 **       Created on: 26 févr. 2013
 **           Author: xinouch
 **
 ****************************************************** */

package parser;

/**
 * Represent a rule for a determinist context-free L-system, where preExpr contains only one symbol.
 * @author xinouch
 *
 */
public class SimpleDOLRule implements Rule
{
	/** Symbol to be replaced in the rule */
	private Symbol preExpr;
	/** List of symbols that will replace preExpr */
	private ListSymbols postExpr;
	
	/**
	 * Constructor
	 * @param pre
	 * @param post
	 */
	public SimpleDOLRule (Symbol pre, ListSymbols post)
	{
		preExpr = pre;
		postExpr = post;
	}
	
	@Override
	public boolean canBeApplied (ListSymbols phrase)
	{
		return phrase.contains(preExpr);
	}

	@Override
	public boolean apply (ListSymbols phrase)
	{
		boolean retour = false;
		final int size = phrase.size(), sizej = postExpr.size();

		if (postExpr.get(0).getCharacter() == 'ε')
			retour = phrase.remove(preExpr);
		else
		{
			for (int i = 0; i < size; i++)
			{
				if (phrase.get(i).getCharacter() == preExpr.getCharacter())
				{
					phrase.set(i, postExpr.get(0));
					for (int j = 1; j < sizej; j++)
						phrase.add(i + j, postExpr.get(j));
					retour = true;
				}
			}
		}

		return retour;
	}

	@Override
	public int applyOnce (int index, ListSymbols phraseFromPreviousIteration, ListSymbols toBeModified)
	{
		final int size = postExpr.size();
		
		if (toBeModified.get(index).getCharacter() != preExpr.getCharacter())
			return 0;
		
		if (postExpr.get(0).getCharacter() == 'ε')
		{
			toBeModified.remove(index);
			return -1;
		}
		
		toBeModified.set(index, postExpr.get(0));
		for (int i = 1; i < size; i++)
			toBeModified.add(index + i, postExpr.get(i));
		return size;
	}

	@Override
	public int getProba ()
	{
		return 1;
	}

	/**
	 * @return the preExpr
	 */
	public Symbol getPreExpr ()
	{
		return preExpr;
	}

	/**
	 * @param preExpr the preExpr to set
	 */
	public void setPreExpr (Symbol preExpr)
	{
		this.preExpr = preExpr;
	}

	/**
	 * @return the postExpr
	 */
	public ListSymbols getPostExpr ()
	{
		return postExpr;
	}

	/**
	 * @param postExpr the postExpr to set
	 */
	public void setPostExpr (ListSymbols postExpr)
	{
		this.postExpr = postExpr;
	}
}
