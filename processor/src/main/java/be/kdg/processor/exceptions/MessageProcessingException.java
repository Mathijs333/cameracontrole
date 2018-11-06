package be.kdg.processor.exceptions;

/**
 *
 * @author Mathijs Constantin
 * @version 1.0 2/11/2018 12:42
 */
public class MessageProcessingException extends Throwable {
    public MessageProcessingException() { super(); }
    public MessageProcessingException(String message) { super(message); }
    public MessageProcessingException(String message, Throwable cause) { super(message, cause); }
    public MessageProcessingException(Throwable cause) { super(cause); }
}
