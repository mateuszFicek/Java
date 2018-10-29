package Exceptions;

public class InvalidSubtitleLineFormat extends Exception{
    /**
     *
     * @param message message that will be thrown
     */
    public InvalidSubtitleLineFormat(String message){
        super(message);
    }
}
