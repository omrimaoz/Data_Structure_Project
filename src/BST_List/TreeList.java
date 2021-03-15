package BST_List;

/**
 *
 * Tree list
 *
 * An implementation of a Tree list with  key and info
 *
 */
 public class TreeList{

	/**fields */
	 AVLTree tree;
	/**constructor */
	 public TreeList() {
		 this.tree = new AVLTree();
	 }
	 
	 /**
   * public Item retrieve(int i)
   *
   * returns the item in the ith position if it exists in the list.
   * otherwise, returns null
   */

  public Item retrieve(int i)
  {
	  /**boundaries of the index */
	  if (this.tree.size() < i || i < 0){
		  return null;
	  }
	  /**findSelect to return node in ith position */
	  AVLTree.IAVLNode node = this.tree.findSelect(i+1);
	  return new Item(node.getKey(), node.getValue());
  }

  /**
   * public int insert(int i, int k, String s) 
   *
   * inserts an item to the ith position in list  with key k and  info s.
   * returns -1 if i<0 or i>n otherwise return 0.
   */
   public int insert(int i, int k, String s) {
	   /**boundaries of the index */
	   if (i < 0 || i > this.tree.size()) return -1;
	   AVLTree.IAVLNode node = this.tree.getRoot();
	   AVLTree.IAVLNode newNode = tree.new AVLNode(k, s, null);
	   if (node == null) {
		   this.tree.root = newNode;
		   return 0;
	   }
	   if (i == this.tree.size()) {
		   while (node.getRight() != null) {
			   node = node.getRight();
		   }
		   node.setRight(newNode);
		   newNode.setParent(node);
	   }
	   else {
		   /**find node with index i+1 */
		   node = tree.findSelect(i+1);
		   /**if there is no left child, add the new node as a left child */
		   if(node.getLeft() == null) {
			   node.setLeft(newNode);
			   newNode.setParent(node);
		   }
		   /**otherwise add the new node as the right child of its predecessor */
		   else {
			   node = node.getLeft();
			   while (node.getRight() != null) {
				   node = node.getRight();
			   }
			   node.setRight(newNode);
			   newNode.setParent(node);
		   }
	   }
	   /**update necessary fields and keep legal AVLTree */
	   tree.rotate(newNode);
	   return 0;
   }

  /**
   * public int delete(int i)
   *
   * deletes an item in the ith posittion from the list.
	* returns -1 if i<0 or i>n-1 otherwise returns 0.
   */

   public int delete(int i) {
	   /**boundaries of the index */
	   if (i<0 || i > this.tree.size())
		   return -1;
	   else {
		   /**findSelect to return node in ith position */
		   AVLTree.IAVLNode node = tree.deleteNode(tree.findSelect(i+1));
		   /**update necessary fields and keep legal AVLTree   */
		   tree.rotate(node);
   		}
	   return 0;
   }
 }
   
   