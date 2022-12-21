
package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmKmDto;
import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.service.ITmKmService;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TmKmServiceImpl implements ITmKmService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ITransportModalityService tmodalityService;

    @Override
    public List<TmKmDto> getTmKms() throws SQLException {
        List<TmKmDto> list = new ArrayList<>();

        String function = "{?= call select_all_tm_km()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_tmodality_km = resultSet.getInt(1);
                double cost_per_km = resultSet.getDouble(2);
                double cost_per_km_go_and_back = resultSet.getDouble(3);
                double cost_per_waiting_hour = resultSet.getDouble(4);
                int id_tmodality = resultSet.getInt(5);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                TmKmDto tmKm = new TmKmDto(id_tmodality_km, tmodality.getTransportModalityName(), cost_per_km,
                        cost_per_km_go_and_back,
                        cost_per_waiting_hour, id_tmodality);
                list.add(tmKm);
            }
        }
        return list;
    }

    @Override
    public TmKmDto getTmKmById(int tmKmId) throws SQLException {
        TmKmDto tmKm = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM tm_km where id_tmodality_km = ?");

            pstmt.setInt(1, tmKmId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                double cost_per_km = resultSet.getDouble(2);
                double cost_per_km_go_and_back = resultSet.getDouble(3);
                double cost_per_waiting_hour = resultSet.getDouble(4);
                int id_tmodality = resultSet.getInt(5);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                tmKm = new TmKmDto(tmKmId, tmodality.getTransportModalityName(), cost_per_km, cost_per_km_go_and_back,
                        cost_per_waiting_hour, id_tmodality);
            }
        }
        return tmKm;
    }

    @Override
    public TmKmDto getTmKmByName(String tmKmName) throws SQLException {
        TmKmDto tmKm = null;
        
        TransportModalityDto tmodality = tmodalityService.getTransportModalityByName(tmKmName);
        int id_tmodality = tmodality.getIdTransportModality();
        
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
        	PreparedStatement pstmt = connection.prepareStatement(
        			"SELECT * FROM tm_km WHERE id_tmodality = ?");

            pstmt.setInt(1, id_tmodality);
            ResultSet resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                int id_tmodality_km = resultSet.getInt(1);
                double cost_per_km = resultSet.getDouble(2);
                double cost_per_km_go_and_back = resultSet.getDouble(3);
                double cost_per_waiting_hour = resultSet.getDouble(4);

                tmKm = new TmKmDto(id_tmodality_km, tmKmName, cost_per_km, cost_per_km_go_and_back,
                        cost_per_waiting_hour, id_tmodality);
            }
        }

        return tmKm;
    }

    @Override
    public void createTmKm(TmKmDto tmKm) throws SQLException {
        String function = "{call tm_km_insert(?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
        	
            statement.setDouble(1, tmKm.getCostPerKm());
            statement.setDouble(2, tmKm.getCostPerKmGoAndBack());
            statement.setDouble(3, tmKm.getCostPerWaitingHour());
            statement.setInt(4, tmodalityService.getTransportModalityByName(tmKm.getTransportModalityName()).getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void updateTmKm(TmKmDto tmKm) throws SQLException {
        String function = "{call tm_km_update(?,?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, tmKm.getIdTmKm());
            statement.setDouble(2, tmKm.getCostPerKm());
            statement.setDouble(3, tmKm.getCostPerKmGoAndBack());
            statement.setDouble(4, tmKm.getCostPerWaitingHour());
            statement.setInt(5, tmKm.getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void deleteTmKm(int idTmKm) throws SQLException {
        String function = "{call tm_km_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTmKm);
            statement.execute();
        }
    }

}
