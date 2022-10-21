/*
* Author    : Sanjay balasubramanian
* Date      : 2022-10-20 23:37:13
* Problem   : Spiderman's workout
* Run time  : 0.18 s
* test case : 2/2
* Source    : Kattis
* Email     : balasub9@buffalo.edu
* github    : https://github.com/balasub9/Algorithms
*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
  public static void main(String[] args) throws IOException, Exception {
    //Read the number of test scenarios
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int totalNoOfTestCases = Integer.parseInt(br.readLine());

    while (totalNoOfTestCases--> 0) {
      // Read the no of distances to take as input
      int noOfDistances = Integer.parseInt(br.readLine());
      int distances[] = new int[noOfDistances + 1];
      String distancesString[] = br.readLine().split(" ");

      int sumOfDistance = 0;
      // Read all distances and calculate their sum
      for (int i = 0; i < noOfDistances; i++) {
        distances[i + 1] = Integer.parseInt(distancesString[i]);
        sumOfDistance += distances[i + 1];
      }

      // If is sum of distance is odd its a impossible solution since he has to travel
      // equal distacnse up and down which makes the sum of distance even
      if (sumOfDistance % 2 != 0) {
        System.out.print("\nIMPOSSIBLE");
        continue;
      }

      //Maximum optimum possible height the spider man can reach is sum of all distance
      int maxPossibleHeight = (sumOfDistance / 2);

      int optHeight[][] = new int[noOfDistances + 1][sumOfDistance + 1];
      char upDownTracker[][] = new char[noOfDistances + 1][sumOfDistance + 1];

      // Fill the optimum height 2D array with Integer Minimum value
      for (int[] array: optHeight) {
        Arrays.fill(array, Integer.MIN_VALUE);
      }

      // Fill the Updown step tracker 2D array with 'X'
      for (char[] array: upDownTracker) {
        Arrays.fill(array, 'X');
      }

      //Since spiderman starts from ground level the  first height is always UP 
      optHeight[1][distances[1]] = distances[1];
      upDownTracker[1][distances[1]] = 'U';

    // For each distance input calculate the optimum height at every height between 0 and maxPossibleHeight
      for (int currentStep = 2; currentStep <= noOfDistances; currentStep++) {
        for (int currHeight = 0; currHeight <= maxPossibleHeight; currHeight++) {

          int newHeightAfterUp = Integer.MAX_VALUE;
          int newHeightAfterDown = Integer.MAX_VALUE;

          // If previous step doesnt have Integer.MIN_VALUE value  then 
          if (optHeight[currentStep - 1][currHeight] != Integer.MIN_VALUE) {

            //Calculate the new height if its a UP step from previous height
            if (currHeight + distances[currentStep] <= maxPossibleHeight) {
              newHeightAfterUp = currHeight + distances[currentStep];
            }
            //Calculate the new height if its a DOWN step from previous height
            if (currHeight - distances[currentStep] >= 0) {
              newHeightAfterDown = currHeight - distances[currentStep];
            }


            if (newHeightAfterUp != Integer.MAX_VALUE) {
              if (optHeight[currentStep][newHeightAfterUp] == Integer.MIN_VALUE) {
                // If the new height after UP step is Integer Minimum
                // Mark the current step as UP step  at new height 
                // and record the optimum height at current step and current height
                // with maximum of newHeightAfterUp and optimum height at previous step
                upDownTracker[currentStep][newHeightAfterUp] = 'U';
                optHeight[currentStep][newHeightAfterUp] =
                  Math.max(newHeightAfterUp, optHeight[currentStep - 1][currHeight]);
              } else {
                // Mark the step as UP step only if the condition satisfies
                if (optHeight[currentStep - 1][currHeight] < optHeight[currentStep][newHeightAfterUp]) {
                  upDownTracker[currentStep][newHeightAfterUp] = 'U';
                }
                // else if the new height after UP step  already have a optimum height value
                // Record the optimum height at current step and current height
                // with minimum of optimum height at current step and optimum height at previous step
                optHeight[currentStep][newHeightAfterUp] =
                  Math.min(optHeight[currentStep - 1][currHeight], optHeight[currentStep][newHeightAfterUp]);
              }
            }

            if (newHeightAfterDown != Integer.MAX_VALUE) {
              if (optHeight[currentStep][newHeightAfterDown] == Integer.MIN_VALUE) {
                // If the new height after DOWN step is Integer Minimum
                // Mark the current step as DOWN step  at new height 
                // and record the optimum height at current step and current height
                // with maximum of newHeightAfterDown and optimum height at previous step
                upDownTracker[currentStep][newHeightAfterDown] = 'D';
                optHeight[currentStep][newHeightAfterDown] =
                  Math.max(newHeightAfterDown, optHeight[currentStep - 1][currHeight]);
              } else {
                // Mark the step as DOWN step only if the condition satisfies
                if (optHeight[currentStep - 1][currHeight] < optHeight[currentStep][newHeightAfterDown]) {
                  upDownTracker[currentStep][newHeightAfterDown] = 'D';
                }
                // else if the new height after DOWN step  already have a optimum height value
                // Record the optimum height at current step and current height
                // with minimum of optimum height at current step and optimum height at previous step
                optHeight[currentStep][newHeightAfterDown] =
                  Math.min(optHeight[currentStep - 1][currHeight], optHeight[currentStep][newHeightAfterDown]);
              }
            }

          }

        }
      }

      // If at last step the spider man doesnt reach ground level
      // then solution is impossible
      if (upDownTracker[noOfDistances][0] != 'D') {
        System.out.print("\nIMPOSSIBLE");
        continue;
      }

      StringBuilder sb = new StringBuilder();

      // Track the Optimal solution from last step since it has optimal height at height 0
      for (int i = noOfDistances, j = 0; i > 0; i--) {
        if (upDownTracker[i][j] == 'D') {
          sb.append(upDownTracker[i][j]);
          //if its a DOWN step then add the current stage distance to 
          // reach the previous step height 
          j = j + distances[i];
        } else {
          //if its a UP step then subract the current stage distance to 
          // reach the previous step height 
          sb.append(upDownTracker[i][j]);
          j = j - distances[i];
        }

      }
      String result = sb.toString();
      System.out.println();
      // Print the string in reverse for optimal solution
      for (int i = result.length() - 1; i >= 0; i--) {
        System.out.print(result.charAt(i));
      }

    }
  }
}