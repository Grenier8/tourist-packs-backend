package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.core.service.IHotelModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HotelModalityServiceImpl implements IHotelModalityService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HotelModalityDto> getHotelModalities() throws SQLException {
        List<HotelModalityDto> list = new ArrayList<>();

        String function = "{?= call select_all_hotel_modality()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idHotelModality = resultSet.getInt(1);
                String hotelModalityName = resultSet.getString(2);

                HotelModalityDto dto = new HotelModalityDto(idHotelModality, hotelModalityName);
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public HotelModalityDto getHotelModalityById(int idHotelModality) throws SQLException {
        HotelModalityDto hotelModality = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM hotel_modality where id_hotel_modality = ?");

            pstmt.setInt(1, idHotelModality);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String hotelModalityName = resultSet.getString(2);

                hotelModality = new HotelModalityDto(idHotelModality, hotelModalityName);
            }
        }
        return hotelModality;
    }

    @Override
    public HotelModalityDto getHotelModalityByName(String hotelModalityName) throws SQLException {
        HotelModalityDto hotelModality = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM hotel_modality where hotel_modality_name = ?");

            pstmt.setString(1, hotelModalityName);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idHotelModality = resultSet.getInt(1);

                hotelModality = new HotelModalityDto(idHotelModality, hotelModalityName);
            }
        }
        return hotelModality;
    }

    @Override
    public void createHotelModality(HotelModalityDto hotelModality) throws SQLException {
        String function = "{call hotel_modality_insert(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, hotelModality.getHotelModalityName());
            statement.execute();
        }

    }

    @Override
    public void updateHotelModality(HotelModalityDto hotelModality) throws SQLException {
        String function = "{call hotel_modality_update(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, hotelModality.getIdHotelModality());
            statement.setString(2, hotelModality.getHotelModalityName());
            statement.execute();
        }

    }

    @Override
    public void deleteHotelModality(int idHotelModality) throws SQLException {
        String function = "{call hotel_modality_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idHotelModality);
            statement.execute();
        }
    }

}
