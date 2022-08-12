/*

Na začetku je urejeni del prazen. Tekom algoritma urejeni del narašča od leve proti desni. 
Notranja zanka v neurejenem delu tabele poišče najmanjši element in ga zamenja s prvim elementom 
neurejenega dela tabele. Nato se izpiše sled. Sled naj se izpiše tudi na začetku algoritma.

Primer izvajanja:

trace ss up 6
8 5 6 1 7 2
| 8 5 6 1 7 2
1 | 5 6 8 7 2 
1 2 | 6 8 7 5 
1 2 5 | 8 7 6 
1 2 5 6 | 7 8 
1 2 5 6 7 | 8

count ss up 6
8 5 6 1 7 2
15 15
15 15
15 15

*/

import java.util.Scanner;

public class SelectionSort {
    
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
    
                    // urejanje
                    int min = 0;
                    int max = 0;
                    int idx = 0;

                    for (int i = 0; i < sizeOfArray; ++i) {
                        printArray(array, i);
                        
                        // minimum
                        if (directionOfSorting.equals("up")) { 
                            min = array[i];
                            idx = i;
                            for (int j = i + 1; j < sizeOfArray; ++j) {
                                if (array[j] < min) {
                                    min = array[j];
                                    idx = j;
                                }
                            }
                            // maksimum
                        } else { 
                            max = array[i];
                            idx = i;
                            for (int j = i + 1; j < sizeOfArray; ++j) {
                                if (array[j] > max) {
                                    max = array[j];
                                    idx = j;
                                }
                            }
                        }
                        swap(array, i, idx);
                    }

                    break;

            case "count": 
                    
                    // uredimo podano zaporedje
                    for (int i = 0; i < sizeOfArray; ++i) {
                        if (directionOfSorting.equals("up")) { 
                            for (int j = i + 1; j < sizeOfArray; ++j) 
                                numComparisons++;
                        }else { 
                                for (int j = i + 1; j < sizeOfArray; ++j) 
                                    numComparisons++;
                        }
 
                        numRearranges += 3;
                    }
                    numRearranges -= 3;
                    
                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;

                    // (že urejenega) še enkrat uredimo
    
                    for (int i = 0; i < sizeOfArray; ++i) {
                        if (directionOfSorting.equals("up")) { 
                            for (int j = i + 1; j < sizeOfArray; ++j) 
                                numComparisons++;
                        }else { 
                                for (int j = i + 1; j < sizeOfArray; ++j) 
                                    numComparisons++;
                        }
 
                        numRearranges += 3;
                    }
                    numRearranges -= 3;
                    
                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;


                   // uredimo v obratni smeri
                    for (int i = 0; i < sizeOfArray; ++i) {
                        if (directionOfSorting.equals("up")) { 
                            for (int j = i + 1; j < sizeOfArray; ++j) 
                                numComparisons++;
                        }else { 
                            for (int j = i + 1; j < sizeOfArray; ++j) 
                                numComparisons++;
                        }

                        numRearranges += 3;
                    }
                    numRearranges -= 3;
                
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