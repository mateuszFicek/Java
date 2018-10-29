/**
 * @author Mateusz Ficek
 *
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhotoSegreg photo = new PhotoSegreg();
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj sciezke do folderu ze zdjeciami do posegregowania");
        String directory = in.nextLine();
        try {
            photo.segreg(directory);
        } catch(Exception exp) {
            System.out.println(exp.getMessage());
        }
    }
}