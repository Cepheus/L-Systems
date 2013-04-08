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
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;


/**
 * @author Caelum Class for 3D object drawing.
 */
public class Drawer extends SimpleApplication implements AnalogListener, ActionListener
{
	/** A white, directional light source */
	protected DirectionalLight sun;
	/** if true we must rotate rootnode */
	protected boolean canRotate = false;

	/**
	 * Default constructor.
	 */
	public Drawer ()
	{
		setPauseOnLostFocus(false);
	}

	/**
	 * Constructor.
	 * 
	 * @param settings The settings such as the size
	 */
	public Drawer (AppSettings settings)
	{
		this();
		setSettings(settings);
	}

	/**
	 * Initiale creation of the scene.
	 */
	@Override
	public void simpleInitApp ()
	{
	}

	/**
	 * Update loop.
	 * 
	 * @param tpf time per frame
	 */
	@Override
	public void simpleUpdate (float tpf)
	{
		sun.setDirection(getCamera().getDirection());
	}

	/**
	 * Render loop.
	 * 
	 * @param rm Render Manager
	 */
	@Override
	public void simpleRender (RenderManager rm)
	{
	}

	/**
	 * Initialize the inputs (keybord and mouse). NOTE: this method is called after all is correctly initialized in JME. So, we can call
	 * here all the methods that need the application to be fully launched.
	 */
	public final void initInputs ()
	{
		String mappingsFlyCam[] =
				new String[] { "FLYCAM_Forward", "FLYCAM_StrafeLeft", "FLYCAM_Backward", "FLYCAM_StrafeRight", "FLYCAM_Rise",
						"FLYCAM_Lower", "FLYCAM_ZoomIn", "FLYCAM_ZoomOut", "FLYCAM_Left", "FLYCAM_Right", "FLYCAM_Up", "FLYCAM_Down",
						"FLYCAM_RotateDrag" };
		String mappingsPerso[] =
				new String[] { "FLYCAM_Accelerate", "NODE_Left", "NODE_Right", "NODE_Up", "NODE_Down", "NODE_RotateDrag", "NODE_ReinitPosition" };
		// first we remove all the listeners
		inputManager.clearMappings();
		// register useful keys for flycam
		inputManager.addMapping("FLYCAM_Forward", new KeyTrigger(KeyInput.KEY_Z), new KeyTrigger(KeyInput.KEY_UP));
		inputManager.addMapping("FLYCAM_StrafeLeft", new KeyTrigger(KeyInput.KEY_Q), new KeyTrigger(KeyInput.KEY_LEFT));
		inputManager.addMapping("FLYCAM_Backward", new KeyTrigger(KeyInput.KEY_S), new KeyTrigger(KeyInput.KEY_DOWN));
		inputManager.addMapping("FLYCAM_StrafeRight", new KeyTrigger(KeyInput.KEY_D), new KeyTrigger(KeyInput.KEY_RIGHT));
		inputManager.addMapping("FLYCAM_Rise", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("FLYCAM_Lower", new KeyTrigger(KeyInput.KEY_LSHIFT));
		inputManager.addMapping("FLYCAM_ZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
		inputManager.addMapping("FLYCAM_ZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
		inputManager.addMapping("FLYCAM_Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
		inputManager.addMapping("FLYCAM_Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
		inputManager.addMapping("FLYCAM_Up", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
		inputManager.addMapping("FLYCAM_Down", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		// register perso
		inputManager.addMapping("FLYCAM_Accelerate", new KeyTrigger(KeyInput.KEY_LCONTROL));
		inputManager.addMapping("NODE_Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
		inputManager.addMapping("NODE_Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
		inputManager.addMapping("NODE_Up", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
		inputManager.addMapping("NODE_Down", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
		inputManager.addMapping("NODE_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		inputManager.addMapping("NODE_ReinitPosition", new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));

		// finally, we tell which listener to use
		inputManager.addListener(this, mappingsPerso);
		inputManager.addListener(flyCam, mappingsFlyCam);
	}

	/**
	 * Initialize the scene (camera position, lights...)
	 */
	public final void initScene ()
	{
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
		flyCam.setMoveSpeed(100);
		flyCam.setZoomSpeed(25);
		cam.setFrustumFar(10000);
		getCamera().setLocation(new Vector3f(0, 0, 100));
	}

	/**
	 * rotate the root node on the given axis
	 * 
	 * @param value the value of rotation on the 3 axis
	 */
	protected void rotateNode (Vector3f value)
	{
		if (canRotate)
			rootNode.rotate(value.x, value.y, value.z);
	}

	@Override
	public void onAction (String name, boolean keyPressed, float tpf)
	{
		if (name.equals("NODE_RotateDrag"))
		{
			canRotate = keyPressed;
			inputManager.setCursorVisible(!keyPressed);
		}
		else if (name.equals("NODE_ReinitPosition") && keyPressed)
		{
			rootNode.setLocalRotation(Quaternion.DIRECTION_Z);
			rootNode.setLocalTranslation(0f,0f,0f);
		}
		else if (name.equals("FLYCAM_Accelerate"))
		{
			if (keyPressed)
				flyCam.setMoveSpeed(flyCam.getMoveSpeed() * 5);
			else
				flyCam.setMoveSpeed(flyCam.getMoveSpeed() / 5);
		}
	}

	@Override
	public void onAnalog (String name, float value, float tpf)
	{
		cam.normalize();
		if (name.equals("NODE_Left"))
			rotateNode(cam.getUp().mult(-value).mult(flyCam.getRotationSpeed()));
		if (name.equals("NODE_Right"))
			rotateNode(cam.getUp().mult(value).mult(flyCam.getRotationSpeed()));
		if (name.equals("NODE_Up"))
			rotateNode(cam.getLeft().mult(value).mult(flyCam.getRotationSpeed()));
		if (name.equals("NODE_Down"))
			rotateNode(cam.getLeft().mult(-value).mult(flyCam.getRotationSpeed()));
	}

}
