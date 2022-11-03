package cu.edu.cujae.touristpacks.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ServiceTypeDto;
import cu.edu.cujae.touristpacks.core.service.IServiceTypeService;

import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {

    @Override
    public List<ServiceTypeDto> getServiceTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceTypeDto getServiceTypeById(int serviceTypeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceTypeDto getServiceTypeByName(String serviceTypeName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createServiceType(ServiceTypeDto serviceType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateServiceType(ServiceTypeDto serviceType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteServiceType(int idServiceType) {
        // TODO Auto-generated method stub

    }

}
