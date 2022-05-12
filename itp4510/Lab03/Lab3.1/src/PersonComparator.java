public class PersonComparator implements Comparator {

    public boolean isEqualTo(Object item1, Object item2) {
        return (((Person) item1).getAge()==((Person) item2).getAge());
    }

    
    public boolean isLessThan(Object item1, Object item2) {
        return (((Person) item1).getAge()<((Person) item2).getAge());
    }

    
    public boolean isLessThanOrEqualTo(Object item1, Object item2) {
        return(isLessThan(item1, item2) || isEqualTo(item1, item2));

    }

    
    public boolean isGreaterThan(Object item1, Object item2) {
        return (((Person) item1).getAge()>((Person) item2).getAge());
    }

    
    public boolean isGreaterThanOrEqualTo(Object item1, Object item2) {
        return (isGreaterThan(item1, item2) || isEqualTo(item1, item2));
    }

}
