package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;

public interface IOtherServiceContractService {
    List<OtherServiceContractDto> getOtherServiceContracts() throws SQLException;

    OtherServiceContractDto getOtherServiceContractById(int otherServiceContractId) throws SQLException;

    OtherServiceContractDto getOtherServiceContractByTitle(String otherServiceContractName) throws SQLException;

    void createOtherServiceContract(OtherServiceContractDto otherServiceContract) throws SQLException;

    void updateOtherServiceContract(OtherServiceContractDto otherServiceContract) throws SQLException;

    void deleteOtherServiceContract(int idOtherServiceContract) throws SQLException;
}
