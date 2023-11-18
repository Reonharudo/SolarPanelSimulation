@Author(name = "Jazlyn Gozar")
@ClassContract(invariante = "invariante > 0")
@RelationshipInfo(reason = "weil StandAlone keine tragende Struktur besitzt", notInRelationshipWith = AttachedFacility.class)
@RelationshipInfo(reason = "weil StandAlone eine Anbindung an das öffentliche Stromnetz besitzen kann", notInRelationshipWith = PrivateSupplyFacility.class)
@RelationshipInfo(reason = "weil StandAlone keine tragende Struktur besitzt", notInRelationshipWith = IntegratedFacility.class)
public abstract class StandAloneFacility extends Facility {
    private PublicPowerGrid publicPowerGrid;
    private final double width;
    private final double length;

    @MethodContract(precond = "publicPowerGrid != null, width > 0, length > 0", postcond = )
    public StandAloneFacility(PublicPowerGrid publicPowerGrid, double width, double length) {
        this.publicPowerGrid = publicPowerGrid;
        this.width = width;
        this.length = length;
    }

    public StandAloneFacility(double width, double length){
        this.width = width;
        this.length = length;
    }


    @MethodContract(precond = , postcond = "returns the size of the area")
    public double area(){
        return width  * length;
    }
}
