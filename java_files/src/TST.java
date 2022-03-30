import java.util.ArrayList;
import java.util.Queue;

public class TST {
    private int n;
    private TSTnode root;

    class TSTnode {
        private char c;
        private TSTnode left,mid,right;
        private boolean isWord;
        private StopInfo stopInfo;

        TSTnode(){
            isWord = false;
            stopInfo = null;
        }
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

    public String getPrefix(String prefix){
        ArrayList<StopInfo> list = keysWithPrefix(prefix);
        String ans = "";
        for(int i=0;i< list.size();i++){
            ans = ans + list.get(i).print() + "\n";
        }
        return ans;
    }

    public ArrayList<StopInfo> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        ArrayList<StopInfo> list = new ArrayList<>();
        TSTnode x = get(root, prefix, 0);
        if (x == null) return null;
        if (x.isWord) list.add(x.stopInfo);
        collect(x.mid, new StringBuilder(prefix), list);
        return list;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(TSTnode x, StringBuilder prefix, ArrayList<StopInfo> list) {
        if (x == null) return;
        collect(x.left,  prefix, list);
        if (x.isWord) list.add(x.stopInfo);
        collect(x.mid,   prefix.append(x.c), list);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, list);
    }

    public String getInfo(String word){
        TSTnode x = get(word);
        if(x==null) return "Stop Not Found";
        if(x.isWord) {
            return x.stopInfo.print();
        }
        return "Stop Not Found";
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
        if(word == null ) return;
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
        else {
            x.isWord = true;
            x.stopInfo = new StopInfo(info);
        }
        return x;
    }

}
