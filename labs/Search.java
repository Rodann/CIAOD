import com.sun.source.tree.BinaryTree;

import java.util.*;

public class Search {

    public static void main(String[] args) {
        final Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("vvedite kol-vo elementov massiva");
        int n = scanner.nextInt();
        int[] mas = create(n);
        int item = mas[random.nextInt(n)];
        System.out.println(Arrays.toString(mas)+" ищем:"+ item);

        long t1= System.currentTimeMillis();
        System.out.println("index: " + BinarySearch(mas, item));
        long t2= System.currentTimeMillis();
        System.out.println("Binsearch: " + (t2-t1));
        System.out.println();
        t1= System.currentTimeMillis();
        System.out.println("index: " + Interpol(mas, item));
        t2= System.currentTimeMillis();
        System.out.println("Interpol: " + (t2-t1));
        System.out.println();
        BinTreeSearch(mas, item);
        System.out.println();
        t1= System.currentTimeMillis();
        System.out.println("index: " + (FibonacciSearch(mas, item)));
        t2= System.currentTimeMillis();
        System.out.println("Fibsearch: " + (t2-t1));
        System.out.println();
        System.out.println(Hashing1(mas,item));
        System.out.println();
        System.out.println(Hashing(mas,item));

    }

    public static int[] create(int n){
        final Random random = new Random();
        int[] a = new int[n];
        for (int i = 0;i<a.length;i++){
                a[i]= random.nextInt(1000);
        }

        Arrays.sort(a);

        return a;
    }

    public static int BinarySearch(int[] arr, int i){

        int start = 0;
        int end = arr.length - 1;
        return Bi(arr, start, end, i);
    }

    public static int Bi(int[] arr,int start,int end,int i){
        if (end==0) return end;
        if (i == arr[end]) return end;
        int middle = start + (end - start)/2;
        if(arr[middle]<i) {start = middle;}
        else if(arr[middle]>i){end = middle;}else return middle;
        return Bi(arr,start,end,i);
    }

    public static int Interpol(int[] arr, int i){
        int start = 0;
        int end = arr.length - 1;
        return Inter(arr,start,end,i);
    }

    public static int Inter(int[] arr,int start,int end,int i){
        if (end==0) return end;
        if (i == arr[end]) return end;
        int middle = start + ((i-arr[start])*(end-start))/(arr[end]-arr[start]);
        if(arr[middle]<i) {start = middle+1;}
        else if(arr[middle]>i){end = middle-1;}else return middle;
        return Inter(arr,start,end,i);
    }

    public static boolean Hashing(int [] arr, int i){
        long t1 = System.currentTimeMillis();

        HashTable HT = new HashTable(arr);

        boolean ka = HT.exists(i);
        long t2= System.currentTimeMillis();
        System.out.println("hash цепочками: " + (t2-t1));
        HT.print();
        return ka;
    }

    public static boolean Hashing1(int[] arr, int i){
        long t1 = System.currentTimeMillis();

        Hash1 HT = new Hash1(arr);

        boolean ka = HT.exists(i);
        long t2= System.currentTimeMillis();
        System.out.println("hash простой: " + (t2-t1));
        HT.print();
        return ka;
    }

    public static void BinTreeSearch(int [] arr, int i){

        long t1= System.currentTimeMillis();

        Tree tree = new Tree();

        for (int item: arr) tree.insert(item);

        Node a = tree.find(i);
        long t2= System.currentTimeMillis();
        System.out.println("bintree: " + (t2-t1));
        a.printNode();

    }

    public static int FibonacciSearch(int [] mas, int ind){
        int n = mas.length;
        int k = 0;
        int indtmp = ind;
        while (Fibonacci(k+1)<n+1) k++;
        int M = Fibonacci(k+1) - (n+1);
        int i = Fibonacci(k)-M;
        int p = Fibonacci(k-1);
        int q = Fibonacci(k-2);
        return FibS(mas,i,q,p,indtmp);

    }

    public static int FibS(int[] mas, int i, int q, int p,int item){

        if (i < 0){
            if ((p != 1) ){i+=q;p-=q;q-=p; return FibS(mas,i,q,p,item);}else {return -1;}//5
        } else {

            if (i >= mas.length) {
                if ((q != 0)) {
                    i -= q;
                    int tmp = q;
                    q = p - tmp;
                    p = tmp;
                    return FibS(mas, i, q, p, item);
                } else {
                    return -1;
                }
            } else {
                if (item < mas[i]) {
                    if ((q != 0)) {
                        i -= q;
                        int tmp = q;
                        q = p - tmp;
                        p = tmp;
                        return FibS(mas, i, q, p, item);
                    } else {
                        return -1;
                    }
                } else {
                    if (item > mas[i]) {
                        if ((p != 1)) {
                            i += q;
                            p -= q;
                            q -= p;
                            return FibS(mas, i, q, p, item);
                        } else {
                            return -1;
                        }//5

                    } else {
                        if (item == mas[i]) return i;
                    }
                }
            }
        }
        return FibS(mas, i ,q,p,item);
    }

    public static int Fibonacci(int i){
        if (i==0) return 0;
        if (i==1) return 1;
        return Fibonacci(i-1) + Fibonacci(i-2);
    }


    public static int[] addd(int[] a,int n){
        int[] b = new int[a.length+1];
        b = Arrays.copyOf(a,  b.length);
        b[b.length-1]= n;
        Arrays.sort(b);
        return b;
    }


    public static int[] drop(int[] a, int n){
        int[] b = new int[a.length-1];
        System.arraycopy(a, 0,b,0,n );
        System.arraycopy(a,n+1,b,n,a.length-n-1);
        Arrays.sort(b);
        return b;
    }
}
