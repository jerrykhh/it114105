public interface Comparator {
    public abstract boolean isEqualTo(Object item1, Object item2);
    public abstract boolean isLessThan(Object item1, Object item2);
    public abstract boolean isLessThanOrEqualTo(Object item1, Object item2);
    public abstract boolean isGreaterThan(Object item1, Object item2);
    public abstract boolean isGreaterThanOrEqualTo(Object item1, Object item2);
}