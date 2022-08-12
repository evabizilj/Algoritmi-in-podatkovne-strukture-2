/*

Na začetku urejeni del vsebuje prvi element tabele. Tekom algoritma urejeni del narašča od leve proti desni. 
Na vsakem koraku notranje zanke se prvi element v neurejenem delu potisne v urejeni del tako, da menjamo 
zaporedne elemente, dokler urejeni del tabele ne postane zopet urejen. Nato se izpiše sled. 
Sled naj se izpiše tudi na začetku algoritma.

trace is up 6
8 5 6 1 7 2
8 | 5 6 1 7 2
5 8 | 6 1 7 2 
5 6 8 | 1 7 2 
1 5 6 8 | 7 2 
1 5 6 7 8 | 2 
1 2 5 6 7 8 |

count is up 6
8 5 6 1 7 2
13 30
5 0
15 45

*/

import java.util.Scanner;

public class InsertionSort {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        // trace - izpis sledi algoritma
        // count - izpis števila primerjav in premikov
        String operation; 

        // oznaka izbranega algoritma
        String algorithm;

        // up - nepadajoče
        // down - nenaraščajoče
        String directionOfSorting;

        // število vhodnih podatkov
        int sizeOfArray;

        operation = sc.next();
        algorithm = sc.next();
        directionOfSorting = sc.next();
        sizeOfArray = sc.nextInt();

        int numComparisons = 0; // št. primerjav
        int numRearranges = 0; // št. prirejanj

        // preberemo tabelo
        int[] array  = new int [sizeOfArray];
        for (int i = 0; i < sizeOfArray; ++i)
            array[i] = sc.nextInt();

        String x = "error";
        switch (operation) {
            case "trace": // izpis sledi algoritma

                    int idx = 0;
                    for (int i = 0; i < sizeOfArray; ++i) {
                        idx = i;
                        for (int j = i - 1; j >= 0; --j) {
                            if (directionOfSorting.equals("up")) {
                                if (array[idx] < array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                } else
                                    break;
                            }
                            else {
                                if (array[idx] > array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                } else
                                    break;
                            }
                        }
                        printArray(array, i + 1);
                    }
                    break;
                    
            case "count": 
                    
                    // uredimo podano zaporedje
                    idx = 0;
                    for (int i = 0; i < sizeOfArray; ++i) {
                        idx = i;
                        for (int j = i - 1; j >= 0; --j) {
                            numComparisons++;
                            if (directionOfSorting.equals("up")) {
                                if (array[idx] < array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                            else {
                                if (array[idx] > array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                        }
                    }
               
                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;

                    // (že urejenega) še enkrat uredimo
                    idx = 0;
                    for (int i = 0; i < sizeOfArray; ++i) {
                        idx = i;
                        for (int j = i - 1; j >= 0; --j) {
                            numComparisons++;
                            if (directionOfSorting.equals("up")) {
                                if (array[idx] < array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                            else {
                                if (array[idx] > array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                        }
                    }
               
                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;

                   // uredimo v obratni smeri
                   idx = 0;
                    for (int i = 0; i < sizeOfArray; ++i) {
                        idx = i;
                        for (int j = i - 1; j >= 0; --j) {
                            numComparisons++;
                            if (directionOfSorting.equals("up")) {
                                if (array[idx] > array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                            else {
                                if (array[idx] < array[j]) {
                                    swap(array, idx, j);
                                    idx--;
                                    numRearranges += 3;
                                } else
                                    break;
                            }
                        }
                    }
               
                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;

            default: x = "Error";
                    break;
        }
    }
    
    static void printArray(int[] array, int limit) {
        for (int i = 0; i < array.length; ++i) {
            if (i == limit)
                System.out.print("| ");
            System.out.format("%d ", array[i]);
        }
        if (limit == array.length)
            System.out.print("| ");
        System.out.println();
    }

    static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}