import java.util.Iterator;

public class SpecialCatalogTest {
    public static void main(String[] args) {
        tester();
    }
        public static void tester() {
            SpecialCatalog<Multiple, Multiple> s1 = new SpecialCatalog<>();
            SpecialCatalog<Inverter, Inverter> s2 = new SpecialCatalog<>();
            SpecialCatalog<PVComponent, PVComponent> s3 = new SpecialCatalog<>();
            SpecialCatalog<Module, PVComponent> s4 = new SpecialCatalog<>();
            SpecialCatalog<PVComponent, Inverter> s5 = new SpecialCatalog<>();
            SpecialCatalog<Module, Inverter> s7 = new SpecialCatalog<>();
            SpecialCatalog<PVComponent, Module> s8 = new SpecialCatalog<>();
            SpecialCatalog<Inverter, Module> s9 = new SpecialCatalog<>();
            SpecialCatalog<Module, Module> s10 = new SpecialCatalog<>();

            s1.addY(new Multiple(2));
            s1.addY(new Multiple(4));
            s1.addY(new Multiple(4));
            s1.addY(new Multiple(6));

            s1.addX(new Multiple(1));
            s1.addX(new Multiple(82));
            s1.addX(new Multiple(1));
            s1.addX(new Multiple(4));


            s2.addY(new Inverter(9, "OC" , "HA"));
            s2.addY(new Inverter(9, "89", "HA"));
            s2.addY(new Inverter(2, "HO", "HA"));
            s2.addY(new Inverter(9, "OC", "HA"));
            s2.addY(new Inverter(8, "OC", "HA"));

            s2.addX(new Inverter(3, "DE", "HA"));
            s2.addX(new Inverter(14, "DE", "HA"));
            s2.addX(new Inverter(16, "KO", "HA"));
            s2.addX(new Inverter(1, "DE", "HA"));
            s2.addX(new Inverter(1, "DE", "HA"));

            for (Inverter i:s2) {
                s5.addY(i);
                s9.addX(i);
            }

            s3.addX(new PVComponent() {
                @Override
                public String[] certified() {
                    return new String[0];
                }

                @Override
                public boolean compatible(PVComponent pvComponent) {
                    return false;
                }

                @Override
                public int rate() {
                    return 0;
                }

            });

            s3.addX(new PVComponent() {
                                @Override
                                public String[] certified() {
                                    return new String[0];
                                }

                                @Override
                                public boolean compatible(PVComponent pvComponent) {
                                    return false;
                                }

                                @Override
                                public int rate() {
                                    return 0;
                                }
                            });
            s3.addY(new PVComponent() {
                @Override
                public String[] certified() {
                    return new String[0];
                }

                @Override
                public boolean compatible(PVComponent pvComponent) {
                    return false;
                }

                @Override
                public int rate() {
                    return 0;
                }
            });

            s3.addY(new PVComponent() {
                @Override
                public String[] certified() {
                    return new String[0];
                }

                @Override
                public boolean compatible(PVComponent pvComponent) {
                    return false;
                }

                @Override
                public int rate() {
                    return 0;
                }
            });

            s4.addX(new Module(6, "HAH"));
            s4.addX(new Module(6, "HAH"));
            s4.addX(new Module(6, "HAH"));

            for (PVComponent p: s3) {
                s4.addY(p);
                s5.addX(p);
                s8.addX(p);
            }

            for (Module m:s4) {
                s8.addY(m);
                s9.addY(m);
                s10.addX(m);
                s10.addY(m);
            }

            /** Punkt 2 */
            s7.addY(new Inverter(9, "DC european", "AC european"));
            s7.addY(new Inverter(9, "DC european", "AC european"));
            s7.addY(new Inverter(2, "DC european", "AC european"));
            s7.addY(new Inverter(9, "DC european", "AC european"));
            s7.addY(new Inverter(8, "DC european", "AC european"));

            s7.addX(new Module(3, "DC european", "AC european"));
            s7.addX(new Module(14, "DC european", "AC european"));
            s7.addX(new Module(16, "DC european", "AC european"));
            s7.addX(new Module(1, "DC european", "AC european"));
            s7.addX(new Module(1, "DC european", "AC european"));


            SpecialCatalog<Inverter, PVComponent> b = new SpecialCatalog<>();
            SpecialCatalog<Module, Inverter> c = s7;

            for(Module moduleX : c){
                Iterator<Inverter> iter = c.iterator(moduleX);
                while(iter.hasNext()){
                    Inverter inverterY =  iter.next();
                    System.out.println("kWp() called: "+inverterY.kWp());
                    b.addX(inverterY);
                }
                System.out.println("size() called: "+moduleX.size());
                b.addY(moduleX);
            }


            /** Punkt 3 */
            /** GeneralCatalog und SpecialCatalog
             * stehen in unserer Implementierung nicht in einer Beziehung,
             * da parallel an den Klassen von verschiedenen Akteuren gearbeitet wurde */
        }
    }
