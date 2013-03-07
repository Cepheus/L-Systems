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
 * * Project: L-Systems* File: XLRule.java** Created on: 6 mars 2013* Author: xinouch******************************************************
 */

package parser;

/**
 * Represent a rule for a context-dependent L-system, where preExpr contains only one symbol.
 * 
 * @author xinouch
 */
public class ILRule implements Rule
{
	/** Symbol to be replaced in the rule */
	private Symbol preExpr;
	/** List of symbols that will replace preExpr */
	private ListSymbols postExpr = new ListSymbols();
	/** List of symbols that have to be readen to validate the rule */
	private ListSymbols prevExpr = new ListSymbols();
	/** List of symbols that have to be present in the future to validate the rule */
	private ListSymbols nextExpr = new ListSymbols();
	/** True if the rule is part of a determinist grammar, false otherwise */
	private boolean determinist = true;
	/** The probability of the rule */
	private double proba = 1;

	/**
	 * Constructor
	 */
	public ILRule ()
	{
	}

	/**
	 * Constructor
	 * 
	 * @param isDeterminist true if the rule is part of a determinist grammar
	 */
	public ILRule (boolean isDeterminist)
	{
		determinist = isDeterminist;
	}

	@Override
	public boolean canBeApplied (int indexPrev, ListSymbols phraseFromPreviousIteration)
	{
		final int sizePrev = prevExpr.size(), sizeNext = nextExpr.size(), sizePhrase = phraseFromPreviousIteration.size();
		int i = 0, j = 0;

		if (phraseFromPreviousIteration.get(indexPrev).getCharacter() != preExpr.getCharacter())
			return false;

		// validation des trucs avant
		while ((i < indexPrev) && (j < sizePrev))
		{
			if (phraseFromPreviousIteration.get(i).getCharacter() == prevExpr.get(j).getCharacter())
				j++;
			i++;
		}
		if (j != sizePrev)
			return false;

		j = 0;
		i = indexPrev + 1;

		// validation des trucs après
		while ((i < sizePhrase) && (j < sizeNext))
		{
			if (phraseFromPreviousIteration.get(i).getCharacter() == nextExpr.get(j).getCharacter())
				j++;
			i++;
		}
		if (j < sizeNext)
			return false;

		return true;
	}

	@Override
	public boolean canBeApplied (ListSymbols phrase)
	{
		final int size = phrase.size();

		for (int i = 0; i < size; i++)
		{
			if (canBeApplied(i, phrase))
				return true;
		}

		return false;
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

	/**
	 * @return List of symbols that have to be read to validate the rule
	 */
	public ListSymbols getPrevExpr ()
	{
		return prevExpr;
	}

	/**
	 * @param prevExpr List of symbols that have to be read to validate the rule
	 */
	public void setPrevExpr (ListSymbols prevExpr)
	{
		this.prevExpr = prevExpr;
	}

	/**
	 * @return List of symbols that have to be present in the future to validate the rule
	 */
	public ListSymbols getNextExpr ()
	{
		return nextExpr;
	}

	/**
	 * @param nextExpr List of symbols that have to be present in the future to validate the rule
	 */
	public void setNextExpr (ListSymbols nextExpr)
	{
		this.nextExpr = nextExpr;
	}

	@Override
	public boolean isContextFree ()
	{
		return false;
	}

	@Override
	public String toString ()
	{
		String s = "";

		s += preExpr.getCharacter();
		if (prevExpr.size() != 0)
		{
			s += " < " + prevExpr;
		}
		if (nextExpr.size() != 0)
		{
			s += " > " + nextExpr;
		}

		s += " -> ";
		s += postExpr;
		s += ((proba == 1) ? "" : ": " + proba);

		return s;
	}
}
