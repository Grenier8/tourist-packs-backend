package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TravelDto;
import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.core.service.ITmTravelService;
import cu.edu.cujae.touristpacks.core.service.ITravelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements ITravelService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ITmTravelService tmTravelService; 
	
    @Override
    public List<TravelDto> getTravels() throws SQLException{
    	List<TravelDto> list = new ArrayList<>();

        String function = "{?= call select_all_travel()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
        	int idTravel = resultSet.getInt(1);
        	TmTravelDto tmTravel = tmTravelService.getTmTravelById(resultSet.getInt(2));
        	String travelName = resultSet.getString(3);
        	
            TravelDto travel = new TravelDto(idTravel, travelName, tmTravel);

            list.add(travel);
        }

        return list;
    }

    @Override
    public TravelDto getTravelById(int travelId) throws SQLException {
        TravelDto travel = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM travel where id_travel = ?");

        pstmt.setInt(1, travelId);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	int idTravel = resultSet.getInt(1);
        	TmTravelDto tmTravel = tmTravelService.getTmTravelById(resultSet.getInt(2));
        	String travelName = resultSet.getString(3);
        	
            travel = new TravelDto(idTravel, travelName, tmTravel);
        }

        return travel;
    }

    @Override
    public TravelDto getTravelByName(String travelName) throws SQLException {
        TravelDto travel = null;
    	
        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM travel where travel_name = ?");

        pstmt.setString(1, travelName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	int idTravel = resultSet.getInt(1);
        	TmTravelDto tmTravel = tmTravelService.getTmTravelById(resultSet.getInt(2));
        	
            travel = new TravelDto(idTravel, travelName, tmTravel);
        }

        return travel;
    }

    @Override
    public void createTravel(TravelDto travel) throws SQLException{
        String function = "{call travel_insert(?,?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        
        statement.setInt(1, travel.getTmodalityTravel().getIdTmTravel());
        statement.setString(2, travel.getTravelName());
        statement.execute();
        }
    }

    @Override
    public void updateTravel(TravelDto travel) throws SQLException{
        String function = "{call travel_update(?,?,?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, travel.getIdTravel());
        statement.setInt(2, travel.getTmodalityTravel().getIdTmTravel());
        statement.setString(3, travel.getTravelName());
        statement.execute();
        }
    }

    @Override
    public void deleteTravel(int idTravel) throws SQLException{
        String function = "{call travel_delete(?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, idTravel);
        statement.execute();
        }
    }

}
