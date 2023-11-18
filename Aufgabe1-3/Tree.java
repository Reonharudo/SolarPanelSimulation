import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tree extends Nature {
    private List<Branch> branchList;
    private List<Leaf> leafList;

    /**
     * 
     Invariante: plantedTime nicht null
     Vorbedingung: height in m & >= 0, maxheight >= height & maxheight in m
     Nachbedingung: Erstellt eine Instanz der Klasse mit den Parameter Werten die den korrespondierenden
     Instanzvariablen zugeordnet werdne
     */
    public Tree(LocalDateTime plantedTime, double height, double maxHeight) {
        super(plantedTime, height, maxHeight);
    }

    /**
     * 
     Invariante: branchList ist nicht null
     Vorbedingung: Branch not null
     Nachbedingung: Fügt ein oder mehrere Branches in die branchlist
     */
    public void addBranch(Branch... branch){
        branchList.addAll(Arrays.asList(branch));
    }

    /**
     * 
     Invariante: leaflist ist nicht null
     Vorbedingung: Leaf not null
     Nachbedingung: Fügt ein oder mehrere Leaves in die branchlist
     */
    public void addLeaf(Leaf... leaf){
        leafList.addAll(Arrays.asList(leaf));
    }

    /**
     * 
     Invariante:
     Vorbedingung: currentTime ist nicht null, toLocalDateTime ist nicht null
     Nachbedingung: lässt diesen Tree in Richtung maxHeight wachsen

     GOOD: hoher Klassenzusammenhalt, niedrige Objektkopplung durch Vererbung. Niederige
     Anzahl an Parametern wird dadurch erreicht das bestimmte Instanzvariablen bereits
     in der Elternklasse Nature initialisiert werden
     */
    @Override
    public void growTo(LocalDateTime toLocalDateTime) {
        LocalDateTime currentTime = super.getCurrentTime();

        long diffDays = ChronoUnit.DAYS.between(currentTime, toLocalDateTime);
        for(int i = 0; i < diffDays; i++){
            if(SEASON.FALL == super.getSeasons()[currentTime.getMonthValue() - 1]){
                int randomLeafDrop = ThreadLocalRandom.current().nextInt(0,leafList.size() + 1);
                int randomBranchDrop = ThreadLocalRandom.current().nextInt(0, branchList.size() + 1);

                for(int a = 0; a < randomLeafDrop; a++){
                    leafList.remove(0);
                }
                for(int a = 0; a < randomBranchDrop; a++){
                    branchList.remove(0);
                }
            }else if(SEASON.WINTER == super.getSeasons()[currentTime.getMonthValue() - 1]){
                leafList = new ArrayList<>(); //drop all leaves
            }else if(SEASON.SPRING == super.getSeasons()[currentTime.getMonthValue() - 1]){
                //simulate growth
                int randomAmountBranches = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                int randomAmountLeaves = ThreadLocalRandom.current().nextInt(randomAmountBranches, 100 + 1);

                for(int branchAmount = 0; branchAmount < randomAmountBranches; branchAmount++){
                    int randomLength = ThreadLocalRandom.current().nextInt(1, 30 + 1);
                    int randomMaxLength = ThreadLocalRandom.current().nextInt(35, 140 + 1);
                    addBranch(new Branch(randomLength, randomMaxLength));
                }

                for(int leafAmount = 0; leafAmount < randomAmountLeaves; leafAmount++){
                    int randomArea = ThreadLocalRandom.current().nextInt(3, 10 + 1);
                    addLeaf(new Leaf(randomArea));
                }

            }if(SEASON.SUMMER == super.getSeasons()[currentTime.getMonthValue() - 1]){
                //simulate growth
                //existing branches grow bigger
                double randomAbsoluteBranchGrowth = ThreadLocalRandom.current().nextDouble(0, 0.5 + 1);
                for(Branch b : branchList){
                    b.grow(randomAbsoluteBranchGrowth);
                }

                //new leaves and branches
                int randomAmountBranches = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                int randomAmountLeaves = ThreadLocalRandom.current().nextInt(randomAmountBranches, 20 + 1);

                for(int branchAmount = 0; branchAmount < randomAmountBranches; branchAmount++){
                    int randomLength = ThreadLocalRandom.current().nextInt(1, 30 + 1);
                    int randomMaxLength = ThreadLocalRandom.current().nextInt(35, 140 + 1);
                    addBranch(new Branch(randomLength, randomMaxLength));
                }

                for(int leafAmount = 0; leafAmount < randomAmountLeaves; leafAmount++){
                    int randomArea = ThreadLocalRandom.current().nextInt(3, 10 + 1);
                    addLeaf(new Leaf(randomArea));
                }
            }

            double randomGrowAbsolute = ThreadLocalRandom.current().nextDouble(0,1.5 + 1);
            if(this.getHeight() + randomGrowAbsolute < getMaxHeight()){
                this.setHeight(this.getHeight()+randomGrowAbsolute);
            }

            super.setCurrentTime(currentTime.plusDays((1))); //advance internal time of Tree
        }
    }
}

class Branch{
    private double length;
    private double maxLength;

    /**
     * 
     Invariante:
     Vorbedingung: length und maxlength in cm, length <= maxLength
     Nachbedingung: Erstellt eine Instanz der Klasse Branch
     */
    public Branch(double length, double maxLength) {
        this.length = length;
        this.maxLength = maxLength;
    }

    /**
     * 
     Invariante:
     Vorbedingung: length >= 0
     Nachbedingung: Setzt Instanzvariable length auf den Parameter Wert length,
     wenn length > maxLength dann wird ein Error-Log in die Konsole geschrieben
     */
    public void grow(double length){
        if(length <= maxLength){
            this.length = length;
        }else{
            System.err.println("Length may not exceed max length");
        }
    }

    /**
     * 
     Invariante: length und maxlength in cm, length <= maxLength
     Vorbedingung:
     Nachbedingung: gibt length zurück
     */
    public double getLength() {
        return length;
    }

    /**
     * 
     Invariante: length und maxlength in cm, length <= maxLength
     Vorbedingung:
     Nachbedingung: gibt maxLength zurück
     */
    public double getMaxLength() {
        return maxLength;
    }
}

class Leaf{
    private double area; //cm^2

    /**
     * 
     Invariante:
     Vorbedingung: area is in cm^2
     Nachbedingung: Erstellt eine Instanz der Klasse Leaf mit den Parameterwerten
     */
    public Leaf(double area){
        this.area = area;
    }

    /**
     * 
     Invariante: area is in cm^2
     Vorbedingung:
     Nachbedingung: gibt area zurück
     */
    public double getArea() {
        return area;
    }
}
