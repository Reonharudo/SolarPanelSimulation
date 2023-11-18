//Container for the streets
public class Community {
    private List streets;
    private final String name;

    public Community(String name) {
        this.name = name;
        this.streets = new List();
    }
    /**
     * VORBEDINGUNG: s != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: adds a street to this community if the given unique characteristic does
     * not exist yet
     */
    public void add(Street s) {
        streets.add(s);
    }

    /**
     * VORBEDINGUNG: s != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: removes a Street from this community by the unique name of the Street
     */
    public void remove(String s){
        streets.remove(s);
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: represents all the PVPanels in this community
     */
    @Override
    public String toString() {
        return "Community name : " + name +"            "+ streets.toString();
    }


}
