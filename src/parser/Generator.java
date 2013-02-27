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
 **             File: Generator.java
 **
 **       Created on: 26 févr. 2013
 **           Author: xinouch
 **
 ****************************************************** */

package parser;

import java.util.ArrayList;

/**
 * This class is used to generate phrases from a given grammar.
 * @author xinouch
 *
 */
public class Generator
{
	/** The grammar to use in generation */
	private Grammar grammar;
	/** The generated phrase */
	private ListSymbols generated;
	/** The last generated phrase (previous iteration) */
	private ListSymbols lastGenerated;
	/** The number of iteration realized */
	private int actualIt = 0;
	/** The number of iteration to realize */
	private int totalIt = 3;
	
	/**
	 * constructor
	 * @param g the grammar to work with to generate words
	 */
	public Generator (Grammar g)
	{
		grammar = g;
		generated = g.getAxiome();
		lastGenerated = generated.clone();
	}
	
	/**
	 * generate a word from the grammar.
	 * The number of iteration to create the word can be precised with setTotalIt(), the default is 3.
	 */
	public void generate ()
	{
		ArrayList<Rule> rules = grammar.getRules();
		final int sizeRules = rules.size();
		int sizeGenerated, i, j, offset;
		boolean isModified;
		
		for (actualIt = 1; actualIt < totalIt; actualIt++) // we begin to 1 because 0 is the axiom
		{
			lastGenerated = generated.clone();
			sizeGenerated = generated.size();
			i = 0;
			while (i < sizeGenerated) // loop for the generated phrase
			{
				j = 0;
				isModified = false;
				while ((!isModified) && (j < sizeRules)) // loop to apply each rules on each symbols of the phrase
				{
					offset = rules.get(j).applyOnce(i, lastGenerated, generated);
					if (offset != 0)
					{
						i += offset;
						sizeGenerated = generated.size();
						isModified = true;
					}
					j++;
				}
				if (!isModified)
					i++;
			}
		}
	}

	/**
	 * @return the grammar
	 */
	public Grammar getGrammar ()
	{
		return grammar;
	}

	/**
	 * @param grammar the grammar to set
	 */
	public void setGrammar (Grammar grammar)
	{
		this.grammar = grammar;
	}

	/**
	 * @return the generated
	 */
	public ListSymbols getGenerated ()
	{
		return generated;
	}

	/**
	 * @param generated the generated to set
	 */
	public void setGenerated (ListSymbols generated)
	{
		this.generated = generated;
	}

	/**
	 * @return the lastGenerated
	 */
	public ListSymbols getLastGenerated ()
	{
		return lastGenerated;
	}

	/**
	 * @param lastGenerated the lastGenerated to set
	 */
	public void setLastGenerated (ListSymbols lastGenerated)
	{
		this.lastGenerated = lastGenerated;
	}

	/**
	 * @return the actualIt
	 */
	public int getActualIt ()
	{
		return actualIt;
	}

	/**
	 * @param actualIt the actualIt to set
	 */
	public void setActualIt (int actualIt)
	{
		this.actualIt = actualIt;
	}

	/**
	 * @return the totalIt
	 */
	public int getTotalIt ()
	{
		return totalIt;
	}

	/**
	 * @param totalIt the totalIt to set
	 */
	public void setTotalIt (int totalIt)
	{
		this.totalIt = totalIt;
	}
}
