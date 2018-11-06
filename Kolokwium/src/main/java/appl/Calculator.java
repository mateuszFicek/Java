package appl;

import java.io.IOException;

public abstract class Calculator {
    public abstract void SaveToFile(String x, String filename) throws IOException;
    public abstract String Add(String a);
    public abstract String Substract(String x);
    public abstract String Multiply(int x);
}
