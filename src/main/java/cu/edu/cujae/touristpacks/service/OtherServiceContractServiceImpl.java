package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.dto.ServiceTypeDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractService;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;
import cu.edu.cujae.touristpacks.core.service.IServiceTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtherServiceContractServiceImpl implements IOtherServiceContractService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IServiceTypeService serviceTypeService;

    @Autowired
    private IProvinceService provinceService;

    @Override
    public List<OtherServiceContractDto> getOtherServiceContracts() throws SQLException {
        List<OtherServiceContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_other_service_contract()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idOtherServiceContract = resultSet.getInt(1);
                String contractDescription = resultSet.getString(2);
                double costPerPax = resultSet.getDouble(3);
                int idServiceType = resultSet.getInt(4);
                int idProvince = resultSet.getInt(5);
                int idContract = resultSet.getInt(6);

                ContractDto contract = contractService.getContractById(idContract);

                String contractTitle = contract.getContractTitle();
                LocalDate startDate = contract.getStartDate();
                LocalDate endDate = contract.getEndDate();
                LocalDate conciliationDate = contract.getConciliationDate();
                int year = conciliationDate.getYear();

                ServiceTypeDto serviceType = serviceTypeService.getServiceTypeById(idServiceType);
                ProvinceDto province = provinceService.getProvinceById(idProvince);

                OtherServiceContractDto otherServiceContract = new OtherServiceContractDto(idOtherServiceContract,
                        contractTitle,
                        startDate, endDate, conciliationDate, contractDescription, costPerPax, serviceType, province,
                        idContract);

                list.add(otherServiceContract);
            }
        }

        return list;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractById(int idOtherServiceContract) throws SQLException {
        OtherServiceContractDto otherServiceContract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM other_service_contract where id_other_service_contract = ?");

            pstmt.setInt(1, idOtherServiceContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String contractDescription = resultSet.getString(2);
                double costPerPax = resultSet.getDouble(3);
                int idServiceType = resultSet.getInt(4);
                int idProvince = resultSet.getInt(5);
                int idContract = resultSet.getInt(6);

                ContractDto contract = contractService.getContractById(idContract);

                String contractTitle = contract.getContractTitle();
                LocalDate startDate = contract.getStartDate();
                LocalDate endDate = contract.getEndDate();
                LocalDate conciliationDate = contract.getConciliationDate();
                int year = conciliationDate.getYear();

                ServiceTypeDto serviceType = serviceTypeService.getServiceTypeById(idServiceType);
                ProvinceDto province = provinceService.getProvinceById(idProvince);

                otherServiceContract = new OtherServiceContractDto(idOtherServiceContract,
                        contractTitle,
                        startDate, endDate, conciliationDate, contractDescription, costPerPax, serviceType, province,
                        idContract);
            }
        }

        return otherServiceContract;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractByTitle(String contractTitle)
            throws SQLException {
        OtherServiceContractDto otherServiceContract = null;

        ContractDto contract = contractService.getContractByTitle(contractTitle);
        int idContract = contract.getIdContract();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM other_service_contract WHERE id_contract = ?");

            pstmt.setInt(1, idContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idOtherServiceContract = resultSet.getInt(1);
                String contractDescription = resultSet.getString(2);
                double costPerPax = resultSet.getDouble(3);
                int idServiceType = resultSet.getInt(4);
                int idProvince = resultSet.getInt(5);

                LocalDate startDate = contract.getStartDate();
                LocalDate endDate = contract.getEndDate();
                LocalDate conciliationDate = contract.getConciliationDate();
                int year = conciliationDate.getYear();

                ServiceTypeDto serviceType = serviceTypeService.getServiceTypeById(idServiceType);
                ProvinceDto province = provinceService.getProvinceById(idProvince);

                otherServiceContract = new OtherServiceContractDto(idOtherServiceContract,
                        contractTitle,
                        startDate, endDate, conciliationDate, contractDescription, costPerPax, serviceType, province,
                        idContract);
            }
        }

        return otherServiceContract;
    }

    @Override
    public void createOtherServiceContract(OtherServiceContractDto otherServiceContract) throws SQLException {
        contractService.createContract(otherServiceContract);
        int idContract = contractService.getContractByTitle(otherServiceContract.getContractTitle()).getIdContract();

        String function = "{call other_service_contract_insert(?,?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, otherServiceContract.getContractDescription());
            statement.setDouble(2, otherServiceContract.getCostPerPax());
            statement.setInt(3, otherServiceContract.getServiceType().getIdServiceType());
            statement.setInt(4, otherServiceContract.getProvince().getIdProvince());
            statement.setInt(5, idContract);
            statement.execute();
        }

    }

    @Override
    public void updateOtherServiceContract(OtherServiceContractDto otherServiceContract) throws SQLException {
        otherServiceContract.setIdContract(
                getOtherServiceContractById(otherServiceContract.getIdOtherServiceContract()).getIdContract());
        contractService.updateContract(otherServiceContract);
        int idContract = contractService.getContractByTitle(otherServiceContract.getContractTitle()).getIdContract();

        String function = "{call other_service_contract_update(?,?,?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, otherServiceContract.getIdOtherServiceContract());
            statement.setString(2, otherServiceContract.getContractDescription());
            statement.setDouble(3, otherServiceContract.getCostPerPax());
            statement.setInt(4, otherServiceContract.getServiceType().getIdServiceType());
            statement.setInt(5, otherServiceContract.getProvince().getIdProvince());
            statement.setInt(6, idContract);
            statement.execute();
        }
    }

    @Override
    public void deleteOtherServiceContract(int idOtherServiceContract) throws SQLException {
        int idContract = getOtherServiceContractById(idOtherServiceContract).getIdContract();

        String function = "{call other_service_contract_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idOtherServiceContract);
            statement.execute();
        }

        contractService.deleteContract(idContract);
    }

}
