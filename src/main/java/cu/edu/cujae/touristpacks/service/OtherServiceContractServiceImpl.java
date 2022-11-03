package cu.edu.cujae.touristpacks.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractService;

import org.springframework.stereotype.Service;

@Service
public class OtherServiceContractServiceImpl implements IOtherServiceContractService {

    @Override
    public List<OtherServiceContractDto> getOtherServiceContracts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractById(int otherServiceContractId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractByName(String otherServiceContractName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createOtherServiceContract(OtherServiceContractDto otherServiceContract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOtherServiceContract(OtherServiceContractDto otherServiceContract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteOtherServiceContract(int idOtherServiceContract) {
        // TODO Auto-generated method stub

    }

}
