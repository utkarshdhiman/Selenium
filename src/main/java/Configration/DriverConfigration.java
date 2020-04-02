package Configration;

public class DriverConfigration {
    public DriverTypes getDrivertype() {
        return drivertype;
    }

    public void setDrivertype(DriverTypes drivertype) {
        this.drivertype = drivertype;
    }

    DriverTypes drivertype;

    public enum DriverTypes{
        CHROME,EDGE,IE,SAFARI,FIREFOX
    }
}
