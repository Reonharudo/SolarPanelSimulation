@Author(name = "Leonhard Muellauer")
@ClassContract(invariante = "building != null")
@RelationshipInfo(reason = "weil es steht auf einem Building (Almhuette)", notInRelationshipWith = PrivateSupplyFacility.class)
@RelationshipInfo(reason = "weil es steht auf einem Building (Almhuette)", notInRelationshipWith = StandAloneFacility.class)
@RelationshipInfo(reason = "weil es wird nicht attached sondern es steht auf einem Building", notInRelationshipWith = AttachedFacility.class)
public class HertaHochrieglerFacility extends IntegratedFacility {
    @MethodContract(precond = "building != null", postcond = )
    public HertaHochrieglerFacility(Building building) {
        super(building);
    }

    @MethodContract(precond = , postcond = "gibt eine string repräsentation zurück")
    @Override
    public String toString() {
        return "HertaHochrieglerFacility{}";
    }
}
