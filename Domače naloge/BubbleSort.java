/*

bs - urejanje z mehurčki (angl. bubble sort);

Na začetku je urejeni del prazen. Tekom algoritma urejeni del narašča od leve proti desni. 
Notranja zanka od desne proti levi menja dva zaporedna elementa, če sta med seboj neurejena. 
Nato se izpiše sled. Sled naj se izpiše tudi na začetku algoritma (glej primer).

trace bs up 6
8 5 6 1 7 2
| 8 5 6 1 7 2
1 | 8 5 6 2 7 
1 2 | 8 5 6 7 
1 2 5 | 8 6 7 
1 2 5 6 | 8 7 
1 2 5 6 7 | 8

count bs up 6
8 5 6 1 7 2
15 30
15 0
15 45

*/

import java.util.Scanner;

public class BubbleSort {
    
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
    
                    for (int i = 0; i < sizeOfArray - 1; i++) {
                        printArray(array, i);
                       for (int j = sizeOfArray - 1; j > i; --j) {
                            if (directionOfSorting.equals("down")) {
                                if (array[j] > array[j - 1]) 
                                    swap(array, j, j - 1);
                            }
                            else {
                                if (array[j] < array[j - 1]) 
                                    swap(array, j, j - 1);
                            }
                        }
                    }
                    printArray(array, sizeOfArray - 1);

                    break;

            case "count": 
                    
                    // uredimo podano zaporedje
                    for (int i = 0; i < sizeOfArray - 1; i++) {
                        for (int j = sizeOfArray - 1; j > i; --j) {
                            numComparisons++;
                            if (directionOfSorting.equals("down")) {
                                if (array[j] > array[j - 1]) {
                                    swap(array, j, j - 1);
                                    numRearranges += 3;
                                }
                            }
                            else {
                                if (array[j] < array[j - 1]) {
                                    swap(array, j, j - 1);
                                    numRearranges += 3;
                                }
                            }
                        }
                    }

                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;

                    // (že urejenega) še enkrat uredimo
                    for (int i = 0; i < sizeOfArray - 1; i++) {
                        for (int j = sizeOfArray - 1; j > i; --j) {
                            numComparisons++;
                            if (directionOfSorting.equals("down")) {
                                if (array[j] > array[j - 1]) {
                                    swap(array, j, j - 1);
                                    numRearranges += 3;
                                }
                            }
                            else {
                                if (array[j] < array[j - 1]) {
                                    swap(array, j, j - 1);
                                    numRearranges += 3;
                                }
                            }
                        }
                    }

                    System.out.printf("%d %d\n", numComparisons, numRearranges);
                    numComparisons = 0;
                    numRearranges = 0;


                   // uredimo v obratni smeri
                    for (int i = 0; i < sizeOfArray - 1; i++) {
                        for (int j = sizeOfArray - 1; j > i; --j) {
                        numComparisons++;
                        if (directionOfSorting.equals("down")) {
                            if (array[j] < array[j - 1]) {
                                swap(array, j, j - 1);
                                numRearranges += 3;
                            }
                        }
                            else {
                                if (array[j] > array[j - 1]) {
                                    swap(array, j, j - 1);
                                    numRearranges += 3;
                                }
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
    
    static void printArray (int[] array, int limit) {
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
