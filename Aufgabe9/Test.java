import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Leonhard: Facility, LocalSupplyFacility, HertaHochrieglerFacility, Test & Reflexion , Konzeption
 * Noel: Building and Subclasses, EmilebendorferFacility, PrivateSupplyFacility, Test & Reflexion , Konzeption
 * Jaz: PublicPowerGrid, AttachedFacility, StandAloneFacility, MaxMustermannFAcility, Test & Reflexion , Konzeption
 */
public class Test {
    public static void main(String... args){

        Class<?>[] oopue9 = {
                Facility.class,
                AttachedFacility.class,
                MaxMustermannFacility.class,
                StandAloneFacility.class,
                EmilEbendorferFacility.class,
                LocalSupplyFacility.class,
                PrivateSupplyFacility.class,
                IntegratedFacility.class,
                HertaHochrieglerFacility.class,

                Building.class,
                House.class,

                PublicPowerGrid.class
        };

        for (Class<?> c : oopue9) {
            Author a = c.getAnnotation(Author.class);
            System.out.println(c.getName() + ": " + a.name());
        }
        System.out.println("--------------------------");
        for (Class<?> c : oopue9) {
            System.out.println(c.getName() + " ist Untertyp von " +  c.getSuperclass());
        }
        System.out.println("--------------------------");
        for (Class<?> c : oopue9) {
            System.out.println(c.getName() + ":");
            for(Class<?> o : oopue9) {
                if (!(o.isAssignableFrom(c) || c.isAssignableFrom(o))) {
                    boolean found = false;
                    Class<?> self = c;
                    while (!found && self != java.lang.Object.class) {
                        RelationshipInfo[] infos = self.getAnnotationsByType(RelationshipInfo.class);
                        for (RelationshipInfo info : infos) {
                            //System.out.println("        " + info.notInRelationshipWith().getName());
                            if (info.notInRelationshipWith().isAssignableFrom(o)) {
                                System.out.println("    " + o.getName() + ": " + info.reason() + " (from: " + self.getName() + ")");
                                found = true;
                                break;
                            }
                        }
                        self = self.getSuperclass();
                    }
                    if (!found) {
                        System.out.println("    " + o.getName() + ": NOT FOUND!!!");
                    }
                }
            }
        }
        //methods
        System.out.println("--------------------------");
        for (Class<?> c : oopue9) {
            Method[] methods = c.getMethods();
            ClassContract cc = c.getAnnotation(ClassContract.class);
            assert cc != null;

            System.out.println(c.getSimpleName() + " INVARIANT: " + cc.invariante());
            for(Method method : methods){
                if (Modifier.isPublic(method.getModifiers()))
                {
                    MethodContract methodContract = method.getAnnotation(MethodContract.class);
                    if(methodContract != null){
                        System.out.println("    " + method.getName() + ": ");
                        System.out.println("        PRECOND:  " + methodContract.precond());
                        System.out.println("        POSTCOND: " + methodContract.postcond());
                    }
                }
            }
        }
    }
}
