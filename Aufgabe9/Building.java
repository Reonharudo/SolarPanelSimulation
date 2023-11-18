@Author(name="Noel Atzwanger")
@ClassContract(invariante = "-")
@RelationshipInfo(notInRelationshipWith = Facility.class, reason = "Building does not generate any solar power.")
@RelationshipInfo(notInRelationshipWith = PublicPowerGrid.class, reason = "Building is not part of the power grid.")
public abstract class Building {
    @Override
    @MethodContract(precond = , postcond = "return Building")
    public String toString() { return "Building"; }
}
