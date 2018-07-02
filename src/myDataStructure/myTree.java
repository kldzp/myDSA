/*
 *               7
 *             /   \
 *           4       9
 *         /   \    /  \
 *        2     6  8    10
 *      /  \   /
 *     1    3 5
 */
package myDataStructure;

public class myTree {

	private int val;
	private myTree left;
	private myTree right;
	private myTree parent;
	private volatile static myTree myInstance;

	public static myTree getInstance() {
		if (myInstance == null) {
			synchronized (myTree.class) {
				if (myInstance == null) {
					myInstance = new myTree();
				}
			}
		}
		return (myTree) myInstance;
	}

	public static void main(String[] args) {
		myTree root = getInstance();
		root = root.initTree(root, 5);
		root = root.addNode(root, 1);
		boolean f= root.searchTree(root, 0);
		p(null);
	}

	public final myTree initTree(myTree m, int x) {
		m.val = x;
		return m;
	}

	public final myTree addNode(myTree m, int x) {
		// compare with the root value

		m.left = new myTree();
		m.left = initTree(m.left, x);
		return m;
	}

	public boolean searchTree(myTree root, int val) {
		boolean isExist = root.findNode(root, val);
		return isExist == true ? true : false;
	}
	public boolean findNode(myTree root, int val) {
		if (null == root) {
			return false ;
		}
		if (val > root.val) {
			return root.findNode( root.right,  val);
		} else if (val < root.val) {
			return root.findNode( root.left,  val);
		} else {
			return  true;
		}
	}

	public static void p(myTree root) {
		System.out.println(root.val);
	}
}
