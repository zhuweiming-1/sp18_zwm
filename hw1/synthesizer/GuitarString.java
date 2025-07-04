package synthesizer;


//Make sure this class is public
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday.
     */
    private static final int SR = 44100;      // 采样率
    private static final double DECAY = .996; // 能量衰减因子

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* 创建给定频率的吉他弦。  */
    public GuitarString(double frequency) {
        // Create a buffer with capacity = SR / frequency.
        /* 您需要
        将此除法作的结果转换为 int。为了更好
        accuracy 时，请在强制转换前使用 Math.round（） 函数。
        缓冲区最初应填充零。*/
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /* 通过将缓冲区替换为白噪声来拨动吉他弦。 */
    public void pluck() {
        // 将缓冲区中的所有内容取消排队，并将其替换为 -0.5 到 0.5 之间的随机数。您可以使用以下方法获取此类数字：
        // double r = Math.random() - 0.5;
        // 确保您的随机数彼此不同。
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* 通过执行
     * Karplus-Strong 算法。
     */
    public void tic() {
        // 将前面的样本取消排队，并加入新样本的队列，该样本是两者的平均值乘以 DECAY 因子。
        // 请勿调用 StdAudio.play（）。
        Double sound = buffer.dequeue();
        buffer.enqueue((sound + buffer.peek()) * DECAY / 2);

    }

    /* 返回缓冲区前面的 double。 */
    public double sample() {
        // 返回正确的内容。
        return buffer.peek();
    }
}
