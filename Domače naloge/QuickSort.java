/*

Deljenje tabele na dva oz. tri dele:

Delitev izvedite po spodnji psevdokodi.
Kot pivot vzemite sredinski element. V primeru dileme se "nagnite" na levo.
Bodite pozorni, da lahko array razpade tudi na tri dele.
Po vsaki delitvi izpišite sled, t.j. izpišite vse tri dele tabele z mejo "|".
Psevdokoda delitve (angl. partition) tabele:

i = levi;  j = desni
while i <= j do
    while table[i] < pivot do i = i + 1
    while table[j] > pivot do j = j - 1
    if i <= j then
        swap(i, j)
        i = i + 1
        j = j - 1
    endif
endwhile

Pri štetju operacij poleg swap ne pozabite šteti tudi premika, kjer shranite pivot.

Primer izvajanja:

trace qs up 8
8 5 6 1 7 2 0 9
0 1 | | 6 5 7 2 8 9 
| 0 | 1 
6 5 2 | | 7 8 9 
2 | 5 | 6 
7 | 8 | 9

count qs up 8
8 5 6 1 7 2 0 9
27 26
21 16
24 29

*/

import java.util.Scanner;

public class QuickSort {
    
    // trace - izpis sledi algoritma
    // count - izpis števila primerjav in premikov
    static String operation; 

    // oznaka izbranega algoritma
    static String algorithm;

    // up - nepadajoče
    // down - nenaraščajdoče
    static String directionOfSorting;
    
    static int sizeOfArray;

    static int numComparisons = 0; // št. primerjav
    static int numRearranges = 0; // št. prirejanj
    static int[] array;

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        operation = sc.next();
        algorithm = sc.next();
        directionOfSorting = sc.next();
        sizeOfArray = sc.nextInt();

        // preberemo tabelo
        int[] array  = new int [sizeOfArray];
        for (int i = 0; i < sizeOfArray; ++i)
            array[i] = sc.nextInt();
    
        
        sort(array, 0, sizeOfArray-1);
        printArray(array);
    }

    static int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        }
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    }
    
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 

   static void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
    static void izpis (int meja1, int meja2, int spodnjaMeja, int zgornjaMeja) {
        if(operation.equals("trace")) {
            for (int i = spodnjaMeja; i <= zgornjaMeja; i++) {
                if (i == meja1 && i == meja2)
                    System.out.format("| | ");
                else if (i == meja1 || i == meja2)
                    System.out.format("| ");
                System.out.format("%d ", array[i]);
            }
            System.out.println();
        }
    }

    static void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        numRearranges += 3;
    }

    static void count() {
        System.out.printf("%d %d\n", numComparisons, numRearranges);
        numComparisons = 0;
        numRearranges = 0;
    }
}
