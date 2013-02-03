package lab9;

public class TreeNode<K,V> {

	K Key; // same as domain
	V Value; // same as range
	TreeNode<K,V> left;
	TreeNode<K,V> right;
	boolean present = true;


	public TreeNode(K key, V value, TreeNode<K,V> left, TreeNode<K,V> right){
		this.Key = key;
		this.Value = value;
		this.left = left;
		this.right = right;
	}

	
	/**
	 * 
	 * @param node
	 * @param count just keeps tally of the number of times this method is called recursively
	 * printTabs is a helper method that prints blank spaces
	 * @return 
	 */
	public String toStringInOrder(TreeNode<K,V> node, int count){
		if(node == null){
			return"";
		}
		else{
		return 	toStringInOrder(node.right, count+1)+ printTabs(count)+ "(" + node.Key + ", " + node.Value + ")" + "\n"+toStringInOrder(node.left, count+1);
			
		}
	}



	/**
	 * helper method for making indentation while printing tree
	 * @param n, supplied by toString method, increases by 1 each time the toString method is called
	 * @returns a space, (n-1) no of times 
	 */
	public String printTabs(int n){
		if (n == 0)
			return "";
		else
			return "        "+ printTabs(n-1);
	}
}

