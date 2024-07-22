package exceptions;

public class notFoundElementException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public notFoundElementException(String mensaje) {
        super(mensaje);
    }
}
