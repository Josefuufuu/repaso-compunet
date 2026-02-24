package icesi.edu.repositories;

import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import icesi.edu.models.Device;

@Repository
public class DeviceRepository {
    
    private final List<Device> devices = new ArrayList<>();
    private int currentId = 2;

    @PostConstruct
    public void init(){
        Device d1 = new Device();
        d1.setId(1);
        d1.setName("Sensor A");
        d1.setSerialNumber("TMP01");
        d1.setLocation("Universidad ICESI");
        d1.setType("Temperature");
        d1.setStatus("Activo");
        devices.add(d1);
        Device d2 = new Device();
        d2.setId(2);
        d2.setName("Sensor B");
        d2.setSerialNumber("TMP02");
        d2.setLocation("Universidad ICESI");
        d2.setType("Temperature");
        d2.setStatus("Activo");
        devices.add(d2);
    }
    public void save(String name, String serialNumber, String location, String type, String status){
        currentId++;
        Device device = new Device();
        device.setId(currentId);
        device.setName(name);
        device.setSerialNumber(serialNumber);
        device.setLocation(location);
        device.setType(type);
        device.setStatus(status);
        devices.add(device);
    }

    public Device getBySerialNumber(String serialNumber){
        for (Device d : devices){
            if (d.getSerialNumber().equalsIgnoreCase(serialNumber)){
                return d;
            }
        }
        return null;
    }

    public List<Device> getAll(){
        return devices;
    }

    public void updateStatus(int id, String status){
        Device device = findById(id);
        device.setStatus(status);
    }

    public Device findById(int id){
        for(Device d : devices){
            if (d.getId() == id){
                return d;
            }
        }
        return null;
    }

    public List<Device> getDevices() {
        return devices;
    }

}
