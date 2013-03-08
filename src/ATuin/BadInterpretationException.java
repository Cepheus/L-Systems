package ATuin;

/**
 * représente l'exception levée lors d'une erreur de parsing de fichier
 * 
 * @author xinouch
 */
public class BadInterpretationException extends Exception
{
	/** */
	private static final long serialVersionUID = -142857;
	/** chaine de caractère expliquant l'erreur produite */
	private String error;

	/**
	 * constructeur par défaut
	 * 
	 * @param str l'erreur
	 */
	public BadInterpretationException (String str)
	{
		error = str;
	}

	@Override
	/**
	 * retourne une chaine de caractère formattée expliquant l'erreur
	 * @return un message d'erreur
	 */
	public String getMessage ()
	{
		return error;
	}
}
