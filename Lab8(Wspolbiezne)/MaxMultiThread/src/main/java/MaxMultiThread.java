import java.io.*;
import java.util.Scanner;


public class MaxMultiThread implements Runnable {
    private BufferedWriter writer;
    private Scanner reader;
    private String line;
    public int funkcjaW(int x){
        return x*x - 2*x;
    }

    MaxMultiThread(BufferedWriter writer, String line){
        this.writer = writer;
        this.line=line;
    }

    @Override public void run(){
        String[] nums = line.split(" ");
        String toPrint = "";
        for(String num : nums){
            int n = Integer.parseInt(num);
            int result = funkcjaW(n);
            toPrint+=Double.toString(result)+" ";
        }
        try {
            synchronized (writer) {
                writer.append(toPrint);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Finished parsing");
            return;
        }
    }
}