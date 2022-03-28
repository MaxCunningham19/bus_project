import java.util.ArrayList;
import java.util.Queue;

public class TST {
    private int n;
    private TSTnode root;

    class TSTnode {
        private char c;
        private TSTnode left,mid,right;
        private boolean isWord;
        StopInfo stopInfo;
    }

    public int size(){
        return n;
    }

    public boolean contains(String word){
        if(word == null){
            return false;
        }
        
        return get(word) != null;
    }

    public ArrayList<String> wordsContaining(String prefix){
        if(prefix == null) return null;
        TSTnode x = get(prefix);
        ArrayList<String> list = new ArrayList<>();
        return null;
    }

    public String[] wordsContaining(TSTnode x, String prefix, ArrayList<String> list){
        return null;
    }

    public TSTnode get(String word){
        if (word == null) return null;
        if (word.length() == 0) return null;
        return get(root, word, 0);
    }

    public TSTnode get(TSTnode x, String word, int d){
        if (x == null) return null;
        if (word.length() == 0) return null;
        char c = word.charAt(d);
        if      (c < x.c)              return get(x.left,  word, d);
        else if (c > x.c)              return get(x.right, word, d);
        else if (d < word.length() - 1) return get(x.mid,   word, d+1);
        else                           return x;
    }

    public void put(String word, String info){
        if(word == null) return;
        root = put(root, word, info, 0);
    }

    private TSTnode put(TSTnode x, String word,String info, int d){
        char c = word.charAt(d);
        if (x == null) {
            x = new TSTnode();
            x.c = c;
        }
        if      (c < x.c)               x.left  = put(x.left,  word, info, d);
        else if (c > x.c)               x.right = put(x.right, word, info,  d);
        else if (d < word.length() - 1) x.mid   = put(x.mid,   word, info,  d+1);
        else                            x.isWord = true; x.stopInfo = new StopInfo(info);
        return x;
    }

}
