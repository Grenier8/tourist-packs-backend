package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.ProviderDto;
import cu.edu.cujae.touristpacks.core.service.IProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl implements IProviderService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
    @Override
    public List<ProviderDto> getProviders() throws SQLException{
        List<ProviderDto> list = new ArrayList<>();

        String function = "{?= call select_all_provider()}";

        try(Connection connection = jdbcTemplate.getDataSource().getConnection()){
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
        	int id_provider = resultSet.getInt(1);
        	String provider_name = resultSet.getString(2);
        	
        	ProviderDto provider = new ProviderDto(id_provider, provider_name);
            list.add(provider);
        }
        }
        return list;
    }

    @Override
    public ProviderDto getProviderById(int providerId) throws SQLException{
    	ProviderDto provider = null;

        try(PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM provider where id_provider = ?")){

        pstmt.setInt(1, providerId);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	String provider_name = resultSet.getString(2);
        	
        	provider = new ProviderDto(providerId, provider_name);
        }
        }
        return provider;
    }

    @Override
    public ProviderDto getProviderByName(String providerName) throws SQLException{
    	ProviderDto provider= null;

        try(PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM provider where provider_name = ?")){

        pstmt.setString(1, providerName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        	int id_provider = resultSet.getInt(1);
        	
        	provider = new ProviderDto(id_provider, providerName);
        }
        }
        return provider;
    }

    @Override
    public void createProvider(ProviderDto provider) throws SQLException{
    	String function = "{call provider_insert(?)}";

    	try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setString(1, provider.getProviderName());
        statement.execute();
    	}
    }

    @Override
    public void updateProvider(ProviderDto provider) throws SQLException{
    	String function = "{call provider_update(?,?)}";
    	
    	try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, provider.getIdProvider());
        statement.setString(2, provider.getProviderName());
        statement.execute();
    	}
    }

    @Override
    public void deleteProvider(int idprovider) throws SQLException{
        String function = "{call provider_delete(?)}";

        try(CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function)){
        statement.setInt(1, idprovider);
        statement.execute();
        }
    }
}
