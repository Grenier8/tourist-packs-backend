package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.MayorDto;
import cu.edu.cujae.touristpacks.core.service.IMayorService;

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
public class MayorServiceImpl implements IMayorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MayorDto> getMayors() throws SQLException {
        List<MayorDto> list = new ArrayList<>();

        String function = "{?= call select_all_dtbtable()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idMayor = resultSet.getInt(1);
            String minorName = resultSet.getString(2);

            MayorDto dto = new MayorDto(idMayor, minorName);
            list.add(dto);
        }

        return list;
    }

    @Override
    public MayorDto getMayorById(int idMayor) throws SQLException {
        MayorDto minor = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM dtbtable where id_dtbtable = ?");

        pstmt.setInt(1, idMayor);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String minorName = resultSet.getString(2);

            minor = new MayorDto(idMayor, minorName);
        }

        return minor;
    }

    @Override
    public void createMayor(MayorDto minor) throws SQLException {
        String function = "{call dtbtable_insert(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, minor.getMayorName());
        statement.execute();

    }

    @Override
    public void updateMayor(MayorDto minor) throws SQLException {
        String function = "{call dtbtable_update(?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, minor.getIdMayor());
        statement.setString(2, minor.getMayorName());
        statement.execute();

    }

    @Override
    public void deleteMayor(int idMayor) throws SQLException {
        String function = "{call dtbtable_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idMayor);
        statement.execute();

    }

    @Override
    public MayorDto getMayorByName(String minorName) throws SQLException {
        MayorDto minor = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM dtbtable where dtbtable_name = ?");

        pstmt.setString(1, minorName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idMayor = resultSet.getInt(2);

            minor = new MayorDto(idMayor, minorName);
        }

        return minor;
    }
}
