import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        File file = new File("randoms.txt");
        BufferedWriter writer=null;
        //Stworzenie pliku z randomowymi liczbami
        try {
            writer = new BufferedWriter(new FileWriter(file));
            List<Thread> randomLists = new LinkedList<>();
            for(int j=0;j<100;j++) {
                randomLists.add(new Thread(new GenerateRandoms(writer)));
            }
            for(int j=0;j<100;j++){
                randomLists.get(j).start();
            }
            for(int i=0;i<100; i++){
                randomLists.get(i).join();
            }
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            Scanner reader = new Scanner(new File("randoms.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("output.txt")));
            List<Thread> funkcje = new LinkedList<>();
            for(int j=0;j<100;j++) {
                funkcje.add(new Thread(new MaxMultiThread(out, reader.nextLine())));
            }
            for (int i = 0; i < 100; i++) {
                funkcje.get(i).start();
            }
            for (int i = 0; i < 100; i++) {
                funkcje.get(i).join();
            }
            out.close();

        } catch (Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }

    }
}
