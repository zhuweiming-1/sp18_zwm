package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /**
     * 返回缓冲区的大小
     *
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * 返回缓冲区中的填充数量
     *
     * @return fillCount
     */
    public int fillCount() {
        return fillCount;
    }
}
