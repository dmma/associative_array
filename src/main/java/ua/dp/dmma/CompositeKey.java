package ua.dp.dmma;

/**
 * @author dmma
 */
public class CompositeKey implements Comparable<CompositeKey> {
    private int firstPart;
    private int secondPart;
    private int thirdPart;

    public CompositeKey(int firstPart, int secondPart, int thirdPart) {
        this.firstPart = firstPart;
        this.secondPart = secondPart;
        this.thirdPart = thirdPart;
    }

    @Override
    public int compareTo(CompositeKey o) {
        return Integer.compare(this.firstPart, o.firstPart) != 0 ?
                Integer.compare(this.firstPart, o.firstPart) :
                Integer.compare(this.secondPart, o.secondPart) != 0 ?
                        Integer.compare(this.secondPart, o.secondPart) :
                        Integer.compare(this.thirdPart, o.thirdPart);
    }

    @Override
    public String toString() {
        return "CompositeKey{" + firstPart + ", " + secondPart + ", " + thirdPart + '}';
    }
}
