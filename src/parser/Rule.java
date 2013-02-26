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
 **             File: Rule.java
 **
 **       Created on: 26 févr. 2013
 **           Author: xinouch
 **
 ****************************************************** */

package parser;

/**
 * Represent a rewrite rule.
 * A rule contains a preExpr which contains a list of Symbols that are going to be replaced, and a postExpr that contains
 * the replacing symbols: preExpr -> postExpr.
 * <p>Each rule has a probability to happen if it is in conflict with another (for non determinist grammars)</p>
 * <p>The rules are divided in 2 groups:
 * <ul>
 * <li>simple rules are those which only have one symbol in preExpr</li>
 * <li>complex rules are those which have more than one symbol in preExpr</li>
 * </ul>
 * </p>
 * @author xinouch
 *
 */
public interface Rule
{
	/**
	 * tell if this rule can be applied in the following phrase
	 * @param phrase
	 * @return true if it can be applied, false otherwise
	 */
	public boolean canBeApplied (ListSymbols phrase);
	
	/**
	 * apply this rule on the given phrase.
	 * @param phrase
	 * @return true if the rule has really been applied.
	 */
	public boolean apply (ListSymbols phrase);
	
	/**
	 * @return the probability for this rule to happen. It is 1 for determinist grammars.
	 */
	public int getProba ();
}
