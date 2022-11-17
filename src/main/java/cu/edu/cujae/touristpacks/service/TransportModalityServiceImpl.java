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
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportModalityServiceImpl implements ITransportModalityService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
    @Override
    public List<TransportModalityDto> getTransportModalities() throws SQLException{
       	List<TransportModalityDto> list = new ArrayList<>();

        String function = "{?= call select_all_transp_modality()}";

        try(Connection connection = jdbcTemplate.getDataSource().getConnection()){
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
        	int id_tmodality = resultSet.getInt(1);
            String tmodality_name = resultSet.getString(2);
            TransportModalityDto transporModality = new TransportModalityDto(id_tmodality, tmodality_name);

            list.add(transporModality);
        }
        }
        return list;
    }

    @Override
    public TransportModalityDto getTransportModalityById(int transportModalityId) throws SQLException{
    	TransportModalityDto tmodality = null;

        try(PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM transp_modality where id_tmodality = ?")){

        pstmt.setInt(1, transportModalityId);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	
            String tmodalityName = resultSet.getString(2);

            tmodality = new TransportModalityDto(transportModalityId, tmodalityName);
        }
        }
        return tmodality;
    }

    @Override
    public TransportModalityDto getTransportModalityByName(String transportModalityName) throws SQLException{
    	TransportModalityDto tmodality = null;

        try(PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM transp_modality where tmodality_name = ?")){

        pstmt.setString(1, transportModalityName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	
            int tmodalityId = resultSet.getInt(1);

            tmodality = new TransportModalityDto(tmodalityId, transportModalityName);
        }
        }
        return tmodality;
    }

    @Override
    public void createTransportModality(TransportModalityDto transportModality) throws SQLException{
        String function = "{call transp_modality_insert(?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        
        statement.setString(1, transportModality.getTransportModalityName());
        statement.execute();
        }
    }

    @Override
    public void updateTransportModality(TransportModalityDto transportModality) throws SQLException{
        String function = "{call transp_modality_update(?,?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, transportModality.getIdTransportModality());
        statement.setString(2, transportModality.getTransportModalityName());
        statement.execute();
        }
    }

    @Override
    public void deleteTransportModality(int idTransportModality) throws SQLException{
        String function = "{call transp_modality_delete(?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, idTransportModality);
        statement.execute();
        }
    }

}
