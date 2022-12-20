package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.service.ITransportContractService;
import cu.edu.cujae.touristpacks.core.service.ITransportContractTransportServiceService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportContractTransportServiceServiceImpl implements ITransportContractTransportServiceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ITransportContractService transportContractService;

    @Autowired
    private ITransportServiceService transportServiceService;

    @Override
    public List<TransportContractTransportServiceDto> getTransportContractTransportServices() throws SQLException {
        List<TransportContractTransportServiceDto> list = new ArrayList<>();

        String function = "{?= call select_all_transport_contract_transport_service()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_transport_contract_transport_service = resultSet.getInt(1);
                TransportContractDto tContract = transportContractService.getTransportContractById(resultSet.getInt(2));
                TransportServiceDto tService = transportServiceService.getTransportServiceById(resultSet.getInt(3));

                TransportContractTransportServiceDto transportContractTransportService = new TransportContractTransportServiceDto(
                        id_transport_contract_transport_service,
                        tContract, tService);
                list.add(transportContractTransportService);
            }
        }
        return list;
    }

    @Override
    public TransportContractTransportServiceDto getTransportContractTransportServiceById(
            int transportContractTransportServiceId) throws SQLException {
        TransportContractTransportServiceDto transportContractTransportService = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM transport_contract_transport_service where id_transport_contract_transport_service = ?");

            pstmt.setInt(1, transportContractTransportServiceId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                TransportContractDto tContract = transportContractService.getTransportContractById(resultSet.getInt(2));
                TransportServiceDto tService = transportServiceService.getTransportServiceById(resultSet.getInt(3));

                transportContractTransportService = new TransportContractTransportServiceDto(
                        transportContractTransportServiceId,
                        tContract, tService);
            }
        }
        return transportContractTransportService;
    }

    @Override
    public void createTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) throws SQLException {
        String function = "{call transport_contract_transport_service_insert(?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContractTransportService.getTransportContract().getIdContract());
            statement.setInt(2, transportContractTransportService.getTransportService().getIdTransportService());
            statement.execute();
        }
    }

    @Override
    public void updateTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) throws SQLException {
        String function = "{call transport_contract_transport_service_update(?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportContractTransportService.getIdTransportContractTransportService());
            statement.setInt(2, transportContractTransportService.getTransportContract().getIdContract());
            statement.setInt(3, transportContractTransportService.getTransportService().getIdTransportService());
            statement.execute();
        }
    }

    @Override
    public void deleteTransportContractTransportService(int idTransportContractTransportService) throws SQLException {
        String function = "{call transport_contract_transport_service_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTransportContractTransportService);
            statement.execute();
        }
    }

    @Override
    public List<TransportServiceDto> getTransportServicesByIdTransportContract(int idTransportContract)
            throws SQLException {
        List<TransportServiceDto> list = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM transport_contract_transport_service where id_transport_contract = ?");

            pstmt.setInt(1, idTransportContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idTransportService = resultSet.getInt(3);

                list.add(transportServiceService.getTransportServiceById(idTransportService));

            }
        }
        return list;
    }

    @Override
    public void deleteTransportContractTransportServiceByIds(int idTransportContract, int idTransportService)
            throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM transport_contract_transport_service where id_transport_contract = ? AND id_transport_service = ?");

            pstmt.setInt(1, idTransportContract);
            pstmt.setInt(2, idTransportService);

            pstmt.executeUpdate();

        }

    }

    @Override
    public void deleteTransportContractTransportServiceByIdTransportContract(int idTransportContract)
            throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM transport_contract_transport_service where id_transport_contract = ?");

            pstmt.setInt(1, idTransportContract);

            pstmt.executeUpdate();

        }
    }

}
