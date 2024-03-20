package test;

import java.util.Scanner;

public class Test {

  public static int minSouvenirs(int n, int[] happiness) {
    int left = 0, right = 0, minSouvenirQuantity = 0;
    int[] vSouvenirs = new int[n];
    for (int i = 0; i < n; i++) {
      right = i + 1;
      left = i - 1;
      if (i == 0) {
        if (happiness[i] >= happiness[right]) {
          vSouvenirs[i] = 1;
        } else {
          vSouvenirs[i] = 2;
        }
      } else if (i == n-1) {
        if (happiness[i] >= happiness[left]) {
          vSouvenirs[i] = 1;
        } else {
          vSouvenirs[i] = vSouvenirs[left] + 1;
        }
      } else {
        if (happiness[i] >= happiness[left] && happiness[i] >= happiness[right]) {
          vSouvenirs[i] = 1;
        } else {
          if (happiness[i] < happiness[left]) {
            vSouvenirs[i] = vSouvenirs[left] + 1;
          } else if (happiness[i] < happiness[right]) {
            vSouvenirs[i] = 2;
          }
        }
      }
    }

    for (int i=0; i<vSouvenirs.length; i++) {
      minSouvenirQuantity += vSouvenirs[i];
    }
    return minSouvenirQuantity;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int N = scanner.nextInt();
    int[] happinessLevels = new int[N];
    for (int i = 0; i < N; i++) {
      happinessLevels[i] = scanner.nextInt();
    }

    int result = minSouvenirs(N, happinessLevels);
    System.out.println(result);
  }
}
