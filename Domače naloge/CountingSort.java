/*

Algoritem izvedite v naslednjih korakih:
    1. štetje elementov (vhodna števila bodo v intervalu od 0 do 255)
    2. izračun kumulative
    3. izpis tabele s kumulativo (v eni vrstici)
    4. generiranje izhodne tabele in izpis indeksov, kamor smo postavii nek element
    5. izpis izhodne tabele (v eni vrstici)

Funkcionalnost count (štetje operacij) pri tem algoritmu ignorirajte. Glej tudi korensko urejanje.

trace cs up 8
5 1 6 1 6 2 0 2
1 3 5 5 5 6 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
4 0 3 7 2 6 1 5
0 1 1 2 2 5 6 6

V zgornjem izpisu sledi so izpisane tri vrstice. Prva vsebuje 256 števil: 1 3 5 5 5 6 8 8 ...; druga vsebuje izpis indeksov, kamor je bil postavljen nek element; in tretja pa je končna urejena array: 0 1 1 2 2 5 6 6.

*/

import java.util.Scanner;

public class CountingSort {
    
    static String operation; 

    static String algorithm;

    static String directionOfSorting;
    
    static int sizeOfArray;

    static int numComparisons = 0; 
    static int numRearranges = 0; 
    static int[] array;
    static int[] output;
    static int[] count; 

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        operation = sc.next();
        algorithm = sc.next();
        directionOfSorting = sc.next();
        sizeOfArray = sc.nextInt();

        // preberemo tabelo
        array = new int [sizeOfArray];
        for (int i = 0; i < sizeOfArray; ++i)
            array[i] = sc.nextInt();
        
        output = new int[sizeOfArray];
        
        // štetje elementov (vhodna števila bodo v intervalu od 0 do 255)
        count = new int[256];
        
        String x = "error";
        switch (operation) {
            case "trace": 

                // izračun kumulative
                for (int i = 0; i < sizeOfArray; ++i)
                    count[array[i]]++;
                for (int i = 1; i < count.length; ++i)
                    count[i] += count[i - 1];

                // izpis tabele s kumulativo (v eni vrstici)
                printArray(count);
        
                // generiranje izhodne tabele in izpis indeksov, kamor smo postavii nek element
                int i = array.length - 1;
                while (i >= 0) {
                    output[count[array[i]] - 1] = array[i]; 
                    --count[array[i]];
                    System.out.printf("%s ", count[array[i]]);
                    --i;
                }

                array = output;
           
                System.out.println();
            
                // izpis izhodne tabele (v eni vrstici)
                printArray(output);
                
                break;

            case "count": 
            // v tem algoritmu ga nimamo 
                    
            default: x = "Error";
                    break;
        }
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; ++i) 
            System.out.printf("%d ", array[i]);
        System.out.println();
    }
}
