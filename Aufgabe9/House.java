@Author(name="Noel Atzwanger")
@ClassContract(invariante = "-")
public class House extends Building {
    @Override
    @MethodContract(precond = , postcond = "return Bridge")
    public String toString() {
        return "Bridge";
    }
}
