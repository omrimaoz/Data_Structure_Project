package BST_List;

import java.util.Random;
public class Experiment {
	public static final int  N = 10000; /** define experiment constant */
	public static void main (String[] args) {
		Random gen = new Random();

		/** experiment 1 - insert items in a way that the circular list have an advantage over the tree list */
		System.out.println("experiment 1\n");
		for (int i = 1; i < 11; i++) {
			System.out.println(i + ":" + N*i + " items");
			testTreeList(i, 1, gen);
			testCircList(i, 1, gen);
		}
		/**experiment 2 - insert items in a way that the tree list have an advantage over the circular list */
		System.out.println("\nexperiment 2\n");
		for (int i = 1; i < 11; i++) {
			System.out.println(i + ":" + N*i + " items");
			testTreeList(i, 2, gen);
			testCircList(i, 2, gen);
		}
		/**experiment 3 - insert items distributed uniformly into circular and tree lists  */
		System.out.println("\nexperiment 3\n");
		for (int i = 1; i < 11; i++) {
			System.out.println(i + ":" + N*i + " items");
			testTreeList(i, 3, gen);
			testCircList(i, 3, gen);
		}
	}
	public static void testTreeList(int i, int experiment, Random gen) {
		TreeList lst = new TreeList();
		long startTime = System.nanoTime();
		for (int j=0; j < i*N; j++) {
			if (experiment == 1) lst.insert(j, j, "");
			else {
				if (experiment == 2) lst.insert(j/2, j, "");
				else {
					int idx=0;
					if (lst.tree.size() != 0) idx = gen.nextInt(lst.tree.size());
					lst.insert(idx, j, "");
				}
			}
		}
		long elapsed = (System.nanoTime() - startTime) / (i * 100000L);
		System.out.println("TreeList: " + elapsed + " milliseconds");
		//System.out.println("left rot: " + lst.tree.left_rot + ". right rot: " + lst.tree.right_rot);
		
	}
	public static void testCircList(int i, int experiment, Random gen) {
		CircularList lst = new CircularList(i*N);
		long startTime = System.nanoTime();
		for (int j=0; j < i*N; j++) {
			if (experiment == 1) lst.insert(j, j, "");
			else {
				if (experiment == 2) lst.insert(j/2, j, "");
				else {
					int idx=0;
					if (lst.length != 0) idx = gen.nextInt(lst.length);
					lst.insert(idx, j, "");
				}
			}
		}
		long elapsed = (System.nanoTime() - startTime)/(i* 100000L);
		System.out.println("CircularList: " + elapsed + " milliseconds");
		//System.out.println(lst.retrieve(i*10000-1).getKey());
	}
}