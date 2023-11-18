import java.util.Iterator;

public class GeneralCatalog<X extends Compatible<? super X>> implements CompatibilityCollection<X, X> {
    MyLinkedList<X> xs;
    MyLinkedList<X> ys;

    /**
     * VORBEDINGUNG:
     * INVARIANTE:
     * NACHBEDINGUNG: Objekt is Valide
     */
    GeneralCatalog() {
        xs = new MyLinkedList<X>();
        ys = new MyLinkedList<X>();
    }

    /**
     * VORBEDINGUNG: x ist nicht null
     * INVARIANTE: Ys bleibt unbeinflusst
     * NACHBEDINGUNG: Es ist mindestens ein x in xs.
     */
    @Override
    public void addX(X x) {
        xs.add(x);
    }

    /**
     * VORBEDINGUNG: y ist nicht null
     * INVARIANTE: Xs bleibt unbeeinflusst
     * NACHBEDINGUNG: Es ist mindestens ein y in ys.
     */
    @Override
    public void addY(X y) {
        ys.add(y);
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Das Objekt bleibt unberührt.
     * NACHBEDINGUNG: Iterator der alle xs enthält wird zurückgegeben.
     */
    @Override
    public Iterator<X> iterator() {
        return new MyXIterator<X>(xs);
    }

    /**
     * VORBEDINGUNG: x ist nicht null
     * INVARIANTE: Das Objekt bleibt unberührt.
     * NACHBEDINGUNG: Iterator der alle zu x compatiblen ys enthält wird zurückgegeben.
     */
    @Override
    public Iterator<X> iterator(X x) {
        return new MyYIterator<X>(x, ys);
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Das Objekt bleibt unberührt.
     * NACHBEDINGUNG: Iterator der alle zu x compatiblen ys enthält wird zurückgegeben.
     */
    public Iterator<X> generalIterator() {
        return new GeneralIterator<X>(xs, ys);
    }
}

class MyYIterator<T extends Compatible<? super T> > implements Iterator<T> {
    MyLinkedList<T> ys;
    MyLinkedListNode<T> last;
    MyLinkedListNode<T> nxt;
    T x;

    /**
     * VORBEDINGUNG: weder x noch ys sind null
     * INVARIANTE:
     * NACHBEDINGUNG: Objekt valide.
     */
    MyYIterator(T x, MyLinkedList<T> ys) {
        this.x = x;
        this.ys = ys;
        this.last = null;
        this.nxt = ys.back;
        while ((nxt != null) && (!nxt.value.compatible(x))) { nxt = nxt.next; }
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Der Zustand des Objektes.
     * NACHBEDINGUNG: -
     */
    @Override
    public boolean hasNext() {
        return nxt != null;
    }

    /**
     * VORBEDINGUNG: hasNext() liefert true
     * INVARIANTE: Der Zustand des Objektes.
     * NACHBEDINGUNG: Gibt ein noch nicht zurückgegebenes und zu x compatibles Objekt zurück.
     */
    @Override
    public T next() {
        last = nxt;
        nxt = nxt.next();
        while ((nxt != null) && (!nxt.value.compatible(x))) { nxt = nxt.next; }
        return last.value;
    }

    /**
     * VORBEDINGUNG: Es wurde zuvor next() aufgerufen.
     * INVARIANTE: -
     * NACHBEDINGUNG: Das zuvor mit next() zurückgegebene Objekt ist nichtmehr im ys speicher.
     */
    @Override
    public void remove() {
        ys.remove(last.value);
    }
}

class MyXIterator<T> implements Iterator<T> {
    MyLinkedList<T> xs;
    MyLinkedListNode<T> last;
    MyLinkedListNode<T> nxt;

    /**
     * VORBEDINGUNG: xs ist nicht null
     * INVARIANTE:
     * NACHBEDINGUNG: Objekt ist valide
     */
    MyXIterator(MyLinkedList<T> xs) {
        this.xs = xs;
        this.last = null;
        this.nxt = xs.back;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: Gibt zurück ob es ein nächstes Objekt gibt.
     */
    @Override
    public boolean hasNext() {
        return nxt != null;
    }

    /**
     * VORBEDINGUNG: hasNext() gibt true zurück.
     * INVARIANTE: -
     * NACHBEDINGUNG: Gibt ein noch nicht zurückgegebenes Objekt zurück.
     */
    @Override
    public T next() {
        last = nxt;
        nxt = nxt.next;
        return last.value;
    }

    /**
     * VORBEDINGUNG: Es wurde zuvor next() aufgerufen.
     * INVARIANTE: -
     * NACHBEDINGUNG: Das zuvor mit next() zurückgegebene Objekt ist nichtmehr im ys speicher.
     */
    @Override
    public void remove() {
        xs.remove(last.value);
    }
}

class GeneralIterator<T> implements Iterator<T> {
    MyLinkedList<T> xs;
    MyLinkedList<T> ys;
    MyLinkedList<T> combined;
    MyLinkedListNode<T> nxt;

    /**
     * VORBEDINGUNG: xs und ys sind nicht null.
     * INVARIANTE:
     * NACHBEDINGUNG: Objekt ist valide
     */
    GeneralIterator(MyLinkedList<T> xs, MyLinkedList<T> ys) {
        this.combined = new MyLinkedList<T>();
        this.xs = xs;
        this.combined.add(xs);
        this.ys = ys;
        this.combined.add(ys);
        this.nxt = combined.back;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: Gibt zurück ob es ein nächstes Objekt gibt.
     */
    @Override
    public boolean hasNext() {
        return nxt != null;
    }

    /**
     * VORBEDINGUNG: hasNext() gibt true zurück.
     * INVARIANTE:
     * NACHBEDINGUNG: Gibt ein noch nicht zurückgegebenes Objekt zurück.
     */
    @Override
    public T next() {
        MyLinkedListNode<T> t = nxt;
        nxt = nxt.next;
        return t.value;
    }

    /**
     * VORBEDINGUNG: Es wurde zuvor next() aufgerufen.
     * INVARIANTE:
     * NACHBEDINGUNG: Das zuvor mit next() zurückgegebene Objekt ist nichtmehr im ys speicher.
     */
    @Override
    public void remove() {
        return;
    }
}

class MyLinkedListNode<T> implements Iterator<MyLinkedListNode<T> > {
    public T value;
    public MyLinkedListNode<T> next;

    /**
     * VORBEDINGUNG: value ist nicht null
     * INVARIANTE: -
     * NACHBEDINGUNG: Valiede Node die value hält
     */
    MyLinkedListNode(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Value bleibt unverändert.
     * NACHBEDINGUNG: Gibt zurück ob es einen Nachfolger gibt.
     */
    public boolean hasNext() {
        return next != null;
    }

    /**
     * VORBEDINGUNG: hasNext() liefert true.
     * INVARIANTE: Das Objekt bleibt gleich.
     * NACHBEDINGUNG: Gibt die Nachfolgene Node (!= null) zurück.
     */
    public MyLinkedListNode<T> next() {
        return next;
    }
}

class MyLinkedList<T> {
    public MyLinkedListNode<T> back;
    public MyLinkedListNode<T> front;

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: Valides Objekt.
     */
    MyLinkedList() {
        this.back = null;
        this.front = null;
    }

    /**
     * VORBEDINGUNG: Value ist noch nicht in der Liste.
     * INVARIANTE: Alle einträge sind unique.
     * NACHBEDINGUNG: Der Wert value ist in der Liste.
     */
    public void append(T value) {
        MyLinkedListNode<T> mlln = new MyLinkedListNode<T>(value);
        if (back == null) {
            back = mlln;
        } else {
            front.next = mlln;
        }
        front = mlln;
    }

    /**
     * VORBEDINGUNG: Others ist nicht null.
     * INVARIANTE: Alle Einträge in der Liste sind unique.
     * NACHBEDINGUNG: Jedes element aus others ist in dieser Liste enthalten.
     */
    public void add(MyLinkedList<T> others) {
        if (others.back == null) {
            return;
        }
        MyLinkedListNode<T> other = others.back;

        add(other.value);

        while (other.hasNext()) {
            other = other.next();
            add(other.value);
        }
    }

    /**
     * VORBEDINGUNG: val ist nicht null.
     * INVARIANTE: Alle einträge sind unique.
     * NACHBEDINGUNG: Der eintrag val ist nicht in der Liste.
     */
    public void remove(T val) {
        if (back == null) return;

        MyLinkedListNode<T> cur;
        MyLinkedListNode<T> nxt;
        cur = back;

        if (back.value.equals(val)) {
            if (back == front) {
                back = null;
                front = null;
            } else {
                back = back.next();
            }
        } else {
            while(cur.hasNext()) {
                nxt = cur.next;
                if (nxt.value.equals(val)) {
                    if (nxt.hasNext()) {
                        cur.next = nxt.next;
                    } else {
                        cur.next = null;
                        front = cur;
                    }
                    break;
                }
                cur = nxt;
            }
        }
    }

    /**
     * VORBEDINGUNG: Value ist nicht null.
     * INVARIANTE: Alle Einträge der Liste sind unique.
     * NACHBEDINGUNG: Value ist genau ein mal in der Liste.
     */
    public boolean add(T value) {
        MyLinkedListNode<T> cur;
        cur = back;
        boolean found = false;
        while (cur != null) {
            if (cur.value.equals(value)) {
                found = true;
                break;
            }
            cur = cur.next;
        }

        if (found) {
            return false;
        } else {
            append(value);
            return true;
        }
    }
}