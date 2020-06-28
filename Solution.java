import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

  public static void main(String[] args) throws Exception {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

    int numberOfGames = Integer.parseInt(stringTokenizer.nextToken());

    while (numberOfGames > 0) {
      stringTokenizer = new StringTokenizer(bufferedReader.readLine());
      int numberOfIntegers_in_stackA = Integer.parseInt(stringTokenizer.nextToken());
      int numberOfIntegers_in_stackB = Integer.parseInt(stringTokenizer.nextToken());
      int maxSum = Integer.parseInt(stringTokenizer.nextToken());

      Stack<Integer> stackA = new Stack<Integer>();
      stackA.setSize(numberOfIntegers_in_stackA);
      stringTokenizer = new StringTokenizer(bufferedReader.readLine());

      for (int i = numberOfIntegers_in_stackA - 1; i >= 0; i--) {
        int current = Integer.parseInt(stringTokenizer.nextToken());
        stackA.set(i, current);
      }

      Stack<Integer> stackB = new Stack<Integer>();
      stackB.setSize(numberOfIntegers_in_stackB);
      stringTokenizer = new StringTokenizer(bufferedReader.readLine());

      for (int i = numberOfIntegers_in_stackB - 1; i >= 0; i--) {
        int current = Integer.parseInt(stringTokenizer.nextToken());
        stackB.set(i, current);
      }

      int result = calculate_maxNumberOfRemovedIntegers(stackA, stackB, maxSum);
      System.out.println(result);

      numberOfGames--;
    }
    bufferedReader.close();
  }

  /**
   * Calculates the maximum number of integers that can be removed from stackA and stackB,
   * the sum of which do not exceed the maxSum.
   *
   * @return An integer, representing the maximum number of removed integers, as described.
   */
  private static int calculate_maxNumberOfRemovedIntegers(Stack<Integer> stackA, Stack<Integer> stackB, int maxSum) {

    long sum = 0;
    int total_removedIntegers = 0;
    Stack<Integer> removed_from_stackA = new Stack<Integer>();

    // First take as many integers as possible from stackA, without exceeding maxSum.
    while (!stackA.isEmpty()) {
      int current = stackA.pop();
      if (sum + (long) current <= maxSum) {
        sum += (long) current;
        removed_from_stackA.push(current);
        total_removedIntegers++;
      } else {
        break;
      }
    }

    // Then try adding integers from stackB.
    while (!stackB.isEmpty()) {

      int current = stackB.pop();

      // If maxSum is not exceeded, just add an integer from stackB.
      if (sum + (long) current <= maxSum) {
        sum += (long) current;
        total_removedIntegers++;
      }

      // If maxSum is exceeded, replace in variable 'sum' the last integer from stackA
      // with the current integer from stackB.
      else if (!removed_from_stackA.isEmpty()) {
        sum += (long) current - (long) removed_from_stackA.pop();
      }

      // If neither of the above two options is possible, stop checking.
      else {
        break;
      }
    }
    return total_removedIntegers;
  }
}
