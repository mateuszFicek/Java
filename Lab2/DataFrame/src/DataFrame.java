import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class DataFrame {
    String[] names;
    String[] types;
    ArrayList<ArrayList<Object>> values;

    public DataFrame(String[] names,String[] types){
        this.names=names;
        this.types=types;
    }

    public int size(){
        return values.get(0).size();
    }

    public ArrayList<Object> get(String colname){
        for(int i=0;i<names.length;i++) {
            if (names[i] == colname) {
                return values.get(i);
            }
        }
        return values.get(1);
    }
    public DataFrame get(String[] cols,boolean copy){
        ArrayList<ArrayList<Object>> valuestocopy;
        String[] typestocopy={};
        for(int i=0;i<cols.length;i++){
            if(Arrays.asList(this.names).contains(cols[i])){
                typestocopy[typestocopy.length]=this.types[Arrays.asList(this.types).indexOf(cols[i])];
            }
        }
        return new DataFrame(cols,typestocopy);
    }

    public DataFrame iloc(int i){
        DataFrame fr=new DataFrame(this.names, this.types);
        fr.values.set(0,this.values.get(i));
        return fr;
    }

    public DataFrame iloc(int from, int to){
        DataFrame fr=new DataFrame(this.names, this.types);
        for(int i=from;i<to;i++){
            fr.values.add(this.values.get(i));
        }
        return fr;
    }


}