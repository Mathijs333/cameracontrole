package be.kdg.simulator.exceptions;

/**
 * @author Mathijs Constantin
 * @version 1.0 6/11/2018 15:48
 */
public class FileReadingException extends Throwable{
    public FileReadingException() { super(); }
    public FileReadingException(String message) { super(message); }
    public FileReadingException(String message, Throwable cause) { super(message, cause); }
    public FileReadingException(Throwable cause) { super(cause); }
}
