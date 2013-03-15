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
 * * Project: L-Systems File: Drawer.java Created on: 08 march. 2013 Author: Cepheus
 * *****************************************************
 */

package ATuin;

import java.util.ArrayList;

import parser.ListSymbols;
import parser.Symbol;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;

/**
 * @author Caelum
 * Class for 3D object drawing.
 */
public class Drawer extends SimpleApplication {	
	
    /**
     * TODO : a supprimer
     * @param args 
     */
    public static void main(String[] args) {
    	Drawer app = new Drawer();
    	app.start();
    	
    	try {
			Thread.sleep(750);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        ListSymbols symbols = new ListSymbols();
        ArrayList<Symbol> arraySymbols = new ArrayList<Symbol>();
        arraySymbols.add(new Symbol('F',1));
        arraySymbols.add(new Symbol('F',1));
        arraySymbols.add(new Symbol('L',2));
        arraySymbols.add(new Symbol('F',1));
        symbols.setSymbols(arraySymbols);
        TubeTurle turtle = new TubeTurle(app, symbols);
        try {
			turtle.checkSymbols();
		} catch (BadInterpretationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        turtle.drawSymbols();
    }
    
    /**
     * Default constructor.
     */
    public Drawer() {
        setPauseOnLostFocus(false);
    }
    
    /**
     * Constructor.
     * @param settings The settings such as the size
     */
    public Drawer(AppSettings settings) {
        this();
        setSettings(settings);
    }
    
    /**
     * 
     */
    public void createLine(Vector3f origine, Vector3f end) {
    	
    	/*Line line = new Line(origine, end);
        Geometry geom = new Geometry("Line", line);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);*/
    	
    	
    	//if(manager!=null) {
			Box b = new Box(5, 5, 5);
			Geometry geom = new Geometry("Box", b);
			Material mat = new Material(assetManager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Green);
			geom.setMaterial(mat);
			rootNode.attachChild(geom);
    	//}
    	//else
    		System.out.println("tralala");
        
        /** An unshaded textured cube. 
        *  Uses texture from jme3-test-data library! */ 
       /*Box boxshape1 = new Box(Vector3f.ZERO, 1f,1f,1f); 
       Geometry cube_tex = new Geometry("A Textured Box", boxshape1); 
       Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
       Texture tex = assetManager.loadTexture("Interface/Logo/Monkey.jpg"); 
       mat_tex.setTexture("ColorMap", tex); 
       cube_tex.setMaterial(mat_tex); 
       rootNode.attachChild(cube_tex);*/ 
    }
    
    /**
     * Initiale creation of the scene.
     */
    @Override
    public void simpleInitApp() {
    	Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);
		
		System.out.println("END InitApp");
    }

    /**
     * Update loop.
     * @param tpf time per frame
     */
    @Override
    public void simpleUpdate(float tpf) {
    	
    }

    /**
     * Render loop.
     * @param rm Render Manager
     */
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}
