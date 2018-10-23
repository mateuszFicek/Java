import java.io.*;
import java.util.Scanner;

public class Cryptographer {
    public static void cryptfile(String FileIn, String FileOut, Algorithm algorithm){
        File fileToCypher = new File(FileIn);
        File fileCyphered = new File(FileOut);
        String line = "";
        String line2 = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileToCypher);
            Scanner in = new Scanner(fileToCypher);
            PrintStream zapis = new PrintStream(fileCyphered);
            while(in.hasNext()){
                line = in.next();
                line2 = algorithm.crypt(line);
                zapis.print(line2+ " ");
            }
            zapis.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            FileIn + "'");
        }
    }

    public static void decryptfile(String FileIn, String FileOut, Algorithm algorithm){
        ROT11 crypter = new ROT11();
        File fileToDecypher = new File(FileIn);
        File fileDecyphered = new File(FileOut);
        String line = "";
        String line2 = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileToDecypher);
            Scanner in = new Scanner(fileToDecypher);
            PrintStream zapis = new PrintStream(fileDecyphered);
            while(in.hasNext()){
                line = in.next();
                line2 = algorithm.decrypt(line);
                zapis.print(line2+ " ");
            }
            zapis.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            FileIn + "'");
        }
    }
}
