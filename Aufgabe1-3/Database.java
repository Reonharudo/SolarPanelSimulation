import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Database {

    private String filename;
    private int nextID;

    /* 
     *
     * PRECON:  valid filepath (even if file does not exist, it schoud be creatable)
     * POSTCON: new Database Object
     * INVAR:   -
     */
    public Database(String filename){
        this.filename = filename;
        setNextID();
    }

    /* 
     *
     * PRECON:  The file does not exist, is empty or already has entries. It should not have unrelated data.
     * POSTCON: The id is non-negative, unique and greater than all other ids
     * INVAR:   State of the Database.
     */
    private void setNextID() {
        int last = -1;
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader r = new BufferedReader(fr);
            String line;

            while((line = r.readLine()) != null) {
                if (line.startsWith("# id:")) {
                    String value = line.substring(line.indexOf(':')+1).strip();
                    last = Integer.parseInt(value);
                }
            }

            fr.close();
            r.close();
            nextID = last + 1;
        } catch (FileNotFoundException e) {
            nextID = 0;
        } catch (IOException e) {
            System.out.println("could not read from file (" +  filename +")");
            throw new RuntimeException(e);
        }
    }

    /* 
     *
     * PRECON:  valid SimulationRun.
     * POSTCON: Run is queryable saved to the database.
     * INVAR:   Id is unique and the Database ist in a valid state.
     */
    public void add(SimulationRun run) {
        try {
            Writer writer = new FileWriter(filename, true);
            writer.write("############################################################################\n");
            writer.write("# id: " + nextID + "\n");
            nextID++;
            run.save(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("could not open file (" + filename + ")");
            throw new RuntimeException(e);
        }

    }

    /* 
     *
     * GOOD:    Used a Class Querry to simplify matching queries and entries.
     *
     * PRECON:  Valid Query.
     * POSTCON: The output contains all entries which matched the Query.
     * INVAR:   State of the Database.
     */
    private SimulationRun[] query(Query q) {
        ArrayList<SimulationRun> matches = new ArrayList<SimulationRun>();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader r = new BufferedReader(fr);

            String line;
            boolean run = true;

            while((line = r.readLine()) != null) {
                if (!line.startsWith("##########")) {
                    continue;
                }

                int id = 0;// id: 0
                String name = ; // name: dummy test 1
                String env = ; // conf.environment: dummy environment 1
                String eu = ; // conf.electricity: dummy electricity usage 1
                String pv = ; // conf.pvfacility: dummy pv-facility 1
                LocalDateTime start = LocalDateTime.now(); // conf.start: 2022-01-01T00:00
                LocalDateTime end = LocalDateTime.now(); // conf.end: 2023-01-01T00:00

                while((line = r.readLine()) != null) {
                    if (!line.startsWith("#")) continue;
                    if (line.startsWith("# start")) break;

                    int dp = line.indexOf(':');
                    if (dp == -1) continue;

                    String key = line.substring(1,dp).strip();
                    String val = line.substring(dp+1).strip();

                    switch (key) {
                        case "id":
                            id = Integer.parseInt(val);
                            break;
                        case "name":
                            name = val;
                            break;
                        case "conf.environment":
                            env = val;
                            break;
                        case "conf.electricity":
                            eu = val;
                            break;
                        case "conf.pvfacility":
                            pv = val;
                            break;
                        case "conf.start":
                            start = LocalDateTime.parse(val);
                            break;
                        case "conf.end":
                            end = LocalDateTime.parse(val);
                            break;
                        case "schema":
                            break;
                        default:
                            System.out.println("unknown key (" + key + ") in database");
                            break;
                    }
                }

                if (q.ok(id, name)) {
                    SimulationRun simrun = new SimulationRun(start, end, name, env, eu, pv);
                    String parts[];

                    // 1.0, 1.0, 2.0
                    while((line = r.readLine()) != null) {
                        if (line.startsWith("# end")) break;

                        parts = line.strip().split(",\\s*");
                        if (parts.length != 3) {
                            System.out.println("bad entry in database (" + line + ")");
                            continue;
                        }

                        double use = Double.parseDouble(parts[0]);
                        double gen = Double.parseDouble(parts[1]);
                        double peak = Double.parseDouble(parts[2]);

                        simrun.add(use, gen, peak);
                    }

                    matches.add(simrun);
                }
                else {
                    while((line = r.readLine()) != null) {
                        if (line.startsWith("# end")) break;
                    }
                }

            }

            fr.close();
            r.close();
        } catch (FileNotFoundException e) {
            return new SimulationRun[0];
        } catch (IOException e) {
            System.out.println("could not read from file (" +  filename +")");
            throw new RuntimeException(e);
        }

        SimulationRun[] r = new SimulationRun[matches.size()];
        matches.toArray(r);
        return r;
    }

    /* 
     *
     * PRECON:  Valid Query.
     * POSTCON: The output contains all entries which matched the Query.
     * INVAR:   State of the Database.
     */
    public SimulationRun query(int id) {
        Query q = new Query() {
            @Override
            public boolean ok(int qid, String qname) {
                return id == qid;
            }
        };

        SimulationRun ans[] = query(q);

        if (0 < ans.length) {
            return ans[0];
        } else {
            return null;
        }
    }

    /* 
     *
     * PRECON:  Valid Query.
     * POSTCON: The output contains all entries which matched the Query.
     * INVAR:   State of the Database.
     */
    public SimulationRun[] query(String name) {
        Query q = new Query() {
            @Override
            public boolean ok(int qid, String qname) {
                return name.equals(qname);
            }
        };

        return query(q);
    }

    /* 
     *
     * PRECON:  Valid Query.
     * POSTCON: The output contains all entries of the Database.
     * INVAR:   State of the Database.
     */
    public SimulationRun[] all() {
        Query q = new Query() {
            @Override
            public boolean ok(int qid, String qname) {
                return true;
            }
        };

        return query(q);
    }
}

interface Query {
    public boolean ok(int qid, String qname);
}
