@Author(name = "Leonhard Muellauer")
@RelationshipInfo(reason = "weil es hat einen integralen Bestandteil", notInRelationshipWith = PrivateSupplyFacility.class)
@RelationshipInfo(reason = "weil es fungiert als integraler Bestandteil zu einem Building ", notInRelationshipWith = StandAloneFacility.class)
@RelationshipInfo(reason = "weil es wird nicht attached sondern integriert", notInRelationshipWith = AttachedFacility.class)
@ClassContract(invariante = "integral == true && building != null")
public class IntegratedFacility extends Facility{
    private Building building;
    private final boolean integral;

    @MethodContract(precond = "building != null", postcond = "erstellt eine instanz der klasse")
    public IntegratedFacility(Building building){
        this.building = building;
        this.integral = true;
    }

    @MethodContract(precond = , postcond = "entfernt das Building von dieser Facility und gibt das entfernte Building zurück")
    public Building removeBuilding(){
        if(integral){
            Building removed = building;
            this.building = null;
            return removed;
        }
        return null;
    }

    @MethodContract(precond = , postcond = "gibt eine string repräsentation zurück")
    @Override
    public String toString() {
        return "IntegratedFacility{" +
                "building=" + building +
                ", integral=" + integral +
                '}';
    }
}
