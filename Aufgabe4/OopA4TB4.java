import java.util.ArrayList;

public abstract class OopA4TB4 /*implements StorageBatteryInverter*/ {
    ArrayList<Series> series;
    ArrayList<Storagebattery> batteries;
    double maxkwp;

    OopA4TB4(double maxkWp) {
        this.maxkwp = maxkWp;
    }

    double kWpLimit() {
        return maxkwp;
    }

    void addToString(Series s) {
        if (series.size() == 4) {
            throw new RuntimeException("OopA4TB4 only allows 4 series which are already used");
        }
        double kwp = s.kWp();
        if (maxkwp < (kwp + this.kWp())) {
            throw new RuntimeException("Adding this series would overrun the maxkWp limit.");
        }
        series.add(s);
    }

    double kWp(){
        double a = 0;
        for (Series s : series) {
            a += s.kWp();
        }
        return a;
    }

    void addBatterie(Storagebattery battery) {
        batteries.add(battery);
    }
}
