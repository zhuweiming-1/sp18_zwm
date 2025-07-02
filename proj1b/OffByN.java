public class OffByN implements CharacterComparator {
    private int n = 0;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == n || x - y == -n;
    }
}
