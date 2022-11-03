package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;

public interface IContractService {
    List<ContractDto> getContracts();

    ContractDto getContractById(int contractId);

    ContractDto getContractByName(String contractName);

    void createContract(ContractDto contract);

    void updateContract(ContractDto contract);

    void deleteContract(int idContract);
}
