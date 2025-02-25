package algstudent.s11;

/**
 * This program serves to measure times automatically increasing 
 * the size of the problem. In addition, we use a repetition value 
 * determined by nTimes, an argument of the program
 */
public class Vector6 {
	static int[]v;
	static int[]u;
	
	public static void main(String arg []) {
		int repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		int matches = 0;
		
		for (int n=10; n<=Integer.MAX_VALUE; n*=5){ //n is increased *5   
			  v = new int[n];
			  Vector1.fillIn(v);
			  u = new int[n];
			  Vector1.fillIn(u);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     matches = Vector1.matches1(v,u);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%d milliseconds MATCHES=%d NTIMES=%d\n", n, t2-t1, matches, repetitions);	
		}//for 
		
	}//main

}
