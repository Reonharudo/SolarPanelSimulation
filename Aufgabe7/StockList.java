import java.util.*;

/**
 * INVARIANTE: storageList instanceof ArrayList == true && assignedInverterMap instanceof HashMap == true
 */
public class StockList {
    private final List<Inverter> storageList;
    private final Map<Inverter, Client> assignedInverterMap;

    public StockList() {
        storageList = new ArrayList<>();
        assignedInverterMap = new HashMap<>();
    }

    /**
     * VORBEDINGUNG: inverter != null
     * NACHBEDINGUNG: inverter wird zu storageList hinzugefügt
     */
    public void addInverter(Inverter inverter){
        storageList.add(inverter);
    }

    /**
     * VORBEDINGUNG: inverter != null
     * NACHBEDINGUNG: inverter wird vom storageList - falls vorhanden - entfernt und
     * retourniert bei erfolgreichem Löschen true, ansonsten false
     */
    public boolean delInverter(Inverter inverter){
        return storageList.remove(inverter);
    }

    /**
     * VORBEDINGUNG: client != null
     * NACHBEDINGUNG: falls kompatibler Inverter gefunden: zu einem Client wird von storageList gelöscht, der kompatible Inverter wird mit
     * dem übergebenen Client in assignedInverterMap hinzugefügt und der kompatible Inverter wird zurückgegeben; ansonsten wird null retourniert
     */
    public Inverter assignInverter(Client client){
        for(Inverter inverter : storageList){
            if(inverter.check(client.getkW(), client.getBattery())){
                assignedInverterMap.put(inverter, client);
                delInverter(inverter);
                return inverter;
            }
        }
        return null;
    }

    /**
     * VORBEDINGUNG: client != null && inverter != null
     * NACHBEDINGUNG: löscht die Zuordnung eines Inverters zu einem Client und fügt diesen Inverter zur storageList hinzu,
     * falls keine Zuordnung vorhanden, wird null retourniert
     */
    public Inverter returnInverter(Client client, Inverter inverter){
        if(assignedInverterMap.remove(inverter) != null){
            storageList.add(inverter);
            return inverter;
        }else{
            return null;
        }
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: gibt die Summe der Zählerstände in Kilowattstunden aller Inverter in storageList zurück
     */
    public double kWhStore(){
        double kwhSum = 0;
        for(Inverter inverter : storageList){
            kwhSum += inverter.getkWh();
        }
        return kwhSum;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: gibt die Summe der Zählerstände in Kilowattstunden
     * aller Inverter zurück, die bei Kunden installiert sind
     */
    public double kWhClient(){
        double kwhSum = 0;
        for(Inverter inverter : assignedInverterMap.keySet()){
            kwhSum += inverter.getkWh();
        }
        return kwhSum;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: zeigt alle Inverter der storageList mit allen
     * Informationen auf der Konsole an
     */
    public void inverterShow(){
        StringBuilder erg = new StringBuilder("##### SHOWING CURRENT INVERTER STORAGE #####" + System.lineSeparator());
        for(Inverter inverter : storageList){
            erg.append(inverter.toString()).append(System.lineSeparator());
        }
        System.out.println(erg);
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: zeigt alle Clients mit allen Informationen (insbesondere welche Inverter bei diesem
     * Client installiert sind) auf der Konsole an.
     */
    public void clientsShow(){
        StringBuilder erg = new StringBuilder("##### SHOWING ASSIGNED INVERTER OF CLIENTS #####" + System.lineSeparator());

        Set<Client> uniqueClientsSet = new HashSet<>(assignedInverterMap.values());

        for(Client client : uniqueClientsSet){
            erg.append(client.toString()).append(" has following inverters: ").append(System.lineSeparator());
            erg.append("-----");
            for(Inverter inverter : assignedInverterMap.keySet()){
                if(assignedInverterMap.get(inverter).equals(client)){
                    erg.append(inverter.toString()).append(System.lineSeparator());
                }
            }
        }
        System.out.println(erg);
    }
}
