/* 

Implementiran algoritem
os - osnovnošolsko množenje
*/

import java.util.Scanner; // SAMO SCANNER

public class MnozenjeStevil {
    static String alghorithm;
    static int base;
    static String number1;
    static String number2;
    static String product;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        alghorithm = sc.next();

        switch (alghorithm) {
            case "os":
                base = sc.nextInt();

                number1 = sc.next();
                number2 = sc.next();

                int first = toDecimal(number1, base);
                int second = toDecimal(number2, base);

                String productPrint = fromDecimal(first * second, base);
                int lengthPP = productPrint.length();
          
                for (int i = 0; i < number2.length(); ++i) {
                    int part = first * value(number2.charAt(i));
                    System.out.println(fromDecimal(part, base));
                }

                while (lengthPP > 0) {
                    System.out.print("-");
                    lengthPP--;
                }

                System.out.println();
                System.out.printf("%s\n", productPrint);

                break;

            case "dv":
                base = sc.nextInt();

                break;

            case "ka":
                base = sc.nextInt();
                break;
            
            default:
                System.out.println("Error!");
        }
    }

    static int value(char c) { 
        if (c >= '0' && c <= '9') 
            return c - '0'; 
        return c - 'a' + 10; 
    } 

    static int toDecimal(String string, int base) { 
        int power = 1;
        int number = 0; 

        for (int i = string.length() - 1; i >= 0; --i) { 
            number += value(string.charAt(i)) * power; 
            power *= base; 
        } 
        return number; 
    }

    static char reValue(int number) { 
        if (number >= 0 && number <= 9) 
            return (char)(number + 48); 
        return (char)(number - 10 + 'a'); 
    } 

    static String fromDecimal(int inputNumber, int base) { 
        String s = ""; 
        String reversed = "";
        String number = "";

        while (inputNumber >= 1) { 
            int remainder = inputNumber % base;
            inputNumber /= base;
            reversed += reValue(remainder);
        } 
   
        for (int i = reversed.length() - 1; i >= 0; --i) 
            number += reversed.charAt(i);
        
        if (number.equals(""))
            return "0";

        return number;
    }  
}
