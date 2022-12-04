
package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.dto.ProviderDto;
import cu.edu.cujae.touristpacks.core.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;
import cu.edu.cujae.touristpacks.core.service.IProviderService;
import cu.edu.cujae.touristpacks.core.service.ITransportContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportContractServiceImpl implements ITransportContractService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IProviderService providerService;

    @Override
    public List<TransportContractDto> getTransportContracts() throws SQLException {
        List<TransportContractDto> list = new ArrayList<>();

        String function = "{?= call select_all_transport_contract()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_transport_contract = resultSet.getInt(1);
                ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));
                ContractDto contract = contractService.getContractById(resultSet.getInt(3));

                TransportContractDto transportContract = new TransportContractDto(id_transport_contract,
                        contract.getIdContract(), contract.getContractTitle(),
                        contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider);
                list.add(transportContract);
            }
        }
        return list;
    }

    @Override
    public TransportContractDto getTransportContractById(int transportContractId) throws SQLException {
        TransportContractDto transportContract = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM transport_contract where id_transport_contract = ?");

        pstmt.setInt(1, transportContractId);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));
            ContractDto contract = contractService.getContractById(resultSet.getInt(3));

            transportContract = new TransportContractDto(transportContractId,
                    contract.getIdContract(), contract.getContractTitle(),
                    contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider);

        }

        return transportContract;
    }

    @Override
    public TransportContractDto getTransportContractByTitle(String transportContractTitle) throws SQLException {
        TransportContractDto transportContract = null;
        ContractDto contract = contractService.getContractByTitle(transportContractTitle);
        int id_contratc = contract.getIdContract();

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM transport_contract where id_transport_contract = ?");

        pstmt.setInt(1, id_contratc);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int id_transport_contract = resultSet.getInt(1);
            ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));

            transportContract = new TransportContractDto(id_transport_contract,
                    contract.getIdContract(), contract.getContractTitle(),
                    contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider);
        }

        return transportContract;
    }

    @Override
    public void createTransportContract(TransportContractDto transportContract) throws SQLException {
        String function = "{call transport_contract_insert(?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContract.getProvider().getIdProvider());
            statement.setInt(2, transportContract.getIdContract());
            statement.execute();
        }
    }

    @Override
    public void updateTransportContract(TransportContractDto transportContract) throws SQLException {
        String function = "{call transport_contract_update(?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContract.getIdTransportContract());
            statement.setInt(2, transportContract.getProvider().getIdProvider());
            statement.setInt(3, transportContract.getIdContract());
            statement.execute();
        }
    }

    @Override
    public void deleteTransportContract(int idTransportContract) throws SQLException {
        String function = "{call transport_contract_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTransportContract);
            statement.execute();
        }
    }

}
