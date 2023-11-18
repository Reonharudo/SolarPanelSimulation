@Author(name = "Leonhard Muellauer")
@ClassContract(invariante = "-")
@RelationshipInfo(reason = "findet keine Codewiederverwendung statt", notInRelationshipWith = Building.class)
@RelationshipInfo(reason = "findet keine Codewiederverwendung statt", notInRelationshipWith = PublicPowerGrid.class)
public abstract class Facility {

    @MethodContract(precond = , postcond = "gibt eine string repräsentation zurück")
    @Override
    public String toString() {
        return super.toString() + this.getClass().getSimpleName();
    }
}
