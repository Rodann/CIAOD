import java.util.*;

public class HashTable {

    Map<Integer, LinkedList<Integer>> dictionary = new HashMap<Integer, LinkedList<Integer>>();

    public HashTable(int[] arr){
        for (int item: arr) this.insert(item);
    }

    public HashTable(){}

    public void insert(int a){
        LinkedList<Integer> ly= new LinkedList<Integer>();
        if (dictionary.get(Hash(a))==null){}else{
        ly = dictionary.get(Hash(a));}
        ly.add(a);
        dictionary.put(Hash(a),ly);
    }

    public boolean exists(int a){
        if (dictionary.get(Hash(a))==null) return false;
        LinkedList<Integer> ly = dictionary.get(Hash(a));
        for (Integer integer : ly) {
            if ( integer == a) return true;
        }
        return false;
    }

    public void print(){Set<Integer> numbersSet =  dictionary.keySet();

        List<Integer> numbersList = new ArrayList<Integer>(numbersSet) ;


        Collections.sort(numbersList);

        for (int key: numbersList) {
            System.out.println(key+" "+(dictionary.get(key)));
        }
    }

    public static int Hash(int digit){return digit%15;}
}
