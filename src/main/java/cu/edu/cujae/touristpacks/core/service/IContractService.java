package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;

public interface IContractService {
    List<ContractDto> getContracts() throws SQLException;

    ContractDto getContractById(int contractId) throws SQLException;

    ContractDto getContractByTitle(String contractTitle) throws SQLException;

    void createContract(ContractDto contract) throws SQLException;

    void updateContract(ContractDto contract) throws SQLException;

    void deleteContract(int idContract) throws SQLException;
}
