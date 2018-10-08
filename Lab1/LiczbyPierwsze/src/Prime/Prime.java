package Prime;

public class Prime {
    private int n;
    public Prime(int _n){
        n = _n;
    }

    public void getPrimeNumbers(int _n){
        for(int i = 2;i<=n;i++){
            boolean primenumber = true;
            for(int j = 2; j<i; j++){
                if(i != j && i%j==0){
                    primenumber = false;
                    break;
                }
            }
            if(primenumber){
                System.out.println(i);
            }
        }
    }
}
