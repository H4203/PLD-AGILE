package donnees;

/**
 * @author H4203 Classe gerant les exceptions du parseur
 */
public class ParseurException extends Exception {

	/**
	 * Constructeur par defaut
	 */
	public ParseurException() {
	}

	/**
	 * Construit une nouvelle exception avec le message specifie.
	 * 
	 * @param message
	 *            message de l'exception
	 */
	public ParseurException(String message) {
		super(message);
	}

	/**
	 * Construit une nouvelle exception avec la cause specifiee.
	 * 
	 * @param cause
	 *            cause de l'exception
	 */
	public ParseurException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construit une nouvelle exception avec le message et la cause specifiee
	 * 
	 * @param message
	 *            message de l'exception
	 * @param cause
	 *            cause de l'exception
	 */
	public ParseurException(String message, Throwable cause) {
		super(message, cause);
	}
}
