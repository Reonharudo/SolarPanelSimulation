public class Multiple implements Compatible<Multiple>{
    private final int id;
    private int compatibleMethodCall = 0;

    /**
     * VORBEDINGUNG: id is unique
     * INVARIANTE:
     * NACHBEDINGUNG: instance of this class is created
     */
    public Multiple(int id) {
        this.id = id;
    }

    /**
     * VORBEDINGUNG: multiple != null
     * INVARIANTE:
     * NACHBEDINGUNG: returns true if division of each id with rest is equal to 0 and increments compatibleMethodCall + 1, otherwise false
     */
    @Override
    public boolean compatible(Multiple multiple) {
        if(id % multiple.id == 0){
            this.compatibleMethodCall++;
            return true;
        }
        return false;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: compatibleMethodCall remains unchanged
     * NACHBEDINGUNG: returns amount of calls of compatible() which result was true
     */
    @Override
    public int rate() {
        return compatibleMethodCall;
    }
}
