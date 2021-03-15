package HashTable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ModHash {
	
	/**Set fields */
	private int m;
	private long p,a,b;
	
	/**Constructor */
	public ModHash(long a, long b, int m, long p){
		this.a = a;
		this.b = b;
		this.m = m;
		this.p = p;
	}  
	
	/**Create random long numbers and call to the constructor */
	public static ModHash GetFunc(int m, long p){
		long a = (ThreadLocalRandom.current().nextLong(Long.MAX_VALUE-1)) +1;
		long b = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
		return new ModHash(a, b, m, p);
	}
	
	/**Create hash func based on the class of the HashTable */
	public int Hash(long key) {
		return Math.abs((int)(((a * key + b) % p) % m));
	}
}

