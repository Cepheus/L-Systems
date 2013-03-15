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
 * * Project: L-Systems File: TubeTurle.java Created on: 08 march. 2013 Author: Cepheus
 * *****************************************************
 */

package ATuin;

import parser.ListSymbols;
import parser.Symbol;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;

/**
 * @author Caelum
 */
public class TubeTurle extends Turtle {

	private float angle = 90;
	
	/** The length of the lines */
	private float length;

	/** The width of the lines */
	private float width;

	/** The color of the lines */
	private ColorRGBA color;

	/** The direction to draw */
	private Vector3f direction = new Vector3f(0, 1, 0);

	/** The list of symbols known by the interpretation */
	public final static ListSymbols authorizedSymbols;

	// définition des trucs static
	static {
		Symbol sym;
		authorizedSymbols = new ListSymbols();

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_FORWARD);
		authorizedSymbols.append(sym);

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_TURNLEFT);
		authorizedSymbols.append(sym);

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_TURNRIGHT);
		authorizedSymbols.append(sym);

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_TURNUP);
		authorizedSymbols.append(sym);

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_TURNDOWN);
		authorizedSymbols.append(sym);
	}

	/**
	 * check if the given list is compatible with this interpretation. If it
	 * exists an interpretation that can't fit with this turtle, return false.
	 * Invalid interpretations are accepted
	 * 
	 * @param symbols
	 *            the list to check
	 * @return true if the interpretation is OK, false if this turtle can't
	 *         represent the list
	 */
	public static boolean checkSymbols(ListSymbols symbols) {
		boolean found;
		final int sizeCheck = symbols.size(), sizeInterpretation = authorizedSymbols
				.size();

		for (int i = 0; i < sizeCheck; i++) {
			found = false;
			for (int j = 0; j < sizeInterpretation; j++) {
				if (symbols.get(i).getInterpretation() == authorizedSymbols
						.get(j).getInterpretation())
					found = true;
			}
			if (!found)
				return false;
		}
		return true;
	}

	/**
	 * Default constructor.
	 */
	public TubeTurle() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param drawer
	 *            The object drawer of the scene
	 * @param symbols
	 */
	public TubeTurle(Drawer drawer, ListSymbols symbols) {
		super(drawer, symbols);
	}

	@Override
	public boolean checkSymbols() {
		for (Symbol symbol : symbols.getSymbols()) {
			System.out.println("Char:" + symbol.getCharacter() + " Inter:"
					+ symbol.getInterpretation());
		}
		return true;
	}

	@Override
	public void drawSymbols() {
		Node node = drawer.getRootNode();
		for (Symbol symbol : symbols.getSymbols()) {
			switch (symbol.getInterpretation()) {
			case 1:	// Forward
				drawLine(node);
				Node tmp = new Node();
				node.attachChild(tmp);
				node = tmp;
				break;
			case 2: // Left
				node.rotate(0, 0, angle * FastMath.DEG_TO_RAD);
				break;
			case 3: // Right
				node.rotate(0, 0, -angle * FastMath.DEG_TO_RAD);
				break;
			case 4: // Up
				node.rotate(angle * FastMath.DEG_TO_RAD, 0, 0);
				break;
			case 5: // Down
				node.rotate(-angle * FastMath.DEG_TO_RAD, 0, 0);
				break;
			case 0: // Undefined
				break;
			default: 
				break;
			}	
		}
	}

	/**
	 * Draws a line
	 * 
	 * @param origine
	 *            The node to link the new line to
	 * @return The new node
	 */
	private void drawLine(Node node) {
		Vector3f start = new Vector3f(0, 0, 0);
		Vector3f end = new Vector3f(0, length, 0);
		Line line = new Line(start, end);
		line.setLineWidth(width);
		Geometry geom = new Geometry("Line", line);
		Material mat = new Material(drawer.getAssetManager(),
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);
		geom.setMaterial(mat);
		node.setLocalTranslation(end);
		node.attachChild(geom);
	}

	/**
	 * @param length
	 * @param width
	 * @param color
	 */
	public void setParameters(float length, float width, float angle,ColorRGBA color) {
		this.length = length;
		this.width = width;
		this.angle = angle;
		this.color = color;
	}
}
