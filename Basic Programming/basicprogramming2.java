/*
 * Author    : Sanjay balasubramanian
 * Date      : 2022-10-20
 * Problem   : Basic Programming 2
 * Run time  : 0.96 s
 * test case : 25/25
 * language  : Java
 * Source    : Kattis
 * Email     : balasub9@buffalo.edu
 * github    : https://github.com/balasub9/Algorithms
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.*;

public class basicprogramming2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // Read the inputs and list of integer
    String[] inputs = br.readLine().split("\\s+", 2);
    String[] numbers = br.readLine().split("\\s+");
    final int n = Integer.parseInt(inputs[0]);
    final int actionRequired = Integer.parseInt(inputs[1]);
    int[] allNumbers = new int[n];
    //Parse all the input string to  integer and store it in a array
    for (int i = 0; i < n; i++)
      allNumbers[i] = Integer.parseInt(numbers[i]);
    //Sorting the array is common theme to all sub problems
    Arrays.sort(allNumbers);
    switch (actionRequired) {
    case 1:
      int id = 0;
      int j = n - 1;
      int sumvalue = 7777;
      String res = "No";
      while (id < j) {
        if (allNumbers[id] + allNumbers[j] == sumvalue) {
          res = "Yes";
          break;
        }
        // Since the array is sorted
        // increment id value by one  if the sum is less than 7777
        if (allNumbers[id] + allNumbers[j] < sumvalue)
          id++;
        else if (allNumbers[id] + allNumbers[j] > sumvalue)
          // decrement j value by one  if the sum is greater than 7777
          j--;
      }
      System.out.println(res);
      break;
    case 2:
      String result = "Unique";
      for (int i = 1; i < n; i++) {
        // If you find a repeated element in previous index 
        // in a sorted array
        // then record "Contains Duplicate" break out of loop
        if (allNumbers[i - 1] == allNumbers[i]) {
          result = "Contains duplicate";
          break;
        }
      }
      System.out.println(result);
      break;
    case 3:
      int x = -1;
      int count = 1;
      // Count the max occurence in sorted array 
      // if it exceeds length/2 then it contains 
      // a element which repeats greater than n/2 times
      for (int i = 1; i < n; i++) {
        if (allNumbers[i - 1] == allNumbers[i]) {
          count++;
        } else {
          count = 0;
        }
        if (count > (allNumbers.length / 2)) {
          x = allNumbers[i];
          break;
        }
      }
      System.out.println(x);
      break;
    case 4:
      if (n % 2 != 0)
        // If odd number of elemets in array then print the middle element
        System.out.println(allNumbers[n / 2]);
      else
        // If even number of elemets in array then print element at n/2 and (n-1)/2
        System.out.println(allNumbers[(n - 1) / 2] + " " + allNumbers[n / 2]);
      break;
    case 5:
      int c = 0;
      // Print element in range [100 , 999]
      for (int i = 0; i < n; i++) {
        if (allNumbers[i] > 99 && allNumbers[i] <= 999) {
          if (c > 0) System.out.print(" ");
          System.out.print(allNumbers[i]);
          c++;
        }
      }
      break;
    }
  }
}