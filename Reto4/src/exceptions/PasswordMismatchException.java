package exceptions;

public class PasswordMismatchException extends Exception {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMismatchException() {
	      super("Las contraseñas no coinciden.");
	   }
	}