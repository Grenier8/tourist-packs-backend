package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportContractDto;

public interface ITransportContractService {
    List<TransportContractDto> getTransportContracts() throws SQLException;

    TransportContractDto getTransportContractById(int transportContractId) throws SQLException;

    TransportContractDto getTransportContractByTitle(String transportContracTitle) throws SQLException;

    void createTransportContract(TransportContractDto transportContract) throws SQLException;

    void updateTransportContract(TransportContractDto transportContract) throws SQLException;

    void deleteTransportContract(int idTransportContract) throws SQLException;
}
