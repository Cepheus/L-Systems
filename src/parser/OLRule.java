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
 * Represent a rule for a context-free L-system, where preExpr contains only one symbol.
 * @author xinouch
 *
 */
public class OLRule implements Rule
{
	/** Symbol to be replaced in the rule */
	private Symbol preExpr;
	/** List of symbols that will replace preExpr */
	private ListSymbols postExpr;
	/** True if the rule is part of a determinist grammar, false otherwise */
	private boolean determinist = true;
	/** The probability of the rule */
	private double proba = 1;
	
	/**
	 * Constructor
	 */
	public OLRule ()
	{
		postExpr = new ListSymbols();
	}
	
	/**
	 * Constructor
	 * @param isDeterminist true if the rule is part of a determinist grammar
	 */
	public OLRule (boolean isDeterminist)
	{
		determinist = isDeterminist;
		postExpr = new ListSymbols();
	}
	
	/**
	 * Constructor
	 * @param pre
	 * @param post
	 */
	public OLRule (Symbol pre, ListSymbols post)
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
	public boolean canBeApplied (int indexPrev, ListSymbols phraseFromPreviousIteration)
	{
		return phraseFromPreviousIteration.get(indexPrev).getCharacter() == preExpr.getCharacter();
	}

	@Override
	public boolean apply (ListSymbols phrase)
	{
		boolean retour = false;
		int offset = 0, indexOld = 0;
		final int size = phrase.size();
		ListSymbols saved = phrase.clone();

		for (int i = 0; i < size; i++)
		{
			offset = applyOnce(indexOld, saved, i, phrase);
			if (offset != 0)
			{
				i += offset;
				retour = true;
			}
			indexOld++;
		}

		return retour;
	}

	@Override
	public int applyOnce (int indexPrev, ListSymbols phraseFromPreviousIteration, int indexNew, ListSymbols toBeModified)
	{
		final int size = postExpr.size();

		if (!canBeApplied(indexPrev, phraseFromPreviousIteration))
			return 0;

		if (postExpr.get(0).getCharacter() == 'ε')
		{
			toBeModified.remove(indexNew);
			return -1;
		}

		toBeModified.set(indexNew, postExpr.get(0));
		for (int i = 1; i < size; i++)
			toBeModified.add(indexNew + i, postExpr.get(i));
		return size;
	}

	@Override
	public double getProba ()
	{
		return proba;
	}
	
	public void setProba (double probability)
	{
		proba = probability;
	}

	/**
	 * @return the preExpr
	 */
	@Override
	public Symbol getPreExpr ()
	{
		return preExpr;
	}

	/**
	 * @param preExpr the preExpr to set
	 */
	@Override
	public void setPreExpr (Symbol preExpr)
	{
		this.preExpr = preExpr;
	}

	/**
	 * @return the postExpr
	 */
	@Override
	public ListSymbols getPostExpr ()
	{
		return postExpr;
	}

	/**
	 * @param postExpr the postExpr to set
	 */
	@Override
	public void setPostExpr (ListSymbols postExpr)
	{
		this.postExpr = postExpr;
	}

	/**
	 * @return the determinist
	 */
	@Override
	public boolean isDeterminist ()
	{
		return determinist;
	}

	/**
	 * @param determinist the determinist to set
	 */
	@Override
	public void setDeterminist (boolean determinist)
	{
		this.determinist = determinist;
	}
	
	@Override
	public boolean isContextFree ()
	{
		return true;
	}

	@Override
	public String toString ()
	{
		return preExpr.getCharacter() + " -> " + postExpr + ((proba == 1) ? "" : ": " + proba);
	}
}
