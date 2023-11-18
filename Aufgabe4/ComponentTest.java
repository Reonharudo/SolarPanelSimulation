import java.util.Iterator;

public class ComponentTest {

    public static void main(String... args){
        ComponentTest test = new ComponentTest();
        test.testConstructor();
        test.testComponent();
    }

    public void test(){
        ComponentTest test = new ComponentTest();
        test.testConstructor();
        test.testComponent();
    }

    public void testConstructor(){
        ConcreteComponent component1 = new ConcreteComponent(123424);
        long actual = component1.getId();
        long expected = 123424;
        System.out.println("Id is "+(actual == expected));
    }


    public void testComponent(){
        ConcreteComponent component1 = new ConcreteComponent(123424);
        ConcreteComponent component2 = new ConcreteComponent(11103);
        ConcreteComponent component3 = new ConcreteComponent(11105);

        ConcrecteModuleTypeA moduleTypeA = new ConcrecteModuleTypeA();
        ConcrecteModuleTypeB moduleTypeB = new ConcrecteModuleTypeB();
        ConcrecteModuleTypeC moduleTypeC = new ConcrecteModuleTypeC();

        component1.addComponent(moduleTypeA, component2);
        component2.addComponent(moduleTypeB, moduleTypeB, component3);
        component3.addComponent(moduleTypeA, moduleTypeC);

        Iterator<Module> iter = component1.iterator();
        while(iter.hasNext()){ //test iterator
            System.out.println(iter.next().toString()); //test tostring
        }

        System.out.println(component1); //test component structure  //test tostring
    }


    public class ConcreteComponent extends Component{

        public ConcreteComponent(long id) {
            super(id);
        }


        @Override
        public double size() {
            return 50;
        }


        @Override
        public boolean decouplingRequired() {
            return false;
        }
    }

    public class ConcrecteModuleTypeA implements Module{

        @Override
        public double size() {
            return 50;
        }

        @Override
        public double kWp() {
            return 20;
        }

        @Override
        public boolean decouplingRequired() {
            return false;
        }

        @Override
        public String toString() {
            return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
        }
    }

    public class ConcrecteModuleTypeB implements Module{

        @Override
        public double size() {
            return 50;
        }

        @Override
        public double kWp() {
            return 11;
        }

        @Override
        public boolean decouplingRequired() {
            return false;
        }

        @Override
        public String toString() {
            return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
        }
    }

    public class ConcrecteModuleTypeC implements Module{

        @Override
        public double size() {
            return 50;
        }

        @Override
        public double kWp() {
            return 110;
        }

        @Override
        public boolean decouplingRequired() {
            return false;
        }

        @Override
        public String toString() {
            return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
        }
    }
}
