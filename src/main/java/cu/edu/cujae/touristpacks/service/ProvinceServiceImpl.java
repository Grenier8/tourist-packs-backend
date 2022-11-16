package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;

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
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProvinceDto> getProvinces() throws SQLException {
        List<ProvinceDto> list = new ArrayList<>();

        String function = "{?= call select_all_province()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idProvince = resultSet.getInt(1);
                String provinceName = resultSet.getString(2);

                ProvinceDto dto = new ProvinceDto(idProvince, provinceName);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public ProvinceDto getProvinceById(int idProvince) throws SQLException {
        ProvinceDto province = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM province where id_province = ?");

            pstmt.setInt(1, idProvince);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String provinceName = resultSet.getString(2);

                province = new ProvinceDto(idProvince, provinceName);
            }
        }

        return province;
    }

    @Override
    public void createProvince(ProvinceDto province) throws SQLException {
        String function = "{call province_insert(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, province.getProvinceName());
            statement.execute();
        }

    }

    @Override
    public void updateProvince(ProvinceDto province) throws SQLException {
        String function = "{call province_update(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, province.getIdProvince());
            statement.setString(2, province.getProvinceName());
            statement.execute();
        }

    }

    @Override
    public void deleteProvince(int idHotelChain) throws SQLException {
        String function = "{call province_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idHotelChain);
            statement.execute();
        }

    }

    @Override
    public ProvinceDto getProvinceByName(String provinceName) throws SQLException {
        ProvinceDto province = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM province where province_name = ?");

            pstmt.setString(1, provinceName);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idProvince = resultSet.getInt(2);

                province = new ProvinceDto(idProvince, provinceName);
            }
        }
        return province;
    }
}
