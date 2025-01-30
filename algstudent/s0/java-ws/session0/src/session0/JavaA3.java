package session0;

import java.util.ArrayList;
import java.util.List;

public class JavaA3 {
	public static void main(String args[]) {
		runTest();
	}

	private static void runTest() {
		int n = 10000;
		for (int i = 0; i < 7; i++) {
			long t1 = System.currentTimeMillis();
			getPrimes(n);
			long t2 = System.currentTimeMillis();
			System.out.println("n = " + n + " *** " + "time = " + (t2 - t1) + " milliseconds");
			n *= 2;
		}
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i < n/2+1; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static int[] getPrimes(int n) {
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		int[] result = new int[primes.size()];
		for (int i = 0; i < primes.size(); i++) {
			result[i] = primes.get(i).intValue();
		}
		return result;
	}
}
