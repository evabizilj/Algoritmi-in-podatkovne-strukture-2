/*

Eksperimentalno ovrednotenje zahtevnosti

Program v programskem jeziku Java za empirično primerjavo dveh 
algoritmov za iskanje elementa v urejeni tabeli:
    - navadnega zaporednega iskanja
    - dvojiškega iskanja

Oba algoritma poženemo večkrat za različne velikosti tabele. 
Pri tem izmerimo in izračunamo povprečni čas iskanja. Izpišemo čase 
za oba algoritma.
*/

public class Izziv1 {
    public static void main(String[] args) {

        System.out.printf("|-----------|-----------|-----------|\n");
        System.out.printf("|     n     |  linearno |  dvojisko |\n");
        System.out.printf("|-----------|-----------|-----------|\n");
        
        for (int i = 1000; i <= 100000; i += 1000)
            System.out.printf("|%10d |%10d |%10d |%n", i, timeLinear(i), timeBinary(i));
        System.out.printf("|-----------|-----------|-----------|\n");
    }

    /* 
        a) Generiranje testnih primerov
            Za podani n vrne (urejeno) tabelo celih števil z vrednostmi od 1 do n.
    */

    static int[] generateTable(int n) {
        int[] array = new int[n];
        for (int i = 1; i < array.length + 1; ++i)
            array[i - 1] = i;
        return array;
    }

    /*
        b) Implementacija obeh algoritmov iskanja elementa
            Oba algoritma za iskanje elementa.
                #a - tabela elementov
                #v - iskana vrednost
                #l - leva meja v tabeli
                #r - desna meja v tabeli
            vrnemo mesto (indeks), kjer se element v nahaja)
    */

    static int findLinear(int[] a, int v) {
        for (int i = 0; i < a.length; ++i)
            if (a[i] == v)
                return i;
        return -1;
    }

    static int findBinary(int a[], int l, int r, int v) { 
        // dokler vse ne pregledamo (iz leve in desne)
        while (r >= l) {
            int middle = l + (r - l) / 2; 
  
        // če je iskani element kar sredinski
        if (a[middle] == v) 
            return middle; 
  
        // če je element manjši kot sredinski, potem bo iskani element na levi strani tabele
        else if (a[middle] > v) 
            return findBinary(a, l, middle - 1, v); 

        // drugače je element lahko le na desni strani
        else
            return findBinary(a, middle + 1, r, v); 
        }
        
        // element ni prisoten v tabeli
        return -1; 
    }

    /*
        c) Izvedba enega testa za tabelo dolžine n
            Metodi, ki izmerita povprečni čas iskanja v tabeli dolžine n.
            Vsaka naredi naslednje: 
                - ustvari tabelo dolžine n z metodo, ki smo jo implementirali predhodno
                - začne meriti čas
                - 1000-krat ponovi: ustvari naključno število med 1 in n, poišče število v tabeli
                - ustavimo merjenje časa
                - izračuna povprečje
    */

    static long timeLinear(int n) {
        int[] array = generateTable(n);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; ++i) {
            int random = (int) (Math.random() * n) + 1;
            findLinear(array, random);
        }
        return (System.nanoTime() - startTime)/1000;
    }

    static long timeBinary(int n) {
        int[] array = generateTable(n);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; ++i) {
            int random = (int) (Math.random() * n) + 1;
            findBinary(array, 0, n - 1, random);
        }
        return (System.nanoTime() - startTime)/1000;
    }
}