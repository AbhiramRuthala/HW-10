package tree;
// Name: Abhiram Ruthala
// Computing ID: kas4kj@virginia.edu
// Homework Name: HW 10
// Resources used: ChatGPT-5 for Debugging, Claude Sonnet 4.5

/**
 * Self-balancing AVL Tree
 * @author CS 2100 Team
 *
 * @param <T>
 */
 
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{
	
	@Override
	public void insert(T data) {
		this.root = insert(data, this.root);
	}
	protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
		//TODO: Implement this method
        if(curNode == null){
            return new TreeNode<>(data);
        }

        int compare = curNode.data.compareTo(data);
        if(compare < 0){
            curNode.left = insert(data, curNode.left);
        } else if (compare > 0){
            curNode.right = insert(data, curNode.right);
        } else {
            return curNode;
        }

        curNode.height = Math.max(height(curNode.left), height(curNode.right)) + 1;
		return balance(curNode);
	}

	
	@Override
	public void remove(T data) {
		/* Call remove starting at the root of the tree */
		this.root = remove(data, this.root);
	}
	protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
		/* Call BST remove before balancing, use “super” to achieve this */
		curNode = super.remove(data,  curNode);
		
		/* Handle the case when remove returns null */
		if(curNode == null) return null;
		
		/* update the height of this node if necessary (if no change, that’s OK) */
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		
		/* rotate if necessary (call balance() method to balance the node) */
		curNode = this.balance(curNode);
		
		return curNode;
	}

	
	/**
	 * Balances the given node. Assumes it is the lowest unbalanced node if unbalanced
	 * @param node
	 * @return
	 */
	private TreeNode<T> balance(TreeNode<T> curNode) {
		//TODO: Implement this method
        if(curNode == null) return null;
        int leftSubTreeHeight = height(curNode.left);
        int rightSubTreeHeight = height(curNode.right);
        int sense = leftSubTreeHeight - rightSubTreeHeight;
		//return curNode.left == curNode.right ? curNode : balance(curNode.left);

        if(sense > 1) {
            if(curNode.left != null && height(curNode.left.left) >= height(curNode.left.right)) {
                return rotateRight(curNode);
            } else{
                curNode.left = rotateLeft(curNode.left);
                return rotateRight(curNode);

            }

        } else if (sense < -1) {
            if(curNode.right != null && height(curNode.right.right) >= height(curNode.right.left)) {
                return rotateLeft(curNode);
            } else {
                curNode.right = rotateRight(curNode.right);
                return rotateLeft(curNode);
            }

        } else {
            return curNode;
        }

        //gotta return the rotations.
	}
	
	private TreeNode<T> rotateRight(TreeNode<T> curNode) {
		//TODO: Implement this method
        if(curNode.left == null) return curNode;

        TreeNode<T> temp = curNode.left;
        TreeNode<T> right = temp.right;

        temp.right = curNode;
        curNode.left = right;

        temp.height = Math.max(height(temp.left), height(temp.right))+1;
        curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;

        return temp;
	}
	
	private TreeNode<T> rotateLeft(TreeNode<T> curNode){
		//TODO: Implement this method
        if(curNode.right == null) return curNode;

        TreeNode<T> temp = curNode.right;
        TreeNode<T> right = temp.left;

        temp.left = curNode;
        curNode.right = right;

        temp.height = Math.max(height(temp.left), height(temp.right))+1;
        curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;

        return temp;
	}
	
	private int balanceFactor(TreeNode<T> node) {
		//TODO: Implement this method
        if(node == null) return 0;
        int sense1 = height(node.left);
        int sense2 = height(node.right);
//        if(node.left != null) {
//            return 1 + balanceFactor(node.left);
//        }
//
//        if (node.right != null) {
//            return 1 + balanceFactor(node.right);
//        }

        return sense1 - sense2;


	}
	
	
}
