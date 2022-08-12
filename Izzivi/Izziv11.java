/* A Naive recursive implementation of 0-1 Knapsack problem */

import java.util.*;
public class Izziv11 
{ 
  
    // A utility function that returns maximum of two integers 
     static int max(int a, int b) { return (a > b)? a : b; } 
       
     // Returns the maximum value that can be put in a knapsack of capacity W 
     static int knapSack(int W, int wt[], int val[], int n) 
     { 
        // Base Case 
    if (n == 0 || W == 0) 
        return 0; 
       
    // If weight of the nth item is more than Knapsack capacity W, then 
    // this item cannot be included in the optimal solution 
    if (wt[n-1] > W) { 
       return knapSack(W, wt, val, n-1); 
    }
       
    // Return the maximum of two cases:  
    // (1) nth item included  
    // (2) not included 
    int maxi = 0;
    if ((val[n-1] + knapSack(W-wt[n-1], wt, val, n-1) > knapSack(W, wt, val, n-1))) {
        maxi = val[n-1] + knapSack(W-wt[n-1], wt, val, n-1);
    }
    else {
        maxi = knapSack(W, wt, val, n-1);
    }
    System.out.println(W);

    return maxi;
}
  
    
   // Driver program to test above function 
   public static void main(String args[]) 
   { 
       Scanner sc = new Scanner(System.in);
       int W = sc.nextInt();
       int n = sc.nextInt();
       int val[] = new int[n];
       int wt[] = new int[n];
       for (int i = 0; i < n; i++)
       {
           val[i] = sc.nextInt();
           wt[i] = sc.nextInt();
       }
        System.out.println(knapSack(W, wt, val, n)); 
    } 
} 