package tree;
//Name: Abhiram Ruthala
//Computing ID: kas4kj
//Homework Name: HW9-BST
//Resources Used: Google AI, ChatGPT 5 for Debugging, Claude Sonnet 4.5


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T>{

    @Override
    public void insert(T data) {
        this.root = insert(data, root);
    }

    /**
     * Helper method for inserting recursively
     * @param data
     * @param curNode
     * @return a reference to the new root of the subtree
     */

    //insert function leverages the logic and checks to see if the curNode data is less than or greater than the data provided in the parameter. Based on that it runs a recursive statement until there are none left (getting to a null pointer) where then it will add a new node with the data value.
    protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        if(curNode == null) {
            return new TreeNode<>(data);
        }
        if(curNode.data.compareTo(data) < 0){
            curNode.left = insert(data, curNode.left);
        } else if (curNode.data.compareTo(data) > 0){
            curNode.right = insert(data, curNode.right);
        }
        return curNode;
    }

    /**
     * Returns a boolean (true of false) if the element was found in a BST
     */
    @Override
    public boolean find(T data) {
        return find(data, root);
    }

    // Helper method

    //find leverages a logic where it checks to see if the data is greater or smaller than the curNode data pointer, for which it will traverse either left or right. when it gets to a point where the data and the node are the same, it then returns true.
    private boolean find(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        if(curNode == null) {
            return false;
        }

        int compare = curNode.data.compareTo(data);

        if(compare == 0) {
            return true;
        } else if(compare < 0) {
            return find(data, curNode.left);
        } else {
            return find(data, curNode.right);
        }

//
//        if(curNode.data.compareTo(data) < 0){
//            curNode = curNode.left;
//            find(data, curNode);
//            if(curNode.data.compareTo(data) == 0){
//                return true;
//            } else {
//                return false;
//            }
//        } else if (curNode.data.compareTo(data) > 0){
//            curNode = curNode.right;
//            find(data, curNode);
//            if(curNode.data.compareTo(data) == 0){
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return false;
    }


    /**
     * Returns the max item in the given subtree
     */
    public T findMax() {
        return findMax(this.root);
    }

    // Helper method
    //find max finds the largest value - through testing we realized that this example is testing for an inverted tree, for which we'll have to travel in the leftmost direction to find the max value, which is differnt from the regular subtree for where we travel in the rightmost value as the higher values than the node are usually placed in the right side of the node.
    private T findMax(TreeNode<T> curNode) {
        //TODO: Implement this method
        if(curNode == null) {
            return null;
        }

        if (curNode.left == null) {
            return curNode.data;
        }
        return findMax(curNode.left);
    }

    //-----------------------------------------------------------------------------
    //THE METHOD BELOW IS PARTIALLY IMPLEMENTED    
    //YOU NEED TO COMPLETE THE IMPLEMENTATION
    //-----------------------------------------------------------------------------



    @Override
    public void remove(T data) {
        this.root = remove(data, root); // Call remove at the root of the tree
    }

    //remove leverages the logic of comparing the node and then traversing through it. for wherever the node needs to be removed, it returns the recursive case so that it actually chains away from the parent. it is written for multiple iterations below.
    protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
        /* Note the use of compareTo() in the solution! */

	// first check if the node to be inserted is null, if so return the node
        if(curNode == null) {
            return curNode;
        }


        // if item I want to remove is smaller than the data of the current node...
        else if (data.compareTo(curNode.data) < 0) {
            // recursively call remove on LEFT subtree
	    /* TODO: insert code here */
            curNode.left = remove(data, curNode.left);

        }
        // if item I want to remove is larger than the data of the current node...
        else if (data.compareTo(curNode.data) > 0) {
	    // recursively call remove on RIGHT subtree
	    /* TODO: insert code here */
            curNode.right = remove(data, curNode.right);
            
        }
	/* Found item to remove, time to remove */
        else { 
	    /* Case 1: node is a leaf, return null */
            if(curNode.left == null && curNode.right == null) {
                return null;
            }
            
	    /* Case 2A: else if node only has a left child, then return left child */
            if(curNode.left != null && curNode.right == null) {
                return curNode.left;
            }

	    /* Case 2B: else if node only has a right child, then return right child */
             else if(curNode.right != null && curNode.left == null) {
                return curNode.right;
            }
            /* Case 3: else (node has 2 children) so:           
               UNCOMMENT CODE BELOW AND PUT IN APPROPRIATE SPOT

                T toDel = findMax(curNode.left);  // find inorder predecessor (max of left child)
                curNode.data = toDel;		  // copy value from inorder predecessor to node
                curNode.left = remove(toDel, curNode.left);  // delete the inorder predecessor
                return curNode;
            */

            else {
                T toDel = findMax(curNode.left);  // find inorder predecessor (max of left child)
                curNode.data = toDel;		  // copy value from inorder predecessor to node
                curNode.left = remove(toDel, curNode.left);  // delete the inorder predecessor
                return curNode;
            }
        }
        return curNode;
    }


}
