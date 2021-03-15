package HashTable;

public class AQPHashTable extends OAHashTable {
	
	/**Set fields*/
	private ModHash HashFunc;
	private int length;

	/**Constructor + create the hash func */
	public AQPHashTable(int m, long p) {
		super(m);
		this.HashFunc = ModHash.GetFunc(m, p);
		this.length = m;
	}
	
	/**Do true modulo operator (over m-size field)*/
	@Override
	public int Hash(long x, int i) {
		int position; 
		if(i%2==0)
			position = (HashFunc.Hash(x) + (i * i)) % this.length;
		else
			position = (HashFunc.Hash(x) - (i * i)) % this.length;
		if (position<0)
			return position + this.length;
		return position;
	}
}
