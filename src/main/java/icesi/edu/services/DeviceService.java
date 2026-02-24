package icesi.edu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import icesi.edu.models.Device;
import icesi.edu.repositories.DeviceRepository;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void addDevice(String name, String serialNumber, String location, String type, String status) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (serialNumber == null || serialNumber.length() < 5) {
            throw new IllegalArgumentException("El serial debe tener mínimo 5 caracteres");
        }
        if (deviceRepository.getBySerialNumber(serialNumber) != null) {
            throw new IllegalArgumentException("Ya existe un dispositivo con ese serial");
        }
        deviceRepository.save(name, serialNumber, location, type, status);
}

    public void updateStatus(int id, String status){
        if(!status.isEmpty()){
            if(deviceRepository.findById(id) != null){
                deviceRepository.updateStatus(id, status);
            }
        }
    }

    public List<Device> getAll(){
        return deviceRepository.getAll();
    }

}