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
 * * Project: L-Systems File: Turtle.java Created on: 08 march. 2013 Author: Cepheus *****************************************************
 */

package ATuin;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

import parser.ListSymbols;

/**
 * Default class of a turtle interpretation. This class factorise the differents
 * turtle interpretation. If you wish to create a new turtle interpretation you
 * should extends this class and implements its methods.
 * 
 * @author Caelum
 */
public abstract class Turtle extends Drawer {

	/** The name of the turtle */
	protected String name;

	/** The list of symbols to interpret */
	public ListSymbols symbols;

	/**
	 * Default constructor.
	 */
	public Turtle() {
		setPauseOnLostFocus(false);
	}

	/**
	 * Constructor.
	 * 
	 * @param settings
	 *            The settings such as the size
	 */
	public Turtle(AppSettings settings) {
		this();
		setSettings(settings);
	}

	/**
	 * Checks if the interpretation can interpret all the symbols.
	 * 
	 * @return true if all is ok, false if all symbols can't be interpreted
	 */
	public abstract boolean checkSymbols();

	/**
	 * Draws the list of symbols depending of the turtle's interpretation.
	 * 
	 * @throws BadInterpretationException
	 *             WARNING! In order to create a new turtle you need to
	 *             redifined this function in your class and the first line of
	 *             your drawSymbols function should be super.drawSymbols()
	 */
	public void drawSymbols() throws BadInterpretationException {
		rootNode.detachAllChildren();
		System.out.println("Moustache!");
	}

	/**
	 * Initiale creation of the scene.
	 */
	@Override
	public void simpleInitApp() {
		initInputs();

		/** A white ambient light source. */
		AmbientLight ambient = new AmbientLight();
		ambient.setColor(ColorRGBA.White);
		rootNode.addLight(ambient);
		/** A white, directional light source */
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);

		FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
		BloomFilter bloom = new BloomFilter();
		bloom.setBlurScale(2.5f);
		bloom.setBloomIntensity(10f);
		fpp.addFilter(bloom);
		viewPort.addProcessor(fpp);

		try {
			drawSymbols();
		} catch (BadInterpretationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update loop.
	 * 
	 * @param tpf
	 *            time per frame
	 */
	@Override
	public void simpleUpdate(float tpf) {

	}

	/**
	 * Render loop.
	 * 
	 * @param rm
	 *            Render Manager
	 */
	@Override
	public void simpleRender(RenderManager rm) {

	}

	/**
	 * @param symbols
	 *            the symbols to set
	 */
	public void setSymbols(ListSymbols symbols) {
		this.symbols = symbols;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
