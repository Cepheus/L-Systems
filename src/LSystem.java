import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;

import gui.Controller;


/**
 * @author Caelum
 */
public class LSystem
{
	/**
	 * Launch the program
	 * 
	 * @param args
	 */
	public static void main (String[] args)
	{
		// we use that because of JME which use AWT whereas I use Swing
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		
		// On lance le programme
		Controller controller = new Controller();
		controller.canvasJMEWidth = 1024;
		controller.canvasJMEHeight = 640;
		controller.startLSystem();
	}
}
