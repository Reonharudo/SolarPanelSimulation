import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Component implements Iterable<Module>, Module{
    private List<Module> parts =  new ArrayList();

    private long id;

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  id is unique
     * POSTCON: creates instance of Component class
     * INVAR:   -
     */
    public Component(long id){
        this.id = id;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  modules != null
     * POSTCON: returns true if components were successfully added
     * INVAR:   -
     */
    public boolean addComponent(Module... components){
        return this.parts.addAll(List.of(components));
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  -
     * POSTCON: returns id of this instance
     * INVAR:   -
     */
    public final long getId(){
        return id;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  -
     * POSTCON: returns total kWP of this Component (including that of its associated components)
     * INVAR:   -
     */
    public double kWp(){
        double kWp = 0;
        for(Module module : parts){
            kWp += module.kWp();
        }
        return kWp;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  -
     * POSTCON: if this component has no components in its components list then return true, otherwise false
     * INVAR:   -
     */
    public boolean istElementar(){
        return parts.size() == 0;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  -
     * POSTCON: returns iterator of components list
     * INVAR:   -
     */
    @Override
    public Iterator<Module> iterator() {
        Iterator<Module> thisIterator = parts.iterator();
        while(thisIterator.hasNext()){
            Module next = thisIterator.next();
            if(next instanceof Component) {
                Component nextComp = (Component) next;
                return nextComp.iterator();
            }
        }
        return thisIterator;
    }

    /* @author  Leonhard Müllauer <e12022501@student.tuwien.ac.at>
     *
     * PRECON:  series has correct toString implementtion
     * POSTCON: returns string representation of this
     * INVAR:   -
     */
    @Override
    public String toString() {
        String erg = "Component { id: "+getId()+";"+ "istElementar: "+istElementar()+ ";"+ "components: "+System.lineSeparator()+"-";
        int row = 0;
        for(Module module : parts){
            for(int i = 0; i < row; i++){
                erg += "-";
            }
            erg += module+System.lineSeparator();
            row++;
        }
        erg += "}";
        return erg;
    }
}
