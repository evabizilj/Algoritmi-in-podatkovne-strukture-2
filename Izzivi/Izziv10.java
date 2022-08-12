/*
Spuščanje jajc
Napiši program, ki za podana N (število nadstropij, prvi parameter na standardnem vhodu) in K 
(število jajc, drugi parameter na standardnem vhodu) izpiše tabelo, ki v n-ti vrstici in k-tem stolpcu 
vsebuje najmanjše število spuščanj jajc za določitev kritičnega nadstropja, kjer se jajce razbije. 
*/

import java.util.*;

public class Izziv10 {
    static int numEggs;
    static int numFloors;
    static int[][] eggFloor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numFloors = sc.nextInt();
        numEggs = sc.nextInt();
        eggFloor = new int[numEggs + 1][numFloors + 1];
        for (int i = 0; i <= numEggs; ++i) {
            eggFloor[i][1] = 1; 
            eggFloor[i][0] = 0;
        }
        for (int i = 0; i <= numFloors; ++i)
            eggFloor[1][i] = i;
        
        drop();

        for (int i = 0; i < numEggs + 1; ++i) {
            if (i == 0)
                System.out.printf("     ");
            else
                System.out.printf("%3d ", i);
        }
        System.out.println();
    
        for (int i = 0; i < numFloors + 1; ++i) {
            System.out.printf("%4d ", i);
            for (int j = 1; j < numEggs + 1; ++j)
                System.out.printf("%3d ", eggFloor[j][i]);
            System.out.println();
        }
    }
    
    public static void drop () {
        for (int i = 2; i <= numEggs; ++i) {
            for (int j = 2; j <= numFloors; ++j) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= j; ++k) {
                    int tmp = 1 + max(eggFloor[i - 1][k - 1], eggFloor[i][j - k]);
                    if (tmp < eggFloor[i][j])
                        eggFloor[i][j] = tmp;
                }
                eggFloor[i][j] = eggFloor[i][j];
            }
        }
    }

    public static int max(int first, int second) {
        if (first > second)
            return first;
        else
            return second;
    }
}

  