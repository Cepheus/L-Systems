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
		// On lance le programme
		Controller controller = new Controller();
		controller.canvasJMEWidth = 1024;
		controller.canvasJMEHeight = 640;
		controller.startLSystem();
	}
}
