public class Main {
    public static void main(String[] args){
        DataFrame df = new  DataFrame(new String[]{"kol1","kol2","kol3"},
                new String[]{"int","double","MyCustomType"});
        System.out.println(df.size());
    }
}
