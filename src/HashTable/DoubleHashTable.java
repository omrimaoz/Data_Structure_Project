package HashTable;

import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	
	/**Set fields */
	private ModHash HashFunc1;
	private ModHash HashFunc2;
	private int length;

	/**Constructor + create the 2 hash func */
	public DoubleHashTable(int m, long p) {
		super(m);
		this.HashFunc1 = ModHash.GetFunc(m, p);
		this.HashFunc2 = ModHash.GetFunc(m, p);
		this.length = m;
	}
	
	/**Check that the second hash func don't return 0 for specific key */
	@Override
	public int Hash(long x, int i) {
		int HF2 = HashFunc2.Hash(x);
		if(HF2==0)
			HF2++;
		return (HashFunc1.Hash(x) + i * HF2) % this.length;
	}
	
}
