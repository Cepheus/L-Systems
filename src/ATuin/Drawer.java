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

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

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
     * Initiale creation of the scene.
     */
    @Override
    public void simpleInitApp() {
        Box b = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
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
