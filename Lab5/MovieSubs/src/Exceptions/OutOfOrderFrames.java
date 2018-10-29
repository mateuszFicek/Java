package Exceptions;

public class OutOfOrderFrames extends Exception{
    /**
     *
     * @param message message that will be thrown
     */
    public OutOfOrderFrames(String message){
        super(message);
    }
}
