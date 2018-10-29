/**
 * @author Mateusz Ficek
 */

import Exceptions.FramesNotRepresentedAsNumbers;
import Exceptions.InvalidSubtitleLineFormat;
import Exceptions.OutOfOrderFrames;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        MovieSubs movie = new MovieSubs();
        int delay;
        int fps;
        String fileIn;
        String fileOut;
        try{
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku z napisami: ");
        fileIn = in.nextLine();
        System.out.println("Podaj nazwe pliku wyjściowego: ");
        fileOut = in.nextLine();
        System.out.println("Podaj o ile milisekund mają zostać przesunięte napisy: ");
        delay = in.nextInt();
        System.out.println("Podaj ile klatek na sekundę ma film: ");
        fps = in.nextInt();
            movie.delay(fileIn,fileOut,delay,fps);
        }
        catch(IOException | OutOfOrderFrames | FramesNotRepresentedAsNumbers | InvalidSubtitleLineFormat e){
            System.out.print(e.getMessage());
        } finally {
            System.out.print("Subtitles delayed and saved to file " + fileOut);
        }
    }
}
