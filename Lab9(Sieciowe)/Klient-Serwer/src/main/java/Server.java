import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 3000");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 3000");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;

        String login = "login";
        String password = "password";
        String ls = "plik1;plik2;plik3;";
        String plik1 = "To jest zawartość pliku pierwszego";
        String plik2 = "To jest zawartość pliku drugiego";
        String plik3 = "To jest zawartość pliku trzeciego";

        boolean isLogged = false;
        Pattern loginPattern = Pattern.compile("LOGIN ([\\w]+);(.*)");
        Pattern getPattern = Pattern.compile("GET ([\\w\\d]+)");

        while ((inputLine = in.readLine()) != null) {
            Matcher matcherLogin = loginPattern.matcher(inputLine);
            Matcher matcherGet = getPattern.matcher(inputLine);

            if (matcherLogin.find() && !isLogged) {
                if (login.equals(matcherLogin.group(1))) {
                    String passwd = matcherLogin.group(2);
                    if (password.equals(passwd)) {
                        out.println("zalogowano");
                        isLogged = true;
                    } else {
                        int l = Levenshtein(password, passwd);
                        out.println("Błędne hasło. Odległość Levenshteina: " + l);
                    }
                }
            } else if (inputLine.equals("LOGOUT") && isLogged) {
                out.println("wylogowano");
                isLogged = false;
            } else if (inputLine.equals("LS")) {
                if (isLogged) {
                    out.println(ls);
                } else {
                    out.println("nie zalogowano");
                }
            } else if (matcherGet.find()) {
                if (isLogged) {
                    if (matcherGet.group(1).equals("plik1")) {
                        out.println(plik1);
                    } else if (matcherGet.group(1).equals("plik2")) {
                        out.println(plik2);
                    } else if (matcherGet.group(1).equals("plik3")) {
                        out.println(plik3);
                    }
                } else {
                    out.println("nie zalogowano");
                }
            } else {
                out.println(inputLine);
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }

    public static int Levenshtein(String orginal, String copy) {
        int origSize = orginal.length();
        int copySize = copy.length();
        int[][] d = new int[origSize+1][copySize+1];
        for (int i = 0; i <= origSize; i++) {
            d[i][0] = i;
        }
        for (int i = 1; i <= copySize; i++) {
            d[0][i] = i;
        }

        int c;
        for (int i = 1; i <= origSize; i++) {
            for (int j = 1; j <= copySize; j++) {
                if (orginal.charAt(i-1) == copy.charAt(j-1)) {
                    c = 0;
                } else {
                    c = 1;
                }
                d[i][j] = Math.min(Math.min(d[i-1][j] + 1, d[i][j-1] + 1), d[i-1][j-1] + c);
            }
        }
        return d[origSize][copySize];
    }
}