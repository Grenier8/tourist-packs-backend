package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;

public interface ITransportContractTransportServiceService {
        List<TransportContractTransportServiceDto> getTransportContractTransportServices() throws SQLException;

        TransportContractTransportServiceDto getTransportContractTransportServiceById(
                        int transportContractTransportServiceId) throws SQLException;

        void createTransportContractTransportService(
                        TransportContractTransportServiceDto transportContractTransportService) throws SQLException;

        void updateTransportContractTransportService(
                        TransportContractTransportServiceDto transportContractTransportService) throws SQLException;

        void deleteTransportContractTransportService(int idTransportContractTransportService) throws SQLException;

        List<TransportServiceDto> getTransportServicesByIdTransportContract(int idTransportContract)
                        throws SQLException;

        void deleteTransportContractTransportServiceByIds(int idTransportContract, int idTransportService)
                        throws SQLException;

        void deleteTransportContractTransportServiceByIdTransportContract(int idTransportContract) throws SQLException;
}
