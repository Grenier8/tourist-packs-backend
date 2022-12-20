package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.service.ITmTravelService;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TmTravelServiceImpl implements ITmTravelService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ITransportModalityService tmodalityService;

    @Override
    public List<TmTravelDto> getTmTravels() throws SQLException {
        List<TmTravelDto> list = new ArrayList<>();

        String function = "{?= call select_all_tm_travel()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id_tmodality_travel = resultSet.getInt(1);
                String travel_description = resultSet.getString(2);
                double cost_travel = resultSet.getDouble(3);
                double cost_travel_go_and_back = resultSet.getDouble(4);
                int id_tmodality = resultSet.getInt(5);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                TmTravelDto tmTravel = new TmTravelDto(id_tmodality_travel, tmodality.getTransportModalityName(),
                        travel_description, cost_travel,
                        cost_travel_go_and_back, id_tmodality);

                list.add(tmTravel);
            }
        }
        return list;
    }

    @Override
    public TmTravelDto getTmTravelById(int tmTravelId) throws SQLException {
        TmTravelDto tmTravel = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM tm_travel where id_tmodality_travel = ?");

            pstmt.setInt(1, tmTravelId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String travel_description = resultSet.getString(2);
                double cost_travel = resultSet.getDouble(3);
                double cost_travel_go_and_back = resultSet.getDouble(4);
                int id_tmodality = resultSet.getInt(5);
                TransportModalityDto tmodality = tmodalityService.getTransportModalityById(id_tmodality);

                tmTravel = new TmTravelDto(tmTravelId, tmodality.getTransportModalityName(), travel_description,
                        cost_travel,
                        cost_travel_go_and_back, id_tmodality);
            }
        }
        return tmTravel;
    }

    @Override
    public TmTravelDto getTmTravelByName(String tmTravelName) throws SQLException {
        TmTravelDto tmTravel = null;
        TransportModalityDto tmodality = tmodalityService.getTransportModalityByName(tmTravelName);
        int id_tmodality = tmodality.getIdTransportModality();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
        	PreparedStatement pstmt = connection.prepareStatement(
        			"SELECT * FROM tm_travel WHERE id_tmodality = ?");

            pstmt.setInt(1, id_tmodality);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id_tmodality_travel = resultSet.getInt(1);
                String travel_description = resultSet.getString(2);
                double cost_travel = resultSet.getDouble(3);
                double cost_travel_go_and_back = resultSet.getDouble(4);

                tmTravel = new TmTravelDto(id_tmodality_travel, tmTravelName, travel_description, cost_travel,
                        cost_travel_go_and_back, id_tmodality);
            }
        }
        return tmTravel;
    }

    @Override
    public void createTmTravel(TmTravelDto tmTravel) throws SQLException {
        String function = "{call tm_travel_insert(?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setString(1, tmTravel.getTravelDescription());
            statement.setDouble(2, tmTravel.getCostTravel());
            statement.setDouble(3, tmTravel.getCostTravelGoAndBack());
            statement.setInt(4, tmodalityService.getTransportModalityByName(tmTravel.getTransportModalityName()).getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void updateTmTravel(TmTravelDto tmTravel) throws SQLException {
        String function = "{call tm_travel_update(?,?,?,?,?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, tmTravel.getIdTmTravel());
            statement.setString(2, tmTravel.getTravelDescription());
            statement.setDouble(3, tmTravel.getCostTravel());
            statement.setDouble(4, tmTravel.getCostTravelGoAndBack());
            statement.setInt(5, tmTravel.getIdTransportModality());
            statement.execute();
        }
    }

    @Override
    public void deleteTmTravel(int idTmTravel) throws SQLException {
        String function = "{call tm_travel_delete(?)}";

        try (CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)) {
            statement.setInt(1, idTmTravel);
            statement.execute();
        }
    }

}
