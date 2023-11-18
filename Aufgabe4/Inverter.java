import java.util.LinkedList;

public abstract class Inverter extends DecouplingInverter implements Storagebattery {
    private LinkedList<FurtherFunctions> f;
    private LinkedList<Series> s;


    public Inverter(LinkedList<Series> s, LinkedList<FurtherFunctions> f){
        this.s = s;
        this.f = f;
    }



    /* 
     *
     * PRECON:  -
     * POSTCON: adds a series of modules to the Inverter
     * INVAR:   kWp <= kWpLimit
     */
    public void addToString(Series s){
        while (this.kwp() <= kWpLimit()) {
            this.s.addFirst(s);
        } if(!(this.kwp() <= kWpLimit())){
            this.s.pollFirst();
        }

    }

    /* 
     *
     * PRECON:  kwp >= 0
     * POSTCON: returns kwp of Inverter
     * INVAR:   -
     */
    public int kwp() {
        int result = 0;
        for (Series m:s) {
            result += m.kWp();
        }return result;
    }


    /* 
     *
     * PRECON:  kWp >= 0
     * POSTCON: returns the Limit of kWp
     * INVAR:   -
     */
    public int kWpLimit(){
        return 20;
    }
}
