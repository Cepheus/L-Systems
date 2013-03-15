/* ******************************************************
 * * Copyright (C) 2013 Cepheus This file is part of L-Systems** L-Systems is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by* the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************
 */
/* ******************************************************
 * *
 * * Project: L-Systems File: TubeTurle.java Created on: 08 march. 2013 Author: Cepheus
 * *****************************************************
 */

package ATuin;

import parser.ListSymbols;
import parser.Symbol;
import com.jme3.math.Vector3f;

/**
 *
 * @author Caelum
 */
public class TubeTurle extends Turtle {
    
	/**
     * Default constructor.
     */
    public TubeTurle() {
        super();
    }
    
    /**
     * Constructor.
     * @param drawer The object drawer of the scene 
     */
    public TubeTurle(Drawer drawer, ListSymbols symbols) {
        super(drawer, symbols);
    }
	
    /**
     * Checks if the interpretation can interpret all the symbols.
     * If not the function throws an exception.
     * @throws BadInterpretationException 
     */
    public void checkSymbols() throws BadInterpretationException {
        for (Symbol symbol : symbols.getSymbols()) {
			System.out.println("Char:"+symbol.getCharacter()+" Inter:"+symbol.getInterpretation());
		}
    }
    
    /**
     * Draws the list of symbols depending of the turtle's interpretation.
     */
    public void drawSymbols() {
    	drawer.createLine(new Vector3f(0f,0f,0f), new Vector3f(100f,100f,100f));
    }
}
