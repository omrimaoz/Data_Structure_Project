package BST_List;

import java.util.Random;
public class experiment {
	public static void main (String[] args) {
		Random gen = new Random();

		//experiment 1
		//System.out.println("experiment 1");
		//for (int i = 1; i < 11; i++) {
		//	System.out.println(i + ":" + 10000*i + " items");
		//	testTreeList(i, 1, gen);
		//	testCircList(i, 1, gen);
		//}
		//experiment 2
		//System.out.println("experiment 2");
		//for (int i = 1; i < 11; i++) {
		//	System.out.println(i + ":" + 10000*i + " items");
		//	testTreeList(i, 2, gen);
		//	testCircList(i, 2, gen);
		//}
		//experiment 3
		System.out.println("experiment 3");
		for (int i = 1; i < 11; i++) {
			System.out.println(i + ":" + 10000*i + " items");
			testTreeList(i, 3, gen);
			testCircList(i, 3, gen);
		}
	}
	public static void testTreeList(int i, int experiment, Random gen) {
		TreeList lst = new TreeList();
		Long startTime = System.nanoTime();
		for (int j=0; j < i*100000; j++) {
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
		Long elapsed = (System.nanoTime()- startTime)/(i*10000);
		System.out.println("TreeList: " + elapsed/1000 + " milliseconds");
		//System.out.println("left rot: " + lst.tree.left_rot + ". right rot: " + lst.tree.right_rot);
		
	}
	public static void testCircList(int i, int experiment, Random gen) {
		CircularList lst = new CircularList(i*10000);
		Long startTime = System.nanoTime();
		for (int j=0; j < i*100000; j++) {
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
		Long elapsed = (System.nanoTime() - startTime)/(i*10000);
		System.out.println("CircularList: " + elapsed/1000 + " milliseconds");
		System.out.println(lst.retrieve(i*10000-1).getKey());
	}
}