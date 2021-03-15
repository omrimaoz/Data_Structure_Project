package BST_List;

/**
 *
 * Circular list
 *
 * An implementation of a circular list with  key and info
 *
 */
 
public class CircularList{
	/**Variables */
	Item[] itemArray;
	int maxLen;
	int length = 0;
	int start = 0;

	/**constructor */
	public CircularList (int maxLen){
		this.maxLen = maxLen;
		this.itemArray = new Item[maxLen];
	}
	/**
   * public Item retrieve(int i)
   *
   * returns the item in the ith position if it exists in the list.
   * otherwise, returns null
   */
	/**time complexity is O(1) because we access an index of an array */
	public Item retrieve(int i)
	{
		/**boundaries of the index */
		if (i < 0 || i > this.length) {
			return null;
		}
		return this.itemArray[(this.start + i)%this.maxLen];
	}

  /**
   * public int insert(int i, int k, String s) 
   *
   * inserts an item to the ith position in list  with key k and  info s.
   * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
   */
	public int insert(int i, int k, String s) {
		/**boundaries of the index
		if (i < 0 || i > this.length || this.length == this.maxLen) {
			return -1;
		}
		/**choose in which direction to "push" according to the location of i in the list */
		/**if i is closer to the end push right, otherwise push left */
		/**this way we can achieve a time complexity of O(min(i, length-i)+1) */
		if (i < this.length/2) {
			for (int j = 0; j < i; j++) {
				/**using custom mod function to handle negative numbers properly */
				this.itemArray[mod(start+j-1,maxLen)] = this.itemArray[mod(start+j,maxLen)];
			}
			this.start = mod(this.start-1, maxLen);
		}
		else {
			for (int j = this.start + this.length - 1; j > i - 1; j--) {
				this.itemArray[mod(start+j+1,maxLen)] = this.itemArray[mod(start+j,maxLen)];
			}
		}
		this.itemArray[mod(this.start + i, maxLen)] = new Item(k, s);
		this.length += 1;
		return 0;
	}

  /**
   * public int delete(int i)
   *
   * deletes an item in the ith posittion from the list.
	* returns -1 if i<0 or i>n-1 otherwise returns 0.
   */
	public int delete(int i)
	{
		/**boundaries of the index */
		if (i < 0 || i > this.length - 1) {
			return -1;
		}
		/**same as insert in terms of time complexity, we choose the direction according */
		/**to the value of i relative to the length of the list */
		if (i < this.length/2) {
			for (int j = i - 1; j > -1; j--) {
				this.itemArray[mod(start+j+1,maxLen)] = this.itemArray[mod(start+j,maxLen)];
			}
			this.start = mod(this.start+1, maxLen);
		}
		else {
			for (int j = i + 1; j < this.length; j++) {
				this.itemArray[mod(start+j-1,maxLen)] = this.itemArray[mod(start+j,maxLen)];
			}
		}
		this.length -= 1;
		return 0;
	}

	/**calculate a%b WITHOUT negative results (apparently java doesn't do it) */
	/**for example: -1%4 = -1, mod(-1,4) = 3 */
	private static int mod(int a, int b) {
		int i = a % b;
		if (i<0) i += b;
		return i;
	}
	  
}
 
 
 
