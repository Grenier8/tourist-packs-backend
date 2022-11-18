package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.dto.TransportServiceTouristPackDto;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceTouristPackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceTouristPackServiceImpl implements ITransportServiceTouristPackService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ITouristPackService touristPackService;

    @Autowired
    ITransportServiceService transportServiceService;

    @Override
    public List<TransportServiceTouristPackDto> getTransportServiceTouristPacks() throws SQLException {
        List<TransportServiceTouristPackDto> list = new ArrayList<>();

        String function = "{?= call select_all_transport_service_turist_pack()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idPack = resultSet.getInt(2);
                int idTransportService = resultSet.getInt(3);

                TouristPackDto touristPack = touristPackService.getTouristPackById(idPack);
                TransportServiceDto transportService = transportServiceService
                        .getTransportServiceById(idTransportService);

                TransportServiceTouristPackDto dto = new TransportServiceTouristPackDto(id, transportService,
                        touristPack);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public TransportServiceTouristPackDto getTransportServiceTouristPackById(int idTransportServiceTouristPack)
            throws SQLException {
        TransportServiceTouristPackDto transportServiceTouristPack = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM transport_service_turist_pack where id_transport_service_turist_pack = ?");

            pstmt.setInt(1, idTransportServiceTouristPack);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idTouristPack = resultSet.getInt(2);
                int idTransportService = resultSet.getInt(3);

                TouristPackDto touristPack = touristPackService.getTouristPackById(idTouristPack);
                TransportServiceDto transportService = transportServiceService
                        .getTransportServiceById(idTransportService);

                transportServiceTouristPack = new TransportServiceTouristPackDto(idTransportServiceTouristPack,
                        transportService, touristPack);
            }
        }

        return transportServiceTouristPack;
    }

    @Override
    public void createTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        String function = "{call transport_service_turist_pack_insert(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, transportServiceTouristPack.getTouristPack().getIdTouristPack());
            statement.setInt(2, transportServiceTouristPack.getTransportService().getIdTransportService());
            statement.execute();
        }
    }

    @Override
    public void updateTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        String function = "{call transport_service_turist_pack_update(?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, transportServiceTouristPack.getIdTransportServiceTouristPack());
            statement.setInt(2, transportServiceTouristPack.getTouristPack().getIdTouristPack());
            statement.setInt(3, transportServiceTouristPack.getTransportService().getIdTransportService());
            statement.execute();
        }

    }

    @Override
    public void deleteTransportServiceTouristPack(int idTransportServiceTouristPack) throws SQLException {
        String function = "{call transport_service_turist_pack_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idTransportServiceTouristPack);
            statement.execute();
        }

    }

}
