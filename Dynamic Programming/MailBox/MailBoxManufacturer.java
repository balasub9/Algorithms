/*
* Author    : Sanjay balasubramanian
* Date      : 2022-11-11 10:38:21
* Problem   : The Mailbox Manufacturers Problem
* Run time  : 0.19 s
* language  : Java 
* test case : 2/2
* Source    : Kattis
* Email     : balasub9@buffalo.edu
* github    : https://github.com/balasub9/Algorithms
*/


package MailBox;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MailBoxManufacturer {
  public static void main(String[] args) throws IOException {
    //Read No of Test Case
    BufferedReader br =
      new BufferedReader(new InputStreamReader(System.in));
    int totalNoOfCases = Integer.parseInt(br.readLine());


    while (totalNoOfCases--> 0) {

      //Read No of mailbox and Mailbox Capacity
      String[] inputs = br.readLine().split("\\s+");
      int noOfMailBox = Integer.parseInt(inputs[0]);
      int mailBoxCapacity = Integer.parseInt(inputs[1]);

      //Decalre a 3D array to store Optimum Carackers Required for Different
      //no of mailbox in the range [0 to mailBoxCapacity]
      Integer[][][] minimumCrackersCount =
        new Integer[noOfMailBox + 1][mailBoxCapacity + 1][mailBoxCapacity +
          1
        ];
      //Fill the complete 3D array to Negative Interger Max value
      for (Integer[][] row: minimumCrackersCount) {
        for (Integer[] rowColumn: row) {
          Arrays.fill(rowColumn, Integer.MIN_VALUE);
        }
      }
      //Print the Optimum crackers required for the given mailbox count and mailbox capacity
      System.out.println(getOptimumCrackersRequired(noOfMailBox, 0, mailBoxCapacity,
        minimumCrackersCount));

    }
  }

  /*
  * Function to get the Optimum crackers Count for the mailbox count and capacity
  * @param  Mailbox count 
  * @param  Lowerlimit for no of crackers to be filled 
  * @param  Upperlimit for the number of crackers to be filled
  * @param  3D array to store the optimum crackers required
  * @return  optimum crackers count
  */
  public static int getOptimumCrackersRequired(int noMailBox, int lowerlimit,
    int upperlimit,
    Integer[][][] minCracerksCount) {

    // If Mail box count is 1 then sum of all the crackers 
    // from lower limit to upper limit
    if (noMailBox == 1) {
      return sumofNnumbers(upperlimit) - sumofNnumbers(lowerlimit);
    }
    // If then number of crackers lower limit is equal to upper limit 
    // then optimal cracker count is zero
    if (lowerlimit >= upperlimit)
      return 0;

    // If the Optimum cracker count is stored already return it
    if (minCracerksCount[noMailBox][lowerlimit][upperlimit] !=
      Integer.MIN_VALUE) {
      return minCracerksCount[noMailBox][lowerlimit][upperlimit];
    } else {
      int optimalNoCrakers = Integer.MAX_VALUE;
      // Find the optimum number of cracker to test to get the minimum crackers required
      // in the worst case
      for (int crackerUsed = lowerlimit + 1; crackerUsed <= upperlimit; crackerUsed++) {
        int maxCrackers =
        // Find the maximum to get no of crackers in the worst case scenario
          Math.max(getOptimumCrackersRequired(noMailBox - 1, lowerlimit, crackerUsed - 1,
            minCracerksCount), getOptimumCrackersRequired(noMailBox, crackerUsed,
            upperlimit,
            minCracerksCount));
        // Find the optimum no of crackers by taking min of all max counts 
        optimalNoCrakers =
          Math.min(optimalNoCrakers, crackerUsed + maxCrackers);
      }

      minCracerksCount[noMailBox][lowerlimit][upperlimit] =
        optimalNoCrakers;
    }
    return minCracerksCount[noMailBox][lowerlimit][upperlimit];

  }

/*
  * Function to get Sum of first n numbers
  * @param  n integer
  * @return  Sum of first n numbers
  */
  public static int sumofNnumbers(int n) {
    return (n * (n + 1)) / 2;
  }
}