package BST_List;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 *
 */

public class AVLTree {
	
	/**fields */
	IAVLNode root;
	
	/**constructors */
	public AVLTree() {
        this.root = null;
	}
	public AVLTree(AVLNode node) {
        this.root= node;
	}
	
  /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
  public boolean empty() {
	  if (this.root == null)
		  return true;
	return false; 
  }

 /**
   * public String search(int k)
   *
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   */
  private String search_rec(IAVLNode node,int k) { /** recursion function */
	  
	  if (node==null) /** node doesn't exist in the tree */
		  return null;
	  if (node.getKey() == k) /** node exist in the tree */
		  return node.getValue();
	  if (k < node.getKey()) { /** binary search */
			 return search_rec(node.getLeft(),k);
	  }
	  return search_rec(node.getRight(),k);
  }

  public String search(int k)
  {
	  return search_rec(this.root,k);  /** recursion function call */
  }

  /**
   * public int insert(int k, String i)
   *
   * inserts an item with key k and info i to the AVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   */
  
  private int insert_rec(IAVLNode node,int k,String i) { /**recursion function */
	  if (node.getKey() == k) /** node already exist in the tree */
		  return -1;
	  if (k < node.getKey()) {
		  if(node.getLeft()==null) {
			  node.setLeft(new AVLNode(k,i,node)); /** insert new node as leaf as left child */
			  return rotate(node); /**update necessary fields and keep legal AVLTree */

		  }
		  return insert_rec(node.getLeft(), k, i);
	  }
	  if(node.getRight()==null) {
		  node.setRight(new AVLNode(k,i,node)); /** insert new node as leaf as right child */
		  return rotate(node); /**update necessary fields and keep legal AVLTree */
	  	}
	  return insert_rec(node.getRight(), k, i);
	  }
  
   public int insert(int k, String i) {
	  if (empty()) { /** if empty, insert immediately */
		  this.root = new AVLNode(k,i,null);
		  return 0;
	  }
	  return insert_rec(this.root, k, i); /**recursion function call */
   }

  /**
   * public int delete(int k)
   *
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * returns -1 if an item with key k was not found in the tree.
   */
   public IAVLNode deleteNode(IAVLNode node) { /** function to delete a node from a BST and keep legal BTS */
	  IAVLNode node1 = new AVLNode();
	  IAVLNode node2 = new AVLNode();
	  IAVLNode node3 = new AVLNode();
	  node1 = node.getParent();
	  
	  if (node.getLeft()==null && node.getRight()==null) { /** node to delete is a leaf */
		  if(node1!=null)
			  if (node == node1.getRight())
				  node1.setRight(null);
			  else
				  node1.setLeft(null);
		  else 
			  setRoot(null);
		  return node1;
	  }
	  if (node.getLeft()!=null && node.getRight()==null) { /**node to delete has one child on the left */
		  if(node1!=null) 
			  if (node == node1.getRight())
				  node1.setRight(node.getLeft());
			  else
				  node1.setLeft(node.getLeft());
		  else 
			  setRoot(node.getLeft());
		  return node1; 	  
	  }
	  if (node.getLeft()==null && node.getRight()!=null) { /**node to delete has one child on the right */
		  if(node1!=null) 
			  if (node == node1.getRight())
				  node1.setRight(node.getRight());
			  else
				  node1.setLeft(node.getRight());
		  else 
			  setRoot(node.getRight());		
		  return node1; 	  
	  }
	  /** node to delete has left and right children */
	  /** search for the node with the first bigger key */
	  node2 = node.getRight();
	  if (node2.getLeft()!=null) {
		  while (node2.getLeft()!=null) {
			  node2= node2.getLeft();
		  }
		  /**change nodes pointers */
		  node3 = node2.getParent();
		  node3.setLeft(node2.getRight());
		  node2.setRight(node.getRight());
		  while (node3!=node2) {
			  updateHeight(node3);
			  node3=node3.getParent();
		  }
		  node3=node2.getParent();/**the node to return and start rotate from there */
	  }
	  else
		  node3=node2; /**the node to return and start rotate from there */
	  node2.setLeft(node.getLeft());
	  updateHeight(node2);
	  if(node1!=null)
		  if (node == node1.getRight()) 
			  node1.setRight(node2);
		  else
			  node1.setLeft(node2);
	  else 
		  	setRoot(node2);
	  return node3; 	    
  }
   
