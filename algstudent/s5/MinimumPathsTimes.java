package algstudent.s5;

import java.util.Random;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPathsTimes {
	static String[] v; // node vector
	static int[][] weights; // weight matrix
	static int[][] costs; // Floyd's paths cost matrix
	static int[][] p; // predecessor matrix (steps) in Floyd paths
	private static Random rng;

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int n = 200;
		do {
			t1 = System.currentTimeMillis();
			floydTime(n);
			t2 = System.currentTimeMillis();
			System.out.println("N = " + n + " | TIME = " + (t2-t1));
			n *= 2;
		}while(t2-t1<=300000);
	}

	static void floydTime(int n) {
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;
		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];
		rng = new Random();
		fillInWeights(weights);
		floyd(weights, costs, p);
		for (int source = 0; source <= n - 1; source++)
			for (int target = 0; target <= n - 1; target++)
				if (source != target) {
					minimumPath(v, weights, costs, p, source, target);
				}
	}

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	static void floyd(int[][] weights, int[][] costs, int[][] p) {
		int n = weights.length;
		// Initialize matrices
		for (int origin = 0; origin < n; origin++) {
			for (int target = 0; target < n; target++) {
				if (weights[origin][target] < 10000000) {
					costs[origin][target] = weights[origin][target];
				} else if (origin == target) {
					costs[origin][target] = 0;
				} else {
					costs[origin][target] = 10000000;
				}
				p[origin][target] = -1;
			}
		}
		// Compute costs
		for (int pivot = 0; pivot < n; pivot++) {
			for (int origin = 0; origin < n; origin++) {
				for (int target = 0; target < n; target++) {
					if (costs[origin][pivot] + costs[pivot][target] < costs[origin][target]) {
						costs[origin][target] = costs[origin][pivot] + costs[pivot][target];
						p[origin][target] = pivot;
					}
				}
			}
		}
	}

	static void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] p, int origin, int target) {
		String result = v[origin] + "-->" + path(v, costs, p, origin, target) + "-->" + v[target];
		result = result.replace("-->-->", "-->");
		// System.out.println(result);
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static String path(String[] v, int[][] costs, int[][] p, int i, int j) {
		String result = "";
		if (p[i][j] <= -1 && costs[i][j] < 10000000)
			return result;
		if (p[i][j] <= -1 && costs[i][j] >= 10000000)
			return " / NO PATH / ";
		result += "-->" + v[p[i][j]];
		return path(v, costs, p, i, p[i][j]) + result + path(v, costs, p, p[i][j], j);
	}

	/* load the example cost matrix */
	static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				if (rng.nextInt(0, 2) > 0) {
					w[i][j] = rng.nextInt(10, 100);
				} else {
					w[i][j] = 10000000;
				}
	}

}