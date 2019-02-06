import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    byte[] encrypted;

    String encryptedtext;
    String decrypted;
    public String Encrypt(String pInput) {

        try {

            String Input = pInput;
            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(Input.getBytes());
            encryptedtext = Base64.encodeToString(encrypted, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedtext;
    }

    public String Decrypt(String pInput) {

        try {

            String Input = pInput;

            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            encrypted = Base64.encodeToString(encryptedtext, Base64.DEFAULT);
            decrypted = new String(cipher.doFinal(encrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pInput;
    }

    public static void main(String[] args) {
        String a = "Mateusz Ficek 12341%#%!@%!";
        String b = "";
        Crypt crypt = new Crypt();
        b = crypt.Encrypt(a);
        String c = crypt.Decrypt(b);
        System.out.println(b);
        System.out.println(c);
        System.out.println(c.equals(a));
    }
}
