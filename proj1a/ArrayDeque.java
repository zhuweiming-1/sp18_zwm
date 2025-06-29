public class ArrayDeque<T> {
    private T[] items;
    private int size = 0;
    private final int initCapacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[initCapacity];
    }

    /**
     * 调整ArrayDeque的容量。
     *
     * @param capacity
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    private void adjustCapacity() {
        double usage = size * 1.0 / items.length;
        double threshold = 0.25;
        int expansionFactor = 2;
        if (items.length > initCapacity && usage <= threshold) {
            int capacity = (int) Math.floor(items.length * threshold);
            resize(capacity);
        } else if (size == items.length) {
            resize(size * expansionFactor);
        }
    }

    /**
     * 将类型为 T 的元素添加到双端队列的前端。
     *
     * @param item
     */
    public void addFirst(T item) {
        adjustCapacity();
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = item;
        size++;
    }

    /**
     * 将类型为 T 的元素添加到双端队列的末端。
     *
     * @param item
     */
    public void addLast(T item) {
        adjustCapacity();
        items[size++] = item;
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
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    /**
     * 移除并返回双端队列前端的元素。如果不存在这样的元素，则返回 null。
     *
     * @return item
     */
    public T removeFirst() {
        adjustCapacity();
        if (isEmpty()) {
            return null;
        }
        T item = items[0];
        for (int i = 0; i < size; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return item;
    }

    /**
     * 移除并返回双端队列末尾的元素。如果不存在该元素，则返回 null。
     *
     * @return item
     */
    public T removeLast() {
        adjustCapacity();
        if (isEmpty()) {
            return null;
        }
        T item = items[--size];
        items[size] = null;
        return item;
    }

    /**
     * 获取给定索引处的元素，其中 0 表示队首，1 表示下一个元素，依此类推。如果不存在该元素，则返回 null。不得修改双端队列！
     *
     * @param index
     * @return item
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }
}
