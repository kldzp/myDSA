package myDataStructure;

class myLinkedList {
	int val;
	myLinkedList next;
	myLinkedList pre;
	public static myLinkedList mLinkedList;

	// get instance of myLinkedList
	public static myLinkedList getInstance() {
		if (mLinkedList == null) {
			synchronized (myLinkedList.class) {
				if (mLinkedList == null) {
					mLinkedList = new myLinkedList();
				}
			}
		}
		return mLinkedList;
	}

	public static void main(String[] args) {
		int[] x = { 1, 2 };

		System.out.println("==============");

		myLinkedList head = getInstance();
		head = head.insert(head, x);
		head = head.reverse(head);
		head.printList(head);

		System.out.println("test");
		System.exit(0);
	}

	/*
	 * print the element of list
	 */
	public void printList(myLinkedList head) {
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	/*
	 * the method reversing the double LinkedList
	 * 
	 * @param head the LinkedList to be reversed
	 */
	private myLinkedList reverse(myLinkedList head) {
		if (head == null)
			return null;
		myLinkedList q = head;
		myLinkedList p = head.next;

		while (p != null) {
			p.pre = p.next;
			p.next = q;
			q = p;
			p = p.pre;
		}
		head.pre = head.next;
		head.next = null;
		return q;
	}

	/*
	 * constructor
	 */
	public myLinkedList() {
	}

	public myLinkedList(int x) {
		val = x;
	}

	/*
	 * 
	 * @param head the LinkedList to be inserted
	 * 
	 * @param x the insert value for LinkedList
	 */
	public myLinkedList insert(myLinkedList head, int x[]) {
		myLinkedList result = head;
		if (head == null || x == null) {
			return null;
		}
		while (head.next != null) {
			head = head.next;
		}
		for (int i : x) {
			head.next = new myLinkedList(i);
			head.next.pre = head;
			head = head.next;
		}
		return result;

	}

	/*
	 * @param head the LinkedList to be inserted
	 * 
	 * @param x the insert value for LinkedList
	 */
	public myLinkedList insert(myLinkedList head, int x) {
		myLinkedList result = head;
		if (head == null) {
			return null;
		}
		while (head.next != null) {
			head = head.next;
		}
		head.next = new myLinkedList(x);
		head.next.pre = head;
		return result;
	}

}