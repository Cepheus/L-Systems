import gui.Controller;

import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;


/**
 * @author Caelum
 */
public class LSystem
{
	/**
	 * Launch the program
	 * 
	 * @param args
	 * @throws Exception if there is an error with the look and feel
	 */
	public static void main (String[] args) throws Exception
	{
		// we use that because of JME which use AWT whereas I use Swing
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		// set the look and feel because swing's default one is just so ugly
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		// On lance le programme
		Controller controller = new Controller();
		controller.canvasJMEWidth = 1024;
		controller.canvasJMEHeight = 640;
		controller.startLSystem();
	}
}
