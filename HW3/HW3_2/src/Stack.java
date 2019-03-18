public class Stack<T> {
    /**
     * @param <T> type parameter
     */
    static class Node<T> {
        Node next = null;
        T data;

        Node(T d) {
            data = d;
        }
    }

    private Node<T> head = null;

    /**
     * @return boolean true if stack is empty
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * @return check top element in the stack but not remove it
     */
    public T peek() {
        return head.data;
    }

    /**
     * @return top element in the stack and remove it
     */
    public T pop() {
        Node<T> tmp = head;
        head = head.next;
        return tmp.data;
    }

    /**
     * @param d add element to the stack
     * @return added element
     */
    public T push(T d) {
        Node<T> el = new Node<T>(d);
        Node<T> tmp = head;
        head = el;
        head.next = tmp;
        return el.data;
    }
}
