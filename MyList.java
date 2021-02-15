package iss.java.list;

/**
 * Created by wenke on 2016/9/16.
 * // TODO: Modify this class to meet requirement B and C.
 */
public class MyList {
    // two guards. Do not call remove() on them!!
    private Node head;
    private Node tail;
    private int size;

    public MyList() {
        head = new Node().setData(0);
        tail = new Node().setData(0).setNext(null).setPrev(head);
        head.setNext(tail);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
    public boolean Check(Node _prev){
    	while(_prev!=null){
    		if(_prev==head||_prev==tail){
    		return true;
    		}
    		else{
    			_prev=_prev.getPrev();
    		}
    	}

    	return false;
    }

    /**
     * Insert a node with <pre>data</pre> after <pre>_prev</pre>.
     *
     * @param _prev
     * @param data
     * @return The node just inserted.
     */
    public synchronized Node insert(Node _prev, int data) {
    	if(Check(_prev)){
        Node node = new Node().setData(data).setNext(_prev.getNext()).setPrev(_prev);
        _prev.getNext().setPrev(node);
        _prev.setNext(node);
        ++size;
        return node;
        }
    	else{
    		System.out.println("插入失败，请输入本链表的节点");
    		return null;
    	}
    }

    /**
     * Remove the node <pre>target</pre>.
     *
     * @param target The node to remove.
     * @return the previous node of target.
     */
    public synchronized Node remove(Node target) {
        if (target == head || target == tail)
            throw new RuntimeException("DO NOT remove the head or tail node. They are guards.");
        // shortcut "target"
        if(Check(target)){
        Node prev = target.getPrev();
        Node next = target.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        --size;

        // Unlike C/C++, the memory of "target" is automatically recycled by GC
        // return the previous one because it is quite likely to insert a new node after prev;
        return prev;
        }
        else{
        	System.out.println("删除失败，请输入本链表的节点");
        	return null;
        }
    }

}
