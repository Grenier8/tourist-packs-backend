package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ServiceTypeDto;

public interface IServiceTypeService {
    List<ServiceTypeDto> getServiceTypes() throws SQLException;

    ServiceTypeDto getServiceTypeById(int serviceTypeId) throws SQLException;

    ServiceTypeDto getServiceTypeByName(String serviceTypeName) throws SQLException;

    void createServiceType(ServiceTypeDto serviceType) throws SQLException;

    void updateServiceType(ServiceTypeDto serviceType) throws SQLException;

    void deleteServiceType(int idServiceType) throws SQLException;
}
