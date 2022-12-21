package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmHourKmDto;
import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.service.ITmHourKmService;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TmHourKmServiceImpl implements ITmHourKmService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ITransportModalityService tmodalityService;

    @Override
    public List<TmHourKmDto> getTmHourKms() throws SQLException {
        List<TmHourKmDto> list = new ArrayList<>();

        String function = "{?= call select_all_tm_hour_km()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_tmodality_hour_km = resultSet.getInt(1);
                double cost_per_travelled_km = resultSet.getDouble(2);
                double cost_per_hour = resultSet.getDouble(3);
                double cost_per_extra_km = resultSet.getDouble(4);
                double cost_per_extra_hour = resultSet.getDouble(5);
                int id_tmodality = resultSet.getInt(6);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                TmHourKmDto tmHourKm = new TmHourKmDto(id_tmodality_hour_km, tmodality.getTransportModalityName(),
                        cost_per_travelled_km, cost_per_hour,
                        cost_per_extra_km, cost_per_extra_hour, id_tmodality);

                list.add(tmHourKm);
            }
        }

        return list;
    }

    @Override
    public TmHourKmDto getTmHourKmById(int tmHourKmId) throws SQLException {
        TmHourKmDto tmHourKm = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM tm_hour_km where id_tmodality_hour_km = ?");

            pstmt.setInt(1, tmHourKmId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                double cost_per_travelled_km = resultSet.getDouble(2);
                double cost_per_hour = resultSet.getDouble(3);
                double cost_per_extra_km = resultSet.getDouble(4);
                double cost_per_extra_hour = resultSet.getDouble(5);
                int id_tmodality = resultSet.getInt(6);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                tmHourKm = new TmHourKmDto(tmHourKmId, tmodality.getTransportModalityName(), cost_per_travelled_km,
                        cost_per_hour,
                        cost_per_extra_km, cost_per_extra_hour, id_tmodality);
            }
        }
        return tmHourKm;
    }

    @Override
    public TmHourKmDto getTmHourKmByName(String tmHourKmName) throws SQLException {
        TmHourKmDto tmHourKm = null;
        TransportModalityDto tmodality = tmodalityService.getTransportModalityByName(tmHourKmName);
        int id_tmodality = tmodality.getIdTransportModality();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
        	PreparedStatement pstmt = connection.prepareStatement(
        			"SELECT * FROM tm_hour_km WHERE id_tmodality = ?");

            pstmt.setInt(1, id_tmodality);
            
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id_tmodality_hour_km = resultSet.getInt(1);
                double cost_per_travelled_km = resultSet.getDouble(2);
                double cost_per_hour = resultSet.getDouble(3);
                double cost_per_extra_km = resultSet.getDouble(4);
                double cost_per_extra_hour = resultSet.getDouble(5);

                tmHourKm = new TmHourKmDto(id_tmodality_hour_km, tmHourKmName, cost_per_travelled_km, cost_per_hour,
                        cost_per_extra_km, cost_per_extra_hour, id_tmodality);
            }
        }
        return tmHourKm;
    }

    @Override
    public void createTmHourKm(TmHourKmDto tmHourKm) throws SQLException {
        String function = "{call tm_hour_km_insert(?,?,?,?,?)}";
        
        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setDouble(1, tmHourKm.getCostPerTravelledKm());
            statement.setDouble(2, tmHourKm.getCostPerHour());
            statement.setDouble(3, tmHourKm.getCostPerExtraKm());
            statement.setDouble(4, tmHourKm.getCostPerExtraHour());
            statement.setInt(5, tmodalityService.getTransportModalityByName(tmHourKm.getTransportModalityName()).getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void updateTmHourKm(TmHourKmDto tmHourKm) throws SQLException {
        String function = "{call tm_hour_km_update(?,?,?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, tmHourKm.getIdTmHourKm());
            statement.setDouble(2, tmHourKm.getCostPerTravelledKm());
            statement.setDouble(3, tmHourKm.getCostPerHour());
            statement.setDouble(4, tmHourKm.getCostPerExtraKm());
            statement.setDouble(5, tmHourKm.getCostPerExtraHour());
            statement.setInt(6, tmHourKm.getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void deleteTmHourKm(int idTmHourKm) throws SQLException {
        String function = "{call tm_hour_km_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTmHourKm);
            statement.execute();
        }
    }

}
