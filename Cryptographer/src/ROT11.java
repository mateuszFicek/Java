public class ROT11 implements Algorithm{
    public String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public boolean checkValidChar(char a){
        return alfabet.contains(Character.toString(a));
    }
    @Override
    public String crypt(String word){
        String newWord = "";
        int przesuniecie = 13;
        for(int i = 0; i<word.length();i++){
            char c = word.charAt(i);
            if(checkValidChar(c)){
                if(c >= 'a' && c <= 'm'){
                    c += przesuniecie;
                    newWord += c;
                }
                else if (c >= 'A' && c <= 'M') {
                    c += przesuniecie;
                    newWord += c;
                }
                else if (c >= 'n' && c <= 'z') {
                    c -= przesuniecie;
                    newWord += c;
                }
                else if  (c >= 'N' && c <= 'Z') {
                    c -= przesuniecie;
                    newWord += c;
                }
            }
            else  newWord+=c;
        }

            return newWord;
        }

    @Override
    public String decrypt(String word) {
        String newWord = "";
        int przesuniecie = 13;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (checkValidChar(c)) {
                if (c >= 'a' && c <= 'm') {
                    c += przesuniecie;
                    newWord += c;
                } else if (c >= 'A' && c <= 'M') {
                    c += przesuniecie;
                    newWord += c;
                } else if (c >= 'n' && c <= 'z') {
                    c -= przesuniecie;
                    newWord += c;
                } else if (c >= 'N' && c <= 'Z') {
                    c -= przesuniecie;
                    newWord += c;
                }
            }
            else newWord += c;
        }
        return newWord;
        }
}
