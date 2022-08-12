/*

Napišite program, ki na standardnem vhodu najprej prebere dve števili 
n in m (dolžina prve in druge tabele), nato pa v prvo tabelo (recimo ji tabela a) prebere n 
celih števil, v drugo tabelo (tabelo b) pa še preostalih m števil. 
Predpostavite lahko, da sta obe tabeli urejeni naraščajoče (nepadajoče). 
Zlijte ti dve tabeli v urejeno tabelo po postopku, ki smo ga vzeli na vajah.
Na standardni izhod najprej izpišite zaporedje a-jev in b-jev, ki bo 
opisovala iz katere tabele se je v urejeno zaporedje zapisalo število.
Npr. če zlivate zaporedji a=2,3 in b=1,4, potem je zaporedje izpisov enako baab. 
V primeru dveh enakih elementov ima prednost tabela a.
Za tem pa v novi vrstici izpišite še urejeno zaporedje, ločeno s presledki.

*/

import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class Izziv3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aLength = sc.nextInt();
        int[] a = new int[aLength];

        int bLength = sc.nextInt();
        int[] b = new int[bLength];

        for (int i = 0; i < aLength; ++i)
            a[i] = sc.nextInt();
        for (int i = 0; i < bLength; ++i)
            b[i] = sc.nextInt();
        
        int[] finalArray = new int[aLength + bLength];

        int i = 0;
        int j = 0;
        int index = 0;

        while (i != aLength || j != bLength) {
            if (i > aLength - 1) {
                System.out.print("b");
                finalArray[index] = b[j];
                j++;
            } else if (j > bLength - 1) {
                System.out.print("a");
                finalArray[index] = a[i];
                i++;
            } else {
                if (a[i] <= b[j]) {
                    System.out.print("a");
                    finalArray[index] = a[i];
                    i++;
                } else {
                    System.out.print("b");
                    finalArray[index] = b[j];
                    j++;
                }
            }
            index++;
        }
        System.out.println();
        
        for (int k = 0; k < finalArray.length; k++)
            System.out.print(finalArray[k] + " ");
        System.out.println();
    }
}

/*
import java.util.Scanner;

public class Izziv3 {

  private static void merge(int[] a, int[] b){
	  int[] c = new int[a.length+b.length];
	  int posC = 0;
	  int posA = 0;
	  int posB = 0;
	  while(posA < a.length && posB < b.length){
		  if(a[posA]<=b[posB]){
			  c[posC] = a[posA];
			  posA++;
			  System.out.print("a");
		  }else{
			  c[posC] = b[posB];
			  posB++;
			  System.out.print("b");
		  }
		  posC++;
	  }

	  while(posA < a.length){
		  System.out.print("a");
		  c[posC] = a[posA];
		  posC++; posA++;
	  }

	  while(posB < b.length){
		  System.out.print("b");
		  c[posC] = b[posB];
		  posC++; posB++;
	  }
	  System.out.println();
	  for (int i=0; i < c.length ; i++)
	  		System.out.print(c[i]+" ");
	   System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
	int m = sc.nextInt();
	int[] a = new int[n];
	int[] b = new int[m];
	for (int i = 0; i < n ; i++ )
		a[i] = sc.nextInt();

	for (int i = 0; i < m ; i++ )
		b[i] = sc.nextInt();

	merge(a,b);
  }
}

*/