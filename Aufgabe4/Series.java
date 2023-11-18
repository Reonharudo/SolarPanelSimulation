import java.util.ArrayList;
import java.util.List;

public abstract class Series {
    private List<GleichstromModule> series = new ArrayList<>();

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  modules != null
     * POSTCON: returns true if modules were successfully added
     * INVAR:   -
     */
    public boolean addToSeries(GleichstromModule... modules){
        return series.addAll(List.of(modules));
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  modules != null
     * POSTCON: returns -1 if this series has no modules or returns sum of kwp aggregated of all modules in this series
     * INVAR:   -
     */
    public double kWp(){
        double sum = 0;
        for(GleichstromModule module : series){
            sum += module.kWp();
        }
        return sum;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  series has correct toString implementtion
     * POSTCON: returns string representation of this
     * INVAR:   -
     */
    @Override
    public String toString() {
        String erg = "Series { kWp: "+kWp()+" ";
        for(GleichstromModule module : series){
            erg += module;
        }
        erg += "}";
        return erg;
    }
}
