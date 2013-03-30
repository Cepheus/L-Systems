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
 **             File: GeneratorPseudoListener.java
 **
 **       Created on: 30 mars 2013
 **           Author: xinouch
 **
 ****************************************************** */

package parser;

/**
 * This interface implements methods that the generator can call during the generation process to indicate its progression.
 * @author xinouch
 *
 */
public interface GeneratorPseudoListener
{
	/**
	 * this method at each iterations
	 * @param step actual step the generator is working on
	 * @param totalStep number total of iterations to compute
	 */
	void setStep (int step, int totalStep);
	
	/**
	 * This method is called when the generator has finished its work
	 * @throws BadSymbolException 
	 */
	void finished () throws BadSymbolException;
}
