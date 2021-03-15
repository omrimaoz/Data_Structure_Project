package HashTable;

public class LPHashTable extends OAHashTable {
	
	/** Set fields */
	private ModHash HashFunc;
	private int length;
	
	/**Constructor + create the hash func */
	public LPHashTable(int m, long p) {
		super(m);
		this.HashFunc = ModHash.GetFunc(m, p);
		this.length = m;
	}
	
	@Override
	public int Hash(long x, int i) {
		return (HashFunc.Hash(x) + i) % this.length;
	}
}
