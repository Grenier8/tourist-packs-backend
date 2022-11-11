package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.ServiceTypeDto;
import cu.edu.cujae.touristpacks.core.service.IServiceTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ServiceTypeDto> getServiceTypes() throws SQLException {
        List<ServiceTypeDto> list = new ArrayList<>();

        String function = "{?= call select_all_service_type()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idServiceType = resultSet.getInt(1);
            String serviceTypeName = resultSet.getString(2);

            ServiceTypeDto dto = new ServiceTypeDto(idServiceType, serviceTypeName);
            list.add(dto);
        }

        return list;
    }

    @Override
    public ServiceTypeDto getServiceTypeById(int idServiceType) throws SQLException {
        ServiceTypeDto serviceType = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM service_type where id_service_type = ?");

        pstmt.setInt(1, idServiceType);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String serviceTypeName = resultSet.getString(2);

            serviceType = new ServiceTypeDto(idServiceType, serviceTypeName);
        }

        return serviceType;
    }

    @Override
    public ServiceTypeDto getServiceTypeByName(String serviceTypeName) throws SQLException {
        ServiceTypeDto serviceType = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM service_type where service_type_name = ?");

        pstmt.setString(1, serviceTypeName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idServiceType = resultSet.getInt(2);

            serviceType = new ServiceTypeDto(idServiceType, serviceTypeName);
        }

        return serviceType;
    }

    @Override
    public void createServiceType(ServiceTypeDto serviceType) throws SQLException {
        String function = "{call service_type_insert(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, serviceType.getServiceTypeName());
        statement.execute();

    }

    @Override
    public void updateServiceType(ServiceTypeDto serviceType) throws SQLException {
        String function = "{call service_type_update(?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, serviceType.getIdServiceType());
        statement.setString(2, serviceType.getServiceTypeName());
        statement.execute();

    }

    @Override
    public void deleteServiceType(int idServiceType) throws SQLException {
        String function = "{call service_type_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idServiceType);
        statement.execute();

    }

}
