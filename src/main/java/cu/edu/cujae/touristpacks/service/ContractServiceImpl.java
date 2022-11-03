package cu.edu.cujae.touristpacks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService {

    @Override
    public List<ContractDto> getContracts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContractDto getContractById(int contractId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContractDto getContractByName(String contractName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createContract(ContractDto contract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateContract(ContractDto contract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteContract(int idContract) {
        // TODO Auto-generated method stub

    }

}
