/**
 * Leonhard:
 * Series, Component (Iterable)
 * StorageBatteryInverter
 *
 * Noel:
 * Module (GleichstromModule, WechselstromModule, CrystallineModuke)
 * OopA4 Complete, OopA4TB4, ThinFilmModule
 *
 * Jaz:
 * OopA4XB16, ZUsatzfunktionen, Speicherbatterie
 * Inverter(Iterable), DecouplingInverter,
 *--------------------------------------------------------------------------
 * Alle: Diskussion der Untertypbeziehungen und Konzeptentwürfe
 */

public class Test {
    public static void main(String... args){
        System.out.println("======= TEST ============ ");
        System.out.println("======= SERIES TEST ============ ");
        SeriesTest seriesTest = new SeriesTest();
        seriesTest.test();

        System.out.println("======= COMPONENT AND MODULES TEST ============ ");
        ComponentTest componentTest = new ComponentTest();
        componentTest.test();

        System.out.println("======= MODULES TEST ============ ");
        ModuleTest.test();

        System.out.println("======= OOPA4 ============ ");
        OopA4XB16Test.test();

        System.out.println("======= INVERTER ============ ");
        InverterTest.test();
    }
}