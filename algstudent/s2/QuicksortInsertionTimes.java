package algstudent.s2;

/* This class measures times for the Quick Sort method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		int k = Integer.parseInt(arg[0]);

		v = new int[16000000];

		Vector.randomSorted(v);

		t1 = System.currentTimeMillis();

		QuicksortInsertion.quicksortInsertion(v, k);

		t2 = System.currentTimeMillis();

		System.out.println(k + "\t" + (t2 - t1));
	}
}
