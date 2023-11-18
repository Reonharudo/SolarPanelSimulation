/**
 * Leonhard:
 * Inverter, Multiple
 *
 * Noel:
 * Module, Generalcatalog
 *
 * Jaz:
 * SpecialCatalog
 * ------------------------------------------------------
 * Alle: Compatible, CompatibilityCollection
 */
public class Test {
    public static void main(String[] args) {
        GeneralCatalogAndModuleTest.test();

        System.out.println("=== TEST INVERTER ===");
        InverterTest invt = new InverterTest();
        invt.test();

        System.out.println("=== TEST MULTIPLE ===");
        MultipleTest mult = new MultipleTest();
        mult.test();

        System.out.println("=== TEST SPECIALCATALOG ===");
        SpecialCatalogTest.tester();
    }
}
