@Author(name="Noel Atzwanger")
@ClassContract(invariante = "grid != null, width > 0, length > 0")
@RelationshipInfo(notInRelationshipWith = LocalSupplyFacility.class, reason = "EmilEbendorferFacility does not have the covered mechanism of LocalSupplyFacility.")
public class EmilEbendorferFacility extends StandAloneFacility{
    @MethodContract(precond = , postcond = "Valid Object")
    public EmilEbendorferFacility(PublicPowerGrid grid, double width, double length) {
        super(grid, width, length);
    }

    @Override
    @MethodContract(precond = , postcond = "return Emil Ebendorfer")
    public String toString() {
        return "Emil Ebendorfer";
    }
}
