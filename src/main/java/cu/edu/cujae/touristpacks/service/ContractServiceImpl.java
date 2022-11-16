package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ContractDto> getContracts() throws SQLException {
        List<ContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_contract()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idContract = resultSet.getInt(1);
                LocalDate startDate = resultSet.getDate(2).toLocalDate();
                LocalDate endDate = resultSet.getDate(3).toLocalDate();
                LocalDate conciliationDate = resultSet.getDate(4).toLocalDate();
                int year = resultSet.getInt(5);
                String contractTitle = resultSet.getString(6);

                ContractDto dto = new ContractDto(idContract, contractTitle, startDate, endDate, conciliationDate);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public ContractDto getContractById(int idContract) throws SQLException {
        ContractDto contract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM contract where id_contract = ?");

            pstmt.setInt(1, idContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                LocalDate startDate = resultSet.getDate(2).toLocalDate();
                LocalDate endDate = resultSet.getDate(3).toLocalDate();
                LocalDate conciliationDate = resultSet.getDate(4).toLocalDate();
                int year = resultSet.getInt(5);
                String contractTitle = resultSet.getString(6);

                contract = new ContractDto(idContract, contractTitle, startDate, endDate, conciliationDate);
            }
        }

        return contract;
    }

    @Override
    public ContractDto getContractByTitle(String contractTitle) throws SQLException {
        ContractDto contract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM contract where contract_title = ?");

            pstmt.setString(1, contractTitle);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idContract = resultSet.getInt(1);
                LocalDate startDate = resultSet.getDate(2).toLocalDate();
                LocalDate endDate = resultSet.getDate(3).toLocalDate();
                LocalDate conciliationDate = resultSet.getDate(4).toLocalDate();
                int year = resultSet.getInt(5);

                contract = new ContractDto(idContract, contractTitle, startDate, endDate, conciliationDate);
            }
        }

        return contract;
    }

    @Override
    public void createContract(ContractDto contract) throws SQLException {
        String function = "{call contract_insert(?,?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setDate(1, Date.valueOf(contract.getStartDate()));
            statement.setDate(2, Date.valueOf(contract.getEndDate()));
            statement.setDate(3, Date.valueOf(contract.getConciliationDate()));
            statement.setInt(4, contract.getConciliationDate().getYear());
            statement.setString(5, contract.getContractTitle());
            statement.execute();
        }

    }

    @Override
    public void updateContract(ContractDto contract) throws SQLException {
        String function = "{call contract_update(?,?,?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, contract.getIdContract());
            statement.setDate(2, Date.valueOf(contract.getStartDate()));
            statement.setDate(3, Date.valueOf(contract.getEndDate()));
            statement.setDate(4, Date.valueOf(contract.getConciliationDate()));
            statement.setInt(5, contract.getConciliationDate().getYear());
            statement.setString(6, contract.getContractTitle());
            statement.execute();
        }

    }

    @Override
    public void deleteContract(int idContract) throws SQLException {
        String function = "{call contract_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idContract);
            statement.execute();
        }

    }

}
