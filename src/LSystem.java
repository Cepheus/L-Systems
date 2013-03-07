import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import parser.Generator;
import parser.IOmanager.*;


/**
 * 
 */

/**
 * @author Caelum
 */
public class LSystem
{

	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		char eps = 'ε';

		System.out.println("Bonjour tortue !" + eps);

		// ----------------- essai de génération
		InputStream istrm = null;
		try
		{
			istrm = new FileInputStream("doc/grammars-example.txt");
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		Analyzer analyzer = new Analyzer(istrm, "UTF-8");
		try
		{
			analyzer.startValidation();
		}
		catch (NumberFormatException | ParseException | BadFileException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}

		Generator generator;
		for (int i = 0; i < analyzer.getGrammars().size(); i++)
		{
			generator = new Generator(analyzer.getGrammars().get(i));
			generator.setTotalIteration(3);
			generator.generate();
			System.out.println(analyzer.getGrammars().get(i).getName());
			System.out.println(generator.getLastGenerated());
			System.out.println(generator.getGenerated());
		}
	}

}
