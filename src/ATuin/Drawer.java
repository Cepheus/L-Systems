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
 * * Project: L-Systems File: Drawer.java Created on: 08 march. 2013 Author: Cepheus *****************************************************
 */

package ATuin;

import java.util.ArrayList;

import parser.ListSymbols;
import parser.Symbol;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.util.TangentBinormalGenerator;

/**
 * @author Caelum Class for 3D object drawing.
 */
public class Drawer extends SimpleApplication {

	/**
	 * TODO : a supprimer
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Drawer app = new Drawer();
		app.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ListSymbols symbols = new ListSymbols();
		ArrayList<Symbol> arraySymbols = new ArrayList<Symbol>();
		arraySymbols.add(new Symbol('F', 1));
		arraySymbols.add(new Symbol('L', 2));
		arraySymbols.add(new Symbol('F', 1));
		arraySymbols.add(new Symbol('R', 3));
		arraySymbols.add(new Symbol('F', 1));
		arraySymbols.add(new Symbol('D', Symbol.S_TURNDOWN));
		arraySymbols.add(new Symbol('F', 1));
		arraySymbols.add(new Symbol('U', Symbol.S_TURNUP));
		arraySymbols.add(new Symbol('F', 1));
		symbols.setSymbols(arraySymbols);
		TubeTurtle turtle = new TubeTurtle(app);
		turtle.setSymbols(symbols);
		turtle.setParameters(5, 10, 90, ColorRGBA.Green);
		try {
			if (turtle.checkSymbols())
				turtle.drawSymbols();
		} catch (BadInterpretationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Default constructor.
	 */
	public Drawer() {
		setPauseOnLostFocus(false);
	}

	/**
	 * Constructor.
	 * 
	 * @param settings
	 *            The settings such as the size
	 */
	public Drawer(AppSettings settings) {
		this();
		setSettings(settings);
	}

	/**
	 * Get the assetManager of the scene
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Get the root node of the scene
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * Initiale creation of the scene.
	 */
	@Override
	public void simpleInitApp() {
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
}
