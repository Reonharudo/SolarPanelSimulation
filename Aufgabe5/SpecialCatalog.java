import java.util.Iterator;

public class SpecialCatalog<X extends Compatible<? super Y>, Y> implements CompatibilityCollection<X, Y> {

    private List<X> XList;
    private List<Y> YList;

    public SpecialCatalog() {
        this.XList = new List<X>();
        this.YList = new List<Y>();
    }

    /* 
     *
     * PRECON:  x != null
     * POSTCON: if x is not included in the list yet, x gets added to the list
     * INVAR:  -
     */
    @Override
    public void addX(X x) {
        XList.add(x);
    }

    /* 
     *
     * PRECON:  y != null
     * POSTCON: if y is not included in the list yet, y gets added to the list
     * INVAR:   -
     */
    @Override
    public void addY(Y y) {
        YList.add(y);
    }

    /* 
     *
     * PRECON:  XList != null
     * POSTCON: allows iteration through the x values, removes the last next object
     * INVAR:   -
     */
    @Override
    public Iterator<X> iterator() {
        return new Iterator<X>() {
            Node<X> current = XList.getNil();
            Node<X> temp = XList.getNil();

            /* 
             *
             * PRECON:  -
             * POSTCON: checks if a next element in the list exists
             * INVAR:   -
             */
            @Override
            public boolean hasNext() {
                return current.next() != null;
            }

            /* 
             *
             * PRECON:  -
             * POSTCON: returns the next element in the list
             * INVAR:   -
             */
            @Override
            public X next() {
                if (hasNext()) {
                    if (temp == null && current != null) {
                        temp = temp.next();
                    }
                    current = current.next();
                    return current.value();
                }
                return null;
            }

            @Override
            public void remove() {
                XList.remove(temp.value());
            }
        };
    }

    /* 
     *
     * PRECON:  YList != null
     * POSTCON: allows iteration through the y which are compatibe with x, removes the last next object
     * INVAR:   -
     */
    @Override
    public Iterator<Y> iterator(X x) {
        return new Iterator<Y>() {
            Node<Y> current = YList.getNil();
            Node<Y> temp = YList.getNil();

            /* 
             *
             * PRECON:  -
             * POSTCON: checks if a next element exists which is compatible with x in the list
             * INVAR:   -
             */
            @Override
            public boolean hasNext() {
                current = current.next;
                while(current != null) {
                    if (x.compatible(current.value())) {
                        return true;
                    }current = current.next;
                }
                return false;
            }

            /* 
             *
             * PRECON:  -
             * POSTCON: returns the next element which is compatible with x in the list
             * INVAR:   -
             */
            @Override
            public Y next() {
                temp = temp.next();
                while(temp != null) {
                    if (x.compatible(temp.value())) {
                        return temp.value;
                    }temp = temp.next;
                }
                return null;
            }

            @Override
            public void remove() {
                YList.remove(temp.value());
            }
        };
    }


    /* 
     *
     * PRECON:  -
     * POSTCON: creates a list with a nil node at the beginning
     * INVAR:   -
     */
    class List<T> implements Iterable<T> {
        private Node<T> nil;

        public List() {
            nil = new Node<>(null, null);
        }

        public Node<T> getNil() {
            return nil;
        }

        /* 
         *
         * PRECON:  x != null
         * POSTCON: if x is not included in the list yet, x gets added to the list
         * INVAR:  -
         */
        public void add(T x) {
            Node<T> current = nil.next;
            if (nil.next() == null) {
                nil.setNext(new Node<T>(x, null));
            } else {
                while (current.next != null) {
                    if (current.equals(x)) {
                        return;
                    }
                    current = current.next();
                }
                current.setNext(new Node(x, null));
            }
        }

        /* 
         *
         * PRECON:  -
         * POSTCON: removes the last next object
         * INVAR:   -
         */
        public void remove(T value) {
            Node<T> current = nil.next();
            if (current.value().equals(current.next())) {
                current = current.next();
            }
            while (current.next() != null) {
                if (current.next().value().equals(value)) {
                    current.setNext(current.next().next());
                }
                current = current.next();
            }
        }

        /* 
         *
         * PRECON:  -
         * POSTCON: allows iteration through the list
         * INVAR:   -
         */
        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node<T> current = nil;
                Node<T> temp = nil;

                @Override
                public boolean hasNext() {
                    return current.next() != null;
                }

                @Override
                public T next() {
                    if (hasNext()) {
                        if (temp == null && current != null) {
                            temp = temp.next();
                        }
                        current = current.next();
                        return current.value();
                    }
                    return null;
                }

            };
        }
    }

    /* 
     *
     * PRECON:  -
     * POSTCON: creates nodes for the Linkedlist
     * INVAR:   -
     */
    class Node<T> {
        private T value;
        private Node<T> next;


        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        /* 
         *
         * PRECON:  -
         * POSTCON: links this to a next node
         * INVAR:   -
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /* 
         *
         * PRECON:  -
         * POSTCON: returns the value of this node
         * INVAR:   -
         */
        public T value() {
            return value;
        }

        /* 
         *
         * PRECON:  -
         * POSTCON: returns the next node
         * INVAR:   -
         */
        public Node next() {
            return next;
        }
    }
}