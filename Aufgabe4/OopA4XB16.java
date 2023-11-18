import java.util.ArrayList;
import java.util.LinkedList;

public abstract class OopA4XB16{
    private ArrayList<Series> s;
    private ArrayList<Storagebattery> storebatt;

    public OopA4XB16(ArrayList<Series> series, ArrayList<Storagebattery> s){
       this.s = series;
       this.storebatt = s;
    }


    /* 
     *
     * PRECONS: -
     * POSTCON: returns all the modules as String
     * INVAR:   -
     */
    @Override
    public String toString() {
        String s = ;
        for (Series m:this.s) {
            s += m.toString();
        }
        return s;
    }

    /* 
     *
     * PRECON:  module.size < 16
     * POSTCON: returns a list of modules in OopA4XB16
     * INVAR:   module.size >= 0
     */
    public void add(Series s){
        if(this.s.size() <= 16)
            this.s.add(s);
    }

    /* 
     *
     * PRECON:  Storagebattery != null
     * POSTCON: adds Storagebattery
     * INVAR:   -
     */
    public void addStoragebattery(Storagebattery batteries){
        storebatt.add(batteries);
    }
}

