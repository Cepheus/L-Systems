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
import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

/**
 * @author Caelum Class for 3D object drawing.
 */
public abstract class Drawer extends SimpleApplication {
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
	 * Clear the scene of all its objects
	 */
	public void clearScene() {
		rootNode.detachAllChildren();
	}

	protected final void initInputs () {
		
	}
}
