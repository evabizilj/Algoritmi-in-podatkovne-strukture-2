/*

Deljenje tabele na dva dela:

Na vsaki stopnji tabelo in-place mergeSortte (brez ustvarjanja novih tabel) na dve polovici, pri čemer je leva polovica enaka oz. kvečjemu za ena večja od desne.
Razdeljevanje tabel izvedite do konca, t.j. dokler ne dobite tabel velikosti ena.
Po vsaki delitvi izpišite sled, t.j. tabelo in mejo "|".
Sestavljanje oz. zlivanje dveh tabel v novo tabelo.

Rezultat zlivanja je newArray array, t.j. zlivanja ni potrebno implementirati in-place.
Najmanjši element z začetka obeh tabel zaporedoma prepisujete v novo tabelo, v primeru enakosti ima prednost prva (leva) array.
Po vsakem zlivanju dveh tabel izpišite sled, t.j. vsebino novo nastale tabele.

Animacija algoritma:
http://www.cse.iitk.ac.in/users/dsrkg/cs210/applets/sortingII/mergeSort/mergeSort.html

trace ms up 8
8 5 6 1 7 2 0 9
8 5 6 1 | 7 2 0 9 
8 5 | 6 1 
8 | 5 
5 8 
6 | 1 
1 6 
1 5 6 8 
7 2 | 0 9 
7 | 2 
2 7 
0 | 9 
0 9 
0 2 7 9 
0 1 2 5 6 7 8 9

count ms up 8
8 5 6 1 7 2 0 9
17 32
12 32
12 32

trace ms down 8
8 5 6 1 7 2 0 9
8 5 6 1 | 7 2 0 9
8 5 | 6 1
8 | 5
8 5
6 | 1
6 1
8 6 5 1
7 2 | 0 9
7 | 2
7 2
0 | 9
9 0
9 7 2 0
9 8 7 6 5 2 1 0

count ms up 8
8 5 6 1 7 2 0 9
17 32
12 32
12 32

*/


import java.util.Scanner;

public class MergeSort {
    
    static String operation; 

    static String algorithm;

    static String directionOfSorting;
    
    static int sizeOfArray;

    static int numComparisons = 0;
    static int numRearranges = 0; 
    static int[] array;
    static int[] newArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        operation = sc.next();
        algorithm = sc.next();
        directionOfSorting = sc.next();
        sizeOfArray = sc.nextInt();

        // preberemo tabelo
        array  = new int [sizeOfArray];
        for (int i = 0; i < sizeOfArray; ++i)
            array[i] = sc.nextInt();
        
        newArray = new int[sizeOfArray];
        
        String x = "error";
        switch (operation) {
            case "trace": 
                    // uredi
                    mergeSort(array, newArray, 0, array.length - 1);
                    break;

            case "count": 
                    // uredimo zaporedje
                    mergeSort(array, newArray, 0, array.length - 1);
                    count();
                    
                    // že urejeno zaporedje
                    mergeSort(array, newArray, 0, array.length - 1);
                    count();

                    // zamenjava smeri
                    if (directionOfSorting.equals("up"))
                        directionOfSorting = "down";
                    else   
                        directionOfSorting = "up";
                    mergeSort(array, newArray, 0, array.length - 1);
                    count();
                   
            default: x = "Error";
                    break;
        }
    }

    static void count() {
        System.out.printf("%d %d\n", numComparisons, numRearranges);
        numRearranges = 0;
        numComparisons = 0;
    }

    static void mergeSort(int[] array, int [] newArray, int left, int right) {
        int middle = (left + right) / 2;
        
        if (right > left) {
        
            if (operation.equals("trace")) {
                for (int i = left; i <= right; i++) {
                    System.out.printf("%d ", array[i]);
                    if (i == middle) {
                        System.out.printf("|");
                        System.out.printf(" ");
                    }
                }
                System.out.println();
            }

            mergeSort(array, newArray, left, middle);
            mergeSort(array, newArray, middle + 1, right);

            if (directionOfSorting.equals("up"))
                mergeUp(array, newArray, left, right);
            else if (directionOfSorting.equals("down"))
                mergeDown(array, newArray, left, right);
        }

        if (left == right)
            numRearranges++;
    }

    static void mergeUp(int[] a1, int[] newArray, int left, int right) {
        int middle = (left + right) / 2;
        int leftIndex = left;
        int rightIndex = middle + 1;
        int i = left;
    
        for (i = left; leftIndex <= middle && rightIndex <= right; i++) {
            if (a1[leftIndex] <= a1[rightIndex])
                newArray[i] = a1[leftIndex++];
            else 
                newArray[i] = a1[rightIndex++];
            numComparisons++;
            numRearranges++;
        }

        if (leftIndex <= middle || rightIndex <= right) {
            if (leftIndex <= middle) {
                for (int j = leftIndex; j <= middle; j++) {
                    newArray[i++] = a1[leftIndex++];
                    numRearranges++;
                }
            } else if (rightIndex <= right) {
                for (int j = rightIndex; j <= right; j++) {
                    newArray[i++] = a1[rightIndex++];
                    numRearranges++;
                }
            }
        }

        right = i - 1;
        if (operation.equals("trace")) {
            for (i = left; i <= right; i++)
                System.out.format("%d ", newArray[i]);
            System.out.println();
        }

        for (int j = left; j < i; j++)
            a1[j] = newArray[j];
    }


    static void mergeDown(int[] a1, int[] newArray, int left, int right) {
        int middle = (left + right) / 2;
        int leftIndex = left;
        int rightIndex = middle + 1;
        int i = left;


        for (i = left; leftIndex <= middle && rightIndex <= right; i++) {
            if (a1[leftIndex] >= a1[rightIndex])
                newArray[i] = a1[leftIndex++];
            else 
                newArray[i] = array[rightIndex++];
            numComparisons++;
            numRearranges++;
        }

        if (leftIndex <= middle) {
            for (int j = leftIndex; j <= middle; j++) {
                newArray[i++] = a1[leftIndex++];
                numRearranges++;
            }
        }
        if (rightIndex <= right) {
            for (int j = rightIndex; j <= right; j++) {
                newArray[i++] = a1[rightIndex++];
                numRearranges++;
            }
        }

        right = i - 1;
        if (operation.equals("trace")) {
            for (i = left; i <= right; i++)
                System.out.format("%d ", newArray[i]);
            System.out.println();
        }

        for (int j = left; j < i; j++)
            a1[j] = newArray[j];
    }
}

