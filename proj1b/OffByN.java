public class OffByN implements CharacterComparator {
    private int target = 0;
    public OffByN(int N) {
        this.target = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == target) {
            return true;
        }
        return false;
    }
}
