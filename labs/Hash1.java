import java.util.*;


public class Hash1 {
    Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();

    public void insert(int a){
        int i = Hash(a);
        if(dictionary.get(i)==null) {
                dictionary.put(i,a);
        }else {
            while (i<=200){
                if(dictionary.get(i)==null){
                    dictionary.put(i,a);
                    break;
                }else i++;
            }
            if (i==201) System.out.println("Таблица переполнена");
        }
    }

    public Hash1(int[] arr){
        for (int item: arr) this.insert(item);
    }

    public Hash1(){}

    public boolean exists(int a){
        int i = Hash(a);
        if(dictionary.get(i)==null) return false;
        if (dictionary.get(i)==a){return true;}else {
            while (i <= 200){
                if(dictionary.get(i)==a){
                    return true;
                }else i++;

            }
        }
        return false;
    }

    public void print(){

        Set<Integer> numbersSet =  dictionary.keySet();

        List<Integer> numbersList = new ArrayList<Integer>(numbersSet) ;        //set -> list


        Collections.sort(numbersList);//Sort the list

        for (int key: numbersList) {
            System.out.println(key+" "+dictionary.get(key));
        }
    }

    public static int Hash(int digit){return digit%15;}
}
