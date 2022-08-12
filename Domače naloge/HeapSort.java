/*

Urejanje s kopico poteka v dveh fazah: gradnja kopice in dejansko urejanje. 
Tekom postopka je kopica v levem delu table, v desnem pa je urejeni seznam. 
Izpis sledi naj sestoji iz izpisa dela tabele, ki vsebuje kopico. 
Sled izpišite takoj po gradnji kopice in nato po vsakem pogrezanju elementa 
(vsakič kot je drevo v tabeli kopica). Vsako sled oz. kopico izpišite v svojo vrstico, 
pri čemer nivoje drevesa ločite z navpično črto. 
Primer: 2 | 3 4 | 5 6 7, predstavlja kopico, ki ima v rootu 2, na prvem nivoju števili 3 in 4, na zadnjem pa 5, 6 in 7.

trace hs up 8
8 5 6 1 7 2 0 9
9 | 8 6 | 5 7 2 0 | 1
8 | 7 6 | 5 1 2 0
7 | 5 6 | 0 1 2
6 | 5 2 | 0 1
5 | 1 2 | 0
2 | 1 0
1 | 0
0

count hs up 8
8 5 6 1 7 2 0 9
26 54
27 66
24 48

*/

import java.util.Scanner;

public class HeapSort {
    
    // trace - izpis sledi algoritma
    // count - izpis števila primerjav in premikov
    static String operation; 

    // oznaka izbranega algoritma
    static String algorithm;

    // up - nepadajoče
    // down - nenaraščajoče
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

        String x = "error";
        switch (operation) {
            case "trace": // izpis sledi algoritma

                    buildHeap(array, sizeOfArray);
                    heapsort(array, sizeOfArray);
                    
                    break;

            case "count": 
                   
                    // zgradimo kopico, za urejeno zaporedje
                    buildHeap(array, sizeOfArray);
                    heapsort(array, sizeOfArray);
                    count();

                    // zgradimo kopico, že urejeno zaporedje
                    buildHeap(array, sizeOfArray);
                    heapsort(array, sizeOfArray);
                    count();

                    // zamenjava smeri
                    if (directionOfSorting.equals("up"))
                        directionOfSorting = "down";
                    else   
                        directionOfSorting = "up";
                    buildHeap(array, sizeOfArray);
                    heapsort(array, sizeOfArray);
                    count();
                    
            default: x = "Error";
                    break;
        }
    }

    static void siftUp(int[] array, int oldRoot, int lenghtHeep) {
        int root = oldRoot;
        int leftSon = 2 * oldRoot + 1;
        int rightSon = 2 * oldRoot + 2;

        if (leftSon < lenghtHeep) 
            numComparisons++;

        if (leftSon < lenghtHeep && array[leftSon] > array[root])
            root = leftSon;
    
        if (rightSon < lenghtHeep) 
            numComparisons++;

        if (rightSon < lenghtHeep && array[rightSon] > array[root])
            root = rightSon;
    
        if (root != oldRoot) {
            swap(array, root, oldRoot);
            siftUp(array, root, lenghtHeep);
        }
    }
        
    static void siftDown(int[] array, int oldRoot, int lenghtHeep) {
        int root = oldRoot;
        int leftSon = 2 * oldRoot + 1;
        int rightSon = 2 * oldRoot+ 2;
        
        if (leftSon < lenghtHeep) 
            numComparisons++;

        if (leftSon < lenghtHeep && array[leftSon] < array[root])
            root = leftSon;
    
        if (rightSon < lenghtHeep) 
            numComparisons++;

        if (rightSon < lenghtHeep && array[rightSon] < array[root])
            root = rightSon;
    
        if (root != oldRoot) {
            swap(array, root, oldRoot);
            siftDown(array, root, lenghtHeep);
        }
    }

    static void heapsort(int[] array, int sizeOfArray) {
        if (operation.equals("trace")) {
            // en element po en vzamemo iz kopice
            int j = sizeOfArray - 1;
            while (j >= 0) {
                // trenutno koren premakni na konec
                printArray(array, j);
                swap(array, 0, j);
                numRearranges -= 3;
                // pokliči maksimalen sink na reducirani kopici
                if (directionOfSorting.equals("down"))
                    siftDown(array, 0, j);
                else if (directionOfSorting.equals("up"))
                    siftUp(array, 0, j);
                j--;
            }
        } else {
            int j = sizeOfArray - 1;
            while (j >= 0) {
                swap(array, 0, j);
                numRearranges -= 3;
                if (directionOfSorting.equals("down"))
                    siftDown(array, 0, j);
                else if (directionOfSorting.equals("up"))
                    siftUp(array, 0, j);
                j--;
                numRearranges += 3;
            }
        }
    }

    static void buildHeap(int[] array, int sizeOfArray) {
        for (int i = sizeOfArray / 2 - 1; i >= 0; i--) {
            if (directionOfSorting.equals("down"))
                siftDown(array, i, sizeOfArray);
            else if (directionOfSorting.equals("up"))
                siftUp(array, i, sizeOfArray);
        }
    }

    static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        numRearranges += 3;
    }

    static void count() {
        System.out.printf("%d %d\n", numComparisons, numRearranges - 3);
        numComparisons = 0;
        numRearranges = 0;
    }

    public static int power(int number, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; ++i) 
            result *= number;
        return result;
    } 

    static void printArray(int[] array, int lenghtHeep) {
        int level = 1; int counter = 1;

        for (int i = 0; i <= lenghtHeep; ++i) {
            if (counter == 0) {
                counter = power(2, level++); // število sinov se povečuje glede na nivo
                System.out.print("| ");
            }
            counter--;
            System.out.printf("%d ", array[i]);
        }
        System.out.println();
    }
}
