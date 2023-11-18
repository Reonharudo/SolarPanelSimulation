
@Author(name = "Leonhard Muellauer")
@ClassContract(invariante = "width > 0 && length > 0 && excessPower >= 0 && covered >= 0 && covered <= 200")
@RelationshipInfo(reason = "weil EmilEbendorferFacility nicht coverage unterstätzt.", notInRelationshipWith = EmilEbendorferFacility.class)
public class LocalSupplyFacility extends StandAloneFacility{
    private final double excessPower;
    private final double covered;

    @MethodContract(precond = "powergrid != null && width > 0 && length > 0 && excessPower >= 0 && covered >= 0 && covered <= 200", postcond = "erstellt eine instanz der klasse")
    public LocalSupplyFacility(PublicPowerGrid publicPowerGrid, double width, double length, double excessPower, double covered){
        super(publicPowerGrid, width, length);
        this.excessPower = excessPower;
        this.covered = covered;
    }

    @MethodContract(precond = "width > 0 && length > 0 && excessPower >= 0 && covered >= 0 && covered <= 200", postcond = "erstellt eine instanz der klasse")
    public LocalSupplyFacility(double width, double length, double excessPower, double covered){
        super(width, length);
        this.excessPower = excessPower;
        this.covered = covered;
    }

    @MethodContract(precond = , postcond = "gibt covered zurück in Prozent")
    public double covered(){
        return covered;
    }


    @MethodContract(precond = , postcond = "gibt eine string repräsentation zurück")
    @Override
    public String toString() {
        return "LocalSupplyFacility{" +
                "excessPower=" + excessPower +
                ", covered=" + covered +
                '}';
    }
}
