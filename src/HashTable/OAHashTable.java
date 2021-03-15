package HashTable;

public abstract class OAHashTable implements IHashTable {
	
	/**Set fields */
	public HashTableElement [] table; 
	private HashTableElement defaultElem;
	
	/**Constructor */
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.defaultElem = new HashTableElement(0,0);
	}

	@Override
	public HashTableElement Find(long key) {
		for (int i = 0; i < this.table.length; i++) { // start probing process
			int cell = this.Hash(key, i);
			if (this.table[cell] == null) {
				return null; // stop probing
			}
			if (this.table[cell].GetKey() == key && this.table[cell] != this.defaultElem) return this.table[cell];
		}
		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		for (int i = 0; i < this.table.length; i++) { // start probing process
			int cell = this.Hash(hte.GetKey(), i);
			if (this.table[cell] == null || this.table[cell] == this.defaultElem) {
				this.table[cell] = hte; // Found free space
				return; // stop probing
			}
			if (this.table[cell].GetKey() == hte.GetKey()) throw new KeyAlreadyExistsException(hte);
		}
		throw new TableIsFullException(hte);
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		for (int i = 0; i < this.table.length; i++) { // start probing process
			int cell = this.Hash(key, i);
			if (this.table[cell] == null) { // Key not found in place
				throw new KeyDoesntExistException(key);
			}
			if (this.table[cell].GetKey() == key && this.table[cell] != this.defaultElem) {
				this.table[cell] = this.defaultElem; // Found key and replace with defaultElem - mark as delete
				return; // stop probing
			}
		}
		throw new KeyDoesntExistException(key);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
	
}
