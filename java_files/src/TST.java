public class TST {
    private int n;
    private TSTnode root;

    class TSTnode {
        private char c;
        private TSTnode left,mid,right;
        private boolean isWord;
        String stopInfo;
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

//    public String[] wordsContaining(String prefix){
//        if(prefix == null) return null;
//
//
//    }

    public String get(String word){
        if (word == null) return null;
        if (word.length() == 0) return null;
        TSTnode x = get(root, word, 0);
        if (x == null) return null;
        return x.stopInfo;
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
        else                            x.isWord = true; x.stopInfo = info;
        return x;
    }

}
