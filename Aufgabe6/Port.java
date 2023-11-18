interface Port {

    void store(double kWh);
    void take(double kWh);
    double stored();
    double taken();

    //PortType type();
}

