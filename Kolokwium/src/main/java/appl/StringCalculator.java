package appl;

import excp.BadInputException;
import excp.NothingToSubstractFromException;
import excp.TooBigNumberException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StringCalculator extends Calculator {
    private String result;

    StringCalculator(String beg) {
        result = beg;
    }

    @Override
    public String Add(String a) {
        result += a;
        return result;
    }

    @Override
    public String Substract(String x) {
        if(result.equals("")){
            throw new NothingToSubstractFromException("Result is empty");
        }
        return result.replace(x, "");
    }

    @Override
    public String Multiply(int x) throws TooBigNumberException{
        if(x>5){
            throw new TooBigNumberException("Too big number");
        }
        String newStr = "";
        for(int i = 1; i<x; i++){
            newStr += result;
        }
        return newStr;
    }

    @Override
    public void SaveToFile(String x, String filename) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter(filename));
        write.write(x);
        write.close();
    }

    public static void main(String[] args) {
        StringCalculator s = new StringCalculator("abmabaabt");
        try {
            System.out.println(s.Add("M"));
            System.out.println(s.Multiply(6));
            System.out.println(s.Substract("ab"));
            s.SaveToFile("m", "file.txt");
        } catch (IOException | TooBigNumberException | NothingToSubstractFromException | BadInputException e) {
            System.out.print(e.getMessage());
        } finally {
            System.out.print("Done");
        }
    }
}
