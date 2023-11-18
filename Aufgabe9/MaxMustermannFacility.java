@Author(name = "Jazlyn Gozar")
@ClassContract(invariante = "b != null, weight > 0, publicPowerGrid != null")
//@RelationshipInfo(reason = "MaxMustermann steht nicht alleine", notInRelationshipWith = EmilEbendorferFacility.class)
//@RelationshipInfo(reason = "MaxMustermann steht nicht alleine", notInRelationshipWith = LocalSupplyFacility.class)
//@RelationshipInfo(reason = "MaxMustermann hat kein integrierten PV-Panel", notInRelationshipWith = HertaHochrieglerFacility.class)
public abstract class MaxMustermannFacility extends AttachedFacility{
    private PublicPowerGrid publicPowerGrid;

    @MethodContract(precond = "publicPowerGrid =! null", postcond = )

    public MaxMustermannFacility(Building b, double weight, PublicPowerGrid publicPowerGrid) {
        super(b, weight);
        this.publicPowerGrid = publicPowerGrid;
    }
}
