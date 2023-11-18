import java.util.Iterator;

public class List implements Iterable{
    private Node nil;
    public List() {
        nil = null;
    }

    /**
     * VORBEDINGUNG: x != null, x.getClass() can be Street and PVPanel
     * INVARIANTE:  -
     * NACHBEDINGUNG: adds PVList or Street at the end of this list if the unique value does not exist yet
     */
    public void add(Object x) {
        if(nil == null){
            if(x instanceof PVPanel) nil = new Node(x, null, ((PVPanel)x).getHouseNr());
            if(x instanceof Street) nil = new Node(x, null, ((Street)x).getName());
            return;
        }else {
            Node current = nil;
            PVPanel p;
            while (current.getNext() != null) {
                if (x instanceof Street) {
                    if (current.getValue().equals(((Street) x).getName())) return;
                }
                if (x instanceof PVPanel) {
                    if ((int) current.getValue() == ((PVPanel) x).getHouseNr()) return;
                }
                current = current.getNext();
            }
            if (x instanceof Street) {
                current.setNext(new Node(x, null, ((Street) x).getName()));
            }
            if (x instanceof PVPanel) {
                p = (PVPanel) x;
                current.setNext(new Node(x, null, p.getHouseNr()));
            }
        }
    }

    /**
     * VORBEDINGUNG: s != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: removes an Object from the list
     */
    public void remove(Object s){
        Node current = nil.getNext();
        Node last = nil;
        if(nil.getValue().equals(s)){
            nil = nil.getNext();
        }else{
            if(current.getValue().equals(s)){
                last.setNext(current.getNext());
            }
        }
    }


    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: iterates through the list with next() and frequently checking if the current
     * iterator has a next Node
     */
    public Iterator iterator() {
        return new Iterator() {
            Node current = nil;


            @Override
            public boolean hasNext() {
                if(current.equals(nil) && nil != null){
                    return true;
                }
                return current.getNext() != null;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    Node temp = current;
                    current = current.getNext();
                    return temp.getObject();
                }
                return null;
            }

        };
    }

    /**
     * VORBEDINGUNG: this.nil != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns the characteristics of the nodes in the list as a string
     */
    @Override
    public String toString() {
        String s = ;
        Node current = nil;
        while(current != null){
            s += current.getObject() + "\n";
            current = current.getNext();
        }return s;
    }

}
