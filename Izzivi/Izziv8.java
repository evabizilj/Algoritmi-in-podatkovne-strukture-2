/*

Napiši program, ki na vhod dobi dva polinoma (podana kot vektorja), ter ju zmnoži s pomočjo rekurzivne hitre 
Fourierjeve transformacije.

Vhodni podatki:
Prvi argument programa je dolžina polinomov, oz. število koeficientov v podanih polinomih (oba polinoma bosta iste stopnje).
Koeficiente obeh polinomov nato preberete s standarnega vhoda. Vsi koeficienti so realna števila (Java tip double).
Zaradi končne natančnosti tipa double, lahko dobite resulte, ki odstopajo od resultov, ki so podani v primerih.

Sled
Najprej izpišite sled izvajanja FFT na prvem polinomu.
Nato na drugem polinomu.
Nazadnje pa še sled izvajanja inverznega FFT.
V zadnji vrstici izpišite še končni result, tj. vektor dobljen iz prejšnjega koraka pomnožen z 1/n.
Sled izvajanja enega FFT
Kot sled izvajanja izpišite dobljeni vektor v vsakem klicu FFT.
Če dobimo na vhod vektor (polinom):
 2 -3 -5 6  
potem je sled izvajanja:
-3.0 7.0 
3.0 -9.0 
0.0 7.0-9.0i -6.0 7.0+9.0i

Primer

Vhod
2
1 1
1 1

Izhod
1.0 1.0 
1.0 1.0 
2.0 1.0+1.0i 0.0 1.0-1.0i 
1.0 1.0 
1.0 1.0 
2.0 1.0+1.0i 0.0 1.0-1.0i 
4.0 4.0 
0.0 4.0i 
4.0 8.0 4.0 0.0 
1.0 2.0 1.0 0.0 
*/

import java.util.Scanner;

public class Izziv8 {
    static Scanner sc = new Scanner (System.in);
    static int numCoefficient;
    static double lengthPolynomial;
    static Complex[] polynomial1;
    static Complex[] polynomial2;
    static Complex[] result;
    static boolean make;

    public static void main(String[] args) {
        
        numCoefficient = sc.nextInt();
        double power = Math.ceil(Math.log(numCoefficient * 2)/ Math.log(2));
        lengthPolynomial = (int) Math.pow(2, (int)power);

        int coefficient = 0;
        make = false;

        // naredi polinom 1
        polynomial1 = new Complex [(int)lengthPolynomial];
 
        for (int i = 0; i < numCoefficient; i++) {
            coefficient = sc.nextInt();
            polynomial1[i] = new Complex (coefficient, 0); // degree
        }

        for (int i = numCoefficient; i < polynomial1.length; i++) 
            polynomial1[i] = new Complex (0, 0); // degree

        // naredi polinom 2
        polynomial2 = new Complex [(int)lengthPolynomial];

        for (int i = 0; i < numCoefficient; i++) {
            coefficient = sc.nextInt();
            polynomial2[i] = new Complex (coefficient, 0); // degree
        }

        for (int i = numCoefficient; i < polynomial2.length; i++)
            polynomial2[i] = new Complex (0, 0); // degree
    

        polynomial1 = divide(polynomial1, make);
        polynomial2 = divide(polynomial2, make);
 
        result = new Complex [(int)lengthPolynomial];

        for (int i = 0; i < lengthPolynomial; i++) 
            result[i] = polynomial2[i].times(polynomial1[i]).conj();

        result = divide(result, !make);

        for (int i = 0; i < result.length; i++)
            result[i] = result[i].conj();
        for (int i = 0; i < result.length; i++)
            result[i] = result[i].times(1 / lengthPolynomial);

        for (int i = 0; i < result.length; i++)
            System.out.printf("%s ", result[i].toString());
        System.out.println();
    }

    // razdeli: sod/lih, n/2
    private static Complex[] divide (Complex[] polynomial, boolean make) {
        if (polynomial.length == 1)
            return polynomial;
        
        Complex[] polynomial1 = new Complex[polynomial.length / 2];
        Complex[] polynomial2 = new Complex[polynomial.length / 2];

        boolean even = true;
        int countEven = 0;
        int countOdd = 0;
        for (int i = 0; i < polynomial.length; ++i) {
            if (even) {
                polynomial1[countEven++] = polynomial[i];
                even = false;
            } else {
                polynomial2[countOdd++] = polynomial[i];
                even = true;
            }
        }

        polynomial1 = divide(polynomial1, make);
        polynomial2 = divide(polynomial2, make);
        polynomial = merge(polynomial, polynomial1, polynomial2);

        if (make) 
            for (int i = 0; i < polynomial.length; ++i)
                polynomial[i] = polynomial[i].conj();
        
        // izpis
        for (int i = 0; i < polynomial.length; ++i)
            System.out.printf("%s ", polynomial[i].toString());
        System.out.println();

        if (make) 
            for (int i = 0; i < polynomial.length; ++i)
                polynomial[i] = polynomial[i].conj();

        return polynomial;
    }

    private static Complex[] merge (Complex[] result, Complex[] polynomial1, Complex[] polynomial2) {
        Complex w = new Complex (Math.cos(2 * Math.PI / result.length), Math.sin(2 * Math.PI / result.length));
        Complex wk = new Complex (1,0);
        int i = 0;
        int j = 0;

        for (i = 0; i < result.length / 2; ++i) {
            result[i] = polynomial1[i].plus(polynomial2[i].times(wk));
            wk = wk.times(w);
        }

        wk = new Complex (1,0);
        for (; i < result.length; ++i, ++j) {
            result[i] = polynomial1[j].minus(polynomial2[j].times(wk));
            wk = wk.times(w);
        }

        return result;
    }
}


class Complex extends Izziv8 {
	double re;
	double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
    	double tRe = (double)Math.round(re * 100000) / 100000;
    	double tIm = (double)Math.round(im * 100000) / 100000;
        if (tIm == 0) return tRe + "";
        if (tRe == 0) return tIm + "i";
        if (tIm <  0) return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

	public Complex conj() {
		return new Complex(re, -im);
	}

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }


    //power komplesnega stevila
    public Complex pow(int k) {

    	Complex c = new Complex(1,0);
    	for (int i = 0; i <k ; i++) {
			c = c.times(this);
		}
    	return c;
    }

}

