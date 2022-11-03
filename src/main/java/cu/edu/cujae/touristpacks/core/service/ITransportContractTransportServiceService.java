package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;

public interface ITransportContractTransportServiceService {
        List<TransportContractTransportServiceDto> getTransportContractTransportServices() throws SQLException;

        TransportContractTransportServiceDto getTransportContractTransportServiceById(
                        int transportContractTransportServiceId) throws SQLException;

        void createTransportContractTransportService(
                        TransportContractTransportServiceDto transportContractTransportService) throws SQLException;

        void updateTransportContractTransportService(
                        TransportContractTransportServiceDto transportContractTransportService) throws SQLException;

        void deleteTransportContractTransportService(int idTransportContractTransportService) throws SQLException;
}
