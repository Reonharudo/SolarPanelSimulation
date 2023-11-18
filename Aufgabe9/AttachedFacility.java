@Author(name = "Jazlyn Gozar")
@ClassContract(invariante = "weight >= 0")
@RelationshipInfo(reason = "weil AttachedFacility nicht alleine steht", notInRelationshipWith = StandAloneFacility.class)
@RelationshipInfo(reason = "weil PrivateSupplyFacility nicht an einem Gebäude angehängt ist", notInRelationshipWith = PrivateSupplyFacility.class)
@RelationshipInfo(reason = "weil bei AttachedFacility das PV-Panel nicht integriert, sondern hinzugefügt wird", notInRelationshipWith = IntegratedFacility.class)
public abstract class AttachedFacility extends Facility{

    private final Building b;
    private final double weight;

    @MethodContract(precond = "b != null, weight >= 0", postcond = )
    public AttachedFacility(Building b, double weight) {
        this.b = b;
        this.weight = weight;
    }
    @MethodContract(precond = , postcond = "returns the weight")
    public double weight(){
        return weight;
    }


}
