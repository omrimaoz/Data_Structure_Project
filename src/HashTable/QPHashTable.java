package HashTable;

public class QPHashTable extends OAHashTable {
	
	/**Set fields */
	private ModHash HashFunc;
	private int length;

	/**Constructor + create the hash func */
	public QPHashTable(int m, long p) {
		super(m);
		this.HashFunc = ModHash.GetFunc(m, p);
		this.length = m;
	}
	
	@Override
	public int Hash(long x, int i) {
		return (HashFunc.Hash(x) + (i * i)) % this.length;

	}
}
