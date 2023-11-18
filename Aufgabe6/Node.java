public class Node {
    private Object object;
    private Node next;
    private Object value;



    public Node(Object object, Node next, Object value) {
        this.object = object;
        this.next = next;
        this.value = value;
    }

    /**
     * VORBEDINGUNG: next != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: sets the next node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns the Object of this node
     */
    public Object getObject() {
        return object;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns the next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns the unique value of this node
     */
    public Object getValue() {
        return value;
    }


}
