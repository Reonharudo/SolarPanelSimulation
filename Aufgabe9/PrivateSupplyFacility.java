@Author(name = "Noel Atzwanger")
@ClassContract(invariante = "-")
@RelationshipInfo(notInRelationshipWith = AttachedFacility.class, reason = "PrivateSupplyFacility has an attached Building.")
@RelationshipInfo(notInRelationshipWith = StandAloneFacility.class, reason = "StandAloneFacility is connected to the Public Power Grid.")
@RelationshipInfo(notInRelationshipWith = IntegratedFacility.class, reason = "IntegratedFacility is part of an Building.")
public class PrivateSupplyFacility extends Facility {
    @Override
    @MethodContract(precond = "-", postcond = "return Private Supply Facility")
    public String toString() {
        return "Private Supply Facility";
    }
}
