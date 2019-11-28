package kata.ex01.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;
    private EtcVersion etcVersion;

    private Driver driver;

    public HighwayDrive() {
    }

    public LocalDateTime getEnteredAt() {
        return this.enteredAt;
    }

    public LocalDateTime getExitedAt() {
        return this.exitedAt;
    }

    public VehicleFamily getVehicleFamily() {
        return this.vehicleFamily;
    }

    public RouteType getRouteType() {
        return this.routeType;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public EtcVersion getEtcVersion(){
    	return this.etcVersion;
	}

    public void setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
    }

    public void setExitedAt(LocalDateTime exitedAt) {
        this.exitedAt = exitedAt;
    }

    public void setVehicleFamily(VehicleFamily vehicleFamily) {
        this.vehicleFamily = vehicleFamily;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setEtcVersion(EtcVersion etcVersion){
    	this.etcVersion = etcVersion;
	}

    public String toString() {
        return "HighwayDrive(enteredAt=" + this.getEnteredAt() + ", exitedAt=" + this.getExitedAt() + ", vehicleFamily=" + this.getVehicleFamily() + ", routeType=" + this.getRouteType() + ", driver=" + this.getDriver() + ")";
    }
}
