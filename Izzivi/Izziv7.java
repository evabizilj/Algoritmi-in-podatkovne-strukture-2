/*

Primitivni koreni enote
Napišite program, ki iz standardnega vhoda prebere celo število n in nato poišče najmanjše 
praštevilo p, tako da v Zp obstaja vsaj en n-ti primitivni koren. Kot rezultat naj program 
izpiše število p, pripadajoče n-te primitivne korene iz Zp ter Vandermondovo matriko, ki 
pripada najmanjšemu izmed primitivnih korenov.

Postopek za n=3 bi bil sledeč:
    1. Program prebere število 3.
    2. Program predpostavi p=5 in poišče tretje primitivne korene v Z5. Ker jih ni, nadaljuje iskanje.
    3. Program predpostavi p=7 in poišče tretje primitivne korene v Z7. Tu najde dva (2 in 4) in zato zaključi iskanje p-ja.
    4. Program sedaj pripravi Vandermondovo matriko za najmanjši primitivni koren, tem primeru je to 2.

Rezultate naj program izpisuje na standardni izhod. V prvi vrstici izhoda naj izpiše p in n-te primitivne 
korene iz Zp v naraščajočem vrstnem redu, v naslednjih n vrsticah pa vsebino Vandermondove matrike.

*/

import java.util.*;

public class Izziv7 {
    private static Scanner sc = new Scanner(System.in);
    private static int n;
    private static int ovojnica;
    private static int[][] vandermondovaMatrika;
    public static void main(String [] args) {
        n = sc.nextInt();
        
        int pke = pke();
        vandermond(pke);
    }

    /*
     *  od tu naprej se pojavljajo metode za racunanje PKE dane ovojnice
     */

    //metoda poisce in izpise ovojnico ter vse primitivne korene enote v najdeni ovojnici
    //metoda vraca najmanjsi primitivni koren enote najdene ovojnice
    private static int pke () {
        ovojnica = praStevilo(n + 1);
        int pke = naslednjiPKE(0);
        int najmanjsiPKE;
        while (pke == -1) {
            ovojnica = praStevilo(ovojnica + 1);
            pke = naslednjiPKE(0);
        }
        najmanjsiPKE = pke;
        System.out.format("%d: ", ovojnica);

        while (pke != -1) {
            System.out.format("%d ", pke);
            pke = naslednjiPKE(pke + 1);
        }
        System.out.println();

        return najmanjsiPKE;
    }

    //metoda vraca naslednji primitivni koren v dani ovojnici, ki je vecji od argumneta spodnjaMeja
    private static int naslednjiPKE (int spodnjaMeja) {
        int kandidat = -1;
        for (int i = spodnjaMeja; i <= ovojnica; i++)
            for (int j = 1; j <= n; j++) {
                int ostanek = ostanek(i, j);
                if (j == n && ostanek == 1)
                    return i;
                if (ostanek == 1)
                    break;
            }
        return kandidat;
    }


    /*
     *  od tu naprej se pojavljajo metode za racunanje Vandermondove matrike
     */

    //metoda izdela vandermondovo matriko
    private static void buildMatrix (int pke) {
        vandermondovaMatrika = new int [n][n];
        int maksElement = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int notranjiElement = (int) Math.pow(pke, i);
                int element = ostanek(notranjiElement, j);
                if(element > maksElement)
                    maksElement = element;
                vandermondovaMatrika[i][j] =  element;
            }
        }

        izpisMatrike(maksElement+"");
    }

    //metoda izpise vandermondovo matriko
    private static void izpisMatrike(String m) {
        int dolzinaZapisa = m.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /*String element = vandermondovaMatrika[i][j] + "";
                while (element.length() < dolzinaZapisa)
                    element = " " + element;
                System.out.format(" %s ", element);*/
                System.out.format("%2d ", vandermondovaMatrika[i][j]);
            }
            System.out.println();
        }
    }
    

    /*
     *  od tu naprej se pojavljajo pomozne metode
     */

    //metoda vraca rezultat: stevilo^na po modulu ovojnice
    private static int ostanek (int stevilo, int na) {
        int prejsnjiClen = 1;
        for (int i = 0; i < na; i++) {
            if (prejsnjiClen == 0) prejsnjiClen++;
            prejsnjiClen = (prejsnjiClen * stevilo) % ovojnica;
        }
        return prejsnjiClen;
    }

    // metoda vraca prvo prastevilo vecje ali enako kot stevilo
    private static int praStevilo(int stevilo) {
        if (stevilo <= 1)
            return praStevilo(++stevilo);

        for (int i = 2; i < stevilo; i++)
            if (stevilo % i == 0)
                return praStevilo(++stevilo);

        return stevilo;
    }
}