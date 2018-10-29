//Ctrl+Shift+Alt+S libraries from maven, dodawanie jary
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Exceptions.*;

public class MovieSubs{
    private String fileName;
    private String fileOut;
    private int delay;
    private int frames;

    /**
     * Constructor to initialize MovieSubs.
     */
    MovieSubs(){
    };

    /**
     * Method to delay subtitles by given time.
     * @param fileI file name with given subtitles.
     * @param fileO file name where delayed subtitles will be saved.
     * @param delay time in milliseconds that subtitles will be delayed
     * @param fps frame rate
     * @throws FramesNotRepresentedAsNumbers thrown when given frames are not represented as numbers
     * @throws InvalidSubtitleLineFormat thrown when subtitles don't match given format
     * @throws OutOfOrderFrames thrown when the beginning frame is before ending frame
     * @throws IOException
     */
    public void delay(String fileI, String fileO, int delay, int fps) throws FramesNotRepresentedAsNumbers, InvalidSubtitleLineFormat, OutOfOrderFrames, IOException{
            Pattern pattern = Pattern.compile("\\{(.*)\\}\\{(.*)\\}(.*)");
            File fIn = new File(fileI);
            File fOut = new File(fileO);
            String line = "";
            String line2 = "";
            int begFrame;
            int endFrame;
            int delayedBegFrame;
            int delayedEndFrame;
            int delayFrames = delay / 1000 * fps;
            int lineCounter = 0;
            try {
                FileReader fileReader = new FileReader(fIn);
                Scanner in = new Scanner(fIn);
                PrintStream zapis = new PrintStream(fOut);
                while (in.hasNext()) {
                    lineCounter++;
                    line = in.nextLine();
                    Matcher matcher = pattern.matcher(line);
                    line = "";
                    line2 = "";
                    if (matcher.find()) {
                        try {
                            begFrame = Integer.parseInt(matcher.group(1)) + delay;
                            endFrame = Integer.parseInt(matcher.group(2)) + delay;
                            if (begFrame > endFrame) {
                                throw new OutOfOrderFrames("Wrong order of frames. Line " + lineCounter + System.lineSeparator());
                            } else {
                                delayedBegFrame = begFrame + delayFrames;
                                delayedEndFrame = endFrame + delayFrames;
                                line2 = "{" + delayedBegFrame + "}{" + delayedEndFrame + "}" + matcher.group(3);
                                zapis.println(line2);
                            }
                        } catch (NumberFormatException e) {
                            throw new FramesNotRepresentedAsNumbers("Frames are not represented as numbers. Line " +lineCounter+System.lineSeparator());
                        }
                    }
                    else
                    {
                        throw new InvalidSubtitleLineFormat("Given subtitle line is incorrect. Line " + lineCounter + System.lineSeparator());
                    }
                }
                in.close();
                zapis.close();
            }catch (IOException e) {
                throw e;
            }
    }
}
