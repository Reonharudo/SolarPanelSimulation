import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SimulationRun {

    private String name;
    private String env;
    private String eu;
    private String pv;

    private LocalDateTime start;
    private LocalDateTime end;

    private ArrayList<Double> used;
    private ArrayList<Double> generated;
    private ArrayList<Double> peak;
    public double totalUsed;
    public double totalGenerated;
    public double totalPeak;
    public double totalOverproduction;
    public double totalUnderproduction;

    /* 
     *
     * PRECON:  Valid start, end, name, env, eu, pv.
     * POSTCON: The Object is in a valid state.
     * INVAR:   -
     */
    public SimulationRun(LocalDateTime start, LocalDateTime end, String name, String env, String eu, String pv) {
        this.name = name;
        this.env = env;
        this.eu = eu;
        this.pv = pv;

        this.start = start;
        this.end = end;

        this.used = new ArrayList<>(366 * 24);
        this.generated = new ArrayList<>(366 * 24);
        this.peak = new ArrayList<>(366 * 24);

        this.totalUsed = 0.0;
        this.totalGenerated = 0.0;
        this.totalPeak = 0.0;
        this.totalOverproduction = 0.0;
        this.totalUnderproduction = 0.0;
    }

    /* 
     *
     * PRECON:  All parameters (cused, cgenerated, cpeak) are non-negative
     * POSTCON: The hourly and total values are updated.
     * INVAR:   The hourly and total records match.
     */
    public void add(double cused, double cgenerated, double cpeak) {
        totalUsed += cused;
        totalGenerated += cgenerated;
        totalPeak = Math.max(totalPeak, cpeak);

        used.add(cused);
        generated.add(cgenerated);
        peak.add(cpeak);

        double overprod = Math.max(cgenerated - cused, 0.0);
        double underprod = Math.max(cused - cgenerated, 0.0);

        totalOverproduction += overprod;
        totalUnderproduction += underprod;
    }

    /* 
     *
     * PRECON:  Writer is writable and o.toString() is only one Text line.
     * POSTCON: The line "# {name}: {o.toString()}\n" is written.
     * INVAR:   State of this.
     */
    private static void println(Writer writer, String name, Object o) throws IOException {
        writer.write("# ");
        writer.write(name);
        writer.write(": ");
        writer.write(o.toString());
        writer.write('\n');
    }

    /* 
     *
     * PRECON:  -
     * POSTCON: Currently held data is written.
     * INVAR:   State of this.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name:                  ").append(name).append('\n');
        sb.append("Peak Output:           ").append(totalPeak).append("kW\n");
        sb.append("Total Generated:       ").append(totalGenerated).append("kW\n");
        sb.append("Total Usage:           ").append(totalUsed).append("kW\n");
        sb.append("Total Overproduction:  ").append(totalOverproduction).append("kW\n");
        sb.append("Total Underproduction: ").append(totalUnderproduction).append("kW\n");

        return sb.toString();
    }

    /* 
     *
     * PRECON:  Writer is writable.
     * POSTCON: An Entry of this is written which when read produces the same data again.
     * INVAR:   State of this.
     */
    public void save(Writer writer) {
        StringBuilder sb = new StringBuilder();

        try {
            // id
            println(writer, "name", name);
            println(writer, "conf.environment", env);
            println(writer, "conf.electricity", eu);
            println(writer, "conf.pvfacility", pv);
            println(writer, "conf.start", start.toString());
            println(writer, "conf.end", end.toString());
            println(writer, "schema", "used, generated, peak");

            writer.write("\n# start\n");

            int n = used.size();

            for (int i = 0; i < n; i++) {
                writer.write(used(i) + ", " + generated(i) + ", " + peak(i) + "\n");
            }

            writer.write("# end\n\n");

        } catch (IOException e) {
            System.out.println("could not save run (" + name + ")");
            System.out.println(e.toString());
            System.exit(-1);
        }

    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the name.
     * INVAR:   State of this.
     */
    public String getName() {
        return name;
    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the used Electricity at the ith simulated hour.
     * INVAR:   State of this.
     */
    public double used(int i) {
        return used.get(i);
    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the generated Electricity at the ith simulated hour.
     * INVAR:   State of this.
     */
    public double generated(int i) {
        return generated.get(i);
    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the peak Electricity used at the ith simulated hour.
     * INVAR:   State of this.
     */
    public double peak(int i) {
        return peak.get(i);
    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the overproduced Electricity at the ith simulated hour.
     * INVAR:   State of this.
     */
    public double overproduction(int i) {
        return Math.max(generated(i) - used(i), 0);
    }

    /* 
     * PRECON:  -
     * POSTCON: Output is the underproduced Electricity at the ith simulated hour.
     * INVAR:   State of this.
     */
    public double underproduction(int i) {
        return Math.max(used(i) - generated(i), 0.0);
    }
}
