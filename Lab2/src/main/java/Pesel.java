public class Pesel {
    public static void check(String to_check){
        if(to_check.length() != 11){
            System.out.print("Nieodpowiednia ilosc znakow, bledny pesel");
            return;
        } else if(last_digit(to_check)){
            System.out.print("Poprawny numer pesel");
        } else {
            System.out.print("Pesel bledny , zla liczba kontrolna");
        }
    }

    static boolean last_digit(String to_check){
        int[] digits = new int[11];
        int sum = 0;
        for(int i=0; i < to_check.length() ; i++ ){

            if(i == to_check.length()-1){
                digits[i] = Integer.parseInt(to_check.substring(i));
            } else {
                digits[i] = Integer.parseInt(to_check.substring(i, i + 1));
            }
        }
        sum = 1*digits[0] + 3*digits[1] + 7*digits[2] + 9*digits[3] + 1*digits[4] + 3*digits[5] + 7*digits[6] + 9*digits[7] + 1*digits[8] + 3*digits[9] + 1*digits[10];
        if(sum%10 == 0){
            return true;
        } else {
            return false;
        }
    }
}
