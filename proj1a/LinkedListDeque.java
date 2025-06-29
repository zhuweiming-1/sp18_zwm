
public class LinkedListDeque<T> {
    private static class Node<T> {
        Node<T> prev = null;
        T value = null;
        Node<T> next = null;

        public Node() {
        }

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> sentinel = new Node<>();
    private int size = 0;

    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 将类型为 T 的元素添加到双端队列的前端。
     *
     * @param item
     */
    public void addFirst(T item) {
        Node<T> p = new Node<>();
        p.value = item;
        p.next = sentinel.next;
        p.next.prev = sentinel;
        p.prev = sentinel;
        sentinel.next = p;
        size++;
    }

    /**
     * 将类型为 T 的元素添加到双端队列的末端。
     *
     * @param item
     */
    public void addLast(T item) {
        Node<T> p = new Node<>();
        p.value = item;
        p.prev = sentinel.prev;
        p.prev.next = sentinel;
        p.next = sentinel;
        sentinel.prev = p;
        size++;
    }

    /**
     * 如果双端队列为空，返回 true；否则返回 false。
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回双端队列中的元素数量。
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * 从头到尾打印双端队列中的元素，元素之间用空格分隔。
     */
    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.value + " ");
        }
    }

    /**
     * 移除并返回双端队列前端的元素。如果不存在这样的元素，则返回 null。
     *
     * @return item
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size--;
        Node<T> p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        return p.value;
    }

    /**
     * 移除并返回双端队列末尾的元素。如果不存在该元素，则返回 null。
     *
     * @return item
     */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        size--;
        Node<T> p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        return p.value;
    }

    /**
     * 获取给定索引处的元素，其中 0 表示队首，1 表示下一个元素，依此类推。
     * 如果不存在该元素，则返回 null。不得修改双端队列！
     *
     * @param index
     * @return item
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int c = 0;
        Node<T> p = sentinel.next;
        while (c < index) {
            p = p.next;
        }
        return p.value;
    }

    /**
     * 与 get 相同，但使用递归。
     *
     * @param index
     * @return item
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> p, int index) {
        if (index == 0) {
            return p.value;
        }
        return getRecursiveHelper(p.next, index - 1);
    }
}
