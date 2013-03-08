/**
 * File of the class Drawer, this is the main class for the turtle interpretation
 */
package ATuin;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * @author Caelum
 * Class for the turtle interpretation
 * 
 */
public class Drawer extends SimpleApplication {
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Drawer app = new Drawer();
        app.start();
    }
    
    /**
     * Création initiale de la scène
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

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}
