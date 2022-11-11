/*
* Author    : Sanjay balasubramanian
* Date      : 2022-10-20 
* Problem   : Spiderman's workout
* Run time  : 0.18 s
* language  : Java 
* test case : 2/2
* Source    : Kattis
* Email     : balasub9@buffalo.edu
* github    : https://github.com/balasub9/Algorithms
*/


package Grid;

import java.lang.*;
import java.io.*;
import java.util.*;

public class Grid {

  private int row;
  private int col;
  private int stepCount;

  // constructor
  public Grid(int row, int col, int stepCount) {
    this.row = row;
    this.col = col;
    this.stepCount = stepCount;
  }


  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // Read the inputs and list of integer
    String[] inputs = br.readLine().split("\\s+", 2);

    final int rows = Integer.parseInt(inputs[0]);
    final int cols = Integer.parseInt(inputs[1]);

    int[][] allNumbers = new int[rows][cols];
    int[][] distancefromSource = new int[rows][cols];

    //Parse all the input string to  integer and store it in a array
    for (int i = 0; i < rows; i++) {
      String[] numbers = br.readLine().split("");
      for (int j = 0; j < cols; j++) {
        allNumbers[i][j] = Integer.parseInt(numbers[j]);
        distancefromSource[i][j] = Integer.MAX_VALUE;
      }
    }


    Queue < Grid > q = new LinkedList < > ();
    q.add(new Grid(0, 0, 0));
    distancefromSource[0][0] = 0;

    //Iterate untill Queue have next moves available in grid
    while (q.size() > 0) {

      Grid qv = q.remove();

      int distanceToMove = allNumbers[qv.row][qv.col];

      //Move rows Up from current position
      if (qv.row - distanceToMove > -1 &&
        distancefromSource[qv.row - distanceToMove][qv.col] > qv.stepCount + 1) {
        //Update step count if the new locations step count is greater than new step count 
        distancefromSource[qv.row - distanceToMove][qv.col] = qv.stepCount + 1;
        //Record the next moves in queue
        q.add(new Grid(qv.row - distanceToMove, qv.col, qv.stepCount + 1));
      }

      //Move rows Down from current position
      if (qv.row + distanceToMove < rows &&
        distancefromSource[qv.row + distanceToMove][qv.col] > qv.stepCount + 1) {
        //Update step count if the new locations step count is greater than new step count 
        distancefromSource[qv.row + distanceToMove][qv.col] = qv.stepCount + 1;
        //Record the next moves in queue
        q.add(new Grid(qv.row + distanceToMove, qv.col, qv.stepCount + 1));
      }

      //Left Step
      if (qv.col - distanceToMove > -1 &&
        distancefromSource[qv.row][qv.col - distanceToMove] > qv.stepCount + 1) {
        //Update step count if the new locations step count is greater than new step count 
        distancefromSource[qv.row][qv.col - distanceToMove] = qv.stepCount + 1;
        //Record the next moves in queue
        q.add(new Grid(qv.row, qv.col - distanceToMove, qv.stepCount + 1));
      }

      //Right Step
      if (qv.col + distanceToMove < cols &&
        distancefromSource[qv.row][qv.col + distanceToMove] > qv.stepCount + 1) {
       //Update step count if the new locations step count is greater than new step count 
        distancefromSource[qv.row][qv.col + distanceToMove] = qv.stepCount + 1;
        //Record the next moves in queue
        q.add(new Grid(qv.row, qv.col + distanceToMove, qv.stepCount + 1));
      }

    }

    // Print the minimum step count from source to destination
    int  result = distancefromSource[rows - 1][cols - 1] == Integer.MAX_VALUE ? -1 
     : distancefromSource[rows - 1][cols - 1];
      System.out.println(result);
 
  }
}