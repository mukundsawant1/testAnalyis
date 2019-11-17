package co.analysis.exceptions;

public class FileDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileDoesNotExistException(String message) {
        super(message);
    }
    
}
