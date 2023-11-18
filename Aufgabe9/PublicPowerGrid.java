@Author(name = "Jazlyn Gozar")
@ClassContract(invariante = "power >= 0")
@RelationshipInfo(reason = "es repräsentiert diverse Objekte, die nichts gemeinsam haben", notInRelationshipWith = Facility.class)
@RelationshipInfo(reason = "PublicPowerGrid is kein Gebäude.", notInRelationshipWith = Building.class)
public class PublicPowerGrid {
    private double power;

    @MethodContract(precond = "power >= 0", postcond = )
    public PublicPowerGrid(double power) {
        this.power = power;
    }

    @MethodContract(precond = "p > 0", postcond = "adds p to the power")
    public void giveToPower(double p){
        power += p;
    }

    @MethodContract(precond = "p > 0", postcond = "returns a boolean value stating if reduction of power with p is bigger than 0")
    public boolean takeFromPower(double p){
        if((power - p) >= 0){
            return true;
        }return false;
    }

    @MethodContract(precond = , postcond = "returns power")
    public double getPower() {
        return power;
    }


}
