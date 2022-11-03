package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;

public interface IOtherServiceContractService {
    List<OtherServiceContractDto> getOtherServiceContracts();

    OtherServiceContractDto getOtherServiceContractById(int otherServiceContractId);

    OtherServiceContractDto getOtherServiceContractByName(String otherServiceContractName);

    void createOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void updateOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void deleteOtherServiceContract(int idOtherServiceContract);
}
