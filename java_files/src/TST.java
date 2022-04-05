import java.util.ArrayList;

public class TST {
    private int n;
    private TSTnode root;

    /*
    * This class is the data structure that i decided to use for storing the stop names
    * it implements a ternary search tree which stores string is the following format
    *
    *                  C
    *                  |
    *                  A ---- O
    *                  |      |
    *                  T  A - K
    *                     |   |
    *                     T   E
    *
    *  I took inspiration for this class and some of its methods from
    *  "https://algs4.cs.princeton.edu/52trie/TST.java.html" / Algorithms, 4th Edition
    */
    TST(){

    }

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

    /*
     * @brief:this returns the number of words in the TST
     *
     * @param: NULL
     *
     * @return: the number of words
     */
    public int size(){
        return n;
    }

    /*
     * @brief: returns all the stops containing the prefix
     *
     * @param:
     *        prefix: this is the sub-string of the stops
     *
     * @return: this returns the string containing all the stops and their info that match the prefix
     */
    public String getPrefix(String prefix){
        ArrayList<StopInfo> list = keysWithPrefix(prefix);
        if(list==null) return "\nNo Stops Exist With That Prefix\n ";
        String ans = "\n---------------\n";
        for (StopInfo stopInfo : list) {
            ans = ans + stopInfo.print() + "\n";
        }
        return ans + "---------------";
    }

    /*
     * @brief: this function returns the list containing all StopsInfo that match the string prefix
     *
     * @param:
     *       prefix: this is the sub-string of the stops the user is searching for
     *
     * @return: An arraylist containing all the stops and their infos that match the prefix
     *
     */
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

    /*
     * @brief: This method looks for string that match the prefix and recursivly looks for more stops with the prefix
     *
     * @param:
     *        x: this is the current TSTnode being looked at by the function
     *
     *        prefix: this is a stringBuilder that contains the sub-string prefix and any other additional chars added on by the function
     *
     *        list: this is an ArrayList containing all stops matching the previous prefixes
     *
     * @return:
     */
    private void collect(TSTnode x, StringBuilder prefix, ArrayList<StopInfo> list) {
        if (x == null) return;
        collect(x.left,  prefix, list);
        if (x.isWord) list.add(x.stopInfo);
        collect(x.mid,   prefix.append(x.c), list);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, list);
    }

    /*
     * @brief: This is a wrapper method that returns the stop info if the stop is found in the TST
     *
     * @param:
     *       word: this is the String that the user has inputted and are searching for in the TST
     *
     * @return: the info regrading the stop they searched for
     */
    public String getInfo(String word){
        TSTnode x = get(word);
        if(x==null) return "Stop Not Found";
        if(x.isWord) {
            return x.stopInfo.print();
        }
        return "Stop Not Found";
    }

    /*
     * @brief: this method returns the node that matches the word inputted by the user
     *
     * @param:
     *       word: this is the user inputted string that they want to find the stop info of
     *
     * @return: TSTnode this returns the node that matches the word
     */
    public TSTnode get(String word){
        if (word == null) return null;
        if (word.length() == 0) return null;
        return get(root, word, 0);
    }

    /*
     * @brief: this method retrives the node mathcing the string word if it exists
     *
     * @param:
     *       x: this is the current node beinging looked at
     *
     *       word: the string that the function is searching for
     *
     *       d: the current index of the character in the word that is being looked at
     *
     * @return: the node that matches the word if it exists
     *
     */
    public TSTnode get(TSTnode x, String word, int d){
        if (x == null) return null;
        if (word.length() == 0) return null;
        char c = word.charAt(d);
        if      (c < x.c)              return get(x.left,  word, d);
        else if (c > x.c)              return get(x.right, word, d);
        else if (d < word.length() - 1) return get(x.mid,   word, d+1);
        else                           return x;
    }

    /*
     * @brief: this adds the string word into the TST
     *
     * @param:
     *       word: the string that is being added to the TST
     *
     *       info: the stop info being stored in the node
     *
     * @return: NULL
     */
    public void put(String word, String info){
        if(word == null ) return;
        root = put(root, word, info, 0);
    }

    /*
     * @brief:  This method recursivly adds nodes into the TST till it has added the whole word and stored the info
     *          it then pushes the nodes back up the TST
     *
     * @param:
     *       x: the current node being loked at
     *
     *       word: the word being added into the TST
     *
     *       info: the stop info being stored in the TST
     *
     *       d: the index of the current char being looked at
     *
     * @return: the TSTnode that is being pushed up the TST
     */
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