   private int delete_rec(IAVLNode node,int k) { /**recursion function */
	  if (node.getKey() == k) { /** found node to delete */
		  IAVLNode node1 = deleteNode(node); /** call BST delete function */
		  if (node1==null)
			  return 0;
		  return rotate(node1); /**update necessary fields and keep legal AVLTree */
	  }
	  if (k < node.getKey()) { /** binary search */
		  if(node.getLeft()==null)
			  return -1;
		  return delete_rec(node.getLeft(), k);
	  }
	  if(node.getRight()==null)
		  return -1;
	  return delete_rec(node.getRight(), k);
	}
   
   public int delete(int k)
   {
	   if (empty()) { /** if empty node doesn't exist */
			  return -1;
	   }
	   return delete_rec(this.root, k); /**recursion function call */
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty
    */
   public String min() {
	   IAVLNode node = this.root;
	   if (node == null) return null;
	   while (node.getLeft() != null) { /**smallest key found on the most left leaf */
		   node = node.getLeft();
	   }
	   return node.getValue();   
	}

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty
    */
   public String max()
   {
	   IAVLNode node = this.root;
	   if (node == null) return null;
	   while (node.getRight() != null) {/**biggest key found on the most right leaf */
		   node = node.getRight();
	   }
	   return node.getValue();   
   }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
   static int keys_rec_idx = 0; /**static arguments to access Array */

   public void keysToArrayRec(int[] array, IAVLNode node) {/**recursion function */
	   	/** in-order search */
		if (node == null) return;
		keysToArrayRec(array, node.getLeft());
		array[keys_rec_idx] = node.getKey();
		keys_rec_idx++;
		keysToArrayRec(array, node.getRight());
   }

  public int[] keysToArray()
  {
	  int[] arr = new int[this.size()];
		IAVLNode node = this.root;
		if (node == null) return arr; /** empty tree */
		keysToArrayRec(arr, this.root); /**recursion function call */
		keys_rec_idx=0; 
		return arr;             
  }

  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  
  static int info_rec_idx = 0; /**static arguments to access Array */

  public void infoToArrayRec(String[] array, IAVLNode node) { /**recursion function */
	/** in-order search */
	  if (node == null) return;
	  infoToArrayRec(array, node.getLeft());
	  array[info_rec_idx] = node.getValue();
	  info_rec_idx++;
	  infoToArrayRec(array, node.getRight());
  }
  
  public String[] infoToArray()
  {
      String[] arr = new String[this.size()];
      IAVLNode node = this.root;
      if (node == null) return arr; /** empty tree */
      infoToArrayRec(arr, this.root); /**recursion function call */
      info_rec_idx=0;
      return arr;               
  }

   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    *
    * precondition: none
    * postcondition: none
    */
   public int size()
   {
	   if (this.root ==null) 
		   return 0;
	   return ((AVLNode)this.root).getSize();
   }
   
     /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    *
    * precondition: none
    * postcondition: none
    */
   public IAVLNode getRoot()
   {
	   return this.root;
   }


   /**
    * Supporting Functions
    *
    * setRoot(IAVLNode node)
    * updateHeight(IAVLNode node)
    * updateSize(IAVLNode node)
    * find_bf(IAVLNode node)
    * findSelect(int i)
    * findRank(IAVLNode node)
    * rotate(IAVLNode node,boolean is_insert)
    */
   
	/** setRoot function for new root in the tree */
	public void setRoot(IAVLNode node)
	{
		this.root = node;
		if (node == null) {
			return;
		}
		node.setParent(null); 
		return ;
	}
	
   /** update Height of node and return nothing */
   private void updateHeight(IAVLNode node) {
 	  int hr=0,hl=0;
 	  if(node.getLeft()!=null)
 		  hl = node.getLeft().getHeight();
 	  else
 		  hl=-1;
 	  if(node.getRight()!=null)
 		  hr = node.getRight().getHeight();
 	  else
 		  hr=-1;
 	  node.setHeight(Math.max(hl,hr)+1);
   }
   /** update Size of v and return nothing */
   private void updateSize(IAVLNode node) {
 	  int sr=0,sl=0;
 	  if(node.getLeft()!=null)
 		  sl = ((AVLNode)node.getLeft()).getSize();
 	  if(node.getRight()!=null)
 		  sr = ((AVLNode)node.getRight()).getSize();
 	  ((AVLNode)node).setSize(sr+sl+1);
   }
   
   /** return the BF(v) */
   private int findBF(IAVLNode node) {
 	  int hr=0,hl=0;
 	  if(node.getLeft()!=null)
 		  hl = node.getLeft().getHeight();
 	  else
 		  hl=-1;
 	  if(node.getRight()!=null)
 		  hr = node.getRight().getHeight();
 	  else
 		  hr=-1;
 	  return hl-hr;
   }
   
   /**return the node of a selected index */
   /** index in boundaries was checked before calling the function */
   public IAVLNode findSelect(int i) {
 	  int select=0; /** set counter */
 	  IAVLNode node = this.root;
 	  if (node.getLeft()!=null) 
 		  select = ((AVLNode)node.getLeft()).getSize() + 1;
 	  else
 		  select =1; /**for  most left leaf in the tree */
 	  while (select!=i){ /**binary search */
 		  if (i<select) {
 			  select -= (((AVLNode)node.getLeft()).getSize() + 1);
 			  node=node.getLeft();
 		  }else
 			  node=node.getRight();
 		  if (node.getLeft()!=null) 
 			  select += ((AVLNode)node.getLeft()).getSize() + 1;
 		  else
 			  select += 1;
 	  }
 	  return node;
  }
   
   /**return the Rank of a given node */
   /** node in the tree was checked before calling the function */
  public int findRank(IAVLNode node) {
 	  int rank=0;
 	  if (node.getLeft()!=null)
 		  rank = ((AVLNode)node.getLeft()).getSize() +1;
 	  else
 		  rank = 1; /**for  most left leaf in the tree */
 	  while (node.getParent()!=null){ /**binary search */
 		  if(node == node.getParent().getRight()) {
 			  node=node.getParent();
 			  if (node.getLeft()!=null)
 				  rank += ((AVLNode)node.getLeft()).getSize() +1;
 			  else
 				  rank +=1;
 		  }
 		  else
 			  node=node.getParent();
 	  }
 	  return rank;
  }
  
   /**Rotate Function */
   public int rotate(IAVLNode node) {
 	  int bf=0,count=0;
 	  IAVLNode node1 = new AVLNode();
 	  IAVLNode node2 = new AVLNode();
 	  IAVLNode node3 = new AVLNode();
 	  while (node!=null){ /** loop from starter node to the root */
 		  updateHeight(node); /**update node height */
 		  updateSize(node); /**update node size */
 		  bf = findBF(node); /**check node BF */
 		  if(bf<-1 || 1<bf) {/** perform a rotation */
 			  if (bf==2) { /**rotate right or left then right */
 				  if (findBF(node.getLeft())>-1) { /** rotate right */
 					  node1 = node; /** biggest key from the 3 nodes */
 					  node2 = node.getLeft();
 					  /**rotate */
 					  node1.setLeft(node2.getRight());
 					  if(node1.getParent()!=null)
 						  if (node1 == node1.getParent().getLeft())
 						  	node1.getParent().setLeft(node2);
 						  else
 							node1.getParent().setRight(node2);
 					  else
 						  setRoot(node2);
 					  node2.setRight(node1);
 					  count++;
 					  /**update fields after rotation */
 					  updateHeight(node1);
 					  updateSize(node1);
 					  updateHeight(node2);
 					  updateSize(node2);
 				  }else { /** rotate left then right */
 					  node1 = node; /** biggest key from the 3 nodes */
 					  node2 = node.getLeft().getRight();
 					  node3 = node.getLeft();/**smallest key from the 3 nodes */
 					  /**rotate */
 					  node3.setRight(node2.getLeft());
 					  if(node1.getParent()!=null)
 						  if (node1 == node1.getParent().getLeft())
 						  	node1.getParent().setLeft(node2);
 						  else
 							node1.getParent().setRight(node2);
 					  else
 						  setRoot(node2);
 					  node2.setLeft(node3);
 					  node1.setLeft(node2.getRight());
 					  node2.setRight(node1);
 					  count+=2;
 					  /**update fields after rotation */
 					  updateHeight(node3);
 					  updateSize(node3);
 					  updateHeight(node1);
 					  updateSize(node1);
 					  updateHeight(node2);
 					  updateSize(node2);
 				  }
 			  }else { /**rotate left or right then left */
 				  if (findBF(node.getRight())<0) { /** rotate left */
 					  node1 = node; /** biggest key from the 3 nodes */
 					  node2 = node.getRight();
 					  /**rotate */
 					  node1.setRight(node2.getLeft());
 					  if(node1.getParent()!=null)
 						  if (node1 == node1.getParent().getRight())
 						  	node1.getParent().setRight(node2);
 						  else
 							node1.getParent().setLeft(node2);
 					  else
 						  setRoot(node2);
 					  node2.setLeft(node1);
 					  count++;
 					  /**update fields after rotation */
 					  updateHeight(node1);
 					  updateSize(node1);
 					  updateHeight(node2);
 					  updateSize(node2);
 				  }else { /** rotate right then left */
 					  node1 = node; /** smallest key from the 3 nodes */
 					  node2 = node.getRight().getLeft();
 					  node3 = node.getRight(); /**biggest key from the 3 nodes */
 					  /**rotate */
 					  node3.setLeft(node2.getRight());
 					  if(node1.getParent()!=null)
 						  if (node1 == node1.getParent().getRight())
 						  	node1.getParent().setRight(node2);
 						  else
 							node1.getParent().setLeft(node2);
 					  else 
 						  	setRoot(node2);					  
 					  node2.setRight(node3);
 					  node1.setRight(node2.getLeft());
 					  node2.setLeft(node1);
 					  count+=2;
 					  /**update fields after rotation */
 					  updateHeight(node3);
 					  updateSize(node3);
 					  updateHeight(node1);
 					  updateSize(node1);
 					  updateHeight(node2);
 					  updateSize(node2);
 				  }
 			  }
 		  }
 		  node = node.getParent();
 	  }
 	  return count;
   }
   
	/**
	   * public interface IAVLNode
	   * ! Do not delete or modify this - otherwise all tests will fail !
	   */
	public interface IAVLNode{	
		public int getKey(); /** returns node's key */
		public String getValue(); /**returns node's value [info] */
		public void setLeft(IAVLNode node); /**sets left child */
		public IAVLNode getLeft(); /**returns left child (if there is no left child return null) */
		public void setRight(IAVLNode node); /**sets right child */
		public IAVLNode getRight(); /**returns right child (if there is no right child return null) */
		public void setParent(IAVLNode node); /**sets parent */
		public IAVLNode getParent(); /**returns the parent (if there is no parent return null) */
    	public void setHeight(int height); /** sets the height of the node */
    	public int getHeight(); /** Returns the height of the node */
	}

   /**
   * public class AVLNode
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in 
   * another file.
   * This class can and must be modified.
   * (It must implement IAVLNode)
   */
  public class AVLNode implements IAVLNode{
	  	/**fields */
	  	int key;
	  	String value;
	  	int height;
	  	int size;
	  	IAVLNode right = null;
	  	IAVLNode left = null;
	  	IAVLNode parent = null;
	  	

	  	/** constructors */
	  	public AVLNode() {
	        this.key = 0;
	        this.value = null;
	        this.height = 0;
	        this.size = 1;
	        this.right = null;
	        this.left = null;
	        this.parent = null;
	  	}
	  	public AVLNode(int key, String value, IAVLNode parent) {
	  		this(); /** default constructor */
	  		this.key = key;
	        this.value = value;
	        this.parent = parent;
	  	}
	  	public int getKey()
		{
			return this.key;
		}
		
		public String getValue()
		{
			return this.value;
		}
		
		public void setLeft(IAVLNode node)
		{
			this.left = node;
			if (node == null) {
				return;
			}
			node.setParent(this);
			return ;
		}
		
		public IAVLNode getLeft()
		{
			return this.left;
		}
		
		public void setRight(IAVLNode node)
		{
			this.right = node;
			if (node == null) {
				return;
			}
			node.setParent(this);
			return ;
		}
		
		public IAVLNode getRight()
		{
			return this.right; 
		}
		
		public void setParent(IAVLNode node)
		{
			this.parent = node;
			return ;
		}
		
		public IAVLNode getParent()
		{
			return this.parent;
		}

		public void setHeight(int height)
		{
			this.height = height;
			return ; 
		}
		public int getHeight()
		{
			return this.height;
		}
		public int getSize()
		{
			return this.size;
		}
		public void setSize(int size)
		{
			this.size = size;
			return ; 
		}
			

  }

}




