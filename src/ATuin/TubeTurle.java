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
public class TubeTurle extends Turtle
{

	private int angle = 90;

	public Quaternion Q_TURNLEFT = new Quaternion().fromAngleAxis(angle * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1));

	public Quaternion Q_TURNRIGHT = new Quaternion().fromAngleAxis(-angle * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1));

	public Quaternion Q_TURNUP = new Quaternion().fromAngleAxis(angle * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0));

	public Quaternion Q_TURNDOWN = new Quaternion().fromAngleAxis(-angle * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0));

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
	static
	{
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
	 * check if the given list is compatible with this interpretation.
	 * If it exists an interpretation that can't fit with this turtle, return false. Invalid interpretations are accepted
	 * @param symbols the list to check
	 * @return true if the interpretation is OK, false if this turtle can't represent the list
	 */
	public static boolean checkSymbols (ListSymbols symbols)
	{
		boolean found;
		final int sizeCheck = symbols.size(), sizeInterpretation = authorizedSymbols.size();
		
		for (int i = 0; i < sizeCheck; i++)
		{
			found = false;
			for (int j = 0; j < sizeInterpretation; j++)
			{
				if (symbols.get(i).getInterpretation() == authorizedSymbols.get(j).getInterpretation())
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
	public TubeTurle ()
	{
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param drawer The object drawer of the scene
	 * @param symbols
	 */
	public TubeTurle (Drawer drawer, ListSymbols symbols)
	{
		super(drawer, symbols);
	}

	@Override
	public boolean checkSymbols ()
	{
		for (Symbol symbol : symbols.getSymbols())
		{
			System.out.println("Char:" + symbol.getCharacter() + " Inter:" + symbol.getInterpretation());
		}
		return true;
	}

	@Override
	public void drawSymbols ()
	{
		/*
		 * Quaternion q = new Quaternion().fromAngleAxis(angle*FastMath.DEG_TO_RAD, new Vector3f(0,0,1)); Vector3f debut = new Vector3f(0f,
		 * 0f, 0f); Vector3f end = debut.add(0, 50, 0); end = q.mult(end); Line line = new Line(debut, end); line.setLineWidth(width);
		 * Geometry geom = new Geometry("Line", line); Material mat = new
		 * Material(drawer.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md"); mat.setColor("Color", color); geom.setMaterial(mat);
		 * drawer.getRootNode().attachChild(geom);
		 */

		Node tmp;
		Node node = drawer.getRootNode();
		Quaternion rotation = new Quaternion();
		Vector3f position = new Vector3f(0, 0, 0);
		for (Symbol symbol : symbols.getSymbols())
		{
			switch (symbol.getInterpretation())
			{
				case 1: // Forward
					position = drawLine(position, rotation, node);
					tmp = new Node();
					node.attachChild(tmp);
					node = tmp;
					// direction = new Quaternion();
					break;
				case 2: // Left
					rotation = Q_TURNLEFT;
					break;
				case 3: // Right
					rotation = Q_TURNRIGHT;
					break;
				case 4: // Up
					rotation = Q_TURNUP;
					break;
				case 5: // Down
					rotation = Q_TURNDOWN;
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Draws a line
	 * 
	 * @param position The position of the beginning of the line
	 * @param origine The node to link the new line to
	 * @return The position for the next line
	 */
	private Vector3f drawLine (Vector3f start, Quaternion rotation, Node origin)
	{
		Vector3f end = new Vector3f(start);
		end = rotation.mult(end);
		Vector3f tmp = new Vector3f(length, length, length).mult(direction);
		end = end.add(tmp);
		end.x = Math.round(end.x);
		end.y = Math.round(end.y);
		end.z = Math.round(end.z);
		direction = end.subtract(start).divide(length);
		/*
		 * direction.x = Math.round(direction.x); direction.y = Math.round(direction.y); direction.z = Math.round(direction.z);
		 */
		System.out.println("Start" + start + "End" + end + "Direction" + direction + "Rotation" + rotation);
		Line line = new Line(start, end);
		line.setLineWidth(width);
		Geometry geom = new Geometry("Line", line);
		Material mat = new Material(drawer.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);
		geom.setMaterial(mat);
		origin.attachChild(geom);
		return end;
	}

	/**
	 * @param length
	 * @param width
	 * @param color
	 */
	public void setParameters (float length, float width, ColorRGBA color)
	{
		this.length = length;
		this.width = width;
		this.color = color;
	}
}
