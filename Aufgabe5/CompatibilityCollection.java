import java.util.Iterator;

public interface CompatibilityCollection<X extends Compatible, Y> extends Iterable<X>{
    void addX(X x);

    void addY(Y y);

    Iterator<X> iterator();

    Iterator<Y> iterator(X x);
}
