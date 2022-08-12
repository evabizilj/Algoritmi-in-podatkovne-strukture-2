/*

Korensko urejanje - od najmanj pomembnega bajta do najbolj pomembnega. 
Kot stabilen podalgoritem uporabite urejanje s štetjem. Pri tem predlagamo, da slednjega 
implementirate tako, da prejme tudi parameter, ki pove glede na kateri bajt elementa se izvede urejanje - 
s tem bo izvedba korenskega urejanja skorajda trivialna.

Predpostavite lahko, da bodo vhodna števila pozitivna. Vseeno pa lahko razmislite, 
kako bi v celoti podprli urejanje glede na Javanski tip int (negativna števila).

Kot sled se uporabi kar sled iz urejanja s štetjem, ki ga večkrat večkrat ponovite (4x).

trace rs up 8
256 123456 100 65536 300 42 7 16777216
3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 5 5 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
2 3 4 5 1 7 6 0

256 65536 16777216 7 42 300 123456 100
5 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
4 7 6 3 2 1 0 5

65536 16777216 7 42 100 256 300 123456
6 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
7 5 4 3 2 1 0 6

16777216 7 42 100 256 300 65536 123456
7 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
6 5 4 3 2 1 0 7
   
7 42 100 256 300 65536 123456 16777216
*/

import java.util.Scanner;

public class RadixSort {

    static String operation; 

    static String algorithm;

    static String directionOfSorting;
    
    static int sizeOfArray;

    static int numComparisons = 0; 
    static int numRearranges = 0; 
    static int[] array;
    static int[] output;
    static int[] count;

    private static final int BYTE_MASK = 0xff;
     // “& 0xff” masks the variable so it leaves only the value in the last 8 bits
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

        String x = "error";
        switch (operation) {
                case "trace": 

                    // 32 bitno število, radix glede na bajt
                    for (int i = 0; i <= 3; ++i) {
                        count = new int[256];
                        output = new int[array.length];
                        array = radix(count, output, array, i * 8);
                    }

                    break;

                case "count": 
             
                default: x = "Error";
                    break;
        }
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; ++i) 
            System.out.printf("%d ", array[i]);
        System.out.println();
    }

    static int[] radix(int count[], int output[], int[] array, int offset) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            index = (array[i] >>> offset) & BYTE_MASK;
            count[index]++;
        }

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        printArray(count);

        // zgradi output array
        for (int i = array.length - 1; i >= 0; i--) {
            index = (array[i] >>> offset) & BYTE_MASK;
            output[--count[index]] = array[i];
            System.out.printf("%d ", count[index]);
        }

        System.out.println();

        printArray(output);

        return output;
    }
}
