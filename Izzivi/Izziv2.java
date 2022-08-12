/*
Napišite program, ki na standardnem vhodu najprej dobi dolžino tabele n, nato pa iz preostanka standardnega vhoda 
prebere tabelo celih števil dolžine n.
Prebrano tabelo uredite s kopico.
Izpisujte sled algoritma na naslednji način:
- vsakič, ko imate v tabeli pravo kopico jo izpišite (po gradnji kopice, nato pa po vsakem pogrezanju),
- vsako kopico izpišite v svojo vrstico,
- kopico izpišite tako, da vsak nivo drevesa ločite z navpično črto
    primer: 7 | 6 5 | 4 3 2, predstavlja kopico, ki ima v korenu 7, na prvem nivoju števili 6 in 5, na zadnjem pa 4, 3 in 2.
*/

import java.util.*;

public class Izziv2 {
    public static int [] array = {};
    public static void main(String [] args) {
        // preberemo dolžino tabele n
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // preberemo tabelo dolžine n
        array = generateTable(n, sc);  
        
        // rgemo od zadnjega notranjega vozlišča proti korenu
        for (int lengthHeap = array.length - 1; lengthHeap >= 0; --lengthHeap) {
            siftDown(0, lengthHeap);

            // izpis kopice
            int level = 1;
            int counter = 1;

            // izpis kopice (in ločitev nivojev kopice) 
            for (int i = 0; i <= lengthHeap; ++i) {
                if (counter == 0) {
                    System.out.print("| ");
                    counter = power(2, level); // število sinov se povečuje glede na nivo
                    level++;
               }
                System.out.format("%d ", array[i]);
                counter--;
            }

            System.out.println();

            // zamenjaj koren kopice (indeks 0) z zadnjim elementom kopice
            int tmp = array[lengthHeap];
            array[lengthHeap] = array[0];
            array[0] = tmp;
        }
    }

   // root ... začetek (prvi element, array[0]) 
   // lengthHeap ... dolžina podtabele, ki trenutno predstavlja kopico
    public static void siftDown(int root, int lengthHeap) {
        int leftSon = 2 * root + 1;
        int rightSon = 2 * root + 2;
        int tmp = 0;
    
        if (rightSon <= lengthHeap)
            siftDown(rightSon, lengthHeap);
        
        if (leftSon <= lengthHeap)
            siftDown(leftSon, lengthHeap);
        else 
            return;

        // sicer izberemo najmanjšega/največjega sina, ga zamenjamo s korenom in 
        // rekurzivno pogreznemo koren v ustrezno poddrevo
        
        if (rightSon > lengthHeap) {
            if (array[leftSon] > array[root]) {
                tmp = array[leftSon];
                array[leftSon] = array[root];
                array[root] = tmp;
                siftDown(leftSon, lengthHeap);
            }
        } else {
            if (max(array, leftSon, rightSon, root) == 0)
                return;
            else if (max(array, leftSon, rightSon, root) == 1) {
                tmp = array[leftSon];
                array[leftSon] = array[root];
                array[root] = tmp;
                siftDown(leftSon, lengthHeap);
            }
            else if (max(array, leftSon, rightSon, root) == 2) {
                tmp = array[rightSon];
                array[rightSon] = array[root];
                array[root] = tmp;
                siftDown(rightSon, lengthHeap);
            }
        }
    }

    public static int max(int[] array, int leftSon, int rightSon, int root) {
        root = array[root];
        leftSon = array[leftSon];
        rightSon = array[rightSon];

        if (root > leftSon && root > rightSon)
            return 0;
        else if (leftSon >= rightSon)
            return 1;
        else
            return 2;
    }

    public static int[] generateTable(int n, Scanner sc) {
        int[] array = new int[n];

        for (int i = 0; i < n; ++i)
            array[i] = sc.nextInt();
        return array;
    }

    public static int power(int number, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; ++i) 
            result *= number;
        return result;
    } 
}

/*

import java.util.Scanner;

public class Izziv2 {
  public static void dump_heap(int n, int a[]) {
    System.out.print(a[0]);

    for (int i = 1, items_on_level = 2; i < n; items_on_level *= 2) {
      System.out.print(" |");
      for (int j = 0; j < items_on_level && i < n; j++, i++)
        System.out.print(" " + a[i]);
    }
    System.out.println();
  }

  public static void swap(int a[], int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void sink(int n, int a[], int i) {
    while (true) {
      int l_anc = 2 * i + 1;
      if (l_anc >= n)
        break;

      int max = l_anc;
      int r_anc = l_anc + 1;
      if (r_anc < n && a[l_anc] < a[r_anc])
        max = r_anc;

      if (a[i] >= a[max])
        break;

      swap(a, i, max);
      i = max;
    }
  }

  public static void heapify(int n, int a[]) {
    for (int i = (n - 1) / 2; i >= 0; i--) {
      sink(n, a, i);
    }
  }

  public static void heapsort(int n, int a[]) {
    heapify(n, a);
    dump_heap(n, a);

    for (int i = n - 1; i > 0; i--) {
      swap(a, 0, i);
      sink(i, a, 0);
      dump_heap(i, a);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] a = new int[n];
    for (int i=0; i < n; i++) {
      a[i] = sc.nextInt();
    }
    heapsort(n, a);
  }
}

*/