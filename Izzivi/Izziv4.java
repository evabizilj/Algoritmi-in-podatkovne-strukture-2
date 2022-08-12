/*

Napišite program, ki tabelo 32 bitnih celih števil uredi glede na število bitov, 
ki so enaki 1 v dvojiški predstavitvi tega števila.
Npr. array 6 5 4 1 3 bi bila sortedArray 4 1 6 5 3, ker 4 = 100, 1 = 1, 6 = 110, 5 = 101 in 3 = 11. 
Števila, ki imajo enako število enic ostanejo v istem vrstnem redu kot pred urejanjem.
Za urejanje uporabite stabilno urejanje s štetjem (counting sort). Na standardnem vhodu 
boste najprej prejeli dolžino tabele n, za tem pa n celih števil.
Na standardni izhod izpisujete potek vpisovanja elementov v končno (urejeno tabelo). 
Za vsak zapis zapišete v eni vrstici par (el, pos), kjer je el število, ki ga zapisujete, 
pos pa indeks v tabeli kamor ta element zapisujete. V zadnji vrstici izpišite 
še urejeno tabelo ločeno s presledki.

*/

import java.util.Scanner;

public class Izziv4 {
        public static void main(String [] args) {
        Scanner sc = new Scanner (System.in);

        // tabela dolžina n
        int length = sc.nextInt();
        int array[] = new int [length];

        // n celih števil
        for (int i = 0; i < array.length; ++i)
            array[i] = sc.nextInt();   

        int sortedArray[] = new int[length]; // c
        int bits[] = new int[32]; // a
        int row[] = new int[32]; // b

        // bitCount prešteje število 1 bitkov

        for (int i = 0; i < array.length; i++) 
            bits[Integer.bitCount(array[i])]++;

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                row[i] = bits[i];
                continue;
            }
            row[i] = bits[i] + row[i - 1]; // prejšnji seštevek preštejemo naslednjemu elementu
        }

        for (int i = length - 1; i > -1; i--) {
            System.out.format("(%d,%d)%n", array[i], row[Integer.bitCount(array[i])] - 1);
            sortedArray[row[Integer.bitCount(array[i])] - 1] = array[i];
            row[Integer.bitCount(array[i])]--;
        }

        for (int i = 0; i < length; ++i)
            System.out.print(sortedArray[i] + " ");
        
        System.out.println();
    }
}

/*

import java.util.Scanner;

public class Izziv4 {

  private static int[] countingSort(int[] a){
	  int[] result = new int[a.length];
	  int[] counts = new int[33];
	  for (int i : a)
	  	counts[Integer.bitCount(i)]++;

	  int cumSum = 0;
	  for (int i=0; i<33 ; i++ ) {
		cumSum += counts[i];
	  	counts[i] = cumSum-1;
	  }

	  for (int i = a.length-1; i>= 0 ; i--) {
		  int pos = Integer.bitCount(a[i]);
		  result[counts[pos]] = a[i];
		  System.out.println("("+a[i]+","+counts[pos]+")");
		  counts[pos]--;
	  }
	  return result;
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
	int[] a = new int[n];
	for (int i = 0; i < n ; i++ )
		a[i] = sc.nextInt();

	a = countingSort(a);

	for (int i : a )
		System.out.print(i+" ");

  }
}

*/