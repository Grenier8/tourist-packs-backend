package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.VehicleDto;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;
import cu.edu.cujae.touristpacks.core.service.IVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceServiceImpl implements ITransportServiceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    ITransportModalityService transportModalityService;

    @Override
    public List<TransportServiceDto> getTransportServices() throws SQLException {
        List<TransportServiceDto> list = new ArrayList<>();

        String function = "{?= call select_all_transport_service()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_transport_service = resultSet.getInt(1);
                TransportModalityDto tmodality = transportModalityService.getTransportModalityById(resultSet.getInt(2));
                VehicleDto vehicle = vehicleService.getVehicleById(resultSet.getInt(3));
                int transport_service_price = resultSet.getInt(4);
                String service_name = resultSet.getString(5);

                TransportServiceDto transportService = new TransportServiceDto(id_transport_service, service_name,
                        vehicle,
                        tmodality, transport_service_price);
                list.add(transportService);
            }
        }
        return list;
    }

    @Override
    public TransportServiceDto getTransportServiceById(int transportServiceId) throws SQLException {
        TransportServiceDto transportService = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM transport_service where id_transport_service = ?");

            pstmt.setInt(1, transportServiceId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                TransportModalityDto tmodality = transportModalityService.getTransportModalityById(resultSet.getInt(2));
                VehicleDto vehicle = vehicleService.getVehicleById(resultSet.getInt(3));
                int transport_service_price = resultSet.getInt(4);
                String service_name = resultSet.getString(5);

                transportService = new TransportServiceDto(transportServiceId, service_name, vehicle,
                        tmodality, transport_service_price);
            }
        }
        return transportService;
    }

    @Override
    public TransportServiceDto getTransportServiceByName(String transportServiceName) throws SQLException {
        TransportServiceDto transportService = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM transport_service where service_name = ?");

            pstmt.setString(1, transportServiceName);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idTransportService = resultSet.getInt(1);
                TransportModalityDto tmodality = transportModalityService.getTransportModalityById(resultSet.getInt(2));
                VehicleDto vehicle = vehicleService.getVehicleById(resultSet.getInt(3));
                int transportServicePrice = resultSet.getInt(4);

                transportService = new TransportServiceDto(idTransportService, transportServiceName, vehicle,
                        tmodality, transportServicePrice);
            }
        }
        return transportService;
    }

    @Override
    public void createTransportService(TransportServiceDto transportService) throws SQLException {
        String function = "{call transport_service_insert(?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportService.getTmodality().getIdTransportModality());
            statement.setInt(2, transportService.getVehicle().getIdVehicle());
            statement.setDouble(3, transportService.getTranspServicePrice());
            statement.setString(4, transportService.getTransportServiceName());
            statement.execute();
        }
    }

    @Override
    public void updateTransportService(TransportServiceDto transportService) throws SQLException {
        String function = "{call transport_service_update(?,?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, transportService.getIdTransportService());
            statement.setInt(2, transportService.getTmodality().getIdTransportModality());
            statement.setInt(3, transportService.getVehicle().getIdVehicle());
            statement.setDouble(4, transportService.getTranspServicePrice());
            statement.setString(5, transportService.getTransportServiceName());
            statement.execute();
        }
    }

    @Override
    public void deleteTransportService(int idTransportService) throws SQLException {
        String function = "{call transport_service_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTransportService);
            statement.execute();
        }
    }

}
