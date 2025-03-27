package algstudent.s5;

import java.util.Random;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPaths {
	static String[] v; // node vector
	static int[][] weights; // weight matrix
	static int[][] costs; // Floyd's paths cost matrix
	static int[][] p; // predecessor matrix (steps) in Floyd paths
	private static Random rng;

	public static void main(String arg[]) {
		int n = 5; // nodes of example graph
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];
		rng = new Random();

		fillInWeights(weights); // weights for the example
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);

		floyd(weights, costs, p);

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different nodes):");
		System.out.println();
		for (int source = 0; source <= n - 1; source++)
			for (int target = 0; target <= n - 1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					minimumPath(v, weights, costs, p, source, target);
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
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
		System.out.println(result);
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
					w[i][j] = rng.nextInt(10,100);
				} else {
					w[i][j] = 10000000;
				}
	}

	/* print the cost matrix */
	static void printMatrix(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(String.format("%10s", a[i][j]));
			System.out.println();
		}
		System.out.println();
	}

}