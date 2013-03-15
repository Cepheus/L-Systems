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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;

/**
 * @author Caelum
 */
public class TubeTurle extends Turtle {

	/** The angle for the rotations between the lines */
	private float angle = 90;

	/** The length of the lines */
	private float length = 5;

	/** The width of the lines */
	private float width = 5;

	/** The color of the lines */
	private ColorRGBA color = ColorRGBA.Green;

	/** The list of symbols known by the interpretation */
	public final static ListSymbols authorizedSymbols;

	// Initialisation of the authorized symbols list
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

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_ROLLLEFT);
		authorizedSymbols.append(sym);

		sym = new Symbol();
		sym.setInterpretation(Symbol.S_ROLLRIGHT);
		authorizedSymbols.append(sym);
	}

	/**
	 * Checks whether or not the given list is compatible with this turtle. If
	 * there is an interpretation that is unknown, returns false. Undefined
	 * interpretations are accepted
	 * 
	 * @param symbols
	 *            the list to be checked
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

	@Override
	public boolean checkSymbols() {
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
		name = "Tube Turle";
	}

	/**
	 * Constructor.
	 * 
	 * @param drawer
	 *            The object drawer of the scene
	 */
	public TubeTurle(Drawer drawer) {
		super(drawer);
		name = "Tube Turle";
	}

	/**
	 * Constructor.
	 * 
	 * @param drawer
	 *            The object drawer of the scene
	 * @param symbols
	 *            The symbols to represent
	 */
	public TubeTurle(Drawer drawer, ListSymbols symbols) {
		super(drawer, symbols);
		name = "Tube Turle";
	}

	@Override
	public void drawSymbols() throws BadInterpretationException {
		Node node = drawer.getRootNode();
		for (Symbol symbol : symbols.getSymbols()) {
			switch (symbol.getInterpretation()) {
			case Symbol.S_FORWARD:
				drawLine(node);
				Node tmp = new Node();
				node.attachChild(tmp);
				node = tmp;
				break;
			case Symbol.S_TURNLEFT:
				node.rotate(0, angle * FastMath.DEG_TO_RAD, 0);
				break;
			case Symbol.S_TURNRIGHT:
				node.rotate(0, -angle * FastMath.DEG_TO_RAD, 0);
				break;
			case Symbol.S_TURNUP:
				node.rotate(angle * FastMath.DEG_TO_RAD, 0, 0);
				break;
			case Symbol.S_TURNDOWN:
				node.rotate(-angle * FastMath.DEG_TO_RAD, 0, 0);
				break;
			case Symbol.S_ROLLLEFT:
				node.rotate(0, 0, angle * FastMath.DEG_TO_RAD);
				break;
			case Symbol.S_ROLLRIGHT:
				node.rotate(0, 0, -angle * FastMath.DEG_TO_RAD);
				break;
			case 0: // UNDEFINED
				break;
			default:
				throw (new BadInterpretationException(
						"The turle has uncounter a symbol impossible to draw!"));
			}
		}
	}

	/**
	 * Draws a line
	 * 
	 * @param origine
	 *            The node to link the drawn line to
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
	 * Sets the parameters for the object to be drawn
	 * 
	 * @param length
	 *            The length of the lines
	 * @param width
	 *            The width of the lines
	 * @param angle
	 *            The angle of the rotations
	 * @param color
	 *            The color of the lines
	 */
	public void setParameters(float length, float width, float angle,
			ColorRGBA color) {
		this.length = length;
		this.width = width;
		this.angle = angle;
		this.color = color;
	}
}
