public class Polibiusz implements Algorithm{
    private static char[][] tab = {{'a','b','c','d','e'},{'f','g','h','i','j'},{'k','l','m','n','o'},{'p','q','r','s','t'},{'u','w','x','y','z'}};

    @Override
    public String crypt(String word) {
        String newWord ="";
        for(int k=0;k<word.length();k++) {
            char c = word.charAt(k);
            if(c == 'j'){
                c='i';
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(tab[i][j] == c){
                        newWord+= Integer.toString(i)+Integer.toString(j);
                        break;
                    }
                }
            }
        }
        return newWord;
    }

    @Override
    public String decrypt(String word) {
        String newWord="";
        int i=0;
        int j=0;
        for(int k=0;k<word.length();k++){
            if(k%2 == 0){
                i = Character.getNumericValue(word.charAt(k));
            }
            else {
                j = Character.getNumericValue(word.charAt(k));
                newWord+=tab[i][j];
            }
        }

        return newWord;
    }
}
