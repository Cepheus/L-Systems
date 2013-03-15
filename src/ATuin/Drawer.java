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
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;


/**
 * @author Caelum Class for 3D object drawing.
 */
public class Drawer extends SimpleApplication
{

	/**
	 * TODO : a supprimer
	 * 
	 * @param args
	 */
	public static void main (String[] args)
	{
		Drawer app = new Drawer();
		app.start();

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e1)
		{
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
		arraySymbols.add(new Symbol('U', 4));
		arraySymbols.add(new Symbol('F', 1));
		arraySymbols.add(new Symbol('D', 5));
		arraySymbols.add(new Symbol('F', 1));
		symbols.setSymbols(arraySymbols);
		TubeTurle turtle = new TubeTurle(app);
		turtle.setSymbols(symbols);
		turtle.setParameters(5, 10, 90, ColorRGBA.Green);
		try
		{
			if (turtle.checkSymbols())
				turtle.drawSymbols();
		}
		catch (BadInterpretationException e)
		{
			e.printStackTrace();
		}
	}

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
	 * Get the assetManager of the scene
	 */
	public AssetManager getAssetManager ()
	{
		return assetManager;
	}

	/**
	 * Get the root node of the scene
	 */
	public Node getRootNode ()
	{
		return rootNode;
	}

	/**
	 * Initiale creation of the scene.
	 */
	@Override
	public void simpleInitApp ()
	{
		/** A white, directional light source */
		/*
		 * DirectionalLight sun = new DirectionalLight(); sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
		 * sun.setColor(ColorRGBA.White); rootNode.addLight(sun);
		 */
		/**
		 * Illuminated bumpy rock with shiny effect. Uses Texture from jme3-test-data library! Needs light source!
		 */
		/*
		 * Sphere rock = new Sphere(32,32, 2f); Geometry rock_shiny = new Geometry("Shiny rock", rock);
		 * rock.setTextureMode(Sphere.TextureMode.Projected); // better quality on spheres TangentBinormalGenerator.generate(rock); // for
		 * lighting effect Material mat_shiny = new Material( assetManager, "Common/MatDefs/Light/Lighting.j3md");
		 * //mat_shiny.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond.png"));
		 * //mat_shiny.setTexture("NormalMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
		 * //mat_shiny.setTexture("GlowMap", assetManager.loadTexture("Textures/glowmap.png")); // requires flow filter!
		 * mat_shiny.setBoolean("UseMaterialColors",true); // needed for shininess mat_shiny.setColor("Specular", ColorRGBA.White); //
		 * needed for shininess mat_shiny.setColor("Diffuse", ColorRGBA.White); // needed for shininess mat_shiny.setFloat("Shininess", 5f);
		 * // shininess from 1-128 rock_shiny.setMaterial(mat_shiny); rootNode.attachChild(rock_shiny);
		 */
	}

	/**
	 * Update loop.
	 * 
	 * @param tpf time per frame
	 */
	@Override
	public void simpleUpdate (float tpf)
	{

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
}
