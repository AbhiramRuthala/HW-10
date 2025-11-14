package tree;
// Name: Abhiram Ruthala
// Computing ID: kas4kj@virginia.edu
// Homework Name: HW 10
// Resources used:

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
        super.insert(data, curNode);
		return null;
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
            return rotateLeft(curNode);
        } else {
            return rotateRight(curNode);
        }

        //gotta return the rotations.
	}
	
	private TreeNode<T> rotateRight(TreeNode<T> curNode) {
		//TODO: Implement this method
        if(curNode.left == null) return curNode;
        if(curNode != null && curNode.left != null && curNode.left.left != null) {
            TreeNode<T> temp = curNode;
            TreeNode<T> temp2 = curNode.left.left;
            curNode = curNode.left;
            curNode.right = temp;
            curNode.left = temp2;

            return curNode;
        }
		return null;
	}
	
	private TreeNode<T> rotateLeft(TreeNode<T> curNode){
		//TODO: Implement this method
        if(curNode.right == null) return curNode;
        if(curNode != null && curNode.right != null && curNode.right.right != null) {
            TreeNode<T> temp = curNode;
            TreeNode<T> temp2 = curNode.right.right;
            curNode = curNode.right;
            curNode.left = temp;
            curNode.right = temp2;

            return curNode;
        }
        return null;
	}
	
	private int balanceFactor(TreeNode<T> node) {
		//TODO: Implement this method
        int sense1 = height(node.left);
        int sense2 = height(node.right);
        if(node.left != null) {
            return 1 + balanceFactor(node.left);
        }

        if (node.right != null) {
            return 1 + balanceFactor(node.right);
        }

        return sense1 - sense2;


	}
	
	
}
