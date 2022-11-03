package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;

public interface ITransportServiceService {
    List<TransportServiceDto> getTransportServices() throws SQLException;

    TransportServiceDto getTransportServiceById(int transportServiceId) throws SQLException;

    TransportServiceDto getTransportServiceByName(String transportServiceName) throws SQLException;

    void createTransportService(TransportServiceDto transportService) throws SQLException;

    void updateTransportService(TransportServiceDto transportService) throws SQLException;

    void deleteTransportService(int idTransportService) throws SQLException;
}
