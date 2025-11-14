package tree;
//Name: Abhiram Ruthala
//Computing ID: kas4kj
//Homework Name: HW9-BST
//Resources Used: Google AI, ChatGPT 5 for Debugging, Claude Sonnet 4.5


public class BinaryTree<T> {

    protected TreeNode<T> root = null;

    /* Tree Traversal methods */

    //HINT for three traversal methods: you can call toString() on the node's
    //     data when you are executing the "root" command on a given node
    //     (e.g. curNode.data.toString() + " ";) // Note the space added at the end

    //inorder uses the logic of left -> root -> right. by simply using the recursive statements, we can add the data, and then call for the particular directions in which we need to traverse through.
    public String getInOrder() {
        return getInOrder(root); // call getInOrder starting at the root!
    }
    private String getInOrder(TreeNode<T> curNode) {
        //TODO: return the in order traversal of this tree, space separated

        if(curNode == null) {
            return "";
        }

        String result = "";
        //in-order: left -> root -> right
//        if(curNode.left != null && curNode.right != null) {
//            result += curNode.left.data.toString() + " " + curNode.data.toString() + " " + curNode.right.data.toString();
//        }

//        result += curNode.left.data.toString() + " ";
//        result += curNode.right.data.toString() + " ";


        result += getInOrder(curNode.right);

        result += curNode.data.toString() + " ";

        result += getInOrder(curNode.left);

        return result;
    }

    //preorder uses the logic of root -> left -> right. by simply using the recursive statements, we can add the data, and then call for the particular directions in which we need to traverse through.
    public String getPreOrder() {
        return getPreOrder(root); // call getPreOrder starting at the root!
    }
    private String getPreOrder(TreeNode<T> curNode) {
        // Pre-order: root -> left -> right
        //TODO: return the pre order traversal of this tree, space separated
        if(curNode == null) {
            return "";
        }

        String result = "";
        result += curNode.data.toString() + " ";

        result += getPreOrder(curNode.right);
        result += getPreOrder(curNode.left);

        return result;
    }


    //postorder uses the logic of left -> right -> root. by simply using the recursive statements, we can add the data, and then call for the particular directions in which we need to traverse through.
    public String getPostOrder() {
        return getPostOrder(root);  // call getPostOrder starting at the root!
    }
    private String getPostOrder(TreeNode<T> curNode) {
        // post-order: left -> right -> root
        //TODO: return the post order traversal of this tree, space separated
        if(curNode == null) {
            return "";
        }

        String result = "";

        result += getPostOrder(curNode.right);
        result += getPostOrder(curNode.left);
        result += curNode.data.toString() + " ";
//
//        result += curNode.left.data.toString() + " ";
//        if(curNode.left.right != null && curNode.left.left != null) {
//            result += curNode.left.right.data.toString() + " ";
//            curNode = curNode.left.left;
//        }

        return result;
    }


    //------------------------------------------------------------------------
    //EVERYTHING BELOW THIS POINT IS IMPLEMENTED FOR YOU
    //YOU SHOULD STILL LOOK AT THIS CODE
    //------------------------------------------------------------------------

    /* A somewhat more pretty print method for debugging */
    public void printTree() {
        printTree(this.root, 0);
        System.out.println("\n\n");
    }
    private void printTree(TreeNode<T> curNode, int indentLev) {
        if(curNode == null) return;
        for(int i=0; i<indentLev; i++) {
            if(i == indentLev - 1) System.out.print("|-----");
            else System.out.print("      ");
        }
        System.out.println(curNode.data);
        printTree(curNode.left, indentLev+1);
        printTree(curNode.right, indentLev+1);
    }

    //TODO: Look at these methods and think about how they might be useful for this assignment
    public int height() {
        return height(root);
    }

    /* Computes the height of the tree on the fly */
    protected int height(TreeNode<T> node) {
        if(node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
