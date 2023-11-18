public class Inverter implements PVComponent{
    private int compatibleMethodCall = 0;
    private String[] certified;
    private double kWp;

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: wenn certifications.length >= 2 || kwP > = 0 == false dann wird eine exception geworfen, ansonsten
     * wird die Klasse instanziert
     */
    public Inverter(double kWp, String... certifications){
        setkWp(kWp);
        setCertified(certifications);
    }
    /**
     * VORBEDINGUNG: PVComponent != null
     * INVARIANTE:
     * NACHBEDINGUNG: returns true if parameter and this has at least one common certiciate when true increments compatibleMethodCall + 1, otherwise false
     */
    @Override
    public boolean compatible(PVComponent o) {
        for(String certificate : this.certified()){
            for(String paramCertificate : o.certified()){
                if(paramCertificate.equals(certificate)){
                    compatibleMethodCall++;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: compatibleMethodCall remains unchanged
     * NACHBEDINGUNG: returns amount of calls of compatible() which result was true
     */
    @Override
    public int rate() {
        return compatibleMethodCall;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: returns string array of this certificates
     */
    @Override
    public String[] certified() {
        return certified;
    }

    private void setCertified(String[] certified) {
        if(certified.length >= 2){
            this.certified = certified;
        }else{
            throw new RuntimeException("Inverter needs to have at least two certifications strings");
        }
    }

    private void setkWp(double kWp) {
        if(kWp >= 0 ){
            this.kWp = kWp;
        }else{
            throw new RuntimeException("Kwp may not be negative. Failed to instantiate "+this.getClass().getSimpleName());
        }
    }

    public double kWp(){
        return kWp;
    }
}
