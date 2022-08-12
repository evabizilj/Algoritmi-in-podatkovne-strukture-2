/*

Napiši program, ki zna iz standardnega vhoda prebrati zaporedje števil in poiskati strnjeno podzaporedje z največjo vsoto elementov. Pri reševanju naloge uporabi deli in vladaj algoritem, ki za delitev problema izrablja dejstvo, da se strnjeno zaporedje z največjo vsoto nahaja bodisi v levi polovici zaporedja bodisi v desni polovici zaporedja bodisi vsebuje vsaj en element iz leve in vsaj en element iz desne polovice zaporedja. Koraki, ki jih algoritem izvaja, so naslednji:
vhodno tabelo razdeli na levo in desno polovico,
v obeh delih tabele poišči podzaporedje z največjo vsoto,
poišči največjo vsoto zaporedja, ki vsebuje vsaj en element iz leve in in vsaj en element iz desne polovice tabele,
vrni največjo vsoto izmed treh izračunanih.
Na zaporedju [2, 3, 1, -1, 4, -5, 3] bi algoritem izvedel naslednje korake:
zaporedje bi razdelil na [2, 3, 1, -1] in [4, -5, 3],
v levi polovici bi našel vsoto vsoto 6 (podzaporedje [2, 3, 1]) in na desni 4 (podzaporedje [4]),
vsota podzaporedja, ki se razteza čez obe polovici, bi bila v tem primeru 9 (podzaporedje [2, 3, 1, -1, 4]),
kot rezultat vrne max(6, 4, 9) = 9.
Iskanje vsote strnjenega podzaporedja, ki vsebuje elemente iz leve in desne polovice zaporedja, izvedemo tako, da v kandidatno podzaporedje dodamo zadnji element leve polovice in prvi element desne polovice zaporedja, ki ga nato širimo levo in desno, med širjenjem pa si zapomnimo največjo doseženo vrednost vsote. Za zgornji primer bi algoritem tako izvedel naslednje korake:
začetno vsoto podzaporedja nastavimo na 3 (vsota elementov -1  iz leve polovice in 4 iz desne polovice) in jo obenem uporabimo kot trenutno največjo vsoto,
ko dodamo 1, vsota kandidatnega zaporedja naraste na 4, zato popravimo tudi največjo vsoto na 4,
ko dodamo 3, vsota kandidatnega zaporedja naraste na 7, zato popravimo tudi največjo vsoto na 7,
ko dodamo 2, vsota kandidatnega zaporedja naraste na 9, zato popravimo tudi največjo vsoto na 9,
ker smo prišli do levega roba, vsoto kandidatnega zaporedja nastavimo na trenutno največjo vsoto,
ko dodamo -5, vsota kandidatnega zaporedja pade na 4, zato največje vsote ne popravljamo,
ko dodamo 3, vsota naraste na 7, a ker je še vedno manjša od trenutno največje vsote, le-te ne popravimo.
končni rezultat je torej 9.
Program naj na standardni izhod ob vsaki določitvi največje vsote izpiše zaporedje ter izračunano največjo vsoto podzaporedja.

*/

import java.util.*;
public class Izziv5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
    
        while (sc.hasNextInt()) 
            input.add(sc.nextInt());

        // Converting object method with InputStream as input to array to print
      
        int[] array = input.stream().mapToInt(i->i).toArray();
        
        maxSumSub(array, 0, array.length - 1);

    }

    // "glavna funkcija" z rekurzijo
    static int maxSumSub(int[] array, int left, int right) {
        int tempSum, rightSum, leftSum, middle, tmp; 
        if (left == right) {
            // izpiši
            System.out.print("[" + array[left]);
            for (int i = left + 1; i <= right; ++i)
                System.out.print(", " + array[i]);
            System.out.println("]: " + array[left]);

            return array[left];
        }

        middle = (left + right) / 2;

        // desna stran
        tempSum = array[middle + 1];
        rightSum = tempSum;
        for (int i = middle + 2; i <= right; ++i) {
            tempSum += array[i];
            if (tempSum > rightSum)
                rightSum = tempSum;
        }

        // leva stran
        tempSum = array[middle];
        leftSum = tempSum;
        for (int i = middle - 1; i >= left; --i) {
            tempSum += array[i];
            if (tempSum > leftSum)
                leftSum = tempSum;
        }

        tmp = Math.max(maxSumSub(array, left, middle), maxSumSub(array, middle + 1, right));

        // izpis
        System.out.print("[" + array[left]);
        for (int i = left + 1; i <= right; ++i)
            System.out.print(", " + array[i]);
        System.out.println("]: " + Math.max(tmp, leftSum + rightSum));

        return Math.max(tmp, leftSum + rightSum);
    }
}