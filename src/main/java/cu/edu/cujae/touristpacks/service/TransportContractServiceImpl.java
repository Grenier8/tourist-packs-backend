
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
import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;
import cu.edu.cujae.touristpacks.core.service.IProviderService;
import cu.edu.cujae.touristpacks.core.service.ITransportContractService;
import cu.edu.cujae.touristpacks.core.service.ITransportContractTransportServiceService;

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

    @Autowired
    private ITransportContractTransportServiceService transportContractTransportServiceService;

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
                int idTransportContract = resultSet.getInt(1);
                ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));
                ContractDto contract = contractService.getContractById(resultSet.getInt(3));
                List<TransportServiceDto> transportServices = transportContractTransportServiceService
                        .getTransportServicesByIdTransportContract(idTransportContract);

                TransportContractDto transportContract = new TransportContractDto(idTransportContract,
                        contract.getIdContract(), contract.getContractTitle(),
                        contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider,
                        transportServices);
                list.add(transportContract);
            }
        }
        return list;
    }

    @Override
    public TransportContractDto getTransportContractById(int idTransportContract) throws SQLException {
        TransportContractDto transportContract = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM transport_contract where id_transport_contract = ?");

            pstmt.setInt(1, idTransportContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));
                ContractDto contract = contractService.getContractById(resultSet.getInt(3));
                List<TransportServiceDto> transportServices = transportContractTransportServiceService
                        .getTransportServicesByIdTransportContract(idTransportContract);

                transportContract = new TransportContractDto(idTransportContract,
                        contract.getIdContract(), contract.getContractTitle(),
                        contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider,
                        transportServices);
            }
        }

        return transportContract;
    }

    @Override
    public TransportContractDto getTransportContractByTitle(String contractTitle) throws SQLException {
        TransportContractDto transportContract = null;

        ContractDto contract = contractService.getContractByTitle(contractTitle);
        int idContract = contract.getIdContract();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM transport_contract where id_contract = ?");

            pstmt.setInt(1, idContract);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idTransportContract = resultSet.getInt(1);
                ProviderDto provider = providerService.getProviderById(resultSet.getInt(2));
                List<TransportServiceDto> transportServices = transportContractTransportServiceService
                        .getTransportServicesByIdTransportContract(idTransportContract);

                transportContract = new TransportContractDto(idTransportContract,
                        contract.getIdContract(), contract.getContractTitle(),
                        contract.getStartDate(), contract.getEndDate(), contract.getConciliationDate(), provider,
                        transportServices);

            }
        }

        return transportContract;
    }

    @Override
    public void createTransportContract(TransportContractDto transportContract) throws SQLException {
        contractService.createContract(transportContract);
        int idContract = contractService.getContractByTitle(transportContract.getContractTitle()).getIdContract();

        String function = "{call transport_contract_insert(?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContract.getProvider().getIdProvider());
            statement.setInt(2, idContract);
            statement.execute();
        }

        TransportContractDto insertedTransportContract = getTransportContractByTitle(
                transportContract.getContractTitle());

        for (TransportServiceDto transportService : transportContract.getTransportServices()) {
            TransportContractTransportServiceDto transportContractTransportService = new TransportContractTransportServiceDto(
                    insertedTransportContract, transportService);
            transportContractTransportServiceService
                    .createTransportContractTransportService(transportContractTransportService);
        }

    }

    @Override
    public void updateTransportContract(TransportContractDto transportContract) throws SQLException {
        transportContract
                .setIdContract(getTransportContractById(transportContract.getIdTransportContract()).getIdContract());
        contractService.updateContract(transportContract);

        String function = "{call transport_contract_update(?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContract.getIdTransportContract());
            statement.setInt(2, transportContract.getProvider().getIdProvider());
            statement.setInt(3, transportContract.getIdContract());
            statement.execute();
        }

        List<TransportServiceDto> formerTransportServices = transportContractTransportServiceService
                .getTransportServicesByIdTransportContract(transportContract.getIdTransportContract());
        List<TransportServiceDto> newTransportServices = transportContract.getTransportServices();

        for (TransportServiceDto formerTransportService : formerTransportServices) {
            boolean deleted = true;
            for (TransportServiceDto newTransportService : newTransportServices) {
                if (formerTransportService.getIdTransportService() == newTransportService
                        .getIdTransportService()) {
                    deleted = false;
                    newTransportServices.remove(newTransportService);
                    break;
                }
            }
            if (deleted) {
                transportContractTransportServiceService.deleteTransportContractTransportServiceByIds(
                        transportContract.getIdTransportContract(),
                        formerTransportService.getIdTransportService());
            }
        }

        for (TransportServiceDto newTransportService : newTransportServices) {
            transportContractTransportServiceService
                    .createTransportContractTransportService(
                            new TransportContractTransportServiceDto(transportContract, newTransportService));
        }
    }

    @Override
    public void deleteTransportContract(int idTransportContract) throws SQLException {
        transportContractTransportServiceService
                .deleteTransportContractTransportServiceByIdTransportContract(idTransportContract);

        int idContract = getTransportContractById(idTransportContract).getIdContract();

        String function = "{call transport_contract_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTransportContract);
            statement.execute();
        }

        contractService.deleteContract(idContract);
    }

}
