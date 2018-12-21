import java.io.*;
import java.util.Random;

public class GenerateRandoms implements Runnable{

    public GenerateRandoms(BufferedWriter plik) {
        this.zapis=plik;
    }
    public BufferedWriter zapis;

    @Override
    public void run(){
        generateRandoms();
    }

    public void generateRandoms(){
            Random rand = new Random();
            String line = "";
            for(int i = 0; i<10000; i++) {
                    int r = rand.nextInt()/1000000;
                    line += Integer.toString(r);
                    line += " ";
            }
            try {
                synchronized (zapis){
                    zapis.append(line);
                    zapis.newLine();
                    System.out.println(Thread.currentThread().getName());
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "- Done");
            }
    }

}
