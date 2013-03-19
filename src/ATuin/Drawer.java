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

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * @author Caelum Class for 3D object drawing.
 */
public class Drawer extends SimpleApplication {
	/** A white, directional light source */
	protected DirectionalLight sun;
	/** The listener used to react with analogic inputs */
	protected AnalogListener analogListener;
	/** The listener used to react with numeric inputs */
	protected ActionListener actionListener;

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
	 * Initiale creation of the scene.
	 */
	@Override
	public void simpleInitApp() {
		initInputs();
		initScene();
	}

	/**
	 * Update loop.
	 * 
	 * @param tpf
	 *            time per frame
	 */
	@Override
	public void simpleUpdate(float tpf) {
		sun.setDirection(getCamera().getDirection());
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
	 * Initialize the inputs (keybord and mouse)
	 */
	protected final void initInputs() {
		// register useful keys
		inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_Z));
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_Q));
		inputManager.addMapping("Bottom", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		
		// we create the action listener
		actionListener = new ActionListener()
		{
			@Override
			public void onAction (String name, boolean keyPressed, float tpf)
			{
				System.out.println("haha");
			}
		};
		
		// we create the analogic listener
		analogListener = new AnalogListener()
		{
			@Override
			public void onAnalog (String name, float value, float tpf)
			{
				System.out.println("héhé");
			}
		};
		
		// finally, we tell which listener to use
		inputManager.addListener(actionListener, new String[] { "Nothing", "Up", "Left", "Bottom", "Right" });
		inputManager.addListener(analogListener, new String[] { "Nothing" });
	}

	/**
	 * Initialize the scene (camera position, lights...)
	 */
	protected final void initScene() {
		// LIGHT
		sun = new DirectionalLight();
		sun.setDirection(getCamera().getDirection());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);
		
		// BLOOM
		FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
		BloomFilter bloom = new BloomFilter();
		// bloom.setBlurScale(2.5f);
		// bloom.setBloomIntensity(10f);
		fpp.addFilter(bloom);
		viewPort.addProcessor(fpp);

		// CAMERA
		flyCam.setMoveSpeed(40);
		getCamera().setLocation(new Vector3f(0,0,100));
	}

}
