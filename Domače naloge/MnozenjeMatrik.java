import java.util.Scanner; 

public class MnozenjeMatrik {
    static String alghorithm;
    static int [][] matrixA;
    static int [][] matrixB;
    static int [][] matrixA1; 
    static int [][] matrixB1; 
    static int [][] result;
    static int rows;
    static int columns;
    static int sizeOfBlocks; 
    static int stop; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        alghorithm = sc.next();

        switch (alghorithm) {
            // enostavno (osnovno) množenje
            case "os": 

                // gradnja matrike A
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixA = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixA[i][j] = sc.nextInt();
                
                // gradnja matrike B
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixB = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixB[i][j] = sc.nextInt();

                result = multiplication(matrixA, matrixB);

                // izpis rezultata
                System.out.printf("DIMS: %dx%d\n", result.length, result[0].length);
            
                for (int i = 0; i < result.length; ++i) {
                    for (int j = 0; j < result[i].length; ++j)
                        System.out.print(result[i][j] + " ");
                    System.out.println();
                }

                break;

            // bločno množenje matrik
            case "bl":

                // velikost bloka
                sizeOfBlocks = sc.nextInt();

                // gradnja matrike A
                rows = sc.nextInt();
                columns = sc.nextInt();
  
                matrixA = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixA[i][j] = sc.nextInt();
                
                // gradnja kopije A
                matrixA1 = new int[rows][columns];

                for (int i = 0; i < matrixA.length; ++i)
                    for (int j = 0; j < matrixA[i].length; ++j)
                        matrixA1[i][j] = matrixA[i][j];
                  
                // gradnja matrike B
                rows = sc.nextInt();
                columns = sc.nextInt();
  
                matrixB = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixB[i][j] = sc.nextInt();

                // gradnja kopije B
                matrixB1 = new int[rows][columns];

                for (int i = 0; i < matrixB.length; ++i)
                    for (int j = 0; j < matrixB[i].length; ++j)
                        matrixB1[i][j] = matrixB[i][j];
                
                // ugotovitev velikosti razširjene matrike
                int x1 = size(maxElement(matrixA1.length, matrixB1.length), sizeOfBlocks);
                int x2 = size(maxElement(matrixA1[0].length, matrixB1[0].length), sizeOfBlocks);
                int newLength = size(maxElement(x1, x2), sizeOfBlocks);

                matrixA = new int[newLength][newLength];
                matrixB = new int[newLength][newLength];

                // razširjena matrika
                for (int i = 0; i < matrixA1.length; ++i)
                    for (int j = 0; j < matrixA1[i].length; ++j)
                        matrixA[i][j] = matrixA1[i][j];
                
                for (int i = 0; i < matrixB1.length; ++i)
                    for (int j = 0; j < matrixB1[i].length; ++j)
                        matrixB[i][j] = matrixB1[i][j];

                result = blockMultiplication(matrixA, matrixB);

                // odstrani ničle in izpiši rezultat
                int[][] without0 = new int[matrixA1.length][matrixB1[0].length];

                for (int i = 0; i < matrixA1.length; ++i)
                    for (int j = 0; j < matrixB1[0].length; ++j)
                        without0[i][j] = result[i][j];

                System.out.printf("DIMS: %dx%d\n", without0.length, without0[0].length);

                for (int i = 0; i < without0.length; ++i) {
                    for (int j = 0; j < without0[i].length; ++j)
                        System.out.printf("%d ", without0[i][j]);
                    System.out.println();
                }
                break;

            // naivni deli in vladaj
            case "dv":
                stop = sc.nextInt();

                // gradnja matrike A
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixA = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixA[i][j] = sc.nextInt();
                
                // gradnja kopije A
                matrixA1 = new int[rows][columns];

                for (int i = 0; i < matrixA.length; ++i)
                    for (int j = 0; j < matrixA[i].length; ++j)
                        matrixA1[i][j] = matrixA[i][j];
                
                // gradnja matrike B
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixB = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixB[i][j] = sc.nextInt();
                
                // gradnja kopije B
                matrixB1 = new int[rows][columns];

                for (int i = 0; i < matrixB.length; ++i)
                    for (int j = 0; j < matrixB[i].length; ++j)
                        matrixB1[i][j] = matrixB[i][j];

                // ugotovitev velikosti razširjene matrike
                int x = size2(maxElement(matrixA1.length, matrixB1.length));
                int y = size2(maxElement(matrixA1[0].length, matrixB1[0].length));

                matrixA = new int[x][y];
                matrixB = new int[x][y];

                // razširanje matrik
                for (int i = 0; i < matrixA1.length; ++i)
                    for (int j = 0; j < matrixA1[i].length; ++j)
                        matrixA[i][j] = matrixA1[i][j];
                
                for (int i = 0; i < matrixB1.length; ++i)
                    for (int j = 0; j < matrixB1[i].length; ++j)
                        matrixB[i][j] = matrixB1[i][j];
                        
                result = multiplicationDC(matrixA, matrixB);

                System.out.printf("DIMS: %dx%d\n", result.length, result[0].length);

                for (int i = 0; i < result.length; ++i) {
                    for (int j = 0; j < result[i].length; ++j)
                        System.out.printf("%d ", result[i][j]);
                    System.out.println();
                }
            
                break;
            
            // Strassenov algoritem
            case "st":
                stop = sc.nextInt();

                // gradnja matrike A
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixA = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixA[i][j] = sc.nextInt();
                
                // gradnja kopije A
                matrixA1 = new int[rows][columns];

                for (int i = 0; i < matrixA.length; ++i)
                    for (int j = 0; j < matrixA[i].length; ++j)
                        matrixA1[i][j] = matrixA[i][j];
                
                // gradnja matrike B
                rows = sc.nextInt();
                columns = sc.nextInt();

                matrixB = new int[rows][columns];
                for (int i = 0; i < rows; ++i)
                    for (int j = 0; j < columns; ++j)
                        matrixB[i][j] = sc.nextInt();
                
                // gradnja kopije B
                matrixB1 = new int[rows][columns];

                for (int i = 0; i < matrixB.length; ++i)
                    for (int j = 0; j < matrixB[i].length; ++j)
                        matrixB1[i][j] = matrixB[i][j];

                // ugotovitev velikosti razširjene matrike
                x = size2(maxElement(matrixA1.length, matrixB1.length));
                y = size2(maxElement(matrixA1[0].length, matrixB1[0].length));

                matrixA = new int[x][y];
                matrixB = new int[x][y];

                // razširanje matrik
                for (int i = 0; i < matrixA1.length; ++i)
                    for (int j = 0; j < matrixA1[i].length; ++j)
                        matrixA[i][j] = matrixA1[i][j];
                
                for (int i = 0; i < matrixB1.length; ++i)
                    for (int j = 0; j < matrixB1[i].length; ++j)
                        matrixB[i][j] = matrixB1[i][j];
                    
                result = printStrassen(matrixA, matrixB);
                result = strassen(matrixA, matrixB);

                System.out.printf("DIMS: %dx%d\n", result.length, result[0].length);

                for (int i = 0; i < result.length; ++i) {
                    for (int j = 0; j < result[i].length; ++j)
                        System.out.printf("%d ", result[i][j]);
                    System.out.println();
                }

                break;
                        
            default:
                System.out.println("Error!");
        }
    }
    
    static int[][] multiplication(int[][] matrixA, int[][] matrixB) {
        int[][] result = new int[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; ++i)
            for (int j = 0; j < matrixB[0].length; ++j)
                for (int k = 0; k < matrixA[i].length; ++k)
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
        return result;
    }
    
    static int[][] blockMultiplication (int[][] matrixA, int[][] matrixB) {
        int[][] result = new int [matrixA.length][matrixB[0].length];
        int[][] matrix;
        int[][] A;
        int[][] B;
        int[][] AxB;
        int[][] sumAB; 

        for (int i = 0; i < result.length; i += sizeOfBlocks) {
            for (int j = 0; j < result.length; j += sizeOfBlocks) {
                
                matrix = new int[sizeOfBlocks][sizeOfBlocks];
               
                for (int k = 0; k < result.length; k += sizeOfBlocks) {
                    
                    // podmatrika A
                    A = new int[sizeOfBlocks][sizeOfBlocks];
                    for (int ii = 0; ii < sizeOfBlocks; ++ii)
                        for (int jj = 0; jj < sizeOfBlocks; ++jj)
                            A[ii][jj] = matrixA[i + ii][k + jj];
                    
                    // podmatrika B
                    B = new int[sizeOfBlocks][sizeOfBlocks];
                    for (int ii = 0; ii < sizeOfBlocks; ++ii)
                        for (int jj = 0; jj < sizeOfBlocks; ++jj)
                            B[ii][jj] = matrixB[k + ii][j + jj];

                    AxB = multiplication(A, B);

                    // skalarno seštejemo matriki A in B
                    int sum = 0;
                    for (int ii = 0; ii < AxB.length; ++ii)
                        for (int jj = 0; jj < AxB.length; ++jj)
                            sum += AxB[ii][jj];
                    if (sum > 0)
                        System.out.printf("%d\n", sum);
                    
                    // seštejemo matriki
                    sumAB = new int[matrix.length][matrix.length];
                    for (int ii = 0; ii < matrix.length; ++ii)
                        for (int jj = 0; jj < matrix[0].length; ++jj)
                            sumAB[ii][jj] = matrix[ii][jj] + AxB[ii][jj];
                    matrix = sumAB;
                }

                for (int ii = 0; ii < sizeOfBlocks; ++ii)
                    for (int jj = 0; jj < sizeOfBlocks; ++jj)
                        result[ii + i][jj + j] = matrix[ii][jj];
            }
        }
        return result;
    }

    static int[][] multiplicationDC(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A.length];
        int[][] a11 = new int[A.length / 2][A.length / 2];
        int[][] a12 = new int[A.length / 2][A.length / 2];
        int[][] a21 = new int[A.length / 2][A.length / 2];
        int[][] a22 = new int[A.length / 2][A.length / 2];
        int[][] b11 = new int[A.length / 2][A.length / 2];
        int[][] b12 = new int[A.length / 2][A.length / 2];
        int[][] b21 = new int[A.length / 2][A.length / 2];
        int[][] b22 = new int[A.length / 2][A.length / 2];
        int[][] matrix1;
        int[][] matrix2;
        int[][] matrix3;
        int[][] matrix4;
        int[][] matrix5;
        int[][] matrix6;
        int[][] matrix7;    
        int[][] matrix8;

        // a11
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                a11[i][j] = A[i][j];

        // a12 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
            a12[i][j] = A[i][A.length / 2 + j];

        // a21 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                a21[i][j] = A[A.length / 2 + i][j];
        
        // a22 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                a22[i][j] = A[A.length / 2 + i][A.length / 2 + j];

        // b11 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                b11[i][j] = B[i][j];

        // b12 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                b12[i][j] = B[i][A.length / 2 + j];

        // b21 
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                b21[i][j] = B[A.length / 2 + i][j];

        // b22
        for (int i = 0; i < A.length / 2; ++i)
            for (int j = 0; j < A.length / 2; ++j)
                b22[i][j] = B[A.length / 2 + i][A.length / 2 + j];

        if (A.length / 2 != stop) {
            matrix1 = multiplicationDC(a11, b11);
                System.out.printf("%d\n", sumScalar(matrix1));
            matrix2 = multiplicationDC(a12, b21);
                System.out.printf("%d\n", sumScalar(matrix2));
            matrix3 = multiplicationDC(a11, b12);
                System.out.printf("%d\n", sumScalar(matrix3));
            matrix4 = multiplicationDC(a12, b22);
                System.out.printf("%d\n", sumScalar(matrix4));
            matrix5 = multiplicationDC(a21, b11);
                System.out.printf("%d\n", sumScalar(matrix5));
            matrix6 = multiplicationDC(a22, b21);
                System.out.printf("%d\n", sumScalar(matrix6));
            matrix7 = multiplicationDC(a21, b12);
                System.out.printf("%d\n", sumScalar(matrix7));
            matrix8 = multiplicationDC(a22, b22);
                System.out.printf("%d\n", sumScalar(matrix8));
        } else {
            matrix1 = multiplication(a11, b11);
                System.out.printf("%d\n", sumScalar(matrix1));
            matrix2 = multiplication(a12, b21);
                System.out.printf("%d\n", sumScalar(matrix2));
            matrix3 = multiplication(a11, b12);
                System.out.printf("%d\n", sumScalar(matrix3));
            matrix4 = multiplication(a12, b22);
                System.out.printf("%d\n", sumScalar(matrix4));
            matrix5 = multiplication(a21, b11);
                System.out.printf("%d\n", sumScalar(matrix5));
            matrix6 = multiplication(a22, b21);
                System.out.printf("%d\n", sumScalar(matrix6));
            matrix7 = multiplication(a21, b12);
                System.out.printf("%d\n", sumScalar(matrix7));
            matrix8 = multiplication(a22, b22);
                System.out.printf("%d\n", sumScalar(matrix8));
        }

        result = mergeMatrix(sumMatrix(matrix1, matrix2), result, 0, 0);
        result = mergeMatrix(sumMatrix(matrix3, matrix4), result, 0, A.length / 2);
        result = mergeMatrix(sumMatrix(matrix5, matrix6), result, A.length / 2, 0);
        result = mergeMatrix(sumMatrix(matrix7, matrix8), result, A.length / 2, A.length / 2);

        return result;
    }

    static int[][] printStrassen(int[][] A, int[][] B) {
        int[][] result = new int[2][2];
        int[][] a11;
        int[][] a12;
        int[][] a21;
        int[][] a22;
        int[][] b11;
        int[][] b12;
        int[][] b21;
        int[][] b22;
        int part1; 
        int part2;
        int part3;
        int part4;
        int part5;
        int part6;
        int part7;

        if (A.length != stop) {
            a11 = divideMatrix(A, 0, 0);
            a12 = divideMatrix(A, 0, A.length / 2);
            a21 = divideMatrix(A, A.length / 2, 0);
            a22 = divideMatrix(A, A.length / 2, A.length / 2);
            b11 = divideMatrix(B, 0, 0);
            b12 = divideMatrix(B, 0, A.length / 2);
            b21 = divideMatrix(B, A.length / 2, 0);
            b22 = divideMatrix(B, A.length / 2, A.length / 2);

            
            part1 = sumScalar(printStrassen(a11, substractionMatrix(b12, b22)));
            System.out.printf("%d\n", part1);

            part2 = sumScalar(printStrassen(sumMatrix(a11, a12), b22));
            System.out.printf("%d\n", part2);

            result[0][1] = part1 + part2;

            part3 = sumScalar(printStrassen(sumMatrix(a21, a22), b11));
            System.out.printf("%d\n", part3);

            part4 = sumScalar(printStrassen(a22, substractionMatrix(b21, b11)));
            System.out.printf("%d\n", part4);

            result[1][0] = part3 + part4;

            part5 = sumScalar(printStrassen(sumMatrix(a11, a22), sumMatrix(b11, b22)));
            System.out.printf("%d\n", part5);

            part6 = sumScalar(printStrassen(substractionMatrix(a12, a22), sumMatrix(b21, b22)));
            System.out.printf("%d\n", part6);

            part7 = sumScalar(printStrassen(substractionMatrix(a11, a21), sumMatrix(b11, b12)));
            System.out.printf("%d\n", part7);
        

            result[0][0] = part5 + part4 - part2 + part6;
            result[1][1] = part1 + part5 - part3 - part7;

            return result;
        }
        if (A.length == stop)
            return multiplication(A, B);
        return result;
    }

    static int[][] strassen(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A.length];
        int[][] matrix1; 
        int[][] matrix2;
        int[][] matrix3;
        int[][] matrix4;
        int[][] matrix5;
        int[][] matrix6;
        int[][] matrix7;
        int[][] part1;
        int[][] part2;
        int[][] part3;
        int[][] part4;

        if (A.length != stop) {
            matrix1 = strassen(sumMatrix(divideMatrix(A, 0, 0), divideMatrix(A, A.length / 2, A.length / 2)), sumMatrix(divideMatrix(B, 0, 0), divideMatrix(B, A.length / 2, A.length / 2)));
            matrix2 = strassen(sumMatrix(divideMatrix(A, A.length / 2, 0), divideMatrix(A, A.length / 2, A.length / 2)), divideMatrix(B, 0, 0));
            matrix3 = strassen(divideMatrix(A, 0, 0), substractionMatrix(divideMatrix(B, 0, A.length / 2), divideMatrix(B, A.length / 2, A.length / 2)));
            matrix4 = strassen(divideMatrix(A, A.length / 2, A.length / 2), substractionMatrix(divideMatrix(B, A.length / 2, 0), divideMatrix(B, 0, 0)));
            matrix5 = strassen(sumMatrix(divideMatrix(A, 0, 0), divideMatrix(A, 0, A.length / 2)), divideMatrix(B, A.length / 2, A.length / 2));
            matrix6 = strassen(substractionMatrix(divideMatrix(A, A.length / 2, 0), divideMatrix(A, 0, 0)), sumMatrix(divideMatrix(B, 0, 0), divideMatrix(B, 0, A.length / 2)));
            matrix7 = strassen(substractionMatrix(divideMatrix(A, 0, A.length / 2), divideMatrix(A, A.length / 2, A.length / 2)), sumMatrix(divideMatrix(B, A.length / 2, 0), divideMatrix(B, A.length / 2, A.length / 2)));

            part1 = sumMatrix(substractionMatrix(sumMatrix(matrix1, matrix4), matrix5), matrix7);
            part2 = sumMatrix(matrix3, matrix5);
            part3 = sumMatrix(matrix2, matrix4);
            part4 = sumMatrix(substractionMatrix(sumMatrix(matrix1, matrix3), matrix2), matrix6);

            result = mergeMatrix(part1, result, 0, 0);
            result = mergeMatrix(part2, result, 0, A.length / 2);
            result = mergeMatrix(part3, result, A.length / 2, 0);
            result = mergeMatrix(part4, result, A.length / 2, A.length / 2);

            return result;
        }

        if (A.length == stop)
            return multiplication(A, B);

        return result;
    }

    static int size(int x, int y) {
        int power = 1;
        while (power < x) 
            power *= y;
        return power;
    }

    static int size2(int x) {
        int power = 1;
        while(true) {
            power *= 2;
            if (power > x)
                return power;
        }
    }

        // razširi matrike -- potrebna ista velikost
    static int maxElement(int x, int y) {
        int max = 0;
        if (x >= y)
             max = x;
        else
            max = y;
        return max;
    }

    static int[][] sumMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] sum = new int[matrix1.length][matrix1.length];

        for (int i = 0; i < matrix1.length; ++i)
            for (int j = 0; j < matrix1[0].length; ++j)
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
        return sum;
    }

    static int[][] divideMatrix(int[][] matrix, int i, int j) {
        int[][] result = new int[matrix.length / 2][matrix.length / 2];
        for (int row = 0; row < result.length; ++row) {
            int jx = j;
            for (int col = 0; col < result[0].length; ++col) {
                result[row][col] = matrix[i][jx];
                jx++;
            }
            i++;
        }
        return result;
    }

    static int[][] mergeMatrix(int[][] part, int[][] result, int row, int col) {
        for (int i = 0; i < part.length; ++i) {
            int jx = col;
            for (int j = 0; j < part[0].length; ++j) {
                result[row][jx] = part[i][j];
                jx++;
            }
            row++;
        }

        return result;
    }

    static int[][] substractionMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix1.length];

        for (int i = 0; i < matrix1.length; ++i)
            for (int j = 0; j < matrix1[0].length; ++j)
                result[i][j] = matrix1[i][j] - matrix2[i][j];
        return result;
    }

    static int sumScalar(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix.length; ++j)
                sum += matrix[i][j];
        return sum;
    }

    static int[][] subMatrix(int[][] matrix, int row, int col, int x, int y) {
        int[][] result = new int[x][y];
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                result[i][j] = matrix[row + i][col + j];
        return result;
    }

}
